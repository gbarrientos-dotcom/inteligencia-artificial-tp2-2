package busqueda2;

public class Estado implements Comparable<Estado> {
	
	// posición actual
	private int posicion;
	
	// movimiento que hizo llegar a la posición actual
	private String movimiento;
	
	// estado anterior
	private Estado predecesor;
	
	// distancia a la media de correcciones
	private int distancia;
	
	// constructor
	public Estado(int posicion, String movimiento, Estado predecesor) {
		this.posicion=posicion;
		this.predecesor=predecesor;
		this.movimiento=movimiento;
		this.distancia = 100;
	}
	
	// métodos getters y setters
	
	public int getPosicion() {
		return posicion;
	}

	public void setPosicion(int posicion) {
		this.posicion = posicion;
	}

	public String getMovimiento() {
		return movimiento;
	}

	public void setMovimiento(String movimiento) {
		this.movimiento = movimiento;
	}

	public Estado getPredecesor() {
		return predecesor;
	}

	public void setPredecesor(Estado predecesor) {
		this.predecesor = predecesor;
	}

	public int getDistancia() {
		return distancia;
	}

	public void setDistancia(int distancia) {
		this.distancia = distancia;
	}

	// método para mover hacia la izquierda
	public Estado moverIzquierda(int lim_inferior, int cm) {
		Estado izquierda = null;
		int posicionMovimiento = this.posicion;
		if((posicionMovimiento - cm) >= lim_inferior) {
			int posicionResultado = posicionMovimiento - cm;
			izquierda = new Estado(posicionResultado,"MOVER_IZQUIERDA_"+cm, this);
		}
		return izquierda;
	}

	// método para mover hacia la derecha
	public Estado moverDerecha(int lim_superior, int cm) {
		Estado derecha = null;
		int posicionMovimiento = this.posicion;
		if( posicionMovimiento + cm <= lim_superior ) {
			int posicionResultado = posicionMovimiento + cm;
			derecha=new Estado(posicionResultado,"MOVER_DERECHA_"+cm, this);
		}
		return derecha;
	}

	// método para determinar la distancia media de correcciones
	public void calcularDistanciaMedia(int lim_inferior, int lim_superior ) {
		int media = (lim_superior + lim_inferior)/2;
		if (media > 0) {	 
			distancia = (media - posicion) > 0 ? media - posicion : (media - posicion)*(-1);
		}
	}
	
	@Override
	public String toString() {
		return "Estado [posicion=" + posicion + ", movimiento=" + movimiento + ", predecesor=" + predecesor
				+ ", distancia=" + distancia + "]";
	}

	// sobrescribir el método compareTo, si son iguales devuelve 0
	// si es menor que el argumento devuelve -1
	// caso contrario devuelve 1
	public int compareTo(Estado o) {
		if ( this.distancia == o.distancia ) return 0;
		else {
		if ( this.distancia < o.distancia ) return -1;
		else 
			return 1;
		}
	}
	
}
