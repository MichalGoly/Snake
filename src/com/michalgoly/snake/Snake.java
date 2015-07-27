package com.michalgoly.snake;

import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class represents a snake which is represented as a list of snake parts
 * and each part is a 20 x 20 px ellipse. 
 * 
 * @author Michal Goly
 */
public class Snake {
	
	// size of a single snake part
	public static final int XSIZE = 20;
	public static final int YSIZE = 20;
	
	private GameField gameField;
	private ScorePanel scorePanel;
	private List<Ellipse2D.Double> snakeParts;
	private Direction direction;

	private Ellipse2D.Double temp;
	private Ellipse2D.Double ass;

	private boolean over = false;
	
	/**
	 * Constructs a snake object with the default snake parts
	 * @param gameField The field on which snake can move
	 * @param scorePanel The panel for displaying user score
	 */
	public Snake(GameField gameField, ScorePanel scorePanel) {
		this.gameField = gameField;
		this.scorePanel = scorePanel;
		initDefaults();
	}
	
	/**
	 * Changes snake's direction
	 * @param direction The new direction
	 */
	public void changeDirection(Direction direction) {
		this.direction = direction;
	}
	
	/**
	 * Moves the snake in the current direction
	 */
	public void move() {
		switch (direction) {
		case UP:
			moveBody();
			// head
			snakeParts.set(0, new Ellipse2D.Double(snakeParts.get(0).getMinX(),
					snakeParts.get(0).getMinY() - 20, XSIZE, YSIZE));
			if (snakeParts.get(0).getMinY() < 0) {
				over = true;
			}
			break;

		case DOWN:
			moveBody();
			// head
			snakeParts.set(0, new Ellipse2D.Double(snakeParts.get(0).getMinX(),
					snakeParts.get(0).getMinY() + 20, XSIZE, YSIZE));
			if (snakeParts.get(0).getMaxY() > gameField.getBounds().getMaxY()) {
				over = true;
			}
			break;

		case LEFT:
			moveBody();
			// head
			snakeParts.set(0, new Ellipse2D.Double(
					snakeParts.get(0).getMinX() - 20, snakeParts.get(0)
							.getMinY(), XSIZE, YSIZE));
			if (snakeParts.get(0).getMinX() < 0) {
				over = true;
			}
			break;

		case RIGHT:
			moveBody();
			// head
			snakeParts.set(0, new Ellipse2D.Double(
					snakeParts.get(0).getMinX() + 20, snakeParts.get(0)
							.getMinY(), XSIZE, YSIZE));
			if (snakeParts.get(0).getMaxX() > gameField.getBounds().getMaxX()) {
				over = true;
			}
			break;

		default:
			new Exception("Unexcepted Direction value!").printStackTrace();
			break;
		}
	}

	/**
	 * @return snakeParts The list containing snake parts
	 */
	public List<Ellipse2D.Double> getParts() {
		return snakeParts;
	}

	/**
	 * Checks if the snake ate the apple or ate itself
	 */
	public void check() {
		Ellipse2D.Double head = snakeParts.get(0);
		Apple apple = gameField.getApple();
		
		// Ate itself
		for (int i = 1; i < snakeParts.size(); i++) {
			if (head.getMinX() == snakeParts.get(i).getMinX()
					&& head.getMinY() == snakeParts.get(i).getMinY()) {
				over = true;
				return;
			}
		}
		// Ate apple
		if (head.getMinX() == apple.getShape().getMinX()
				&& head.getMinY() == apple.getShape().getMinY()) {
			scorePanel.addPoints(10);
			apple.next(this);
			snakeParts.add(ass);
		}
	}
	
	/**
	 * @return true if game over, false otherwise
	 */
	public boolean isGameOver() {
		return over;
	}

	private void moveBody() {
		for (int i = snakeParts.size() - 1; i > 0; i--) {
			if (i == snakeParts.size() - 1) {
				ass = (Ellipse2D.Double) snakeParts.get(i).clone();
			}
			temp = (Ellipse2D.Double) snakeParts.get(i - 1).clone();
			snakeParts.set(i, temp);
		}
	}

	private void initDefaults() {
		snakeParts = Collections
				.synchronizedList(new ArrayList<Ellipse2D.Double>());
		snakeParts.add(new Ellipse2D.Double(260, 260, 20, 20));
		snakeParts.add(new Ellipse2D.Double(260, 280, 20, 20));
		snakeParts.add(new Ellipse2D.Double(260, 300, 20, 20));
		snakeParts.add(new Ellipse2D.Double(260, 320, 20, 20));
	}
}
