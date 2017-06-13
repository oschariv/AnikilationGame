package main;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Bala extends Rectangle {
	// Atributos de la bala
	private static final int ANCHO_BALA = 6;
	private static final int ALTO_BALA = 20;
	private static final Color COLOR_FONDO_BALA = Color.YELLOW;
	private static final Color COLOR_BORDE_BALA = Color.RED;
	private static final int ARCO_BALA = 20;
	private int balaSpeed = 5;

	/**
	 * Constructor para bala
	 * 
	 * @param anchoPantalla
	 *            obtiene el anto de la pantalla
	 * @param altoPantalla
	 *            obtiene el alto de la pantalla
	 */
	public Bala(double posicionXNave, double posicionYNave, Nave nave) {
		super();
		setWidth(ANCHO_BALA);
		setHeight(ALTO_BALA);
		setFill(COLOR_FONDO_BALA);
		setStroke(COLOR_BORDE_BALA);
		setArcHeight(ARCO_BALA);
		setArcWidth(ARCO_BALA);
		setVisible(true);
		setX(nave.getTranslateX() + nave.getFitWidth() / 2 - ANCHO_BALA / 2);
		setY(posicionYNave + ALTO_BALA);
	}

	public void moverBala() {
		setTranslateY(getTranslateY() - balaSpeed);
	}

}
