package com.michalgoly.snake;

/**
 * This class is responsible for running the game, or indeed making the
 * snake move. 
 * 
 * @author Michal Goly
 */
public class Game implements Runnable {

	// The amount of time in miliseconds between each 'tick'
	public static final int DELAY = 400;

	private SnakeFrame frame;
	private GameField gameField;
	private Snake snake;
	private Apple apple;
	
	/**
	 * Constructs a new runnable Game object which can be used to create
	 * a Thread. 
	 * @param gameField The rectangular area where snake can move
	 * @param snake The snake object
	 * @param frame The frame which will be notified when the game is over
	 */
	public Game(GameField gameField, Snake snake, SnakeFrame frame) {
		apple = new Apple(100, 100);
		this.frame = frame;
		this.snake = snake;
		this.gameField = gameField;

		this.gameField.setSnakeParts(snake.getParts());
		this.gameField.setApple(apple);
	}

	@Override
	public void run() {
		try {
			while (true) {
				snake.move();
				snake.check();
				if (snake.isGameOver()) {
					Thread.currentThread().interrupt();
				}
				if (!Thread.currentThread().isInterrupted()) {
					gameField.repaint();
				}
				Thread.sleep(DELAY);
			}
		} catch (InterruptedException ex) {
			frame.gameOver();
		}
	}
}
