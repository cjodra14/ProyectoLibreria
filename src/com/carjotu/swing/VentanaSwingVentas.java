package com.carjotu.swing;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.carjotu.controler.ControladorAutor;
import com.carjotu.controler.ControladorLibro;
import com.carjotu.controler.ControladorVenta;
import com.carjotu.dao.DAOAutor;
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
	private JButton btnBotonEditar;
	private JButton btnBotonEliminar;
	private JButton btnBotonAniadir;
	private JLabel lblCodigoDeAutor;
	private JTextField textFieldCodigoAutor;
	private JLabel lblNombre;
	private JTextField textFieldNombreAutor;
	private JLabel lblPrimerApellido;
	private JTextField textFieldPApelAutor;
	private JLabel lblSegundoApellido;
	private JTextField textFieldSApelAutor;
	private DefaultTableModel dataModel;
	private JScrollPane scrollPane;
	private JPanel panel;
	private int npedido=0;
	private String fecha="";
	private String usuario="";

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
		tableVentas.addMouseListener(new MouseAdapter() 
		   {
		      public void mouseClicked(MouseEvent e) 
		      {
		         int fila = tableVentas.rowAtPoint(e.getPoint());
		         int columna = 0;
		         if ((fila > -1) && (columna > -1))
		        	 
		         	npedido= Integer.valueOf((String) dataModel.getValueAt(fila, columna));
		         	fecha=(String) dataModel.getValueAt(fila, columna+1);
		         	usuario=(String) dataModel.getValueAt(fila, columna+2);
		         
		            System.out.println("Pedido nº: "+npedido+", realizado el "+fecha+" por "+usuario);
		           
						
						try {
							detalles=controlador.obtenerVentaLibro(String.valueOf(npedido));
							double total=0;
							Iterator<DAOVenta_libro> itVentasLibros = detalles.iterator();
							while(itVentasLibros.hasNext()) {
								DAOVenta_libro ventaLibro= itVentasLibros.next();
								DAOLibro libro= controladorLibro.obtenerLibro(ventaLibro.getIsbn());
								System.out.println("Ha pedido "+ventaLibro.getCantidad()+" unidades del libro con el ISBN "+ventaLibro.getIsbn()+" -->"+libro.getTitulo()+"  al precio de: "+libro.getPrecio());
								total=total+(ventaLibro.getCantidad()*libro.getPrecio());	
							}
							System.out.println("El total del pedido es: "+total);
						} catch (Exception exc) {
							System.err.println("Vista: FALLO A OBTENER  VENTAS");
							exc.printStackTrace();
						}
						
		            
		            
		      
		      }
		   });
		scrollPane= new JScrollPane(tableVentas);
		scrollPane.setBounds(25, 52, 600, 275);
		
		panel.add(scrollPane);
		
	
		
		btnBotonEditar = new JButton("Editar Autor");
		btnBotonEditar.setBounds(665, 98, 145, 29);
		btnBotonEditar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String codAutor=textFieldCodigoAutor.getText();
				String nombreAutor=textFieldNombreAutor.getText();
				String pApelAutor=textFieldPApelAutor.getText();
				String sApelAutor=textFieldSApelAutor.getText();
				if (codAutor.equals("")||nombreAutor.equals("")) {
					JOptionPane.showMessageDialog(null, "No puede quedar vacio ni el CODIGO DE AUTOR ni el NOMBRE ","Error",JOptionPane.INFORMATION_MESSAGE);
				}else {
					try {
						int filasModificadas=-1;
						//filasModificadas=controlador.modificarAutor(codAutor, nombreAutor, pApelAutor, sApelAutor);
						if (filasModificadas>0) {
							JOptionPane.showMessageDialog(null, "Autor modificado con exito");
						}else {
							JOptionPane.showMessageDialog(null, "No se ha modificado ningún autor");
						}
						listarVentas();
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "Error al modificar el autor");
					}
				}
				
				
			}
		});
		panel.add(btnBotonEditar);
		
		btnBotonEliminar = new JButton("Eliminar Autor");
		btnBotonEliminar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String codAutor=textFieldCodigoAutor.getText();
				
				if (codAutor.equals("")) {
					JOptionPane.showMessageDialog(null, "No puede estar vacio el CODIGO DE AUTOR a eliminar ");
				}else {
					try {
						//controlador.borrarAutor(codAutor);
						JOptionPane.showMessageDialog(null, "Autor eliminado con exito");
						listarVentas();
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "Error al eliminar el autor");
					}
				}
				
				
			}
		});
		btnBotonEliminar.setBounds(665, 184, 145, 29);
		panel.add(btnBotonEliminar);
		
		btnBotonAniadir = new JButton("A\u00F1adir Autor");
		btnBotonAniadir.setBounds(665, 269, 145, 29);
		btnBotonAniadir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String codAutor=textFieldCodigoAutor.getText();
				String nombreAutor=textFieldNombreAutor.getText();
				String pApelAutor=textFieldPApelAutor.getText();
				String sApelAutor=textFieldSApelAutor.getText();
				if (codAutor.equals("")||nombreAutor.equals("")) {
					JOptionPane.showMessageDialog(null, "No puede quedar vacio ni el CODIGO DE AUTOR ni el NOMBRE ");
				}else {
					try {
						//controlador.insertarAutor(codAutor, nombreAutor, pApelAutor, sApelAutor);
						JOptionPane.showMessageDialog(null, "Autor añadido con exito");
						listarVentas();
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "Error al añadir el autor");
					}
				}
				
				
			}
		});
		panel.add(btnBotonAniadir);
		
		lblCodigoDeAutor = new JLabel("Codigo de Autor:");
		lblCodigoDeAutor.setBounds(15, 349, 145, 20);
		panel.add(lblCodigoDeAutor);
		
		textFieldCodigoAutor = new JTextField();
		textFieldCodigoAutor.setBounds(15, 385, 146, 26);
		panel.add(textFieldCodigoAutor);
		textFieldCodigoAutor.setColumns(10);
		
		lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(210, 349, 69, 20);
		panel.add(lblNombre);
		
		textFieldNombreAutor = new JTextField();
		textFieldNombreAutor.setColumns(10);
		textFieldNombreAutor.setBounds(210, 385, 146, 26);
		panel.add(textFieldNombreAutor);
		
		lblPrimerApellido = new JLabel("Primer Apellido:");
		lblPrimerApellido.setBounds(393, 349, 126, 20);
		panel.add(lblPrimerApellido);
		
		textFieldPApelAutor = new JTextField();
		textFieldPApelAutor.setColumns(10);
		textFieldPApelAutor.setBounds(393, 385, 146, 26);
		panel.add(textFieldPApelAutor);
		
		lblSegundoApellido = new JLabel("Segundo Apellido:");
		lblSegundoApellido.setBounds(584, 349, 131, 20);
		panel.add(lblSegundoApellido);
		
		textFieldSApelAutor = new JTextField();
		textFieldSApelAutor.setColumns(10);
		textFieldSApelAutor.setBounds(584, 385, 146, 26);
		panel.add(textFieldSApelAutor);
		
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
