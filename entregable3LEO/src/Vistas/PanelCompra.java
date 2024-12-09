package Vistas;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import appModels.*;

public class PanelCompra extends JPanel{
	private final Color colorCeleste= new Color(92,195,242); 
	private final Color colorVerde = new Color(125, 222, 118);
	private final Font fuenteTxt = new Font("Arial", Font.BOLD, 24);
	private final Font fuenteBtn= new Font("Arial", Font.BOLD, 15);
	private Cripto monedaDestino;
	private List<Activo> activos;
	private JTextField textCantCompra;
	private JLabel stock;
	private JLabel precio;
	private JLabel cantDestino;
	private boolean error = true;
	private List<JButton> btnList;
	private DefaultComboBoxModel<String> comboBoxModel;
	
	public PanelCompra() {
		btnList = new ArrayList<JButton>(2);
		comboBoxModel = new DefaultComboBoxModel<String>();
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = gbc.WEST;
        gbc.fill = gbc.NONE;

		JLabel textStock= new JLabel("Stock Disponible: ");
		textStock.setFont(fuenteTxt);
		gbc.gridx = 0; 
        gbc.gridy = 0; 
        gbc.anchor = gbc.WEST;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        this.add(textStock, gbc);
        
        stock = new JLabel("100");
        stock.setFont(fuenteTxt);
        gbc.gridx = 1; 
        gbc.gridy = 0; 
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.anchor = gbc.CENTER;
        this.add(stock, gbc);
        
        JLabel textPrecio = new JLabel("Precio de Compra: ");
        textPrecio.setFont(fuenteTxt);
        gbc.gridx = 0; 
        gbc.gridy = 1; 
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.anchor = gbc.WEST;
        this.add(textPrecio, gbc);
        
        precio = new JLabel();
        precio.setFont(fuenteTxt);
        gbc.gridx = 1; 
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.anchor = gbc.CENTER;
        this.add(precio, gbc);
        
        JLabel textCompra = new JLabel("Comprar con:");
        textCompra.setFont(fuenteTxt);
        gbc.gridx =0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.anchor = gbc.EAST;
        this.add(textCompra, gbc);
        
        textCantCompra = new JTextField(5);
        textCantCompra.setFont(fuenteTxt);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = gbc.CENTER;
        this.add(textCantCompra, gbc);
        
        JComboBox<String> opciones = new JComboBox<String>(comboBoxModel);
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.insets = new Insets(0,10,0,10);
        this.add(opciones, gbc);
        
        JButton btnConvertir = new JButton("Convertir");
        btnConvertir.setActionCommand("CONVERTIR");
        Border bordeInt = BorderFactory.createEmptyBorder(7, 7, 7, 7);
        Border bordeExt = BorderFactory.createLineBorder(Color.DARK_GRAY, 2);
        btnConvertir.setBorder(BorderFactory.createCompoundBorder(bordeExt, bordeInt));
        btnConvertir.setBackground(colorVerde);
        btnConvertir.setForeground(Color.WHITE);
        btnConvertir.setFont(fuenteBtn);
        gbc.gridx = 3;
        gbc.gridy = 2;
        
        this.add(btnConvertir, gbc);
        btnList.add(btnConvertir);
        
        JLabel textEquivale = new JLabel("equivale a:");
        textEquivale.setFont(fuenteTxt);
        gbc.anchor = gbc.WEST;
        gbc.gridx = 0;
        gbc.gridy = 3;
		this.add(textEquivale, gbc);
		
		cantDestino = new JLabel("");
        textEquivale.setFont(fuenteTxt);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
		this.add(cantDestino, gbc);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setActionCommand("CANCELAR");
		btnCancelar.setBorder(BorderFactory.createCompoundBorder(bordeExt, bordeInt));
		btnCancelar.setBackground(colorCeleste);
		btnCancelar.setForeground(Color.WHITE);
		btnCancelar.setFont(fuenteBtn);
		gbc.gridx = 1;
		gbc.gridy = 4;
		gbc.gridwidth = 1;
        gbc.gridheight = 1;
		gbc.anchor = gbc.CENTER;
		this.add(btnCancelar, gbc);
	    btnList.add(btnCancelar);
	        
		JButton btnRealizar = new JButton("Realizar Compra");
		btnRealizar.setActionCommand("REALIZAR");
        btnRealizar.setBorder(BorderFactory.createCompoundBorder(bordeExt, bordeInt));
        btnRealizar.setBackground(colorCeleste);
        btnRealizar.setForeground(Color.WHITE);
		btnRealizar.setFont(fuenteBtn);
        gbc.gridx = 2;
		gbc.gridy=4;
		gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.insets = new Insets(10,0,10,0);
        this.add(btnRealizar, gbc);
        btnList.add(btnRealizar);
        

	}

	public void convertir(Moneda monedaOrigen) {
		try {
		double cant = Double.valueOf(textCantCompra.getText());
		cantDestino.setForeground(Color.black);	
		cantDestino.setText(""+ (double) monedaOrigen.convertir(cant, monedaDestino) + monedaDestino.getNomenclatura());
		setError(false);
		} catch(NumberFormatException e) {
			cantDestino.setText("ERROR, SOLO INGRESE NUMEROS.");
			cantDestino.setForeground(Color.red);
			setError(true);
		}
		
	}
	public List<JButton> getButtons(){
		return btnList;
	}
	public String getActivoSelected() {
		return (String) comboBoxModel.getSelectedItem();
	}
	public void resetVentana() {
		textCantCompra.setText("");
		cantDestino.setText("");
		comboBoxModel.removeAllElements();
	}
	public void actualizarValores(Cripto cripto, List<Activo> activos) {
		this.monedaDestino = cripto;
		this.activos = activos;
		stock.setText("         "+cripto.getStock()+ " "+ cripto.getNombre()+" ("+cripto.getNomenclatura()+")");
		precio.setText("$"+ cripto.getValor_Dolar()+ " USD");
		for(Activo activoAct : activos) {
			comboBoxModel.addElement(activoAct.getMoneda().getNomenclatura());
		}
	}

	/**
	 * @return the error
	 */
	public boolean isError() {
		return error;
	}

	/**
	 * @param error the error to set
	 */
	public void setError(boolean error) {
		this.error = error;
	}
}
