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

import com.carjotu.controler.ControladorCategoria;
import com.carjotu.dao.DAOCategoria;


public class VentanaSwingCategoria {

	private JTextArea jtareaResultado;
	private JButton btnListar,btnTerminar,btnLimpiar;
	
	private ControladorCategoria controlador;
	private Vector<DAOCategoria> resultado;
	JFrame marco;
	
	private JLabel lblListadoDeCategorias;
	private JTable tableCategorias;
	private JButton btnBotonEditar;
	private JButton btnBotonEliminar;
	private JButton btnBotonAniadir;
	private JLabel lblCodigoDeCategoria;
	private JTextField textFieldCodigoCategoria;
	private JLabel lblNombre;
	private JTextField textFieldNombreCategoria;
	private DefaultTableModel dataModel;
	private JScrollPane scrollPane;
	private JPanel panel;

	public VentanaSwingCategoria(ControladorCategoria controlador, JFrame marco) {
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
		lblListadoDeCategorias = new JLabel("Listado de Categoria");
		lblListadoDeCategorias.setBounds(15, 16, 164, 20);
		panel.add(lblListadoDeCategorias);
		
		Vector<String> header= new Vector<String>();
		header.add("Código");
		header.add("Nombre");
		dataModel= new DefaultTableModel(0, 0);
		 dataModel.setColumnIdentifiers(header);
		listarCategorias();
		tableCategorias = new JTable(dataModel);
		tableCategorias.setEnabled(false);
		tableCategorias.addMouseListener(new MouseAdapter() 
		   {
		      public void mouseClicked(MouseEvent e) 
		      {
		         int fila = tableCategorias.rowAtPoint(e.getPoint());
		         int columna = 0;
		         if ((fila > -1) && (columna > -1))
		        	 
		        	 textFieldCodigoCategoria.setText((String) dataModel.getValueAt(fila, columna));
		         	textFieldNombreCategoria.setText((String) dataModel.getValueAt(fila, columna+1));
		            System.out.println(dataModel.getValueAt(fila, columna)+": "+dataModel.getValueAt(fila,columna+1));
		      }
		   });
		scrollPane= new JScrollPane(tableCategorias);
		scrollPane.setBounds(25, 52, 600, 275);
		
		panel.add(scrollPane);
		
	
		
		btnBotonEditar = new JButton("Editar Categoría");
		btnBotonEditar.setBounds(665, 98, 145, 29);
		btnBotonEditar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String idCategoria=textFieldCodigoCategoria.getText();
				String nombreCategoria=textFieldNombreCategoria.getText();
				if (idCategoria.equals("")||nombreCategoria.equals("")) {
					JOptionPane.showMessageDialog(null, "No puede quedar vacio ni el CODIGO DE CATEGORIA ni el NOMBRE ","Error",JOptionPane.INFORMATION_MESSAGE);
				}else {
					try {
						controlador.modificarCategoria(idCategoria, nombreCategoria);
						JOptionPane.showMessageDialog(null, "Categoria modificada con exito");
						listarCategorias();
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "Error al modificar la categoria");
					}
				}
				
				
			}
		});
		panel.add(btnBotonEditar);
		
		btnBotonEliminar = new JButton("Eliminar Categoría");
		btnBotonEliminar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String idCategoria=textFieldCodigoCategoria.getText();
				
				if (idCategoria.equals("")) {
					JOptionPane.showMessageDialog(null, "No puede estar vacio el CODIGO DE CATEGORIA a eliminar ");
				}else {
					try {
						controlador.borrarCategoria(idCategoria);
						JOptionPane.showMessageDialog(null, "Categoria eliminada con exito");
						listarCategorias();
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "Error al eliminar la categoria");
					}
				}
				
				
			}
		});
		btnBotonEliminar.setBounds(665, 184, 145, 29);
		panel.add(btnBotonEliminar);
		
		btnBotonAniadir = new JButton("A\u00F1adir Categoría");
		btnBotonAniadir.setBounds(665, 269, 145, 29);
		btnBotonAniadir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String idCategoria=textFieldCodigoCategoria.getText();
				String nombreCategoria=textFieldNombreCategoria.getText();
				if (idCategoria.equals("")||nombreCategoria.equals("")) {
					JOptionPane.showMessageDialog(null, "No puede quedar vacio ni el CODIGO DE CATEGORIA ni el NOMBRE ");
				}else {
					try {
						controlador.insertarCategoria(idCategoria, nombreCategoria);
						JOptionPane.showMessageDialog(null, "Categoria añadida con exito");
						listarCategorias();
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "Error al añadir la categoria");
					}
				}
				
				
			}
		});
		panel.add(btnBotonAniadir);
		
		lblCodigoDeCategoria = new JLabel("Codigo de Categoria:");
		lblCodigoDeCategoria.setBounds(15, 349, 145, 20);
		panel.add(lblCodigoDeCategoria);
		
		textFieldCodigoCategoria = new JTextField();
		textFieldCodigoCategoria.setBounds(15, 385, 146, 26);
		panel.add(textFieldCodigoCategoria);
		textFieldCodigoCategoria.setColumns(10);
		
		lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(210, 349, 69, 20);
		panel.add(lblNombre);
		
		textFieldNombreCategoria = new JTextField();
		textFieldNombreCategoria.setColumns(10);
		textFieldNombreCategoria.setBounds(210, 385, 146, 26);
		panel.add(textFieldNombreCategoria);
		
	}

	
	private void listarCategorias() {
		dataModel.setRowCount(0);
		
		Vector<DAOCategoria> categorias;
		try {
			categorias = controlador.obtenerCategorias();

			Iterator<DAOCategoria> itCategorias = categorias.iterator();
			while(itCategorias.hasNext()) {
				DAOCategoria categoria= itCategorias.next();
				Vector<String> vectordeCategorias = new Vector<String>();
				vectordeCategorias.add(categoria.getCod_categoria());
				vectordeCategorias.add(categoria.getNombre_categoria());
				
				dataModel.addRow(vectordeCategorias);
			
				
			}
		} catch (Exception e) {
			System.err.println("Vista: FALLO A OBTENER  CATEGORIAS");
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
