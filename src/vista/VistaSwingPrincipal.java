
package vista;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;

import controlador.ControladorAutor;
import controlador.ControladorCategoria;
import controlador.ControladorCliente;
import controlador.ControladorEditorial;
import controlador.ControladorLibro;

public class VistaSwingPrincipal implements ActionListener {
	JFrame marco;
	ControladorAutor controladorAutor;
	ControladorEditorial controladorEditorial;
	ControladorCategoria controladorCategoria;
	ControladorLibro controladorLibro;
	ControladorCliente controladorCliente;
	JMenuBar menuBar;
	JButton btnHome, btnCategoria,btnEditorial, btnAutor, btnLibro;
	VentanaSwingAutor ventanaAutor;
	VentanaSwingLibro ventanaLibro;
	
	
	
	
	
	private int opcion;	
	private Scanner entrada;
		
		public VistaSwingPrincipal(ControladorAutor controladorAutor, ControladorEditorial controladorEditorial, ControladorCategoria controladorCategoria, ControladorLibro controladorLibro, ControladorCliente controladorCliente) {
		this.controladorAutor=controladorAutor;
		this.controladorEditorial=controladorEditorial;
		this.controladorCategoria=controladorCategoria;
		this.controladorLibro=controladorLibro;
		this.controladorCliente=controladorCliente;
		marco = new JFrame("Menú principal");
		marco.setLayout(null);
		marco.setSize(850, 250);
		marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		marco.setResizable(false);
		menuBar = new JMenuBar();
		marco.setJMenuBar(menuBar);
		
		btnHome = new JButton("Home");
		menuBar.add(btnHome);
		btnHome.addActionListener(this);
		
		btnAutor = new JButton("Autor");
		menuBar.add(btnAutor);
		btnAutor.addActionListener(this);
		
		
		btnCategoria = new JButton("Categoria");
		menuBar.add(btnCategoria);
		btnCategoria.addActionListener(this);
		
		
		btnEditorial = new JButton("Editorial");
		menuBar.add(btnEditorial);
		btnEditorial.addActionListener(this);
		btnLibro = new JButton("Libro");
		menuBar.add(btnLibro);
		btnLibro.addActionListener(this);
		
		marco.setVisible(true);
		
	}			
		

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(btnAutor)) {
				if (ventanaAutor==null) {
					marco.getContentPane().removeAll();
					marco.setSize(850,525);
					ventanaAutor = new VentanaSwingAutor(controladorAutor, marco);
					
				}else {
					marco.getContentPane().removeAll();
					marco.setSize(850,525);
					ventanaAutor.repintar();
				}
				
			}
			if (e.getSource().equals(btnLibro)) {
				if (ventanaLibro==null) {
					marco.getContentPane().removeAll();
					marco.setSize(850,850);
					ventanaLibro = new VentanaSwingLibro(controladorLibro,controladorAutor,controladorCategoria,controladorEditorial, marco);
					
				}else {
					marco.getContentPane().removeAll();
					marco.setSize(850,850);
					ventanaLibro.repintar();
				}
				
			}
		}
		
			
			
			
}

