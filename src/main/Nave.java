package main;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Nave extends Rectangle {
	// Temporal para comprobacion
	private static final int ANCHO_NAVE = 50;
	private static final int ALTO_NAVE = 80;
	private static final Color COLOR_NAVE = Color.BLUE;
	private static int naveSpeed = 0;
	private int anchoPantalla;
	private int altoPantalla;

	/**
	 * Constructor de la nave
	 * 
	 * @param anchoPantalla
	 *            obtiene el ancho de la pantalla
	 * @param altoPantalla
	 *            obtiene el alto de la pantalla
	 */
	public Nave(int anchoPantalla, int altoPantalla) {
		setWidth(ANCHO_NAVE);
		setHeight(ALTO_NAVE);
		setFill(COLOR_NAVE);
		this.anchoPantalla = anchoPantalla;
		this.altoPantalla = altoPantalla;
		setX(this.anchoPantalla / 2 - getWidth() / 2);
		setY(this.altoPantalla - getHeight() - 20);
	}

	/**
	 * Metodo para mover la nave y evitar que la nave se salga por los bordes o
	 * rebote con ellos.
	 * 
	 */
	public void Mover() {
		setTranslateX(getTranslateX() + naveSpeed);
		if (getBoundsInParent().getMinX() == 0 || getBoundsInParent().getMaxX() == anchoPantalla) {
			naveSpeed = 0;
		}
	}

	/**
	 * Metodo para mover la nave a la izquierda de la pantalla
	 */
	public void moverIzquierda() {
		naveSpeed = 1;
	}

	/**
	 * Metodo para mover la nave a la derecha de la pantalla
	 */
	public void moverDerecha() {
		naveSpeed = -1;
	}
}
