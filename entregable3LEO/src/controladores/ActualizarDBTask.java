package controladores;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.TimerTask;

import org.json.JSONObject;

import app.Modelo;
import appModels.Moneda;

public class ActualizarDBTask extends TimerTask{
	private static final String URL_API = "https://api.coingecko.com/api/v3/simple/price?ids=bitcoin,ethereum,usd-coin,tether,dogecoin&vs_currencies=usd";
	private Modelo modelo;
	private HttpRequest solicitud;
	HttpClient cliente;
	public ActualizarDBTask(Modelo modelo){
		this.modelo = modelo;
		solicitud = HttpRequest.newBuilder().uri(URI.create(URL_API)).GET().build();
		cliente = HttpClient.newHttpClient();
	}
	@Override
	public void run() {
		List<Moneda> monedas = modelo.listarMonedasTipo(modelo.CRIPTO);

	       try {
	           HttpResponse<String> respuesta = cliente.send(solicitud, HttpResponse.BodyHandlers.ofString());
	           if (respuesta.statusCode() == 200) {
	               ActualizarPrecios(respuesta.body(), monedas);
	               modelo.actualizarValores(monedas);
	           } else {
	               System.out.println("Error: " + respuesta.statusCode());
	           }
	       } catch (IOException | InterruptedException e) {
	           e.printStackTrace();
	       }
	}
	   private void ActualizarPrecios(String cuerpoRespuesta, List<Moneda> monedas) {
	       JSONObject json = new JSONObject(cuerpoRespuesta);
	       for(Moneda moneda : monedas)	
	    	   moneda.setValor_Dolar(json.getJSONObject(moneda.getNombre()).getDouble("usd"));
	   }
}
