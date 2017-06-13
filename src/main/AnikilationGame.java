package main;

import java.util.Timer;
import java.util.TimerTask;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Proyecto para el desarrollo de un videojuego. Tema elegido: juego de naves al
 * estilo Space Invaders.
 * 
 * Clase para la ejecucion del videojuego.
 * 
 * @author Oscar Charro Rivera
 * @version 1.0
 */

public class AnikilationGame extends Application {

	// Atributos de clase AnikilationGame
	private static final int ANCHO_PANTALLA = 600;
	private static final int ALTO_PANTALLA = 800;
	private boolean disparo = false;
	private int tiempoEnSegundos = 0;
	private int contadorAliensGenerados = 1;

	/**
	 * Metodo que inicia el juego.
	 * 
	 * @param escenario
	 *            obtiene el escenario a crear.
	 */
	@Override
	public void start(Stage escenario) {

		// Generamos el panel, la escena y lo a�adimos al escenario
		Pane root = new Pane();
		Scene escena = new Scene(root, ANCHO_PANTALLA, ALTO_PANTALLA);
		escenario.setScene(escena);
		escenario.setTitle("ANIKILATION GAME");

		// A�adimos el fondo de pantalla
		root.getChildren().add(new Fondo(ALTO_PANTALLA, ALTO_PANTALLA));

		// Creamos la nave
		Nave naveEspacial = new Nave(ANCHO_PANTALLA, ALTO_PANTALLA);
		root.getChildren().add(naveEspacial);

		// Creamos un objeto para la gestion de arrayList.
		GestionArrayList gestionArraylist = new GestionArrayList();

		// Creamos un alien
		Enemigo alien = new Enemigo(ANCHO_PANTALLA, ALTO_PANTALLA);
		root.getChildren().add(alien);

		// COMIENZO DE LOS EVENTOS DE TECLADO.
		// movimiento de la nave cuando de pulsa un tecla.
		escena.setOnKeyPressed(event -> {

			if (event.getCode() == KeyCode.RIGHT && naveEspacial.getBoundsInParent().getMaxX() != ANCHO_PANTALLA) {
				naveEspacial.moverIzquierda();
			} else if (event.getCode() == KeyCode.LEFT && naveEspacial.getBoundsInParent().getMinX() != 0) {
				naveEspacial.moverDerecha();
			} else if (event.getCode() == KeyCode.SPACE) {
				gestionArraylist.dispararBala(root, naveEspacial);
				disparo = true;
			}
		});

		// evitamos que la nave se mueva al soltar la tecla.
		escena.setOnKeyReleased(event -> {
			if (event.getCode() == KeyCode.RIGHT) {
				naveEspacial.velocidadNaveCero();
			} else if (event.getCode() == KeyCode.LEFT) {
				naveEspacial.velocidadNaveCero();
			} else if (event.getCode() == KeyCode.SPACE) {
				disparo = true;
			}
		});
		// FINAL DE LOS EVENTOS DE TECLADO.

		// Creamos el timeline y el KeyFrame para dar movimiento al juego.
		Timeline timeline = new Timeline();
		timeline.setAutoReverse(true);
		KeyFrame keyframe = new KeyFrame(Duration.seconds(0.007), event -> {

			// Movimiento de la nave espacial
			naveEspacial.Mover();

			// Movimiento alien
			alien.moverAlien();

			// Movimiento de la bala
			if (disparo) {
				for (int i = 0; i < gestionArraylist.getArrayBalas().size(); i++) {
					gestionArraylist.getArrayBalas().get(i).moverBala();
					if (gestionArraylist.getArrayBalas().get(i).getBoundsInParent().getMinY() == 0) {
						gestionArraylist.getArrayBalas().remove(i);
					}
				}
			}

		});

		timeline.getKeyFrames().add(keyframe);
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.play();
		// Mostramos el escenario completo.
		escenario.show();

		// cronometro para tiempo de juego
		TimerTask cronometro = new TimerTask() {
			@Override
			public void run() {
				tiempoEnSegundos++;
				// contador aliens
				if (tiempoEnSegundos % 5 == 0) {
					System.out.println("Segundos: " + String.valueOf(tiempoEnSegundos));
					System.out.println("Aliens: " + String.valueOf(contadorAliensGenerados));
					contadorAliensGenerados++;
				}
			}
		};
		Timer timer = new Timer();
		timer.schedule(cronometro, 0, 1000);
	}

	/**
	 * Metodo para lanzar el juego.
	 * 
	 * @param args
	 *            obtiene los argumentos para lanzarlo.
	 */
	public static void main(String[] args) {
		launch(args);
	}

}
