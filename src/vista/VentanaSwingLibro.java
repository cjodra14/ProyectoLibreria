package vista;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import controlador.ControladorLibro;
import dao.DAOAutor;
import dao.DAOLibro;

public class VentanaSwingLibro {
	JFrame marco;
	JPanel panelLibro;
	ControladorLibro controladorLibro;
	
	private JTable tableLibros;
	private JTable tabelAutores;
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
	private JScrollPane scrollPane;
	
	
	

	public VentanaSwingLibro(ControladorLibro controladorLibro, JFrame marco) {
	this.controladorLibro=controladorLibro;
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
		        	 
//		        	 textFieldCodigoAutor.setText((String) dataModel.getValueAt(fila, columna));
//		         	textFieldNombreAutor.setText((String) dataModel.getValueAt(fila, columna+1));
//		         	textFieldPApelAutor.setText((String) dataModel.getValueAt(fila, columna+2));
//		         	textFieldSApelAutor.setText((String) dataModel.getValueAt(fila, columna+3));
//		            System.out.println(dataModel.getValueAt(fila, columna)+": "+dataModel.getValueAt(fila,columna+1)+",  "+dataModel.getValueAt(fila,columna+2)+", "+dataModel.getValueAt(fila,columna+3));
		      }
		   }});
		scrollPane= new JScrollPane(tableLibros);
		scrollPane.setBounds(25, 52, 767, 275);
		
		panelLibro.add(scrollPane);
		
		tabelAutores = new JTable();
		tabelAutores.setEnabled(false);
		tabelAutores.setBounds(25, 390, 650, 115);
		panelLibro.add(tabelAutores);
		
		lblAutoresDelLibro = new JLabel("Autores del libro");
		lblAutoresDelLibro.setBounds(15, 351, 133, 20);
		panelLibro.add(lblAutoresDelLibro);
		
		btnAddAutor = new JButton("ADD Autor");
		btnAddAutor.setBounds(677, 383, 115, 29);
		panelLibro.add(btnAddAutor);
		
		btnDropAutor = new JButton("DROP Autor");
		btnDropAutor.setBounds(677, 476, 115, 29);
		panelLibro.add(btnDropAutor);
		
		btnRefresh = new JButton("Refresh");
		btnRefresh.setBounds(677, 431, 115, 29);
		panelLibro.add(btnRefresh);
		
		lblImagen = new JLabel("Imagen:");
		lblImagen.setBounds(15, 521, 69, 20);
		panelLibro.add(lblImagen);
		
		label = new JLabel("");
		label.setBounds(25, 557, 115, 115);
		panelLibro.add(label);
		
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
		tfUds.setText("Ud's");
		tfUds.setBounds(59, 738, 48, 26);
		panelLibro.add(tfUds);
		tfUds.setColumns(10);
		
		buttonMenos = new JButton("-");
		buttonMenos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		buttonMenos.setBounds(106, 737, 45, 26);
		panelLibro.add(buttonMenos);
		
		buttonMas = new JButton("+");
		buttonMas.setFont(new Font("Tahoma", Font.PLAIN, 14));
		buttonMas.setBounds(15, 737, 45, 26);
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
		
		comboEditorial = new JComboBox();
		comboEditorial.setBounds(188, 738, 176, 26);
		panelLibro.add(comboEditorial);
		
		comboCategoria = new JComboBox();
		comboCategoria.setBounds(414, 738, 211, 26);
		panelLibro.add(comboCategoria);		
		
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
				
				dataModel.addRow(vectordeLibros);
			
				
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
