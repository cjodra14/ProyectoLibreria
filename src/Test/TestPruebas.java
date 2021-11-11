package Test;

import controlador.*;
import modelo.ModeloLibreria;
import vista.*;

public class TestPruebas {

	public TestPruebas() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		ModeloLibreria modelo = new ModeloLibreria();
		ControladorAutor controlador = new ControladorAutor(modelo);
		VistaAutor vistaAutor = new VistaAutor(controlador);
		VistaLibro vistaLibro=null;
		VistaEditorial vistaEditorial=null;
		VistaCategoria vistaCategoria=null;
		VistaPrincipal visor = new VistaPrincipal(vistaAutor, vistaEditorial, vistaCategoria, vistaLibro);
		
		visor.getAccion();

	}

}
