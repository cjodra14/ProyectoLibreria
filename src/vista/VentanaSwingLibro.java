package vista;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import controlador.ControladorAutor;
import controlador.ControladorCategoria;
import controlador.ControladorEditorial;
import controlador.ControladorLibro;
import dao.DAOAutor;
import dao.DAOCategoria;
import dao.DAOEditorial;
import dao.DAOLibro;
import dao.DAOlibro_escritor;

public class VentanaSwingLibro {
	 private static final String IMG_PATH = "assets/books/";
	JFrame marco;
	JPanel panelLibro;
	ControladorLibro controladorLibro;
	ControladorCategoria controladorCategoria;
	ControladorEditorial controladorEditorial;
	ControladorAutor controladorAutor;
	
	private JTable tableLibros;
	private JTable tableAutores;
	private JLabel lblAutoresDelLibro;
	private JTextField tfUds;
	private JTextField tfPrecio;
	private JLabel lblCategoria;
	private JButton btnAddAutor;
	private JButton btnDropAutor;
	private JButton btnRefresh;
	private JLabel lblImagen;
	private JLabel label;
	private JButton btnCambiarImg;
	private JLabel lblStock;
	private JButton buttonMenos;
	private JButton buttonMas;
	private JLabel lblIsbn;
	private JTextField tfISBN;
	private JLabel lblTitulo;
	private JTextField tfTitulo;
	private JLabel lblPrecio;
	private JLabel lblEuro;
	private JButton btnAadirLibro;
	private JButton btnEditarLibro;
	private JButton btnEliminarLibro;
	private JButton btnRefreshEdCat;
	private JLabel lblDescripcion;
	private JTextArea tAreaDescripcion;
	private JLabel lblEditorial;
	private JComboBox comboEditorial;
	private JComboBox comboCategoria;
	private JLabel lblListadoDeLibros;
	private DefaultTableModel dataModel;
	private DefaultTableModel autorDataModel;
	private JScrollPane scrollPaneAutor;
	private JScrollPane scrollPane;
	private JTabbedPane tabPaneAutores;
	private JPanel panelAutoresActuales;
	private JPanel panelAddAutor;
	private JScrollPane scrollPaneAutoresActuales;
	private JScrollPane scrollPaneAddAutor;
	private DefaultTableModel modeloAutores;
	private JTable tableAddAutores;
	private int contador = 0;
	
	

	public VentanaSwingLibro(ControladorLibro controladorLibro, ControladorAutor controladorAutor,ControladorCategoria controladorCategoria ,ControladorEditorial controladorEditorial , JFrame marco) {
	this.controladorLibro=controladorLibro;
	this.controladorAutor= controladorAutor;
	this.controladorCategoria = controladorCategoria;
	this.controladorEditorial= controladorEditorial;
	
	this.marco = marco;
	
	panelLibro = new JPanel();
	panelLibro.setLayout(null);
	panelLibro.setSize(850, 845);
	
	crearPantalla();
	
	panelLibro.setVisible(true);
	marco.add(panelLibro);
	marco.repaint();
	}
	
