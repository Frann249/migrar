package app;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import Comparators.CompCantActivo;
import Comparators.CompNMCTActivo;
import Comparators.CompNMCTMoneda;
import Comparators.CompStockCripto;
import Comparators.CompValorMoneda;
import appDAO.ActivoDAO;
import appDAO.FactoryDAO;
import appDAO.MonedaDAO;
import appDAO.PersonaDAO;
import appDAO.TransaccionDAO;
import appDAO.UsuarioDAO;
import appModels.Activo;
import appModels.Compra;
import appModels.Cripto;
import appModels.Fiat;
import appModels.Moneda;
import appModels.Swap;
import appModels.Transaccion;
import appModels.Usuario;

public class Modelo {
	private ActivoDAO activoDAO;
	private MonedaDAO monedaDAO;
	private TransaccionDAO transaccionDAO;
	private PersonaDAO personaDAO;
	private UsuarioDAO usuarioDAO;
	
	private CompNMCTActivo NMCTActivo;
	private CompCantActivo cantActivo;
	
	private CompStockCripto stockCripto;
	private CompValorMoneda valorMoneda;
	private CompNMCTMoneda NMCTMoneda;
	public static final int CRIPTO = 0;
	public static final int FIAT = 1;
	public Modelo(){
		activoDAO=FactoryDAO.getActivoDAO();
		monedaDAO = FactoryDAO.getMonedaDAO();
		transaccionDAO = FactoryDAO.getTransaccionDAO();
		personaDAO = FactoryDAO.getPersonaDAO();
		usuarioDAO = FactoryDAO.getUsuarioDAO();
		
		NMCTActivo = new CompNMCTActivo();
		cantActivo = new CompCantActivo();
		
		stockCripto = new CompStockCripto();
		valorMoneda = new CompValorMoneda();
		NMCTMoneda = new CompNMCTMoneda();
	
		if(monedaDAO.isEmpty()) {
			monedaDAO.guardarMoneda (new Cripto(".\\Logos\\Bitcoin_logo.png" , "bitcoin","BTC"));
			monedaDAO.guardarMoneda(new Cripto(".\\Logos\\Ethereum_logo.png" , "ethereum","ETH"));
			monedaDAO.guardarMoneda(new Cripto(".\\Logos\\Dogecoin_Logo.png" , "dogecoin","DOGE"));
			monedaDAO.guardarMoneda(new Cripto(".\\Logos\\Tether_logo.png" , "tether","USDT"));
			monedaDAO.guardarMoneda(new Cripto(".\\Logos\\Usdc_logo.png" , "usd-coin","USDC"));
			monedaDAO.guardarMoneda(new Fiat(".\\Logos\\Ars_logo.png" , "Peso Arg","ARS", 0.00092));
			monedaDAO.guardarMoneda(new Fiat(".\\Logos\\Usd_logo.png" , "Dolar","USD", 1));
		}
	}	
	public int crearActivosUsuario(int id_usuario) {
		Moneda monedaAct = this.obtenerMoneda("BTC");
		activoDAO.crearActivo(id_usuario, monedaAct);
		monedaAct = this.obtenerMoneda("DOGE");
		activoDAO.crearActivo(id_usuario, monedaAct);
		monedaAct = this.obtenerMoneda("ARS");
		activoDAO.crearActivo(id_usuario, monedaAct);
		monedaAct = this.obtenerMoneda("USD");
		activoDAO.crearActivo(id_usuario, monedaAct);
		return 1;
	}
	public void actualizarValores(List<Moneda> monedas) {
		for(Moneda moneda : monedas)
			monedaDAO.actualizarValor(moneda);
	}
	public Usuario obtenerUsuario(String email) {
		Usuario user = usuarioDAO.existeUsuario(email);
		if(user == null)
			return null;
		personaDAO.existePersona(user.getPersona());
		return user;
	}
	public int guardarUsuario(Usuario usuario){
		if(usuarioDAO.existeUsuario(usuario.getEmail()) != null )
			return 1;
		personaDAO.guardarPersona(usuario.getPersona());
		usuarioDAO.guardarUsuario(usuario);
		return 0;
	}
	
	public List<Moneda> listarMonedasTipo(int tipo){
		if(tipo == CRIPTO)
			return monedaDAO.listarCripto();
		if(tipo == FIAT)
			return monedaDAO.listarFiat();
		return null; 
		
	}
	
