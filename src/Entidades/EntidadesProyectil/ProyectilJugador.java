package Entidades.EntidadesProyectil;

import javax.swing.ImageIcon;

import Entidades.EntidadGrafica;
import Entidades.EntidadesPersonaje.Jugador;
import Logica.Juego;
import Logica.Visitor;
import Logica.VisitorProyectilJugador;

public class ProyectilJugador extends Proyectil	{
	
	public ProyectilJugador() {
		
		velocidad = 5;
		entidadGrafica = new EntidadGrafica(); 
		cargarEntidadGrafica();
		visitor = new VisitorProyectilJugador(this);	
		poder_desinfeccion = 15;
	}
	
	private void cargarEntidadGrafica() {
		
		entidadGrafica.getLabel().setIcon(new ImageIcon(this.getClass().getResource("/Imagenes/Disparo.png")));
		entidadGrafica.getLabel().setVisible(false);
	}

	@Override
	public void aceptar(Visitor a) {
		a.visit(this);
	}

	@Override
	public void accionar() {
		
		System.out.println("entre al accionar del proyectil");
		int x = this.getPosX()+35;
		int y = this.getPosY()-30;
		
		entidadGrafica.getLabel().setBounds(x, y, 120, 120);
		entidadGrafica.getLabel().setLocation(x, y-velocidad);
		entidadGrafica.getLabel().setVisible(true);
		
		this.setPosY(y-velocidad);
		
		if(this.getPosY() <=350) {
			Juego.instancia().getEntidadesEnJuego().remove(this); 
			entidadGrafica.getLabel().setVisible(false);
		}
		
	}

	

}
