package main;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

/**
 * Clase para generar los enimgos.
 * 
 * @author Oscar Charro Rivera
 * @version 1.0
 */
public class Enemigo extends ImageView {
	// Atributos de los enemigos
	public static final String STRING_IMAGEN = "main/recursos/alien.png";
	private static final int ANCHO_ALIEN = 50;
	private static final int ALTO_ALIEN = 50;
	private int alienSpeed = 2;
	private int anchoPantalla;
	private int altoPantalla;
	private Rectangle colision;

	/**
	 * Constructor de la clase Enemigo
	 * 
	 * @param root
	 *            obtiene el ancho de la pantalla.
	 * @param altoPantalla
	 *            obtiene el alto de la pantalla.
	 */
	public Enemigo(int anchoPantalla, int altoPantalla, int posicionX) {
		super();
		Image image = new Image(STRING_IMAGEN);
		setImage(image);
		this.anchoPantalla = anchoPantalla;
		this.altoPantalla = altoPantalla;
		setFitHeight(ALTO_ALIEN);
		setFitWidth(ANCHO_ALIEN);
		setX((posicionX * ANCHO_ALIEN) + ANCHO_ALIEN);
		setY(-50);
		colision = new Rectangle(getFitWidth(), getFitHeight());
		colision.setX(getX());
		colision.setY(getY());
	}

	/**
	 * Metodo que permite mover al alien.
	 */
	public void moverAlien() {
		setTranslateY(getTranslateY() + alienSpeed);
	}

	public Rectangle getColision() {
		return colision;
	}
}
