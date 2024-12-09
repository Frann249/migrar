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

public class PanelLogin extends JPanel{
	private final Color colorCeleste= new Color(92,195,242); 
	private final Font fuenteTxt = new Font("Arial", Font.BOLD, 24);
	private final Font fuenteField= new Font("Arial", Font.ITALIC, 15);
	private JLabel labelAviso;
	private List<JButton> btnList;
	private List<JTextField> camposTexto;
	public static final int CAMPOS_INCOMPLETOS =0;
	public static final int EMAIL_CONTRA = 1;
	public static final int REGISTRO_EXITOSO = 2;
	public PanelLogin() {
		btnList = new ArrayList<JButton>(2);
		setLayout(new GridBagLayout());
		camposTexto = new ArrayList<JTextField>(2);
		GridBagConstraints gbc = new GridBagConstraints();
		
		JLabel nombre = new JLabel("E-Mail:");
		nombre.setFont(fuenteTxt);
		gbc.anchor = gbc.WEST;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(10,0,5,10);
		this.add(nombre, gbc);
		
		JTextField textEmail= new JTextField(20);
		textEmail.setFont(fuenteField);
		gbc.gridx = 1;
		gbc.gridy = 0;
		this.add(textEmail,gbc);
		camposTexto.add(textEmail);
		
		JLabel apellido = new JLabel("Password:");
		apellido.setFont(fuenteTxt);
		gbc.gridx = 0;
		gbc.gridy = 1;
		this.add(apellido, gbc);
		
		JPasswordField textPassword= new JPasswordField(20);
		textPassword.setFont(fuenteField);
		gbc.gridx = 1;
		gbc.gridy = 1;
		this.add(textPassword,gbc);
		camposTexto.add(textPassword);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setActionCommand("LOGIN");
        Border bordeInt = BorderFactory.createEmptyBorder(7, 7, 7, 7);
        Border bordeExt = BorderFactory.createLineBorder(Color.DARK_GRAY, 2);
        btnLogin.setBorder(BorderFactory.createCompoundBorder(bordeExt, bordeInt));
        btnLogin.setBackground(colorCeleste);
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFont(fuenteField);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
		gbc.gridheight = 1;
		gbc.anchor = gbc.CENTER;
		gbc.insets = new Insets(5,0,5,0);
		this.add(btnLogin, gbc);
		btnList.add(btnLogin);
		
		
		labelAviso = new JLabel(" ");
		labelAviso.setFont(fuenteField);
		gbc.gridwidth = 2;
		gbc.gridheight = 1;
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.anchor = gbc.CENTER;
		gbc.insets = new Insets(15,0,15,0);
		this.add(labelAviso, gbc);
		
		
		JLabel labelRegistro = new JLabel("¿Todavia no estas registrado?");
		labelRegistro.setFont(fuenteField);
		gbc.gridwidth = 2;
		gbc.gridheight = 1;
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.anchor = gbc.WEST;
		this.add(labelRegistro, gbc);
		
		JButton btnRegistrarse = new JButton("Registrarse");
		btnRegistrarse.setActionCommand("REGISTRARSE");
		btnRegistrarse.setBorder(BorderFactory.createCompoundBorder(bordeExt, bordeInt));
		btnRegistrarse.setBackground(colorCeleste);
		btnRegistrarse.setForeground(Color.WHITE);
		btnRegistrarse.setFont(fuenteField);
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
		gbc.gridheight = 1;
		gbc.insets = new Insets(0, 80, 0 ,0);
		gbc.anchor = gbc.CENTER;
		this.add(btnRegistrarse, gbc);
		btnList.add(btnRegistrarse);
		
		
		this.setVisible(true);
	}
	public void notificar(int tipo) {
		if(tipo == CAMPOS_INCOMPLETOS) {
			labelAviso.setText("Por favor, no deje campos en blanco.");
			labelAviso.setForeground(Color.RED);
			return;
		}
		if(tipo == EMAIL_CONTRA) {	
			labelAviso.setText("EMAIL O PASSWORD incorrecta.");
			labelAviso.setForeground(Color.RED);
			return;
		}
		if(tipo == REGISTRO_EXITOSO) {
			labelAviso.setForeground(colorCeleste);
			labelAviso.setText("Usuario Registrado Correctamente.");
		}
	}
	public String getEmail() {
		return camposTexto.get(0).getText();
	}
	public String getPassword() {
		return camposTexto.get(1).getText();
	}
	public List<JButton> getButtons(){
		return this.btnList;
	}
	
}
