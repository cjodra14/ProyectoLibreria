package com.carjotu.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;



import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;



import org.jdom2.output.XMLOutputter;

import com.carjotu.controler.ControladorLibro;
import com.carjotu.controler.ControladorVenta;
import com.carjotu.dao.DAOLibro;
import com.carjotu.dao.DAOVenta;
import com.carjotu.dao.DAOVenta_libro;
import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class VentanaSwingVentas {
	
	private ControladorVenta controlador;
	private ControladorLibro controladorLibro;
	private Vector<DAOVenta> resultado;
	private Vector<DAOVenta_libro> detalles;
	JFrame marco;
	
	private JLabel lblListadoDeVenta;
	private JTable tableVentas;
	private JButton btnBotonGenTXT;
	private JButton btnBotonGenXML;
	private JTextField textFieldCodigoAutor;
	private JTextField textFieldNombreAutor;
	private JTextField textFieldPApelAutor;
	private JTextField textFieldSApelAutor;
	private DefaultTableModel dataModel;
	private JScrollPane scrollPane;
	private JPanel panel;
	private int npedido=0;
	double total=0;
	private String fecha="";
	private String usuario="";
	private int filaSelecAux=-1;
	DecimalFormat df = new DecimalFormat("#.00");
	private JButton btnBotonGenPDF;

	public VentanaSwingVentas(ControladorVenta controlador, JFrame marco, ControladorLibro controladorLibro) {
		this.marco=marco;
		this.controlador=controlador;
		this.controladorLibro=controladorLibro;
		
		panel = new JPanel();
		panel.setLayout(null);
		panel.setSize(850, 525);
		
		crearPantalla();
		
		panel.setVisible(true);
		marco.add(panel);
		marco.repaint();
	}
	
	
	public void crearPantalla() {
		lblListadoDeVenta = new JLabel("Listado de Ventas:");
		lblListadoDeVenta.setBounds(15, 16, 164, 20);
		panel.add(lblListadoDeVenta);
		
		Vector<String> header= new Vector<String>();
		header.add("Nº de pedido");
		header.add("Fecha");
		header.add("Usuario que ha hecho el pedido:");
		
		dataModel= new DefaultTableModel(0, 0);
		 dataModel.setColumnIdentifiers(header);
		listarVentas();
		tableVentas = new JTable(dataModel){
			@Override
			public boolean isCellEditable(int row, int column) {
		        return false;
		    }
		};
		tableVentas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableVentas.addMouseListener(new MouseAdapter() 
		   {
		      public void mouseClicked(MouseEvent e) 
		      {
		         int fila = tableVentas.rowAtPoint(e.getPoint());
		         int columna = 0;
		         if ((fila > -1) && (columna > -1)) {
		        	 filaSelecAux=fila;
		         	npedido= Integer.valueOf((String) dataModel.getValueAt(fila, columna));
		         	fecha=(String) dataModel.getValueAt(fila, columna+1);
		         	usuario=(String) dataModel.getValueAt(fila, columna+2); 
		           
		      
		      }
		      
		      }});
		scrollPane= new JScrollPane(tableVentas);
		scrollPane.setBounds(25, 52, 600, 275);
		
		panel.add(scrollPane);
		
	
		
		btnBotonGenTXT = new JButton("Generar TXT");
		btnBotonGenTXT.setBounds(665, 98, 145, 29);
		btnBotonGenTXT.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (npedido>0) {
					String userHomeFolder = System.getProperty("user.home");
					String nombreFichero="factura"+npedido+".txt";
					File textFile = new File(userHomeFolder+"\\Desktop", nombreFichero);
					try {
						BufferedWriter out = new BufferedWriter(new FileWriter(textFile));
						
						out.write("Pedido nº: "+npedido+", realizado el "+fecha+" por "+usuario+" ");
						out.newLine();
				           		
						try {
							detalles=controlador.obtenerVentaLibro(String.valueOf(npedido));
							double total=0;
							Iterator<DAOVenta_libro> itVentasLibros = detalles.iterator();
							while(itVentasLibros.hasNext()) {
								DAOVenta_libro ventaLibro= itVentasLibros.next();
								DAOLibro libro= controladorLibro.obtenerLibro(ventaLibro.getIsbn());
								out.write("Ha pedido "+ventaLibro.getCantidad()+" unidades del libro con el ISBN "+ventaLibro.getIsbn()+" -->"+libro.getTitulo()+"  al precio de: "+df.format(libro.getPrecio())+"€ - Subtotal:"+df.format(libro.getPrecio()*ventaLibro.getCantidad())+"€");
								out.newLine();
								total=total+(ventaLibro.getCantidad()*libro.getPrecio());	
							}
							out.write("El total del pedido es: "+df.format(total)+"€");
							
							out.close();
							JOptionPane.showMessageDialog(null, "Fichero TXT creado con exito");
						} catch (Exception exc) {
							System.err.println("Vista: FALLO AL CREAR EL ARCHIVO");
							exc.printStackTrace();
						}
						
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
			}
				
		});
		panel.add(btnBotonGenTXT);
		
		btnBotonGenXML = new JButton("Generar XML");
		btnBotonGenXML.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (npedido>0) {
				String userHomeFolder = System.getProperty("user.home");
				String nombreFichero="factura"+npedido+".xml";
				File xmlFile = new File(userHomeFolder+"\\Desktop", nombreFichero);
				org.jdom2.Element raiz = new org.jdom2.Element("FACTURA");
				org.jdom2.Document doc=new org.jdom2.Document(raiz);
			
				org.jdom2.Element numeroPedido=new org.jdom2.Element("NPEDIDO");
				numeroPedido.addContent(String.valueOf(npedido));
				
				org.jdom2.Element elementofecha = new org.jdom2.Element("FECHA");
				elementofecha.addContent(String.valueOf(fecha));
				
				org.jdom2.Element elementoUsuario = new org.jdom2.Element("USUARIO");
				elementoUsuario.addContent(String.valueOf(usuario));
				org.jdom2.Element detalle = new org.jdom2.Element("DETALLES");
				
				try {
					detalles=controlador.obtenerVentaLibro(String.valueOf(npedido));
					double total=0;
					Iterator<DAOVenta_libro> itVentasLibros = detalles.iterator();
					while(itVentasLibros.hasNext()) {
						org.jdom2.Element fila = new org.jdom2.Element("FILA");
						DAOVenta_libro ventaLibro= itVentasLibros.next();
						DAOLibro libro= controladorLibro.obtenerLibro(ventaLibro.getIsbn());
						fila.addContent(new org.jdom2.Element("UDS").addContent(String.valueOf(ventaLibro.getCantidad())));
						fila.addContent(new org.jdom2.Element("ISBN").addContent(String.valueOf(ventaLibro.getIsbn())));
						fila.addContent(new org.jdom2.Element("TITULO").addContent(libro.getTitulo()));
						fila.addContent(new org.jdom2.Element("SUBTOTAL").addContent(String.valueOf(ventaLibro.getCantidad()*libro.getPrecio())));
						
						//out.write("Ha pedido "+ventaLibro.getCantidad()+" unidades del libro con el ISBN "+ventaLibro.getIsbn()+" -->"+libro.getTitulo()+"  al precio de: "+df.format(libro.getPrecio())+"€ - Subtotal:"+df.format(libro.getPrecio()*ventaLibro.getCantidad())+"€");
						
						total=total+(ventaLibro.getCantidad()*libro.getPrecio());	
						
						detalle.addContent(fila);
					}
					org.jdom2.Element precioTotal = new org.jdom2.Element("TOTAL");
					precioTotal.addContent(String.valueOf(total));
					
					raiz.addContent(numeroPedido);
					raiz.addContent(elementofecha);
					raiz.addContent(elementoUsuario);
					raiz.addContent(detalle);
					raiz.addContent(precioTotal);
					
					
				XMLOutputter salida=new XMLOutputter();
				FileWriter fw;
				
					fw = new FileWriter(xmlFile);
				
				salida.output(doc, fw);
				
				JOptionPane.showMessageDialog(null, "XML creado con exito");
				}catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Error al crear el archivo XML");
				}
				
				
				
			}
		}});
		btnBotonGenXML.setBounds(665, 184, 145, 29);
		panel.add(btnBotonGenXML);
		
		btnBotonGenPDF = new JButton("Generar PDF");
		btnBotonGenPDF.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
			            Font.BOLD);
			    Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
			            Font.NORMAL, BaseColor.RED);
			    Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
			            Font.BOLD);
			    Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
			            Font.BOLD);
				 try {
				if (npedido>0) {
				com.itextpdf.text.Document document = new com.itextpdf.text.Document();
				String userHomeFolder = System.getProperty("user.home");
				String nombreFichero="factura"+npedido+".pdf";
				
					PdfWriter.getInstance(document, new FileOutputStream(userHomeFolder+"\\Desktop\\"+nombreFichero));
						document.open();
						document.addTitle("Factura nº:"+npedido);
				        document.addSubject("Carjotu");
				        document.addKeywords("Java, PDF, Report");
				        document.addAuthor("CARJOTU");
				        document.addCreator("CARJOTU");
				        
				        Paragraph preface = new Paragraph();
				        // We add one empty line
				        addEmptyLine(preface, 1);
				        // Lets write a big header
				        preface.add(new Paragraph("Factura nº:"+npedido, catFont));

				        addEmptyLine(preface, 1);
				        // Will create: Report generated by: _name, _date
				        preface.add(new Paragraph("Pedido realizado el "+fecha+" por el usuario "+usuario,smallBold));
				        addEmptyLine(preface, 3);
				        
				        preface.add( new Paragraph("Productos Comprados:", subFont));
				        addEmptyLine(preface, 1);
				        // Start a new page
				       // document.newPage();
				        createTable(preface);
				        addEmptyLine(preface, 3);
				        preface.add(new Paragraph("Total : "+total+"€"));
				        document.add(preface);
				        
				        
				        document.close();

				JOptionPane.showMessageDialog(null, "PDF creado con exito");				
				
			}}catch (Exception e1) {
				JOptionPane.showMessageDialog(null, "Error al crear el archivo PDF");
			}
		}});
		btnBotonGenPDF.setBounds(665, 270, 145, 29);
		panel.add(btnBotonGenPDF);
		
				
	}

	
	private void listarVentas() {
		dataModel.setRowCount(0);
		
		Vector<DAOVenta> ventas;
		try {
			ventas = controlador.obtenerVentas();

			Iterator<DAOVenta> itVentas = ventas.iterator();
			while(itVentas.hasNext()) {
				DAOVenta venta= itVentas.next();
				Vector<String> vectordeVentas = new Vector<String>();
				vectordeVentas.add(String.valueOf(venta.getNpedido()));
				vectordeVentas.add(venta.getFecha());
				vectordeVentas.add(venta.getUsuario());
				
				dataModel.addRow(vectordeVentas);
			
				
			}
		} catch (Exception e) {
			System.err.println("Vista: FALLO A OBTENER  VENTAS");
			e.printStackTrace();
		}
		
		
	}
	
	
	public void repintar() {
		panel.setVisible(true);
		marco.add(panel);
		marco.repaint();
	}
	
	private void terminarAplicacion() {			
			controlador.terminar();
			//marco.dispatchEvent(new WindowEvent(marco, WindowEvent.WINDOW_CLOSING));
			System.out.println("Aplicación terminada");
			System.exit(0);
		}
	 private  void createTable(Paragraph para) throws Exception{
	        PdfPTable table = new PdfPTable(5);

	        // t.setBorderColor(BaseColor.GRAY);
	        // t.setPadding(4);
	        // t.setSpacing(4);
	        // t.setBorderWidth(1);
	        String [] headerPDF = {"UD's","ISBN","Título","Precio","Subtotal"};
	        for (int i = 0; i < headerPDF.length; i++) {
	        	 PdfPCell c1 = new PdfPCell(new Phrase(headerPDF[i]));
			        c1.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
			        table.addCell(c1);
			
			}
	    	  
	       

	       
	        table.setHeaderRows(1);
	        detalles=controlador.obtenerVentaLibro(String.valueOf(npedido));
			
			Iterator<DAOVenta_libro> itVentasLibros = detalles.iterator();
			while(itVentasLibros.hasNext()) {
				DAOVenta_libro ventaLibro= itVentasLibros.next();
				DAOLibro libro= controladorLibro.obtenerLibro(ventaLibro.getIsbn());
				table.addCell(String.valueOf(ventaLibro.getCantidad()));
				table.addCell(String.valueOf(ventaLibro.getIsbn()));
				table.addCell(libro.getTitulo());
				table.addCell(String.valueOf(libro.getPrecio()));
				table.addCell(String.valueOf(ventaLibro.getCantidad()*libro.getPrecio()));
				
				//out.write("Ha pedido "+ventaLibro.getCantidad()+" unidades del libro con el ISBN "+ventaLibro.getIsbn()+" -->"+libro.getTitulo()+"  al precio de: "+df.format(libro.getPrecio())+"€ - Subtotal:"+df.format(libro.getPrecio()*ventaLibro.getCantidad())+"€");
				
				total=total+(ventaLibro.getCantidad()*libro.getPrecio());	
				
			
			}
			
	        para.add(table);

	    }

	    private  void createList(Section subCatPart) {
	        List list = new List(true, false, 10);
	        list.add(new ListItem("First point"));
	        list.add(new ListItem("Second point"));
	        list.add(new ListItem("Third point"));
	        subCatPart.add(list);
	    }

	    private void addEmptyLine(Paragraph paragraph, int number) {
	        for (int i = 0; i < number; i++) {
	            paragraph.add(new Paragraph(" "));
	        }
	    }
       
    
	
	
}
