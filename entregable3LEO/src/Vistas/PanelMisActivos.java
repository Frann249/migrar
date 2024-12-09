package Vistas;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import appDAO.ActivoDAOjdbc;
import appDAO.FactoryDAO;
import appDAO.MonedaDAOjdbc;
import appModels.Activo;
import appModels.Moneda;

public class PanelMisActivos extends JPanel {
	private List<JButton> btnList;
    private final Font fuenteS = new Font("Arial", Font.PLAIN, 12);
    private final Font fuenteM = new Font("Arial", Font.PLAIN, 16);
    private final Font fuenteL = new Font("Arial", Font.BOLD, 20);
    private JButton btnGenerarDatos;
    private JButton btnExportarCSV;
    private JTable tablaCriptos;
    public PanelMisActivos() {
        btnList = new ArrayList<JButton>(15);
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Colores personalizados
        Color colorCeleste = new Color(92, 195, 242);
        Color colorRojo = new Color(255, 69, 58);
        
        // Sección superior: Logo, nombre y botón
        // Redimensionar el logo del usuario
        ImageIcon iconoPerfil = new ImageIcon(new ImageIcon("Logos/perfil.png").getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH));
        JLabel lblLogoUsuario = new JLabel(iconoPerfil); // Logo usuario
    
        // Etiqueta del nombre
        JLabel lblNombreUsuario = new JLabel("Nombre Apellido");
        lblNombreUsuario.setFont(fuenteM);
    
        // Botón de cerrar sesión
        JButton btnCerrarSesion = new JButton("Cerrar sesión");
        btnCerrarSesion.setBackground(colorCeleste);
        btnCerrarSesion.setForeground(Color.WHITE);
    
        // Configuración del layout
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridx = 1;
        gbc.gridy = 0;
        this.add(lblLogoUsuario, gbc);
    
        gbc.gridx = 2;
        gbc.anchor = GridBagConstraints.WEST;
        this.add(lblNombreUsuario, gbc);
    
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.SOUTH;
        this.add(btnCerrarSesion, gbc);

        // Botón Generar Datos de Prueba
        btnGenerarDatos = new JButton("Generar Datos de Prueba");
        btnGenerarDatos.setActionCommand("GENERAR_DATOS");
        btnGenerarDatos.setBackground(colorRojo);
        btnGenerarDatos.setForeground(Color.WHITE);
        btnGenerarDatos.setFont(fuenteS);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.WEST;
        this.add(btnGenerarDatos, gbc);

        // Balance
        JLabel lblIconoBalance = new JLabel(new ImageIcon("Logos/balance.png"));
        JLabel lblBalance = new JLabel("Balance: ARS 8'000,000.39");
        lblBalance.setFont(fuenteL);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        this.add(lblIconoBalance, gbc);

        gbc.gridx = 1;
        this.add(lblBalance, gbc);

     // Inicializa la tabla
        String[] columnas = {"", "Cripto", "Monto"};
        Object[][] datos = {};  // Inicializa con datos vacíos

