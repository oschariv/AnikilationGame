package main;

import java.util.ArrayList;

import javafx.scene.layout.Pane;

/**
 * Clase que gestiona los ArrayList de Balas y Aliens.
 * 
 * @author Oscar Charro Rivera
 * @version 1.0
 */
public class GestionArrayList {
	private ArrayList<Bala> balasDisparadas;
	private ArrayList<Enemigo> enemigosGenerados;

	public GestionArrayList() {
		balasDisparadas = new ArrayList<>();
		enemigosGenerados = new ArrayList<>();
	}

	/**
	 * Metodo que dispara las balas.
	 * 
	 * @param panel
	 *            obtiene el panel para añidir las balas.
	 */
	public void dispararBala(Pane panel, Nave nave) {
		Bala bala = new Bala(nave.getTranslateX(), nave.getY(), nave);
		// System.out.println("bola Creada");
		balasDisparadas.add(bala);
		panel.getChildren().add(bala);
	}

	/**
	 * Metodo que devuelve el array donde se guardan las balas.
	 * 
	 * @return El ArrayList que contiene las balas.
	 */
	public ArrayList<Bala> getArrayBalas() {
		return balasDisparadas;
	}

	/**
	 * Metodo que dispara las balas.
	 * 
	 * @param panel
	 *            obtiene el panel para añidir las balas.
	 */
	public void generarEnemigo(Pane panel, int anchoPantalla, int altoPantalla) {
		Enemigo enemigo = new Enemigo(anchoPantalla, altoPantalla);
		// System.out.println("bola Creada");
		enemigosGenerados.add(enemigo);
		panel.getChildren().add(enemigo);

	}

	/**
	 * Metodo que devuelve el array donde se guardan las balas.
	 * 
	 * @return El ArrayList que contiene las balas.
	 */
	public ArrayList<Enemigo> getArrayEnemigos() {
		return enemigosGenerados;
	}
}
