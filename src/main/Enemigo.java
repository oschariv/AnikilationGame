package main;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Clase para generar los enimgos.
 * 
 * @author Oscar Charro Rivera
 * @version 1.0
 */
public class Enemigo extends ImageView {
	// Atributos de los enemigos
	public static final String STRING_IMAGEN = "main/alien.png";
	private static final int ANCHO_ALIEN = 50;
	private static final int ALTO_ALIEN = 50;
	private int alienSpeed = 2;
	private int anchoPantalla;
	private int altoPantalla;

	/**
	 * Constructor de la clase Enemigo
	 * 
	 * @param anchoPantalla
	 *            obtiene el ancho de la pantalla.
	 * @param altoPantalla
	 *            obtiene el alto de la pantalla.
	 */
	public Enemigo(int anchoPantalla, int altoPantalla) {
		super();
		Image image = new Image(STRING_IMAGEN);
		setImage(image);
		this.anchoPantalla = anchoPantalla;
		this.altoPantalla = altoPantalla;
		setFitHeight(ANCHO_ALIEN);
		setFitWidth(ALTO_ALIEN);
		setX(this.anchoPantalla / 2);
		setY(50);
		// System.out.println("Alien generado");
	}

	/**
	 * Metodo que permite mover al alien.
	 */
	public void moverAlien() {
		setTranslateY(getTranslateY() + alienSpeed);
		// System.out.println("Alien se mueve");
	}

	public ImageView getBounds() {
		return this;
	}
}
