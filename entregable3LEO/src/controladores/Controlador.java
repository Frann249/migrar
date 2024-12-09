package controladores;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;

import Vistas.PanelLogin;
import Vistas.PanelRegistro;
import Vistas.Vista;
import app.Modelo;
import appModels.Cripto;
import appModels.Usuario;


public class Controlador {
	private Vista vista;
	private Modelo modelo;
	private Usuario userLogeado;
	
	public Controlador(Vista vista, Modelo modelo){
		this.vista = vista;
		this.setModelo(modelo);
		
		vista.getCotizaciones().configurar(modelo.listarMonedasTipo(modelo.CRIPTO));
		List<JButton> buttonsList = vista.getCotizaciones().getButtons();
		for(JButton botonAct : buttonsList) {
			botonAct.addActionListener(new ListenerCotizacion());
		}
		buttonsList = vista.getCompra().getButtons();
		for(JButton botonAct : buttonsList) {
			botonAct.addActionListener(new ListenerCompra());
		}
		buttonsList = vista.getRegistro().getButtons();
		for(JButton botonAct : buttonsList) {
			botonAct.addActionListener(new ListenerRegistro());
		}
		buttonsList= vista.getLogin().getButtons();
		for(JButton botonAct : buttonsList) {
			botonAct.addActionListener(new ListenerLogin());
		}
		vista.mostrarPanel("LOGIN");
		vista.setVisible(true);
	}
	

	/**
	 * @return the modelo
	 */
	public Modelo getModelo() {
		return modelo;
	}

	/**
	 * @param modelo the modelo to set
	 */
	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}


	class ListenerCotizacion implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String command = e.getActionCommand();
			
			if (command.contains("SWAP")) {
	            System.out.println("Esta función no está disponible.");

	            // Crear el JDialog
	            JDialog dialog = new JDialog(vista, "NOTIFICACIÓN", true); // Modal
	            dialog.setLayout(new FlowLayout());
	            dialog.setSize(300, 100);
	            dialog.setLocationRelativeTo(vista);

	            // Etiqueta con el mensaje
	            JLabel label = new JLabel("La función SWAP aún no está disponible.");
	            dialog.add(label);

	            // Botón "Aceptar" con acción para cerrar el diálogo
	            JButton botonAcept = new JButton("Aceptar");
	            botonAcept.addActionListener(new ActionListener() {
	                @Override
	                public void actionPerformed(ActionEvent e) {
	                    dialog.dispose(); // Cerrar el diálogo
	                }
	            });
	       
	            dialog.add(botonAcept);
	            // Mostrar el diálogo
	            dialog.setVisible(true);
	        } 
			else {
				Cripto cripto = (Cripto) modelo.obtenerMoneda(command);
				vista.getCompra().actualizarValores(cripto, modelo.listarActivosTipo(modelo.FIAT, userLogeado.getId()));;
				vista.mostrarPanel("COMPRAR");
			}
		}
	}
	class ListenerCompra implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String command = e.getActionCommand();
			if(command.equals("CONVERTIR")) {
				String nomenclaturaSelect = vista.getCompra().getActivoSelected();
				vista.getCompra().convertir(modelo.obtenerMoneda(nomenclaturaSelect));
			}
			if(command.equals("REALIZAR")) {
				
			}
			if(command.equals("CANCELAR")) {
				vista.getCotizaciones().actualizarPrecios(modelo.listarMonedasTipo(modelo.CRIPTO));
				vista.mostrarPanel("COTIZACION");
			}
		}
		
	}
	class ListenerRegistro implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			String command = e.getActionCommand();
			if(command.equals("VOLVER")) {
				vista.mostrarPanel("LOGIN");
			}

			if(command.equals("REGISTRAR")) {
				PanelRegistro registro = vista.getRegistro();
				Usuario newUser = registro.getUsuario();
				
				if(newUser == null)
					return;
				if(modelo.guardarUsuario(newUser)== 1) {
					registro.yaExisteUsuario();
					return;
				}
				userLogeado = newUser;
				modelo.crearActivosUsuario(userLogeado.getId());
				vista.mostrarPanel("LOGIN");
				vista.getLogin().notificar(vista.getLogin().REGISTRO_EXITOSO);
			}
		}
	}
	class ListenerLogin implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			String command = e.getActionCommand();
			if(command.equals("LOGIN")) {
				PanelLogin login = vista.getLogin();
				
				String email = login.getEmail();
				String password =login.getPassword();
				if(email.isBlank() || password.isBlank()) {
					login.notificar(login.CAMPOS_INCOMPLETOS);
					return;
				}
				Usuario user = modelo.obtenerUsuario(email);
				
				if(user == null) {
					login.notificar(login.EMAIL_CONTRA);
					return;
				}
				if(user.getPassword().equals(login.getPassword())) {
					userLogeado = user;
					vista.getActivos().actualizarTablaCriptos(modelo.listarActivos(userLogeado.getId()));
					vista.mostrarPanel("ACTIVOS");
					return;
				}
				login.notificar(login.EMAIL_CONTRA);
					
			}

			if(command.equals("REGISTRARSE")) {
				vista.mostrarPanel("REGISTRO");
			}
		}
	}
	
}