package Test;

import controlador.*;
import modelo.ModeloLibreria;
import vista.*;

public class TestPruebas {

	public TestPruebas() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		
		//Declara e Inicia el modelo
		ModeloLibreria modelo = new ModeloLibreria();
		
		//Declara e Inicia los diferentes Controladores y se les agrega el modelo unico a cada uno de esos controladoes
		ControladorEditorial controladorEditorial = new ControladorEditorial(modelo);
		ControladorAutor controladorAutor = new ControladorAutor(modelo);
		ControladorCategoria controladorCategoria = new ControladorCategoria(modelo);
		ControladorLibro controladorLibro = new ControladorLibro(modelo);
		
		//Declara e inicia las vistas  y les agrega a cada vista su propio controlador
		VistaAutor vistaAutor = new VistaAutor(controladorAutor);
		VistaCategoria vistaCategoria = new VistaCategoria(controladorCategoria);
		VistaLibro vistaLibro= new VistaLibro(controladorLibro);
		VistaEditorial vistaEditorial= new VistaEditorial(controladorEditorial);
		
		
		//Declara en inicia la vista principal desde la que se accede al resto de las vistas
		VistaPrincipal visor = new VistaPrincipal(vistaAutor, vistaEditorial, vistaCategoria, vistaLibro);
		
		visor.getAccion();

	}

}
