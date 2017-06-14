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
	public static final String STRING_IMAGEN = "main/alien.png";
	private static final int ANCHO_ALIEN = 50;
	private static final int ALTO_ALIEN = 50;
	private int alienSpeed = 2;
	private int anchoPantalla;
	private int altoPantalla;
	private Rectangle alienOculto;

	/**
	 * Constructor de la clase Enemigo
	 * 
	 * @param root
	 *            obtiene el ancho de la pantalla.
	 * @param altoPantalla
	 *            obtiene el alto de la pantalla.
	 */
	public Enemigo(int anchoPantalla, int altoPantalla, int posicionX, int posicionY) {
		super();
		Image image = new Image(STRING_IMAGEN);
		setImage(image);
		this.anchoPantalla = anchoPantalla;
		this.altoPantalla = altoPantalla;
		setFitHeight(ALTO_ALIEN);
		setFitWidth(ANCHO_ALIEN);
		setX((posicionX * ANCHO_ALIEN) + ANCHO_ALIEN);
		setY((-posicionY * ANCHO_ALIEN) + ALTO_ALIEN);
		alienOculto = new Rectangle(getFitWidth(), getFitHeight());
		alienOculto.setX(getX());
		alienOculto.setY(getY());
	}

	/**
	 * Metodo que permite mover al alien.
	 */
	public void moverAlien() {
		setTranslateY(getTranslateY() + alienSpeed);
	}

	public Rectangle getAlienOculto() {
		return alienOculto;
	}
}
