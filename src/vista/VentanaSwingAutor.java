package vista;

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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import controlador.ControladorAutor;
import dao.DAOAutor;

public class VentanaSwingAutor extends WindowAdapter implements ActionListener{
	private JTextArea jtareaResultado;
	private JButton btnListar,btnTerminar,btnLimpiar;
	
	private ControladorAutor controlador;
	private Vector<DAOAutor> resultado;
	JFrame marco = new JFrame("Vista para Autores");
	
	private JLabel lblListadoDeAutores;
	private JTable tableAutores;
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

	public VentanaSwingAutor(ControladorAutor controlador) {
		this.controlador=controlador;
		
		marco.setLayout(null);
		marco.setSize(850, 525);
		marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		jtareaResultado= new JTextArea("", 4,16);
		
		btnListar= new JButton("Listar autores");
		btnTerminar = new JButton("Terminar");
		btnLimpiar = new JButton("Limpiar");
		
		
		
		btnLimpiar.addActionListener(this);
		btnTerminar.addActionListener(this);
		btnListar.addActionListener(this);
		
		marco.add(jtareaResultado);
		marco.add(btnTerminar);
		marco.add(btnLimpiar);
		marco.add(btnListar);
		
		marco.addWindowListener(this);
		
		crearPantalla();
		
		marco.setVisible(true);
	}
	
