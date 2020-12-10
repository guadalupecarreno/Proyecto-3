package Logica;

import java.util.List;
import java.util.LinkedList;
import java.util.Random;

import Entidades.EntidadesPersonaje.Infectado;
import Entidades.EntidadesPersonaje.InfectadoAlpha;
import Entidades.EntidadesPersonaje.InfectadoBeta;

public class Nivel {
    protected int nivel;
    protected int infectados_tanda;
    protected int tanda_actual;
    protected List<Infectado> infectados; 

    public Nivel() {
    	
        nivel = 1;
        infectados_tanda = 6;
        tanda_actual = 1;
        agregarInfectados();
    }

    //Getters
    public List<Infectado> getInfectados() {
        return infectados;
    }

    public int getInfectadosTanda() {
        return infectados_tanda;
    }

    public int getTandaActual() {
        return tanda_actual;
    }

    public int getNivel() {
        return nivel;
    }

    public boolean tieneInfectados() {

    	return (infectados.size() != 0);
    }
    
    public void subirTanda() {
    	
    	if(tanda_actual == 1) {
	        tanda_actual=2;
	        agregarInfectados();
    	}   
    }

    public void aumentarNivel() {
    	
    	if(nivel == 1) {
    		
	        nivel++;
	        infectados_tanda += 10;
	        tanda_actual = 1;
	        agregarInfectados();
    	} 
    }

    public void agregarInfectados() {
    	
        Random random = new Random();
        infectados = new LinkedList<Infectado>();
        int numAleatorio;
        int i = 0;
        while(i<infectados_tanda){
        	
            numAleatorio = random.nextInt(2);
            if(numAleatorio == 0)
                infectados.add(new InfectadoAlpha());  
            else
                infectados.add(new InfectadoBeta());
            i++;
        }
    }
}