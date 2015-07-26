package com.michalgoly.snake;

public class Game implements Runnable {
	
	public static final int DELAY = 400;
	
	private SnakeFrame frame;
	private ScorePanel scorePanel;
	private SnakeComponent snakeComp;
	private Snake snake;
	private Apple apple;
	
	public Game(ScorePanel scorePanel, SnakeComponent snakeComp, Snake snake,
			SnakeFrame frame) {
		apple = new Apple(100, 100);
		this.frame = frame;
		this.scorePanel = scorePanel;
		this.snake = snake;
		this.snakeComp = snakeComp;
		
		this.snakeComp.addSnakeAndApple(snake.getParts(), apple);
	}
	
	@Override
	public void run() {
		try {
			while (true) {
				snake.move(snakeComp);
				snake.check(apple, scorePanel);
				if (snake.gameOver()) {
					Thread.currentThread().interrupt();
				}
				if (!Thread.currentThread().isInterrupted()) {
					snakeComp.repaint();
				}
				Thread.sleep(DELAY);
			}
		} catch (InterruptedException ex) {
			frame.gameOver();
		}
	}
}
