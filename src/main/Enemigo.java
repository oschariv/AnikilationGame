package main;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Enemigo extends ImageView {
	// Atributos de los enemigos
	private static final int ANCHO_ALIEN = 50;
	private static final int ALTO_ALIEN = 50;
	private int alienSpeed = 2;
	private int anchoPantalla;
	private int altoPantalla;

	public Enemigo(int anchoPantalla, int altoPantalla) {
		super();
		Image image = new Image("main/alien.png");
		setImage(image);
		this.anchoPantalla = anchoPantalla;
		this.altoPantalla = altoPantalla;
		setFitHeight(ANCHO_ALIEN);
		setFitWidth(ALTO_ALIEN);
		setX(this.anchoPantalla / 2);
		setY(50);
		System.out.println("Alien generado");
	}

	public void moverAlien() {
		setTranslateY(getTranslateY() + alienSpeed);
		System.out.println("Alien se mueve");
	}
}
