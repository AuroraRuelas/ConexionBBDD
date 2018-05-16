package Controller;



import Model.Model;
import View.Vista;

public class Main {

	public static void main(String[] args) {
	controller miControlador = new controller ();
	Model miModelo = new Model ();
	Vista miVista = new Vista();
	miControlador.setVista(miVista);
	miControlador.setModelo(miModelo);
	
	miModelo.setVista(miVista);
	
	miVista.setModelo(miModelo);
	miVista.setControlador(miControlador);
	
	miVista.setVisible(true);
	}
}

