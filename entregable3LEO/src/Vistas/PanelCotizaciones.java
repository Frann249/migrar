package Vistas;

import javax.swing.*;
import javax.swing.border.Border;
import appModels.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.awt.*;

public class PanelCotizaciones extends JPanel {
    private final Color naranjaOscuro = new Color(255, 95, 0);
    private List<JButton> btnList;
    private List<JLabel> precios;
    private boolean configurado = false;
    //prueba
	public PanelCotizaciones() {
		setLayout(new GridBagLayout());
		btnList = new ArrayList<JButton>(15);
		precios = new ArrayList<JLabel>(15);
	}
	public void configurar(List<Moneda> monedas) {
		if(configurado)
			return;
		configurado = true;
        GridBagConstraints gbc = new GridBagConstraints();
        // Configuración del JLabel con ícono
        
        if(monedas.size() >0) {
        	 // Configuración para "nombre"
            gbc.gridx = 1; 
            gbc.gridy = 0;
            gbc.anchor = gbc.WEST;
            JLabel titulo = new JLabel("CRIPTO");
            titulo.setFont(new Font("Arial", Font.BOLD, 24));
            this.add(titulo, gbc);
           
            gbc.gridx = 2; 
            gbc.gridy = 0;
            JLabel precio = new JLabel("VALOR COMPRA");
            precio.setFont(new Font("Arial", Font.BOLD, 24));
            this.add(precio, gbc);

        }
        gbc.anchor = gbc.CENTER;
        for(int j=0; j<monedas.size(); j++) {
        Moneda monedaAct = monedas.get(j);
        
        // Configuración para el ícono
        ImageIcon image = new ImageIcon(monedaAct.getNombre_icono());
        Image scaledImage = image.getImage().getScaledInstance(65, 65, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JLabel logo = new JLabel();
        logo.setIcon(scaledIcon);
        int i = j+1;
        gbc.gridx = 0; 
        gbc.gridy = i; 
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH; 
        gbc.insets = new Insets(5, 5, 5, 5);
        this.add(logo, gbc);
        
        // Configuración para "nombre"
        gbc.gridx = 1; 
        gbc.gridy = i;
        JLabel cripto = new JLabel(monedaAct.getNombre()+"("+monedaAct.getNomenclatura()+")");
        cripto.setFont(new Font("Arial", Font.BOLD, 24));
        this.add(cripto, gbc);
        
     // Configuración para "Precio"
        gbc.gridx = 2; 
        gbc.gridy = i;
        JLabel precio = new JLabel("$"+String.valueOf(monedaAct.getValor_Dolar()));
        precio.setFont(new Font("Arial", Font.BOLD, 24));
        this.add(precio, gbc);
        precios.add(precio);
        
        // Configuración para "COMPRA"
        gbc.gridx = 3; 
        gbc.gridy = i;
        gbc.fill = gbc.NONE;
        JButton btnComprar = new JButton("COMPRAR");
        btnComprar.setActionCommand(monedaAct.getNomenclatura());
        //btnComprar.addActionListener(new adapterCompra());
        btnComprar.setBackground(Color.GREEN);
        btnComprar.setForeground(Color.WHITE);
        Border bordeInt = BorderFactory.createEmptyBorder(7, 7, 7, 7);
        Border bordeExt = BorderFactory.createLineBorder(Color.DARK_GRAY, 2);
        btnComprar.setBorder(BorderFactory.createCompoundBorder(bordeExt, bordeInt));
        this.add(btnComprar, gbc);
        
        // Configuración para "SWAP"
        gbc.gridx = 4; 
        gbc.gridy = i;
        gbc.fill = gbc.NONE;
        JButton btnSwap = new JButton("SWAP");
        btnSwap.setForeground(Color.white);
        btnSwap.setBackground(naranjaOscuro);
        btnSwap.setOpaque(true);
        btnSwap.setActionCommand("SWAP_"+monedaAct.getNomenclatura());
        bordeInt = BorderFactory.createEmptyBorder(7, 15, 7, 15);
        bordeExt = BorderFactory.createLineBorder(Color.DARK_GRAY, 2);
        btnSwap.setBorder(BorderFactory.createCompoundBorder(bordeExt, bordeInt));
        this.add(btnSwap, gbc);
        
        btnList.add(btnComprar);
        btnList.add(btnSwap);
        }
        this.setBorder(BorderFactory.createEmptyBorder(20, 10, 0, 10));
        this.setBackground(new Color(248, 255, 243));
        this.setVisible(true);
	}
	public List<JButton> getButtons(){
		return this.btnList;
	}

	/**
	 * @return the precios
	 */
	public List<JLabel> getPrecios() {
		return precios;
	}
	public void actualizarPrecios(List<Moneda> monedas) {
		for(int i =0; i<precios.size(); i++) {
			precios.get(i).setText(""+monedas.get(i).getValor_Dolar());
		}
	}
	/**
	 * @param precios the precios to set
	 */
	public void setPrecios(List<JLabel> precios) {
		this.precios = precios;
	}
}
