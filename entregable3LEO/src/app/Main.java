package app;

import java.util.Timer;

import javax.swing.SwingUtilities;

import Vistas.Vista;
import controladores.ActualizarDBTask;
import controladores.Controlador;

public class Main {

	 public static void main(String[] args) {
	        
	            Vista vista = new Vista();
	            Modelo modelo = new Modelo();
	            Controlador control = new Controlador(vista, modelo);
	            Timer timer;
	            timer = new Timer();
	            ActualizarDBTask actualizarDB = new ActualizarDBTask(modelo);
//	            timer.schedule(actualizarDB, 10, 15000);
	 }
}
