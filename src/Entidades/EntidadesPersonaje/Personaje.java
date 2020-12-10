package Entidades.EntidadesPersonaje;

import Entidades.Entidad;

public abstract class Personaje extends Entidad{	
	
	/** Carga viral del personaje (del 0 al 100).*/
	protected int carga_viral; 
	//protected int danio; 
	protected boolean estaVivo;
	
	public void setEstaVivo(boolean vivo) {
		
		estaVivo = vivo;
	}
	
	public boolean getEstaVivo() {
		
		return estaVivo;
	}
	
	public void setCargaViral(int cv) {
		
		carga_viral = cv;
	}

	public int getCargaViral() {
		
		return carga_viral;
	}
	
	public abstract void morir();

}
