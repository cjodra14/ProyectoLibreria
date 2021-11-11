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
		ControladorAutor controladorAutor = new ControladorAutor(modelo);
		ControladorCategoria controladorCategoria = new ControladorCategoria(modelo);
		VistaAutor vistaAutor = new VistaAutor(controladorAutor);
		VistaCategoria vistaCategoria = new VistaCategoria(controladorCategoria);
		VistaLibro vistaLibro=null;
		VistaEditorial vistaEditorial=null;
		VistaPrincipal visor = new VistaPrincipal(vistaAutor, vistaEditorial, vistaCategoria, vistaLibro);
		
		visor.getAccion();

	}

}