	public List<Activo> listarActivosTipo(int tipo, int id_usuario){
		List<Activo> activos = null;
		if(tipo == FIAT)
			activos = activoDAO.listarActivosFiat(id_usuario);
		if(tipo == CRIPTO)
			activos= activoDAO.listarActivosCripto(id_usuario);
		for(Activo activoAct : activos)
			monedaDAO.obtenerMonedaID(activoAct.getMoneda());		
		return activos;
	}

	
 	public List<Moneda> listarMonedas(int opcion){
		List<Moneda> lista = monedaDAO.listarMonedas();	
    	switch (opcion){
      	case 1:
        	Collections.sort(lista, valorMoneda);
          	break;
      	case 2:
      		Collections.sort(lista, NMCTMoneda);
        	break;
      	default:
    	}
    	return lista;
	}
 	
 	public List<Activo> listarActivos(int id_usuario){
 		List<Activo> listaActivos = activoDAO.listarActivos(id_usuario);
 		for(Activo activoAct : listaActivos)
			monedaDAO.obtenerMonedaID(activoAct.getMoneda());
		
 		return listaActivos;
	}
 	
	public Moneda obtenerMoneda(String nomenclatura) {
		return monedaDAO.existeMoneda(nomenclatura);
	}

 	/*
	public List<Moneda> listarCripto(int opcion){
		List<Moneda> lista = monedaDAO.listarCripto();	
		List<Cripto> listaCripto = new LinkedList<Cripto>();
		for(Moneda monedaAct :lista) {
			listaCripto.add((Cripto) monedaAct);
		}
		switch (opcion){
      	case 1:
        	Collections.sort(listaCripto, stockCripto);
          	break;
      	case 2:
      		Collections.sort(listaCripto, NMCTMoneda);
        	break;
      	default:
    	}
		return lista;
	}
	*/
	public boolean generarStocks() {
		List<Moneda> monedas = monedaDAO.listarMonedas();
		if(monedas == null)
			return false;
		for(Moneda moneda: monedas) {
			if(moneda instanceof Cripto) {
				Cripto cripto = (Cripto) moneda;
				cripto.setStock(Math.random()*100);
				if(!monedaDAO.actualizarStock(cripto))
					return  false;
			}
		}
		return true;
	}
	/*
	public int generarActivo(Activo activo) {
		Moneda moneda = monedaDAO.existeMoneda(activo.getNomenclatura());
		if(moneda == null) 
			return 2; //la nomenclatura del activo no corresponde a una moneda.
		
		Activo activoAux = activoDAO.existeActivo(moneda);
		
		if(activoAux == null) { // si el activo no existe
			if(activoDAO.crearActivo(activo, moneda)) 
				return 0; // el activo se crea correctamente
			else
				return 3; //error al crear el activo
		} 
		else { //si el activo si existe
			if(activoDAO.actualizarActivo(activo, moneda)) 
				return 1; //se ha actualizado con exito la cantidad del activo
			else
				return 3;// ha fallado la actualizacion del activo
		}
		
	}
		
	public int crearActivos(int id_Usuario) {
		List<Moneda> monedas = monedaDAO.listarMonedas();
		int  cant = (int)(Math.random()* monedas.size() - 0.001) +1;
		for(int i = 0; i< cant; i++){
			
		}
		return 1;
	}
	public List<Activo> listarActivos(int opcion){
		List<Activo> lista = activoDAO.listarActivos();
		switch (opcion){
      	case 1:
      		Collections.sort(lista, cantActivo);
          	break;
      	case 2:
      		Collections.sort(lista, NMCTActivo);
        	break;
      	default:
    	}
		return lista;
	}
	*/
	/*
	public Activo compraCripto (String fiatOrigenNMCT, String criptoDestinoNMCT, double cantidadFiat) {
		Moneda fiat= monedaDAO.existeMoneda(fiatOrigenNMCT);
		if(fiat == null) {
			return (new Activo("_ERROR_", 1));  // La nomenclatura pasada como origen no existe.
		}
		if(!(fiat instanceof Fiat)) 
			return (new Activo("_ERROR_", 2)); //La nomenclatura de origen no corresponde a un fiat.

		Activo activoFiat= activoDAO.existeActivo(fiat);
		
		if(activoFiat == null)
			return (new Activo("_ERROR_", 3)); //El activo fiat aun no existe.
	
		
		Moneda cripto = monedaDAO.existeMoneda(criptoDestinoNMCT);
		if(cripto == null)
			return (new Activo("_ERROR_", 4)); //La moneda destino pasada no existe.
		if(!(cripto instanceof Cripto)) 	
			return (new Activo("_ERROR_", 5)); //La nomenclatura de destino no corresponde a un cripto		
		
		if(activoFiat.getCantidad() < cantidadFiat) {
			return (new Activo("_ERROR_", 10)); //saldo fiat insuficiente al ingresado
		}
		
		double cantidadCripto= fiat.convertir(cantidadFiat, cripto);
		
		Cripto criptoDestino = (Cripto) cripto;
		if(criptoDestino.getStock() < cantidadCripto)
			return (new Activo("_ERROR_", 11)); //stock insuficiente al requerido
		
		criptoDestino.setStock(criptoDestino.getStock() - cantidadCripto);
		if(!monedaDAO.actualizarStock(criptoDestino))
			return (new Activo("_ERROR_", 6));//error al actualizar stock de la criptomoneda.
		
		activoFiat.setCantidad(activoFiat.getCantidad() - cantidadFiat);
		Activo activoCripto= activoDAO.existeActivo(criptoDestino);
		if(activoCripto == null) {
			activoCripto = new Activo(criptoDestino.getNomenclatura(), cantidadCripto);
			if(!(activoDAO.crearActivo(activoCripto, cripto) && activoDAO.actualizarActivo(activoFiat, fiat)))
				return (new Activo("_ERROR_", 7));
		} 
		  else {
			activoCripto.setCantidad(activoCripto.getCantidad() + cantidadCripto);
			if(!(activoDAO.actualizarActivo(activoCripto, cripto) && activoDAO.actualizarActivo(activoFiat, fiat)))
				return (new Activo("_ERROR_", 7)); //error al actualizar los valores en los activos
		}
		LocalDateTime fecha= LocalDateTime.now();
		
		// Define el formato 
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

		// Convierte LocalDateTime a String 
		String fechaString = fecha.format(formato);
		
		Transaccion compra = new Compra(fechaString, fiat.getNomenclatura(), criptoDestino.getNomenclatura(), cantidadFiat, cantidadCripto);
		transaccionDAO.guardarTransaccion(compra);
		Activo activoInfo= new Activo(cripto.getNomenclatura(), cantidadCripto);
		return activoInfo; // funcion finalizo con exito
							// devuelve el modificado para la impresion de resultados del mismo
	}
	
	public int realizarSwap(String criptoOrigenNMCT, double cantidadOrigen, String criptoDestinoNMCT) {
		Moneda monedaOrigen = monedaDAO.existeMoneda(criptoOrigenNMCT);
		if(monedaOrigen == null) {
			return 1;
		}
		if(!(monedaOrigen instanceof Cripto)) 
			return 2; //La nomenclatura de origen no corresponde a un cripto.
		Activo activoOrigen = activoDAO.existeActivo(monedaOrigen);
		if(activoOrigen == null)
			return 3; //fallo a la hora de buscar el activo origen.
		
		
		Moneda monedaDestino = monedaDAO.existeMoneda(criptoDestinoNMCT);
		if(monedaDestino == null)
			return 4; //La moneda destino pasada no existe.
		if(!(monedaDestino instanceof Cripto)) 	
			return 5; //La nomenclatura de destino no corresponde a un cripto		
		
		Activo activoDestino = activoDAO.existeActivo(monedaDestino);
		if(activoDestino == null)
			return 6;//fallo a la hora de buscar el activo destino.
		
		if(activoOrigen.getCantidad() < cantidadOrigen) {
			return 10; //saldo insuficiente.
		}
		
		double cantidadDestino = monedaOrigen.convertir(cantidadOrigen, monedaDestino);
		Cripto criptoDestino = (Cripto) monedaDestino;
		
		if(criptoDestino.getStock() < cantidadDestino)
			return 11; //stock insuficiente.
		criptoDestino.setStock(criptoDestino.getStock()-cantidadDestino);
		if(!monedaDAO.actualizarStock(criptoDestino))
			return 7;//error al actualizar stock.
		
		activoOrigen.setCantidad(activoOrigen.getCantidad()- cantidadOrigen);
		activoDestino.setCantidad(activoDestino.getCantidad() + cantidadDestino);
		
		LocalDateTime fecha= LocalDateTime.now();

    	// Define el formato 
    	DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    	// Convierte LocalDateTime a String 
    	String fechaString = fecha.format(formato);
		Swap swap = new Swap(fechaString, criptoOrigenNMCT, criptoDestinoNMCT, cantidadOrigen, cantidadDestino);
		transaccionDAO.guardarTransaccion(swap);
		if(!(activoDAO.actualizarActivo(activoDestino, monedaDestino) && activoDAO.actualizarActivo(activoOrigen, monedaOrigen)))
			return 8; //error al actualizar los valores en los activos
		return 0;
	}
	*/
}
