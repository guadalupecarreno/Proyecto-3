package Main;

import java.util.LinkedList;
import java.util.List;

import javax.swing.SwingUtilities;

import Entidades.Entidad;
import Logica.Juego;
import Logica.Nivel;

public class HiloEntidades extends Thread{
	

	private Juego juego;
	private LinkedList<Entidad> listaAuxiliar; // uso esta lista para recorrer e ir fijandome los visitor con la otra lista
	private Entidad entidadAuxiliar;
		protected List<Entidad> entidades;
		protected MapaGUI GUI;
		private List<Entidad> entidadesAux;
		
		public HiloEntidades(MapaGUI GUI) {
			
			this.GUI = GUI;
			this.start();
			entidades = Juego.instancia().getEntidadesEnJuego();
		}
		
		@Override
		public void run() {
			do {				
	            try {
	                Thread.sleep(500);
	            } 
	            catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	            Juego.instancia().accionar();
	            
			}while(true);

		}

}
