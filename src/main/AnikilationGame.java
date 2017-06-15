package main;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
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
	private ArrayList<Enemigo> enemigosGenerados;
	private int puntos = 0;
	private Timeline timeline;
	private TimerTask cronometro;
	private static final int NUMERO_ALIENS_A_GENERAR = 20;

	/**
	 * Metodo que inicia el juego.
	 * 
	 * @param escenario
	 *            obtiene el escenario a crear.
	 */
	@Override
	public void start(Stage escenario) throws InterruptedException {

		// Generamos el panel, la escena y lo añadimos al escenario
		Pane root = new Pane();
		Scene escena = new Scene(root, ANCHO_PANTALLA, ALTO_PANTALLA);
		escenario.setScene(escena);
		escenario.setTitle("ANIKILATION GAME");
		escenario.initStyle(StageStyle.UNDECORATED);

		// Añadimos el fondo de pantalla
		root.getChildren().add(new Fondo(ANCHO_PANTALLA, ALTO_PANTALLA));

		// Creamos la nave
		Nave naveEspacial = new Nave(ANCHO_PANTALLA, ALTO_PANTALLA);
		root.getChildren().add(naveEspacial);

		// Creamos los aliens
		enemigosGenerados = new ArrayList<>();
		int numeroAliensAnidados = 0;
		while (numeroAliensAnidados < NUMERO_ALIENS_A_GENERAR) {
			boolean encontradoAlienValido = false;
			while (!encontradoAlienValido) {
				Random aleatorio = new Random();
				int posicionX = aleatorio.nextInt(10);
				int posicionY = aleatorio.nextInt(10);
				Enemigo posibleEnemigo = new Enemigo(ANCHO_PANTALLA, ALTO_PANTALLA, posicionX, posicionY);
				int alienActual = 0;
				boolean solapamientoDetectado = false;
				while (alienActual < enemigosGenerados.size() && !solapamientoDetectado) {
					if (posibleEnemigo.intersects(enemigosGenerados.get(alienActual).getX(),
							enemigosGenerados.get(alienActual).getY(), enemigosGenerados.get(alienActual).getFitWidth(),
							enemigosGenerados.get(alienActual).getFitHeight())) {
						solapamientoDetectado = true;
					}
					alienActual++;
				}
				// Si hemos encontrado un Alien Valido
				if (!solapamientoDetectado) {
					encontradoAlienValido = true;
					enemigosGenerados.add(posibleEnemigo);
					root.getChildren().add(posibleEnemigo);
				}
			}
			numeroAliensAnidados++;
		}

		// COMIENZO DE LOS EVENTOS DE TECLADO.
		// movimiento de la nave cuando de pulsa un tecla.
		escena.setOnKeyPressed(event -> {

			if (event.getCode() == KeyCode.RIGHT && naveEspacial.getBoundsInParent().getMaxX() != ANCHO_PANTALLA) {
				naveEspacial.moverIzquierda();
			} else if (event.getCode() == KeyCode.LEFT && naveEspacial.getBoundsInParent().getMinX() != 0) {
				naveEspacial.moverDerecha();
			} else if (event.getCode() == KeyCode.SPACE) {
				naveEspacial.dispararBala(root);
				disparo = true;
			} else if (event.getCode() == KeyCode.ESCAPE) {
				System.exit(0);
			} else if (event.getCode() == KeyCode.P) {
				if (timeline.getStatus().equals(Animation.Status.RUNNING)) {
					timeline.stop();
					escenario.setTitle("ANIKILATION GAME (PAUSE)");

				} else {
					timeline.play();
					escenario.setTitle("ANIKILATION GAME");
				}
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

		// Label para cronometro
		Label tiempoPasado = new Label("0");
		root.getChildren().add(tiempoPasado);

		// Label para puntuacion
		Label puntuacion = new Label("0");
		root.getChildren().add(puntuacion);

		// Creamos el timeline y el KeyFrame para dar movimiento al juego.
		timeline = new Timeline();
		timeline.setAutoReverse(true);
		KeyFrame keyframe = new KeyFrame(Duration.millis(3), event -> {

			// Movimiento de la nave espacial
			naveEspacial.Mover();

			// Movimiento alien
			for (int i = 0; i < enemigosGenerados.size(); i++) {
				enemigosGenerados.get(i).moverAlien();
				if (enemigosGenerados.get(i).getBoundsInParent().getMinY() > ALTO_PANTALLA) {
					root.getChildren().remove(enemigosGenerados.get(i));
					enemigosGenerados.remove(i);
				}
			}

			// Movimiento de la bala
			if (disparo) {
				for (int i = 0; i < naveEspacial.getArrayBalas().size(); i++) {
					naveEspacial.getArrayBalas().get(i).moverBala();
					if (naveEspacial.getArrayBalas().get(i).getBoundsInParent().getMaxY() <= 0) {
						root.getChildren().remove(naveEspacial.getArrayBalas().get(i));
						naveEspacial.getArrayBalas().remove(i);
					}
				}
			}

			// Desctruccion Aliens.
			for (int j = 0; j < naveEspacial.getArrayBalas().size(); j++) {
				for (int i = 0; i < enemigosGenerados.size() && !enemigosGenerados.isEmpty(); i++) {
					if (naveEspacial.getArrayBalas().get(j).aciertoAEnemigo(enemigosGenerados.get(i))) {
						root.getChildren().remove(enemigosGenerados.get(i));
						root.getChildren().remove(naveEspacial.getArrayBalas().get(j));
						enemigosGenerados.remove(i);
						naveEspacial.getArrayBalas().remove(j);
						puntos += 10;
						if (i != 0) {
							i--;
						}
						if (j != 0) {
							j--;
						}
					}
				}
			}

			// mostramos la puntuacion por destruir ladrillos
			puntuacion.setText("Puntuacion: " + String.valueOf(puntos));
			puntuacion.setTextFill(Color.WHITE);
			puntuacion.setLayoutX(ANCHO_PANTALLA - 150);
			puntuacion.setLayoutY(ALTO_PANTALLA - 20);

			// mostramos el tiempo pasado con un label
			tiempoPasado.setText("Tiempo: " + String.valueOf(tiempoEnSegundos));
			tiempoPasado.setTextFill(Color.WHITE);
			tiempoPasado.setLayoutX(15);
			tiempoPasado.setLayoutY(ALTO_PANTALLA - 20);

			// SALIDA BOLA POR DEBAJO DE LA PANTALLA.

			if (enemigosGenerados.isEmpty()) {
				Label GOMessage = new Label(
						"     GAME OVER!" + "\n  Puntucacion: " + puntos + "\nTecla ESC para salir");
				GOMessage.setFont(Font.font("Courier New", FontWeight.BOLD, 48));
				GOMessage.setTextFill(Color.WHITE);
				root.getChildren().add(GOMessage);
				GOMessage.layoutXProperty().bind(root.widthProperty().subtract(GOMessage.widthProperty()).divide(2));
				GOMessage.layoutYProperty().bind(root.heightProperty().subtract(GOMessage.heightProperty()).divide(2));
				escenario.setTitle("ARKANOID (GAME OVER)");
				timeline.stop();
			}

			for (int i = 0; i < enemigosGenerados.size(); i++) {
				if (naveEspacial.eliminacionPorEnemigo(enemigosGenerados.get(i))) {
					// Mostramos mensaje de GAME OVER
					Label GOMessage = new Label(
							"     GAME OVER!" + "\n  Puntucacion: " + puntos + "\nTecla ESC para salir");
					GOMessage.setFont(Font.font("Courier New", FontWeight.BOLD, 48));
					GOMessage.setTextFill(Color.WHITE);
					root.getChildren().add(GOMessage);
					GOMessage.layoutXProperty()
							.bind(root.widthProperty().subtract(GOMessage.widthProperty()).divide(2));
					GOMessage.layoutYProperty()
							.bind(root.heightProperty().subtract(GOMessage.heightProperty()).divide(2));
					escenario.setTitle("ARKANOID (GAME OVER)");
					timeline.stop();
					// Removemos alien y nave de la pantalla
					root.getChildren().remove(enemigosGenerados.get(i));
					root.getChildren().remove(naveEspacial);
					enemigosGenerados.remove(i);

				}
			}

		});

		timeline.getKeyFrames().add(keyframe);
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.play();

		// Mostramos el escenario completo.
		escenario.show();

		// cronometro para tiempo de juego
		cronometro = new TimerTask() {

			@Override
			public void run() {
				tiempoEnSegundos++;
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