	@Override
	public void windowClosing(WindowEvent e) {
		//terminarAplicacion();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource().equals(btnListar)) {
//			listarAutores();
		}else if(arg0.getSource().equals(btnTerminar)) {
			terminarAplicacion();
		}else if (arg0.getSource().equals(btnLimpiar)) {
			limpiarSalida();
		}
		
	}
	
	public void crearPantalla() {
		lblListadoDeAutores = new JLabel("Listado de Autores");
		lblListadoDeAutores.setBounds(15, 16, 164, 20);
		marco.add(lblListadoDeAutores);
		
		Vector<String> header= new Vector<String>();
		header.add("Código");
		header.add("Nombre");
		header.add("Primer Apellido");
		header.add("Segundo Apellido");
		dataModel= new DefaultTableModel(0, 0);
		 dataModel.setColumnIdentifiers(header);
		listarAutores();
		tableAutores = new JTable(dataModel);
		tableAutores.setBounds(25, 52, 600, 275);
		tableAutores.setEnabled(false);
		tableAutores.addMouseListener(new MouseAdapter() 
		   {
		      public void mouseClicked(MouseEvent e) 
		      {
		         int fila = tableAutores.rowAtPoint(e.getPoint());
		         int columna = 0;
		         if ((fila > -1) && (columna > -1))
		        	 
		        	 textFieldCodigoAutor.setText((String) dataModel.getValueAt(fila, columna));
		         	textFieldNombreAutor.setText((String) dataModel.getValueAt(fila, columna+1));
		         	textFieldPApelAutor.setText((String) dataModel.getValueAt(fila, columna+2));
		         	textFieldSApelAutor.setText((String) dataModel.getValueAt(fila, columna+3));
		            System.out.println(dataModel.getValueAt(fila, columna)+": "+dataModel.getValueAt(fila,columna+1)+",  "+dataModel.getValueAt(fila,columna+2)+", "+dataModel.getValueAt(fila,columna+3));
		      }
		   });
		
		marco.add(tableAutores);
		
	
		
		btnBotonEditar = new JButton("Boton Editar");
		btnBotonEditar.setBounds(665, 98, 145, 29);
		btnBotonEditar.addActionListener(new ActionListener() {
			
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
						controlador.modificarAutor(codAutor, nombreAutor, pApelAutor, sApelAutor);
						JOptionPane.showMessageDialog(null, "Autor modificado con exito");
						listarAutores();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				
				
			}
		});
		marco.add(btnBotonEditar);
		
		btnBotonEliminar = new JButton("Boton Eliminar");
		btnBotonEliminar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String codAutor=textFieldCodigoAutor.getText();
				
				if (codAutor.equals("")) {
					JOptionPane.showMessageDialog(null, "No puede estar vacio el CODIGO DE AUTOR a eliminar ");
				}else {
					try {
						controlador.borrarAutor(codAutor);
						JOptionPane.showMessageDialog(null, "Autor eliminado con exito");
						listarAutores();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				
				
			}
		});
		btnBotonEliminar.setBounds(665, 184, 145, 29);
		marco.add(btnBotonEliminar);
		
		btnBotonAniadir = new JButton("Boton A\u00F1adir");
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
						controlador.insertarAutor(codAutor, nombreAutor, pApelAutor, sApelAutor);
						JOptionPane.showMessageDialog(null, "Autor añadido con exito");
						listarAutores();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				
				
			}
		});
		marco.add(btnBotonAniadir);
		
		lblCodigoDeAutor = new JLabel("Codigo de Autor:");
		lblCodigoDeAutor.setBounds(15, 349, 145, 20);
		marco.add(lblCodigoDeAutor);
		
		textFieldCodigoAutor = new JTextField();
		textFieldCodigoAutor.setBounds(15, 385, 146, 26);
		marco.add(textFieldCodigoAutor);
		textFieldCodigoAutor.setColumns(10);
		
		lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(210, 349, 69, 20);
		marco.add(lblNombre);
		
		textFieldNombreAutor = new JTextField();
		textFieldNombreAutor.setColumns(10);
		textFieldNombreAutor.setBounds(210, 385, 146, 26);
		marco.add(textFieldNombreAutor);
		
		lblPrimerApellido = new JLabel("Primer Apellido:");
		lblPrimerApellido.setBounds(393, 349, 126, 20);
		marco.add(lblPrimerApellido);
		
		textFieldPApelAutor = new JTextField();
		textFieldPApelAutor.setColumns(10);
		textFieldPApelAutor.setBounds(393, 385, 146, 26);
		marco.add(textFieldPApelAutor);
		
		lblSegundoApellido = new JLabel("Segundo Apellido:");
		lblSegundoApellido.setBounds(584, 349, 131, 20);
		marco.add(lblSegundoApellido);
		
		textFieldSApelAutor = new JTextField();
		textFieldSApelAutor.setColumns(10);
		textFieldSApelAutor.setBounds(584, 385, 146, 26);
		marco.add(textFieldSApelAutor);
	}

	
	private void listarAutores() {
		dataModel.setRowCount(0);
		String datos="";
		Vector<DAOAutor> autores= controlador.obtenerAutores();
		
		
		try {
			Iterator<DAOAutor> itAutores = autores.iterator();
			while(itAutores.hasNext()) {
				DAOAutor autor= itAutores.next();
				Vector<String> vectordeAutores = new Vector<String>();
				vectordeAutores.add(autor.getCod_autor());
				vectordeAutores.add(autor.getNombre_autor());
				vectordeAutores.add(autor.getP_apellido());
				vectordeAutores.add(autor.getS_apellido());
				
				dataModel.addRow(vectordeAutores);
			
//				datos=datos+" ID: "+autor.getCod_autor()+" || Nombre: "+autor.getNombre_autor();
//				if(autor.getP_apellido()!=null)datos=datos+" , "+autor.getP_apellido();
//				if(autor.getS_apellido()!=null)datos=datos+" , "+autor.getS_apellido();
//				datos=datos+"\n";
				
			}
			
			
			
			
			
				
				
		} catch (Exception e) {
			System.err.println("Vista: FALLO A OBTENER  AUTORES");
			e.printStackTrace();
		}
//		jtareaResultado.setText(datos);
		
		
	}
	
	private void limpiarSalida() {
		String texto=" ";
		jtareaResultado.setText(texto);
	}
	
	private void terminarAplicacion() {			
			controlador.terminar();
			//marco.dispatchEvent(new WindowEvent(marco, WindowEvent.WINDOW_CLOSING));
			System.out.println("Aplicación terminada");
			System.exit(0);
		}
	
}
