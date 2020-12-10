package Entidades.EntidadesPersonaje;

import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import Entidades.EntidadGrafica;
import Logica.Juego;
import Logica.Visitor;
import Logica.VisitorInfectado;

public class InfectadoAlpha extends Infectado{
	
	public InfectadoAlpha() {
		
		carga_viral = 100;
		velocidad = 15; 
		rango_alcance = 0; 
		capacidad_danio = 15; 
		entidadGrafica = new EntidadGrafica(); 
		cargarEntidadGrafica();
		estaVivo = true;
	}
	
	private void cargarEntidadGrafica() {
		
		Random NumeroAleatorio= new Random();
		int numero;
		numero = NumeroAleatorio.nextInt(300) + 370;
        entidadGrafica.getLabel().setBounds(numero, 400, 170, 150);
		entidadGrafica.getLabel().setIcon(new ImageIcon(this.getClass().getResource("/Imagenes/AlphaGrande.gif")));
		this.setPosX(numero);
		this.setPosY(400);
	}
	
	public void duplicarVelocidad() {
		
		System.out.println("holi duplicando la velocidad");
		this.setVelocidad(velocidad*2);
			
	}
	
	public void setearCuarentena() {
		
		System.out.println("holi setenado la velocidad 0 ifnectado alpha");
		
		velocidad = 0;
	}
	
	public void volverVelocidad() {
		
		velocidad = 15;
	}
}	
