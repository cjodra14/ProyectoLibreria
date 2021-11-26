package vista;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

import controlador.ControladorAutor;
import dao.DAOAutor;

public class VentanaSwingAutor extends WindowAdapter implements ActionListener{
	private JTextArea jtareaResultado;
	private JButton btnListar,btnTerminar,btnLimpiar;
	
	private ControladorAutor controlador;
	private Vector<DAOAutor> resultado;
	JFrame marco = new JFrame("Vista para Autores");

	public VentanaSwingAutor(ControladorAutor controlador) {
		this.controlador=controlador;
		
		marco.setLayout(new FlowLayout());
		marco.setSize(300,200);
		marco.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
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
		
		
		marco.setVisible(true);
	}
	
	@Override
	public void windowClosing(WindowEvent e) {
		//terminarAplicacion();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource().equals(btnListar)) {
			listarAutores();
		}else if(arg0.getSource().equals(btnTerminar)) {
			terminarAplicacion();
		}else if (arg0.getSource().equals(btnLimpiar)) {
			limpiarSalida();
		}
		
	}

	
	private void listarAutores() {
		String datos="";
		Vector<DAOAutor> autores= controlador.obtenerAutores();
		
		try {
			Iterator<DAOAutor> itAutores = autores.iterator();
			while(itAutores.hasNext()) {
				DAOAutor autor= itAutores.next();
				datos=datos+" ID: "+autor.getCod_autor()+" || Nombre: "+autor.getNombre_autor();
				if(autor.getP_apellido()!=null)datos=datos+" , "+autor.getP_apellido();
				if(autor.getS_apellido()!=null)datos=datos+" , "+autor.getS_apellido();
				datos=datos+"\n";
				
			}
				
				
		} catch (Exception e) {
			System.err.println("Vista: FALLO A OBTENER  AUTORES");
			e.printStackTrace();
		}
		jtareaResultado.setText(datos);
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
