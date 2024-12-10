package Vistas;


import java.awt.*;


import javax.swing.*;



public class Vista extends JFrame {
    private JPanel mainPanel; // Panel principal que contendrá los subpaneles
    private CardLayout cardLayout; // Para cambiar entre paneles
    private PanelCotizaciones cotizaciones;
    private PanelCompra compra;
    private PanelMisActivos activos;
    private PanelRegistro registro;
    private PanelLogin login;
    // Constructor
    public Vista() {
        // Configuración del JFrame
    	Image image = new ImageIcon("./Logos/App_Logo.png").getImage();
        this.setIconImage(image);
    	setTitle("Gestión de Paneles");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Inicialización del CardLayout y el panel principal
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Crear los subpaneles
        cotizaciones = new PanelCotizaciones();
        compra = new PanelCompra(); 
        activos = new PanelMisActivos();
        registro = new PanelRegistro();
        login = new PanelLogin();
        // Añadir subpaneles al CardLayout
        mainPanel.add(cotizaciones, "COTIZACION");
        mainPanel.add(activos, "ACTIVOS");
        mainPanel.add(compra, "COMPRAR");
        mainPanel.add(registro, "REGISTRO");        
        mainPanel.add(login, "LOGIN");
        add(mainPanel, BorderLayout.CENTER);
        setLocationRelativeTo(null);
        this.setResizable(true);
    }

    // Método para mostrar un panel específico
    public void mostrarPanel(String nombrePanel) {
    	if(nombrePanel.equals("LOGIN")) {
    		this.setSize(500,500);
    		this.setTitle("Bienvenido a la billetera virtual");
    	}
    	if(nombrePanel.equals("REGISTRARSE")) {
    		this.setSize(500,500);
    		this.setTitle("Billetera Virtual - Registrarse");
    	}
    	if(nombrePanel.equals("COTIZACION")) {
    		this.setSize(780, 500);
    		this.setTitle("Billetera Virtual - Cotizaciones");
    	}
    	if(nombrePanel.equals("COMPRAR")) {
    		this.setSize(780, 500);
    		this.setTitle("Billetera Virtual - Comprar Cripto");
    	}
    	if(nombrePanel.equals("ACTIVOS")) {
    		this.setSize(800,950);
    		this.setTitle("Billetera Virtual - Tus Activos");
    	}    	
        cardLayout.show(mainPanel, nombrePanel);
    }

    public PanelLogin getLogin() {
    	return this.login;
    }
    public PanelCompra getCompra() {
    	return this.compra;
    }
    
	/**
	 * @return the cotizaciones
	 */
	public PanelCotizaciones getCotizaciones() {
		return cotizaciones;
	}

	/**
	 * @param cotizaciones the cotizaciones to set
	 */
	public void setCotizaciones(PanelCotizaciones cotizaciones) {
		this.cotizaciones = cotizaciones;
	}

	public PanelRegistro getRegistro() {
		return registro;
	}

	public void setRegistro(PanelRegistro registro) {
		this.registro = registro;
	}
	// Método para obtener el panel de "Mis Activos"
    public PanelMisActivos getActivos() {
        return activos;
    }

}
