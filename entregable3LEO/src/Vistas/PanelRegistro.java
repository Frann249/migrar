package Vistas;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;

import appModels.Persona;
import appModels.Usuario;

public class PanelRegistro extends JPanel{
	private final Color colorCeleste= new Color(92,195,242); 
	private final Font fuenteTxt = new Font("Arial", Font.BOLD, 24);
	private final Font fuenteField= new Font("Arial", Font.ITALIC, 15);
	private JLabel labelAviso;
	private List<JButton> btnList;
	
	private List<JTextField> camposTexto;
	private JCheckBox checkTYC;
	
	public PanelRegistro() {
		btnList = new ArrayList<JButton>(2);
		setLayout(new GridBagLayout());
		camposTexto = new ArrayList<JTextField>(4);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = gbc.WEST;
		gbc.gridx = 0;
		gbc.gridy = 0;
		JLabel nombre = new JLabel("Nombres: ");
		nombre.setFont(fuenteTxt);
		this.add(nombre, gbc);
		
		JTextField textNombre = new JTextField(20);
		textNombre.setFont(fuenteField);
		gbc.gridx = 1;
		gbc.gridy = 0;
		this.add(textNombre,gbc);
		camposTexto.add(textNombre);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		JLabel apellido = new JLabel("Apellidos: ");
		apellido.setFont(fuenteTxt);
		this.add(apellido, gbc);
		
		JTextField textApellido = new JTextField(20);
		textApellido.setFont(fuenteField);
		gbc.gridx = 1;
		gbc.gridy = 1;
		this.add(textApellido,gbc);
		camposTexto.add(textApellido);
		
		gbc.gridx = 0;
		gbc.gridy = 2;
		JLabel email = new JLabel("E-Mail: ");
		email.setFont(fuenteTxt);
		this.add(email, gbc);
		
		JTextField textEmail = new JTextField(20);
		textEmail.setFont(fuenteField);
		gbc.gridx = 1;
		gbc.gridy = 2;
		this.add(textEmail,gbc);
		camposTexto.add(textEmail);
		
		JLabel Password = new JLabel("Password: ");
		Password.setFont(fuenteTxt);
		gbc.gridx = 0;
		gbc.gridy = 3;
		this.add(Password, gbc);
		
		JPasswordField textPassword = new JPasswordField(20);
		textPassword.setFont(fuenteField);
		gbc.gridx = 1;
		gbc.gridy = 3;
		this.add(textPassword,gbc);
		camposTexto.add(textPassword);
		
		
		checkTYC = new JCheckBox("Acepto términos y condiciones", false);
		checkTYC.setFont(fuenteField);
		gbc.gridwidth = 2;
		gbc.gridheight = 1;
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.anchor = gbc.CENTER;
		gbc.insets = new Insets(5,0,5,0);
		this.add(checkTYC, gbc);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.setActionCommand("VOLVER");
		Border bordeInt = BorderFactory.createEmptyBorder(7, 7, 7, 7);
        Border bordeExt = BorderFactory.createLineBorder(Color.DARK_GRAY, 2);
		btnVolver.setBorder(BorderFactory.createCompoundBorder(bordeExt, bordeInt));
		btnVolver.setBackground(colorCeleste);
		btnVolver.setForeground(Color.WHITE);
		btnVolver.setFont(fuenteField);
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.anchor = gbc.EAST;
		this.add(btnVolver, gbc);
		btnList.add(btnVolver);
		
		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.setActionCommand("REGISTRAR");
        btnRegistrar.setBorder(BorderFactory.createCompoundBorder(bordeExt, bordeInt));
        btnRegistrar.setBackground(colorCeleste);
        btnRegistrar.setForeground(Color.WHITE);
        btnRegistrar.setFont(fuenteField);
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.anchor = gbc.CENTER;
		gbc.insets = new Insets(0,0,0,0);
		this.add(btnRegistrar, gbc);
		btnList.add(btnRegistrar);
		
		labelAviso= new JLabel(" ");
		labelAviso.setFont(fuenteField);
		gbc.gridwidth = 2;
		gbc.gridheight = 1;
		gbc.gridx = 0;
		gbc.gridy = 6;
		gbc.anchor = gbc.CENTER;
		gbc.insets = new Insets(10,0,0,0);
		this.add(labelAviso, gbc);
		this.setVisible(true);
	}
	public boolean aceptoTYC() {
		return checkTYC.isSelected();
	}
	public List<JButton> getButtons(){
		return this.btnList;
	}

	public void yaExisteUsuario() {
		labelAviso.setForeground(Color.RED);
		labelAviso.setText("El correo asociado ya esta registrado.");
	}
	public Usuario getUsuario() {
		if(!this.aceptoTYC()) {
			labelAviso.setText("debe aceptar los terminos y condiciones");
			labelAviso.setForeground(Color.RED);
			return null;
		}
		String nombre = camposTexto.get(0).getText();
		String apellido = camposTexto.get(1).getText();
		String email = camposTexto.get(2).getText();
		String password = camposTexto.get(3).getText();
		
		if(nombre.isBlank()|| apellido.isBlank()|| email.isBlank() || password.isBlank()) {
			labelAviso.setText("Por favor, no deje campos en blanco.");
			labelAviso.setForeground(Color.RED);
			return null;
		}
		
		return new Usuario(new Persona(nombre, apellido) ,email, password);
	}
}
	
