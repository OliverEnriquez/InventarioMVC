package Inventrio;

import Controller.Controller;
import View.View;

public class Principal {

	public static void main(String[] args) {
		new Bd("Inventario");
		View vista = new View();
		Controller controlador = new Controller(vista);
		
		vista.conectaControlador(controlador);

	}

}
