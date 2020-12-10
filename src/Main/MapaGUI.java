package Main;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import Entidades.EntidadesPersonaje.Jugador;
import Logica.Juego;
import Logica.Nivel;

import java.awt.Dimension;

public class MapaGUI extends JFrame {

	private JPanel contentPane;
	private JLayeredPane panel;
	private JLabel lblFondo; 
	private JLabel lblPanel;
	private JLabel lblJugador;	
	private ImageIcon lblFondoActual;
	private HiloEntidades tiempo;
	private MovimientoJugador movimiento = new MovimientoJugador(this);
	private MovimientoInfectados infectados;
	private Juego juego;
	protected Nivel nivel;
	protected Thread hiloJuego;
	protected JLabel lblBarricada;
	protected JButton btnJugar;
	protected JButton btnFinalizar;		
	private String [] fondos=new String[] {"Imagenes/FondoNivel1.jpg", "Imagenes/FondoNivel2.jpg"};
	private JLabel lblTandaActual;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MapaGUI frame = new MapaGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	

	/**
	 * Create the frame.
	 */
	
	
	public MapaGUI() {
		
		inicializarGUI();
		inicializarPanelNivel(); 
		addKeyListener(new Adapter());	
		juego = Juego.instancia();
		juego.setGUI(this);
		
		juego.setNivel(new Nivel());
		infectados = new MovimientoInfectados(this);
		
		tiempo = new HiloEntidades(this);
		
	}
	
		
	public void inicializarGUI() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1130, 710);
		this.setResizable(false);
		contentPane = new JPanel();
		contentPane.setSize(this.getSize());
		contentPane.setOpaque(false);
		contentPane.setLayout(null);
		setContentPane(contentPane);
		panel = new JLayeredPane();
		panel.setLocation(0, 0);
		panel.setSize(new Dimension(1124, 681));
		panel.setLayout(null);
		contentPane.add(panel);		
		panel.add(Jugador.instancia().getEntidadGrafica().getLabel(), 10, 2);
	}
	
	public void inicializarPanelNivel() {
		
		//Inicializando el fondo del juego
		lblFondo = new JLabel(); 
        lblFondo.setSize(panel.getSize());
        lblFondo.setBounds(0, 0, 1130, 710);
        lblFondo.setIcon(new ImageIcon(new ImageIcon(this.getClass().getClassLoader().getResource(this.fondos[0])).getImage().getScaledInstance(lblFondo.getWidth(), lblFondo.getHeight(), Image.SCALE_DEFAULT)));
        panel.add(lblFondo,1, 0);
        
        //Inicializando los labels del nivel, la tanda y la carga viral
        lblTandaActual = new JLabel();
        lblTandaActual.setBounds(1100, 100, 500, 500);
        lblTandaActual.setText("Tanda actual: 1");
        lblTandaActual.setVisible(true);
        panel.add(lblTandaActual, 10, 1);
        
        
        
        //Agregando la barricada
        lblBarricada = new JLabel();
        lblBarricada.setBounds(0,300,1124,681);
        ImageIcon icon = new ImageIcon(new ImageIcon(this.getClass().getClassLoader().getResource("Imagenes/Barricadita.png")).getImage().getScaledInstance(lblFondo.getWidth(), 30, Image.SCALE_SMOOTH));
        lblBarricada.setIcon(icon);
        panel.add(lblBarricada,1,0);
        
        //lblBarricada.setIcon(new ImageIcon(new ImageIcon(this.getClass().getClassLoader().getResource("Imagenes/Barricadita.png")).getImage().getScaledInstance(lblBarricada.getWidth(), lblBarricada.getHeight(), Image.SCALE_SMOOTH)));
    }
	
	
	public void insertarEntidad(JLabel label) {
		
		panel.add(label, 10, 2);
		label.setLocation(label.getBounds().getLocation());
		repaint();
	}
	
	public void remover(JLabel lbl) {
		
		panel.remove(lbl);
		lbl.setIcon(null);
		lbl.setVisible(false);
		System.out.println("entre al remove");
		lbl.repaint();
		panel.revalidate();
		panel.repaint();
	}
	
	public void cambiarNivel(int nivel) {
		
		lblFondoActual = (new ImageIcon(new ImageIcon(this.getClass().getClassLoader().getResource("texturas/fondo_nivel" + nivel + ".png")).getImage().getScaledInstance(lblFondo.getWidth(), lblFondo.getHeight(), Image.SCALE_SMOOTH)));		
		//lblFondoIndicador = (new ImageIcon(new ImageIcon(this.getClass().getClassLoader().getResource("texturas/fondo_marcadoNivel" + nivel + ".png")).getImage().getScaledInstance(lblFondo.getWidth(), lblFondo.getHeight(), Image.SCALE_SMOOTH)));		
		lblFondo.setIcon(lblFondoActual);
	}
	
	/*
	public void ganar() {
		
		JLabel ganar = new JLabel();
		ganar.setBounds(110,0,580,720);
		ganar.setIcon(new ImageIcon(this.getClass().getResource("/Imagenes/win.png")));
		terminarJuego(ganar);
	}
	
	public void gameOver() {
		
		JLabel gameOver = new JLabel();
		gameOver.setBounds(0,0,1280,720);
		gameOver.setIcon(new ImageIcon(this.getClass().getResource("/Imagenes/GameOver.jpg")));
		terminarJuego(gameOver);
	}
	
	public void insertarEntidades() {

        for(Infectado i : juego.getNivel().getInfectados()) {
        	//JLabel lbl=i.getEntidadGrafica().getLabel();
        	//lbl.setBounds(120, 120, 500, 500);
        	//lbl.setIcon(new ImageIcon(new ImageIcon(this.getClass().getClassLoader().getResource("JUGADOR/Jugador.png")).getImage().getScaledInstance(lbl.getWidth(), lbl.getHeight(), Image.SCALE_DEFAULT)));
            panel.add(i.getEntidadGrafica().getLabel(),10,2);

        }
    }*/
	
	public void modificarTanda(int tandaActual) {
		
		JOptionPane.showMessageDialog(null, "Cambio de tanda de infectados.", "Nueva tanda", JOptionPane.INFORMATION_MESSAGE);
		lblTandaActual.setText(""+tandaActual+1);		
	}
	
	private class Adapter extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e) {
           movimiento.keyPressed(e, lblFondo);
        }
    }


}
