package Logica;


import java.awt.Rectangle;
import java.awt.geom.Area;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.Timer;

import Entidades.Entidad;
import Entidades.EntidadesPersonaje.Infectado;
import Entidades.EntidadesPersonaje.Jugador;
import Main.MapaGUI;


public class Juego {
	
	protected Nivel nivel;
	protected List<Entidad> entidades;
	protected LinkedList<Entidad> aEliminar;
	protected static Juego juego;
	protected MapaGUI gui; 
	protected boolean hayCuarentena;
	
	private Juego() { 
		
		//nivel = new Nivel();
		entidades = new LinkedList<Entidad>(); 
		aEliminar = new LinkedList<Entidad>();
		hayCuarentena = false;	
		addEntidadEnJuego(Jugador.instancia());
	}
	
    public static Juego instancia() {
    	
        if(juego == null)       	
            juego = new Juego();
 
        
        return juego;
    }
    
	public void removeEntidadEnJuego(Entidad e) {
		
		entidades.remove(e);
	}
	
	public void setNivel(Nivel n) {
		
		nivel = n;
	}
	
	public void addEntidadAEliminar(Entidad e) {
		
		aEliminar.add(e);
	}
	
	public void addEntidadEnJuego(Entidad e) {
		
		entidades.add(e);
    }
	
	public List<Entidad> getEntidadesEnJuego() {
		
		return entidades;
	}
	
	public List<Entidad> getEntidadesAEliminar(){
		
		return aEliminar; 
	}
	
	public Nivel getNivel() {
		
		return nivel;
	}
	
	public void setHayCuarentena(boolean hay) {
		
		hayCuarentena = hay;
	}
	
	public boolean getHayCuarente() {
		
		return hayCuarentena;
	}
	
	public void setGUI(MapaGUI gui) {
		
		this.gui = gui;
	}
	
	public MapaGUI getGUI() {		
		
		return gui;
	}
	
 	public void accionar() {
 		
 
 		for(int i = 0; i < entidades.size();i++) {

			Entidad e1 = entidades.get(i);
			
			for(int j = 0; j < entidades.size();j++) {
				
				Entidad e2 = entidades.get(j);
				
				if(!e1.equals(e2) && verificarColision(e1,e2)) {
					
					e1.aceptar(e2.getVisitor());
				}				
			}			
			e1.accionar();
			
 		}	
 		
 		for(int h = 0; h< aEliminar.size(); h++) {
 			
 			Entidad entidad = aEliminar.getFirst();
 			
 			aEliminar.remove(entidad); 
 			
 			gui.insertarEntidad(entidad.getEntidadGrafica().getLabel());
 		}
 		
 		chequearCambiodeNivel();
 	}
 		
 	private boolean verificarColision(Entidad entidad_1, Entidad entidad_2) {
 		
		Rectangle r1= entidad_1.getEntidadGrafica().getLabel().getBounds();
		r1.height/=2.15;
		r1.width/=3;
		Rectangle r2= entidad_2.getEntidadGrafica().getLabel().getBounds();
		r2.height/=2.15;
		r2.width/=2;
		return r1.intersects(r2);
 	}
 	
 	private void chequearCambiodeNivel() {
 		
 		if(!nivel.tieneInfectados()) {
 			
 			if(nivel.getTandaActual() == 1) {
 				
 				nivel.subirTanda();
 				gui.modificarTanda(1);
 			}
 			else if (nivel.getNivel() == 1) {
 				
 				nivel.aumentarNivel();
 			//	gui.aumentarNivel(); 
 			}
 			//else
 				//gui.gano(); 			
 		} 		
 	}

}
