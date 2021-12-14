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

import com.carjotu.controler.ControladorEditorial;
import com.carjotu.dao.DAOEditorial;

public class VentanaSwingEditorial {
	private JTextArea jtareaResultado;
	private JButton btnListar,btnTerminar,btnLimpiar;
	
	private ControladorEditorial controlador;
	private Vector<DAOEditorial> resultado;
	JFrame marco;
	
	private JLabel lblListadoDeEditoriales;
	private JTable tableEditoriales;
	private JButton btnBotonEditar;
	private JButton btnBotonEliminar;
	private JButton btnBotonAniadir;
	private JLabel lblCodigoDeEditorial;
	private JTextField textFieldCodigoEditorial;
	private JLabel lblNombre;
	private JTextField textFieldNombreEditorial;
	private DefaultTableModel dataModel;
	private JScrollPane scrollPane;
	private JPanel panel;

	public VentanaSwingEditorial(ControladorEditorial controlador, JFrame marco) {
		this.marco=marco;
		this.controlador=controlador;
		
		panel = new JPanel();
		panel.setLayout(null);
		panel.setSize(850, 525);
		
		crearPantalla();
		
		panel.setVisible(true);
		marco.add(panel);
		marco.repaint();
	}
	
	
	public void crearPantalla() {
		lblListadoDeEditoriales = new JLabel("Listado de Editoriales");
		lblListadoDeEditoriales.setBounds(15, 16, 164, 20);
		panel.add(lblListadoDeEditoriales);
		
		Vector<String> header= new Vector<String>();
		header.add("Código");
		header.add("Nombre");
		dataModel= new DefaultTableModel(0, 0);
		 dataModel.setColumnIdentifiers(header);
		listarEditoriales();
		tableEditoriales = new JTable(dataModel);
		tableEditoriales.setEnabled(false);
		tableEditoriales.addMouseListener(new MouseAdapter() 
		   {
		      public void mouseClicked(MouseEvent e) 
		      {
		         int fila = tableEditoriales.rowAtPoint(e.getPoint());
		         int columna = 0;
		         if ((fila > -1) && (columna > -1))
		        	 
		        	 textFieldCodigoEditorial.setText((String) dataModel.getValueAt(fila, columna));
		         	textFieldNombreEditorial.setText((String) dataModel.getValueAt(fila, columna+1));
		            System.out.println(dataModel.getValueAt(fila, columna)+": "+dataModel.getValueAt(fila,columna+1));
		      }
		   });
		scrollPane= new JScrollPane(tableEditoriales);
		scrollPane.setBounds(25, 52, 600, 275);
		
		panel.add(scrollPane);
		
	
		
		btnBotonEditar = new JButton("Editar Editorial");
		btnBotonEditar.setBounds(665, 98, 145, 29);
		btnBotonEditar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String codEditorial=textFieldCodigoEditorial.getText();
				String nombreEditorial=textFieldNombreEditorial.getText();
				if (codEditorial.equals("")||nombreEditorial.equals("")) {
					JOptionPane.showMessageDialog(null, "No puede quedar vacio ni el CODIGO DE EDITORIAL ni el NOMBRE ","Error",JOptionPane.INFORMATION_MESSAGE);
				}else {
					try {
						controlador.modificarEditorial(codEditorial, nombreEditorial);
						JOptionPane.showMessageDialog(null, "Editorial modificada con exito");
						listarEditoriales();
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "Error al modificar la editorial");
					}
				}
				
				
			}
		});
		panel.add(btnBotonEditar);
		
		btnBotonEliminar = new JButton("Eliminar Editorial");
		btnBotonEliminar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String codEditorial=textFieldCodigoEditorial.getText();
				
				if (codEditorial.equals("")) {
					JOptionPane.showMessageDialog(null, "No puede estar vacio el CODIGO DE EDITORIAL a eliminar ");
				}else {
					try {
						controlador.borrarEditorial(codEditorial);
						JOptionPane.showMessageDialog(null, "Editorial eliminada con exito");
						listarEditoriales();
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "Error al eliminar la editorial");
					}
				}
				
				
			}
		});
		btnBotonEliminar.setBounds(665, 184, 145, 29);
		panel.add(btnBotonEliminar);
		
		btnBotonAniadir = new JButton("A\u00F1adir Editorial");
		btnBotonAniadir.setBounds(665, 269, 145, 29);
		btnBotonAniadir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String codEditorial=textFieldCodigoEditorial.getText();
				String nombreEditorial=textFieldNombreEditorial.getText();
				if (codEditorial.equals("")||nombreEditorial.equals("")) {
					JOptionPane.showMessageDialog(null, "No puede quedar vacio ni el CODIGO DE LA EDITORIAL ni el NOMBRE ");
				}else {
					try {
						controlador.insertarEditorial(codEditorial, nombreEditorial);
						JOptionPane.showMessageDialog(null, "Editorial añadida con exito");
						listarEditoriales();
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "Error al añadir la editorial");
					}
				}
				
				
			}
		});
		panel.add(btnBotonAniadir);
		
		lblCodigoDeEditorial = new JLabel("Codigo de Editorial:");
		lblCodigoDeEditorial.setBounds(15, 349, 145, 20);
		panel.add(lblCodigoDeEditorial);
		
		textFieldCodigoEditorial = new JTextField();
		textFieldCodigoEditorial.setBounds(15, 385, 146, 26);
		panel.add(textFieldCodigoEditorial);
		textFieldCodigoEditorial.setColumns(10);
		
		lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(210, 349, 69, 20);
		panel.add(lblNombre);
		
		textFieldNombreEditorial = new JTextField();
		textFieldNombreEditorial.setColumns(10);
		textFieldNombreEditorial.setBounds(210, 385, 146, 26);
		panel.add(textFieldNombreEditorial);
						
	}

	
	private void listarEditoriales() {
		dataModel.setRowCount(0);
		
		Vector<DAOEditorial> editoriales;
		try {
			editoriales = controlador.obtenerEditoriales();

			Iterator<DAOEditorial> itEditoriales = editoriales.iterator();
			while(itEditoriales.hasNext()) {
				DAOEditorial editorial= itEditoriales.next();
				Vector<String> vectordeEditoriales = new Vector<String>();
				vectordeEditoriales.add(editorial.getCod_editorial());
				vectordeEditoriales.add(editorial.getNombre_editorial());
				
				dataModel.addRow(vectordeEditoriales);
			
				
			}
		} catch (Exception e) {
			System.err.println("Vista: FALLO A OBTENER  EDITORIALES");
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
