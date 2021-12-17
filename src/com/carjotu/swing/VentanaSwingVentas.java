package com.carjotu.swing;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.Vector;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.XMLOutputter;

import com.carjotu.controler.ControladorAutor;
import com.carjotu.controler.ControladorLibro;
import com.carjotu.controler.ControladorVenta;
import com.carjotu.dao.DAOLibro;
import com.carjotu.dao.DAOVenta;
import com.carjotu.dao.DAOVenta_libro;

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
	private String fecha="";
	private String usuario="";
	private int filaSelecAux=-1;
	DecimalFormat df = new DecimalFormat("#.00");

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
				String nombreFichero=""+npedido+".txt";
				File textFile = new File(userHomeFolder, nombreFichero);
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
						out.write("El total del pedido es: "+df.format(total));
						
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
				String nombreFichero=""+npedido+".xml";
				File xmlFile = new File(userHomeFolder, nombreFichero);
				Element raiz = new Element("FACTURA");
				Document doc=new Document(raiz);
			
				Element numeroPedido=new Element("NPEDIDO");
				numeroPedido.addContent(String.valueOf(npedido));
				
				Element detalle = new Element("DETALLES");
				
				try {
					detalles=controlador.obtenerVentaLibro(String.valueOf(npedido));
					double total=0;
					Iterator<DAOVenta_libro> itVentasLibros = detalles.iterator();
					while(itVentasLibros.hasNext()) {
						Element fila = new Element("FILA");
						DAOVenta_libro ventaLibro= itVentasLibros.next();
						DAOLibro libro= controladorLibro.obtenerLibro(ventaLibro.getIsbn());
						fila.addContent(new Element("UDS").addContent(String.valueOf(ventaLibro.getCantidad())));
						fila.addContent(new Element("TITULO").addContent(libro.getTitulo()));
						
						//out.write("Ha pedido "+ventaLibro.getCantidad()+" unidades del libro con el ISBN "+ventaLibro.getIsbn()+" -->"+libro.getTitulo()+"  al precio de: "+df.format(libro.getPrecio())+"€ - Subtotal:"+df.format(libro.getPrecio()*ventaLibro.getCantidad())+"€");
						
						total=total+(ventaLibro.getCantidad()*libro.getPrecio());	
						
						detalle.addContent(fila);
					}
					Element precioTotal = new Element("TOTAL");
					precioTotal.addContent(String.valueOf(total));
					
					raiz.addContent(numeroPedido);
//					raiz.addContent(elementofecha);
//					raiz.addContent(elementoUsuario);
					raiz.addContent(detalle);
					raiz.addContent(precioTotal);
					
					
				XMLOutputter salida=new XMLOutputter();
				FileWriter fw;
				
					fw = new FileWriter(xmlFile);
				
				salida.output(doc, fw);
				JOptionPane.showMessageDialog(null, "XML creado con exito");
				}catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Erroral crear el archivo XML");
				}
				
				
				
			}
		}});
		btnBotonGenXML.setBounds(665, 184, 145, 29);
		panel.add(btnBotonGenXML);
		
				
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
	
}
