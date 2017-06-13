package main;

import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Clase para crear la nave del videojuego.
 * 
 * @author Oscar Charro Rivera
 * @version 1.0
 */
public class Nave extends ImageView {
	// Atributos de la nave
	public static final String STRING_IMAGEN = "main/nave.png";
	private static final int ANCHO_NAVE = 50;
	private static final int ALTO_NAVE = 50;
	private static int naveSpeed = 0;
	private int anchoPantalla;
	private int altoPantalla;
	private ArrayList<Bala> balasDisparadas;

	/**
	 * Constructor de la nave
	 * 
	 * @param anchoPantalla
	 *            obtiene el ancho de la pantalla
	 * @param altoPantalla
	 *            obtiene el alto de la pantalla
	 */
	public Nave(int anchoPantalla, int altoPantalla) {
		super();
		Image image = new Image(STRING_IMAGEN);
		setImage(image);
		this.anchoPantalla = anchoPantalla;
		this.altoPantalla = altoPantalla;
		setFitHeight(ANCHO_NAVE);
		setFitWidth(ALTO_NAVE);
		// WARNING!!! No modificar este valor bajo ningun concepto sino la bala
		// se va a la putisima en el eje X.
		setX(0);
		setY(this.altoPantalla - 50);
		balasDisparadas = new ArrayList<>();
	}

	/**
	 * Metodo para mover la nave y evitar que la nave se salga por los bordes o
	 * rebote con ellos.
	 * 
	 */
	public void Mover() {
		setTranslateX(getTranslateX() + naveSpeed);
		if (getBoundsInParent().getMinX() == 0 || getBoundsInParent().getMaxX() == anchoPantalla) {
			// System.out.println("Se ejecuta Correctamente");
			naveSpeed = 0;
		}
	}

	/**
	 * Metodo para mover la nave a la izquierda de la pantalla
	 */
	public void moverIzquierda() {
		naveSpeed = 3;
	}

	/**
	 * Metodo para mover la nave a la derecha de la pantalla
	 */
	public void moverDerecha() {
		naveSpeed = -3;
	}

	/**
	 * Metodo que establece a cero la velocidad de la nave
	 */
	public void velocidadNaveCero() {
		naveSpeed = 0;
	}

}
