package main;

import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

/**
 * Clase para crear la nave del videojuego.
 * 
 * @author Oscar Charro Rivera
 * @version 1.0
 */
public class Nave extends ImageView {
	// Atributos de la nave
	private static final String STRING_IMAGEN = "main/nave.png";
	private static final String STRING_IMAGEN_DOS = "main/naveVelocidadCero.png";
	private static final int ANCHO_NAVE = 50;
	private static final int ALTO_NAVE = 50;
	private static int naveSpeed = 0;
	private int anchoPantalla;
	private int altoPantalla;
	private ArrayList<Bala> balasDisparadas;
	private Image imagen;
	private Image image2;
	private Rectangle naveOculta;

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
		imagen = new Image(STRING_IMAGEN);
		image2 = new Image(STRING_IMAGEN_DOS);
		setImage(image2);
		this.anchoPantalla = anchoPantalla;
		this.altoPantalla = altoPantalla;
		setFitHeight(ANCHO_NAVE);
		setFitWidth(ALTO_NAVE);
		// WARNING!!! No modificar este valor bajo ningun concepto sino la bala
		// se va a la putisima en el eje X.
		setX(0);
		setY(this.altoPantalla - 65);
		naveOculta = new Rectangle(getFitWidth(), getFitHeight());
		naveOculta.setX(getX());
		naveOculta.setY(getY());

		balasDisparadas = new ArrayList<>();
	}

	/**
	 * Metodo para mover la nave y evitar que la nave se salga por los bordes o
	 * rebote con ellos.
	 * 
	 */
	public void Mover() {
		setTranslateX(getTranslateX() + naveSpeed);
		if (getBoundsInParent().getMinX() == 0 || this.getBoundsInParent().getMaxX() == anchoPantalla) {
			naveSpeed = 0;
			setImage(image2);
		}
	}

	/**
	 * Metodo para mover la nave a la izquierda de la pantalla
	 */
	public void moverIzquierda() {
		naveSpeed = 3;
		setImage(imagen);
	}

	/**
	 * Metodo para mover la nave a la derecha de la pantalla
	 */
	public void moverDerecha() {
		naveSpeed = -3;
		setImage(imagen);
	}

	/**
	 * Metodo que establece a cero la velocidad de la nave
	 */
	public void velocidadNaveCero() {
		naveSpeed = 0;
		setImage(image2);
	}

	/**
	 * Metodo que devuelve el Rectangle que forma parte de la nave.
	 * 
	 * @return El objeto Rectangle de la Nave.
	 */
	public Rectangle getNaveOculta() {
		return naveOculta;
	}

	/**
	 * Metodo que detecta si se produce la colision que se genera de impactar el
	 * alien contra la nave.
	 * 
	 * @param enemigo
	 *            Obtiene el objeto Enemigo.
	 * @return Un booleano, true si se produce la colision o false si no se
	 *         produce.
	 */
	public boolean eliminacionPorEnemigo(Enemigo enemigo) {
		boolean colision = false;
		Shape interseccion = Shape.intersect(getNaveOculta(), enemigo.getAlienOculto());
		if (interseccion.getBoundsInParent().getWidth() != -1) {
			colision = true;
		}
		return colision;
	}

	/**
	 * Metodo que dispara las balas.
	 * 
	 * @param panel
	 *            obtiene el panel para añidir las balas.
	 */
	public void dispararBala(Pane panel) {
		Bala bala = new Bala(getTranslateX(), getY(), ANCHO_NAVE);
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
}
