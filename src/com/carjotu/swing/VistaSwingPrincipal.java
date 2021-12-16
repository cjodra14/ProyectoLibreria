
package com.carjotu.swing;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

import com.carjotu.controler.ControladorAutor;
import com.carjotu.controler.ControladorCategoria;
import com.carjotu.controler.ControladorCliente;
import com.carjotu.controler.ControladorEditorial;
import com.carjotu.controler.ControladorLibro;
import com.carjotu.controler.ControladorVenta;

public class VistaSwingPrincipal implements ActionListener {
	JFrame marco;
	ControladorAutor controladorAutor;
	ControladorEditorial controladorEditorial;
	ControladorCategoria controladorCategoria;
	ControladorLibro controladorLibro;
	ControladorCliente controladorCliente;
	ControladorVenta controladorVenta;
	
	JMenuBar menuBar;
	JButton btnHome, btnCategoria,btnEditorial, btnAutor, btnLibro, btnVenta;
	VentanaSwingAutor ventanaAutor;
	VentanaSwingLibro ventanaLibro;
	VentanaSwingEditorial ventanaEditorial;
	VentanaSwingCategoria ventanaCategoria;
	VentanaSwingVentas ventanaVenta;
	JPanel panelHome;
	
	
	
	
	
	private int opcion;	
	private Scanner entrada;
	private JButton btnAutorHome;
	private JButton btnLibroHome;
	private JButton btnCategoriaHome;
	private JButton btnEditorialHome;
		
		public VistaSwingPrincipal(ControladorAutor controladorAutor, ControladorEditorial controladorEditorial, ControladorCategoria controladorCategoria, ControladorLibro controladorLibro, ControladorCliente controladorCliente, ControladorVenta controladorVenta) {
		this.controladorAutor=controladorAutor;
		this.controladorEditorial=controladorEditorial;
		this.controladorCategoria=controladorCategoria;
		this.controladorLibro=controladorLibro;
		this.controladorCliente=controladorCliente;
		this.controladorVenta=controladorVenta;
		marco = new JFrame("Menú principal");
		marco.setLayout(null);
		marco.setSize(850, 525);
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
		
		btnVenta = new JButton("Ventas");
		menuBar.add(btnVenta);
		btnVenta.setVisible(true);
		btnVenta.addActionListener(this);
		
		panelHome = new JPanel();
		panelHome.setLayout(null);
		panelHome.setSize(850, 525);
		
		btnAutorHome = new JButton("Autor");
		btnAutorHome.setBounds(100, 61, 250, 150);
		btnAutorHome.addActionListener(this);
		panelHome.add(btnAutorHome);
		
		btnLibroHome = new JButton("Libro");
		btnLibroHome.setBounds(425, 61, 250, 150);
		btnLibroHome.addActionListener(this);
		panelHome.add(btnLibroHome);
		
		btnCategoriaHome = new JButton("Categoria");
		btnCategoriaHome.setBounds(100, 234, 250, 150);
		btnCategoriaHome.addActionListener(this);
		panelHome.add(btnCategoriaHome);
		
		btnEditorialHome = new JButton("Editorial");
		btnEditorialHome.setBounds(425, 234, 250, 150);
		btnEditorialHome.addActionListener(this);
		panelHome.add(btnEditorialHome);
		
		panelHome.setVisible(true);
		marco.add(panelHome);
		
		
		
		marco.setVisible(true);
		
	}			
	
		private void repintar() {
			panelHome.setVisible(true);
			marco.add(panelHome);
			marco.repaint();
		}
		

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(btnAutor)||e.getSource().equals(btnAutorHome)) {
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
			if (e.getSource().equals(btnLibro)||e.getSource().equals(btnLibroHome)) {
				if (ventanaLibro==null) {
					marco.getContentPane().removeAll();
					marco.setSize(850,850);
					try {
						ventanaLibro = new VentanaSwingLibro(controladorLibro,controladorAutor,controladorCategoria,controladorEditorial, marco);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}else {
					marco.getContentPane().removeAll();
					marco.setSize(850,850);
					ventanaLibro.repintar();
				}
				
			}
			if (e.getSource().equals(btnEditorial)||e.getSource().equals(btnEditorialHome)) {
				if (ventanaEditorial==null) {
					marco.getContentPane().removeAll();
					marco.setSize(850,525);
					ventanaEditorial = new VentanaSwingEditorial(controladorEditorial, marco);
					
				}else {
					marco.getContentPane().removeAll();
					marco.setSize(850,525);
					ventanaEditorial.repintar();
				}
				
			}
			if (e.getSource().equals(btnCategoria)||e.getSource().equals(btnCategoriaHome)) {
				if (ventanaCategoria==null) {
					marco.getContentPane().removeAll();
					marco.setSize(850,525);
					ventanaCategoria = new VentanaSwingCategoria(controladorCategoria, marco);
					
				}else {
					marco.getContentPane().removeAll();
					marco.setSize(850,525);
					ventanaCategoria.repintar();
				}
				
		}
			if (e.getSource().equals(btnVenta)) {
				if (ventanaVenta==null) {
					marco.getContentPane().removeAll();
					marco.setSize(850,525);
					ventanaVenta = new VentanaSwingVentas(controladorVenta, marco, controladorLibro);
					
				}else {
					marco.getContentPane().removeAll();
					marco.setSize(850,525);
					ventanaCategoria.repintar();
				}
				
		}
			if (e.getSource().equals(btnHome)) {
				
				marco.getContentPane().removeAll();
				marco.setSize(850,525);
				repintar();
			
		
	}
	}
		
			
			
			
}

