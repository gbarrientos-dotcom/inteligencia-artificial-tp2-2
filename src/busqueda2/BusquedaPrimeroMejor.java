package busqueda2;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class BusquedaPrimeroMejor {
	
	int lim_inf;
	int lim_sup;
	int cm;

	public PriorityQueue<Estado> listaEstados;
	public ArrayList<Estado> historialEstados;
	
	public Estado inicial;
	public Estado objetivo;
	public Estado actual;
	
	public boolean encontrada;
	
	//constructor
	public BusquedaPrimeroMejor(int origen, int destino, int inf, int sup, int mag) {
		listaEstados = new PriorityQueue<>();
		historialEstados = new ArrayList<>();
		encontrada = false;
		inicial = new Estado(origen, "INICIO", null);
		objetivo = new Estado(destino, "", null);
		lim_inf = inf;
		lim_sup = sup;
		cm = mag;
	}

	// método que realiza la búsqueda
	public void busqueda() {
		insertarEstado(inicial);
		while (!listaEstados.isEmpty() && !encontrada){
				actual = listaEstados.poll(); //saca el menor
				if (actual.getPosicion() == objetivo.getPosicion()) {
					objetivo.setMovimiento(actual.getMovimiento());
					objetivo.setPredecesor(actual.getPredecesor());
					construirSolucion();
					encontrada=true;
				} else {
				expandirEstado(actual);
			}
		}
	}
		
	private void expandirEstado(Estado e) {
		desplazarIzquierda(e);
		desplazarDerecha(e);
	}

	private void desplazarIzquierda(Estado e) {
		Estado izquierda = e.moverIzquierda(lim_inf, cm);
		if (izquierda !=null) {
			insertarEstado(izquierda);
		}
	}

	private void desplazarDerecha(Estado e) {
		Estado derecha = e.moverDerecha(lim_sup, cm);
		if (derecha !=null) {
			insertarEstado(derecha);
		}
	}
	
	private void insertarEstado(Estado nuevo){
		nuevo.calcularDistanciaMedia(lim_inf, lim_sup);
		if (!historialEstados.contains(nuevo)){
			historialEstados.add(nuevo);
			listaEstados.add(nuevo);
		}
	}
	
	private void construirSolucion() {
		ArrayList<Estado> solucion = new ArrayList<>();
		solucion.add(objetivo);
		Estado predecesor = objetivo.getPredecesor();
		while (predecesor != null){
			solucion.add(predecesor);
			predecesor=predecesor.getPredecesor();
		}
		System.out.println("\nSolucion con búsqueda primero el mejor: " + "\n");
		for (int i = solucion.size()-1 ; i >= 0 ; i--) {
			System.out.print("Paso " + solucion.get(i).getMovimiento() + ": ");
			System.out.println(solucion.get(i).toString());
		}
		//objetivo.predecesor=null;
	}

	// método para imprimir el historial de estados expandidos
	public void imprimirHistorialEstados() {
		System.out.println("\nHistorial de Estados:" + "\n");
		for (Estado e : historialEstados) {
		     System.out.println(e);
		}
	}
	
}

