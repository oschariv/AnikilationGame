package main;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Clase que obtiene la imagen de fondo de pantalla.
 * 
 * @author Oscar Charro Rivera
 * @version 1.0
 */

public class Fondo extends ImageView {
	// Cargamos la imagen de fondo
	public static final String STRING_IMAGEN = "main/recursos/fondo.png";

	/**
	 * Constructor de la clase Fondo.
	 * 
	 * @param anchoPantalla
	 *            obtiene el ancho de la pantalla.
	 * @param altoPantalla
	 *            obtiene el alto de la pantalla.
	 */
	public Fondo(int anchoPantalla, int altoPantalla) {
		Image imagenFondo = new Image(STRING_IMAGEN);
		setImage(imagenFondo);
		setFitWidth(anchoPantalla);
		setFitHeight(altoPantalla);
	}

}
