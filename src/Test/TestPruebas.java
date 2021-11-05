package Test;

import controlador.ControladorAutor;
import modelo.ModeloLibreria;
import vista.VistaAutor;

public class TestPruebas {

	public TestPruebas() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		ModeloLibreria modelo = new ModeloLibreria();
		ControladorAutor controlador = new ControladorAutor(modelo);
		VistaAutor visor = new VistaAutor(controlador);
		visor.getAccion();

	}

}