        tablaCriptos = new JTable(new DefaultTableModel(datos, columnas) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnIndex == 0 ? ImageIcon.class : String.class;
            }
        });

        tablaCriptos.setRowHeight(40);
        tablaCriptos.setFont(fuenteM);
        JScrollPane scrollTabla = new JScrollPane(tablaCriptos);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(scrollTabla, gbc);

        // Botón Exportar CSV
        btnExportarCSV = new JButton("Exportar como CSV");
        btnExportarCSV.setActionCommand("EXPORTAR_CSV");
        btnExportarCSV.setBackground(colorCeleste);
        btnExportarCSV.setForeground(Color.WHITE);

        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        this.add(btnExportarCSV, gbc);
        
        // Botones inferiores
        JButton btnMisOperaciones = new JButton("Mis Operaciones");
        JButton btnCotizaciones = new JButton("Cotizaciones");

        // Crear iconos redimensionados
        ImageIcon iconoOperaciones = new ImageIcon(new ImageIcon("Logos/misOperaciones.png").getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH));
        ImageIcon iconoCotizaciones = new ImageIcon(new ImageIcon("Logos/cotizaciones.png").getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH));

        // Asignar los iconos a los botones
        btnMisOperaciones.setIcon(iconoOperaciones);
        btnCotizaciones.setIcon(iconoCotizaciones);

        // Ajustar posición del texto e ícono
        btnMisOperaciones.setHorizontalTextPosition(SwingConstants.RIGHT); // Texto a la derecha del ícono
        btnMisOperaciones.setVerticalTextPosition(SwingConstants.CENTER);  // Texto centrado verticalmente

        btnCotizaciones.setHorizontalTextPosition(SwingConstants.RIGHT);
        btnCotizaciones.setVerticalTextPosition(SwingConstants.CENTER);

        // Configuración de colores
        btnMisOperaciones.setBackground(colorCeleste);
        btnCotizaciones.setBackground(colorCeleste);
        btnMisOperaciones.setForeground(Color.WHITE);
        btnCotizaciones.setForeground(Color.WHITE);

        // Posicionar botones
        gbc.gridx = 0;
        gbc.gridy = 5;
        this.add(btnMisOperaciones, gbc);

        gbc.gridx = 2;
        this.add(btnCotizaciones, gbc);
        
        btnList.add(btnCerrarSesion);
        btnList.add(btnGenerarDatos);
        btnList.add(btnMisOperaciones);
        btnList.add(btnExportarCSV);
        btnList.add(btnCotizaciones);
    }
    
    public List<JButton> getButtons(){
    	return this.btnList;
    }

    // Métodos específicos para cada acción
//    public void generarDatosDePrueba() {
//        // Simulando la lógica para generar los datos de prueba
//    	System.out.println("Generando datos de prueba...");
//        // Llamamos a la función para actualizar la tabla
//        if (actualizarTablaCriptos()) {
//            mostrarMensaje("Generación de datos de prueba exitosa.", "Éxito");
//        } else {
//            mostrarMensaje("Hubo un error al generar los datos de prueba.", "Error");
//        }
//    }

    public void exportarComoCSV() {
        // Simulando la lógica para exportar los datos
        boolean exito = true; // Cambiar esta variable según la lógica real

        if (exito) {
            mostrarMensaje("Exportación a CSV exitosa.", "Éxito");
        } else {
            mostrarMensaje("Hubo un error al exportar los datos a CSV.", "Error");
        }
    }

    // Método para mostrar los mensajes
    private void mostrarMensaje(String mensaje, String tipo) {
        int tipoMensaje = tipo.equals("Éxito") ? JOptionPane.INFORMATION_MESSAGE : JOptionPane.ERROR_MESSAGE;
        JOptionPane.showMessageDialog(this, mensaje, tipo, tipoMensaje);
    }
    
    public boolean actualizarTablaCriptos(List<Activo> listaActivos) {
    	
      
        // Creamos una lista para almacenar las filas de la tabla
        List<Object[]> datosTabla = new LinkedList<>();

        // Iteramos sobre los activos
        for (Activo activo : listaActivos) {
        	Moneda moneda = activo.getMoneda();
            if (moneda != null) {
                // Agregamos la fila con el icono, nombre de la moneda y la cantidad
                datosTabla.add(new Object[]{
                    new ImageIcon(new ImageIcon(moneda.getNombre_icono()).getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH)),  // Icono
                    moneda.getNombre(),  // Nombre de la moneda
                    activo.getCantidad()  // Cantidad del activo
                });
            }
        }

        // Convertimos la lista de filas a un array bidimensional
        Object[][] datos = new Object[datosTabla.size()][3];
        for (int i = 0; i < datosTabla.size(); i++) {
            datos[i] = datosTabla.get(i);
        }

        // Establecemos los datos en el modelo de la tabla
        String[] columnas = {"", "Cripto", "Monto"};
        DefaultTableModel modelo = new DefaultTableModel(datos, columnas) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnIndex == 0 ? ImageIcon.class : String.class;
            }
        };
     // Establecer el modelo actualizado a la tabla
        tablaCriptos.setModel(modelo);  // Actualizamos la tabla con el nuevo modelo

        // Revalidamos para asegurarnos de que la tabla se actualiza correctamente
        tablaCriptos.revalidate();
        return true;
        
//        tablaCriptos.setModel(modelo);  // Actualizamos la tabla con el nuevo modelo
//        tablaCriptos.revalidate();  // Revalidamos para asegurarnos de que la tabla se actualiza correctamente
    }
}

 