	private void crearPantalla() {
		lblListadoDeLibros = new JLabel("Listado de Libros");
		lblListadoDeLibros.setBounds(15, 16, 164, 20);
		panelLibro.add(lblListadoDeLibros);
		
		lblImagen = new JLabel("Imagen:");
		lblImagen.setBounds(15, 521, 69, 20);
		panelLibro.add(lblImagen);
		
		label = new JLabel("");
		label.setBounds(25, 550, 115, 115);
		panelLibro.add(label);
		
		
		Vector<String> header= new Vector<String>();
		header.add("ISBN");
		header.add("Título");
		header.add("Precio");
		header.add("Stock");
		header.add("Imagen");
		header.add("Descripción");
		header.add("Editorial");
		header.add("Categoría");
		
		dataModel= new DefaultTableModel(0, 0);
		dataModel.setColumnIdentifiers(header);
		listarLibros();
		tableLibros = new JTable(dataModel);
		tableLibros.setEnabled(false);
		tableLibros.addMouseListener(new MouseAdapter() 
		   {
		      public void mouseClicked(MouseEvent e) 
		      {
		         int fila = tableLibros.rowAtPoint(e.getPoint());
		         int columna = 0;
		         if ((fila > -1) && (columna > -1)) {
		        	 tfISBN.setText((String)dataModel.getValueAt(fila, columna));
		        	 tfTitulo.setText((String)dataModel.getValueAt(fila, columna+1));
		        	 tfPrecio.setText((String)dataModel.getValueAt(fila, columna+2));
		        	 tfUds.setText((String)dataModel.getValueAt(fila, columna+3));
		        	 if (null!=dataModel.getValueAt(fila, columna+4)) {
		        		 try {
		        			 label.setIcon(null);
		        	         BufferedImage img = ImageIO.read(new File(IMG_PATH+(String)dataModel.getValueAt(fila, columna+4)));
		        	         Image dimg= img.getScaledInstance(label.getHeight(),label.getWidth(),Image.SCALE_SMOOTH);
		        	         ImageIcon icon = new ImageIcon(dimg);
		        	         label.setIcon(icon);
		        	         
		        	      } catch (IOException e2) {
		        	         e2.printStackTrace();
		        	      }
					}
		        	 tAreaDescripcion.setText((String)dataModel.getValueAt(fila, columna+5));
		        	 
		        	listarAutores();
//		            System.out.println(dataModel.getValueAt(fila, columna)+": "+dataModel.getValueAt(fila,columna+1)+",  "+dataModel.getValueAt(fila,columna+2)+", "+dataModel.getValueAt(fila,columna+3));
		      }
		   }});
		scrollPane= new JScrollPane(tableLibros);
		scrollPane.setBounds(25, 52, 767, 275);
		
		panelLibro.add(scrollPane);
		
		Vector<String> headerAutores = new Vector<String>();
		headerAutores.add("Código Autor");
		headerAutores.add("Nombre");
		headerAutores.add("Primer Apellido");
		headerAutores.add("Segundo Apellido");
//////
		
		
		autorDataModel= new DefaultTableModel(0, 0);
		autorDataModel.setColumnIdentifiers(headerAutores);
//		listarAutores();
		tableAutores = new JTable(autorDataModel);
		tableAutores.setEnabled(false);
		
		
		modeloAutores = new DefaultTableModel(0,0);
		modeloAutores.setColumnIdentifiers(headerAutores);
		listarTodosAutores();
		tableAddAutores = new JTable(modeloAutores);
		
//		tableAutores.addMouseListener(new MouseAdapter() 
//		   {
//		      public void mouseClicked(MouseEvent e) 
//		      {
//		         int fila = tableLibros.rowAtPoint(e.getPoint());
//		         int columna = 0;
//		         if ((fila > -1) && (columna > -1)) {
//		        	 tfISBN.setText((String)dataModel.getValueAt(fila, columna));
//		        	 tfTitulo.setText((String)dataModel.getValueAt(fila, columna+1));
//		        	 tfPrecio.setText((String)dataModel.getValueAt(fila, columna+2));
//		        	 tfUds.setText((String)dataModel.getValueAt(fila, columna+3));
//		        	 if (null!=dataModel.getValueAt(fila, columna+4)) {
//		        		 try {
//		        			 
//		        	         BufferedImage img = ImageIO.read(new File(IMG_PATH+(String)dataModel.getValueAt(fila, columna+4)));
//		        	         Image dimg= img.getScaledInstance(label.getHeight(),label.getWidth(),Image.SCALE_SMOOTH);
//		        	         ImageIcon icon = new ImageIcon(dimg);
//		        	         label.setIcon(icon);
//		        	         
//		        	      } catch (IOException e2) {
//		        	         e2.printStackTrace();
//		        	      }
//					}
//		        	 tAreaDescripcion.setText((String)dataModel.getValueAt(fila, columna+5));
//}
//		   }});
//		scrollPaneAutor= new JScrollPane(tableAutores);
//		scrollPaneAutor.setBounds(25, 390, 650, 115);
//		
//		panelLibro.add(scrollPaneAutor);
		
		
//////		
		
		tabPaneAutores = new JTabbedPane(JTabbedPane.TOP);
		tabPaneAutores.setBounds(15, 351, 779, 168);
		panelLibro.add(tabPaneAutores);
		
		panelAutoresActuales = new JPanel();
		tabPaneAutores.addTab("Autores Actuales", null, panelAutoresActuales, null);
		panelAutoresActuales.setLayout(null);
		
		btnDropAutor = new JButton("DROP Autor");
		btnDropAutor.setBounds(647, 57, 115, 29);
		panelAutoresActuales.add(btnDropAutor);
		
		scrollPaneAutoresActuales = new JScrollPane(tableAutores);
		scrollPaneAutoresActuales.setBounds(12, 13, 623, 112);
		panelAutoresActuales.add(scrollPaneAutoresActuales);
		
		panelAddAutor = new JPanel();
		tabPaneAutores.addTab("A\u00F1adir Autor", null, panelAddAutor, null);
		panelAddAutor.setLayout(null);

		btnAddAutor = new JButton("ADD Autor");
		btnAddAutor.setBounds(646, 26, 115, 29);
		panelAddAutor.add(btnAddAutor);
		
		btnRefresh = new JButton("Refresh");
		btnRefresh.setBounds(647, 72, 115, 29);
		panelAddAutor.add(btnRefresh);
		
		scrollPaneAddAutor = new JScrollPane(tableAddAutores);
		scrollPaneAddAutor.setBounds(12, 13, 622, 112);
		panelAddAutor.add(scrollPaneAddAutor);
		
		
//		lblAutoresDelLibro = new JLabel("Autores del libro");
//		lblAutoresDelLibro.setBounds(15, 351, 133, 20);
//		panelLibro.add(lblAutoresDelLibro);
//		
//		btnAddAutor = new JButton("ADD Autor");
//		btnAddAutor.setBounds(677, 383, 115, 29);
//		panelLibro.add(btnAddAutor);
//		
//		btnDropAutor = new JButton("DROP Autor");
//		btnDropAutor.setBounds(677, 476, 115, 29);
//		panelLibro.add(btnDropAutor);
//		
//		btnRefresh = new JButton("Refresh");
//		btnRefresh.setBounds(677, 431, 115, 29);
//		panelLibro.add(btnRefresh);
		
		
		
		btnCambiarImg = new JButton("cambiar IMG");
		btnCambiarImg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCambiarImg.setBounds(15, 672, 133, 29);
		panelLibro.add(btnCambiarImg);
		
		lblStock = new JLabel("Stock:");
		lblStock.setBounds(15, 717, 69, 20);
		panelLibro.add(lblStock);
		
		tfUds = new JTextField();
		tfUds.setText("0");
		tfUds.setBounds(59, 738, 48, 26);
//		tfUds.setEnabled(false);
		panelLibro.add(tfUds);
//		tfUds.setColumns(10);
		
		buttonMenos = new JButton("-");
		buttonMenos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		buttonMenos.setBounds(106, 737, 45, 26);
		buttonMenos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contador = Integer.parseInt(tfUds.getText());
				if(contador>0){
				contador--;
				tfUds.setText(String.valueOf(contador));}
			}
		});
		panelLibro.add(buttonMenos);
		
		buttonMas = new JButton("+");
		buttonMas.setFont(new Font("Tahoma", Font.PLAIN, 14));
		buttonMas.setBounds(15, 737, 45, 26);
		buttonMas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contador = Integer.parseInt(tfUds.getText());
				contador++;
				tfUds.setText(String.valueOf(contador));
			}
		});
		panelLibro.add(buttonMas);
		
		lblIsbn = new JLabel("ISBN:");
		lblIsbn.setBounds(188, 521, 69, 20);
		panelLibro.add(lblIsbn);
		
		tfISBN = new JTextField();
		tfISBN.setBounds(198, 547, 224, 26);
		panelLibro.add(tfISBN);
		tfISBN.setColumns(10);
		
		lblTitulo = new JLabel("T\u00EDtulo:");
		lblTitulo.setBounds(188, 578, 69, 20);
		panelLibro.add(lblTitulo);
		
		tfTitulo = new JTextField();
		tfTitulo.setBounds(198, 606, 427, 26);
		panelLibro.add(tfTitulo);
		tfTitulo.setColumns(10);
		
		lblPrecio = new JLabel("Precio:");
		lblPrecio.setBounds(456, 521, 69, 20);
		panelLibro.add(lblPrecio);
		
		tfPrecio = new JTextField();
		tfPrecio.setBounds(466, 547, 115, 26);
		panelLibro.add(tfPrecio);
		tfPrecio.setColumns(10);
		
		lblEuro = new JLabel("\u20AC");
		lblEuro.setBounds(591, 550, 19, 20);
		panelLibro.add(lblEuro);
		
		btnAadirLibro = new JButton("A\u00F1adir Libro");
		btnAadirLibro.setBounds(659, 559, 133, 29);
		panelLibro.add(btnAadirLibro);
		
		btnEditarLibro = new JButton("Editar Libro");
		btnEditarLibro.setBounds(659, 620, 133, 29);
		panelLibro.add(btnEditarLibro);
		
		btnEliminarLibro = new JButton("Eliminar Libro");
		btnEliminarLibro.setBounds(659, 672, 133, 29);
		panelLibro.add(btnEliminarLibro);
		
		btnRefreshEdCat = new JButton("Refresh Ed. Cat.");
		btnRefreshEdCat.setBounds(659, 737, 147, 29);
		panelLibro.add(btnRefreshEdCat);
		
		lblDescripcion = new JLabel("Descripci\u00F3n:");
		lblDescripcion.setBounds(188, 638, 103, 20);
		panelLibro.add(lblDescripcion);
		
		tAreaDescripcion = new JTextArea();
		tAreaDescripcion.setBounds(198, 660, 427, 54);
		panelLibro.add(tAreaDescripcion);
		
		lblEditorial = new JLabel("Editorial");
		lblEditorial.setBounds(188, 717, 69, 20);
		panelLibro.add(lblEditorial);
		
		lblCategoria = new JLabel("Categoria");
		lblCategoria.setBounds(404, 717, 69, 20);
		panelLibro.add(lblCategoria);
		
		
		Vector<DAOEditorial> editoriales;
		try {
			editoriales = controladorEditorial.obtenerEditoriales();
		
		Iterator<DAOEditorial> itVector = editoriales.iterator();
		Vector<String> editorialesVector= new Vector<String>();
		while (itVector.hasNext()) {
			editorialesVector.add(itVector.next().getNombre_editorial());
			
		}
		
		
		comboEditorial = new JComboBox(editorialesVector);
		comboEditorial.setBounds(188, 738, 176, 26);
		panelLibro.add(comboEditorial);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			
		
		Vector<DAOCategoria> categorias=controladorCategoria.obtenerCategorias();
		Iterator<DAOCategoria> itCat = categorias.iterator();
		Vector<String> categoriasVector= new Vector<String>();
		while (itCat.hasNext()) {
			categoriasVector.add(itCat.next().getNombre_categoria());
			
		}
		
		comboCategoria = new JComboBox(categoriasVector);
		comboCategoria.setBounds(414, 738, 211, 26);
		panelLibro.add(comboCategoria);	
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	private void listarAutores() {
		autorDataModel.setRowCount(0);
		
		Vector<DAOlibro_escritor> autores;
		try {
			autores =  controladorLibro.obtenerLibroEscritorIsbn(Long.valueOf(tfISBN.getText()));

			Iterator<DAOlibro_escritor> itAutoresLibro = autores.iterator();
			while(itAutoresLibro.hasNext()) {
				DAOlibro_escritor autorLibro= itAutoresLibro.next();
				Vector<String> vectordeAutoresLibro = new Vector<String>();
				vectordeAutoresLibro.add(autorLibro.getCod_autor());
				vectordeAutoresLibro.add(autorLibro.getNombreAutor());
				vectordeAutoresLibro.add(autorLibro.getpApelAutor());
				vectordeAutoresLibro.add(autorLibro.getsApelAutor());
				
				autorDataModel.addRow(vectordeAutoresLibro);
			}
		} catch (Exception e) {
			System.err.println("Vista: FALLO A OBTENER  LOS AUTORES");
			e.printStackTrace();
		}
		
	}

	private void listarLibros() {
		dataModel.setRowCount(0);
		
		Vector<DAOLibro> libros;
		try {
			libros =  controladorLibro.obtenerLibros();

			Iterator<DAOLibro> itAutores = libros.iterator();
			while(itAutores.hasNext()) {
				DAOLibro libro= itAutores.next();
				Vector<String> vectordeLibros = new Vector<String>();
				vectordeLibros.add(String.valueOf(libro.getIsbn()));
				vectordeLibros.add(libro.getTitulo());
				vectordeLibros.add(String.valueOf(libro.getPrecio()));
				vectordeLibros.add(String.valueOf(libro.getUd_stock()));
				vectordeLibros.add(libro.getImagen());
				vectordeLibros.add(libro.getDescripcion());
				vectordeLibros.add(controladorEditorial.obtenerEditorial(libro.getCod_editorial()).getNombre_editorial());
				vectordeLibros.add(controladorCategoria.obtenerCategoria(libro.getCod_categoria()).getNombre_categoria());
				
			dataModel.addRow(vectordeLibros);
			}
		} catch (Exception e) {
			System.err.println("Vista: FALLO A OBTENER  LOS LIBROS");
			e.printStackTrace();
		}
	}
	
	private void listarTodosAutores() {
		modeloAutores.setRowCount(0);
		
		Vector<DAOAutor> autores;
		try {
			autores = controladorAutor.obtenerAutores();

			Iterator<DAOAutor> itAutores = autores.iterator();
			while(itAutores.hasNext()) {
				DAOAutor autor= itAutores.next();
				Vector<String> vectordeAutores = new Vector<String>();
				vectordeAutores.add(autor.getCod_autor());
				vectordeAutores.add(autor.getNombre_autor());
				vectordeAutores.add(autor.getP_apellido());
				vectordeAutores.add(autor.getS_apellido());
				
				modeloAutores.addRow(vectordeAutores);
			
				
			}
		} catch (Exception e) {
			System.err.println("Vista: FALLO A OBTENER  AUTORES");
			e.printStackTrace();
		}
		
		
	}
	
	
	public void repintar() {
		panelLibro.setVisible(true);
		marco.add(panelLibro);
		marco.repaint();
	}

}