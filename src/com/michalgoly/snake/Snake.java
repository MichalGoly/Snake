package com.michalgoly.snake;

import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Snake {
	
	public static final int XSIZE = 20;
	public static final int YSIZE = 20;
		
	private List<Ellipse2D.Double> snakeParts;
	private Direction direction;
	
	private Ellipse2D.Double temp;
	private Ellipse2D.Double ass;
	
	private boolean over = false;
	
	public Snake() {
		initDefaults();
	}
	
	public void changeDirection(Direction direction) {
		this.direction = direction;
	}
	
	public void move(SnakeComponent component) {
		switch (direction) {
			case UP:
				moveBody();
				// head
				snakeParts.set(0, new Ellipse2D.Double(snakeParts.get(0)
						.getMinX(), snakeParts.get(0).getMinY() - 20,
						XSIZE, YSIZE));
				if (snakeParts.get(0).getMinY() < 0) over = true;	
				break;
				
			case DOWN:
				moveBody();
				//head
				snakeParts.set(0, new Ellipse2D.Double(snakeParts.get(0)
						.getMinX(), snakeParts.get(0).getMinY() + 20,
						XSIZE, YSIZE));
			if (snakeParts.get(0).getMaxY() > component.getBounds().getMaxY()) {
				over = true;	
			}
				break;
				
			case LEFT:				
				moveBody();
				// head
					snakeParts.set(0, new Ellipse2D.Double(snakeParts.get(0)
							.getMinX() - 20, snakeParts.get(0).getMinY(),
							XSIZE, YSIZE));
				if (snakeParts.get(0).getMinX() < 0) over = true;				
				break;
				
			case RIGHT:
				moveBody();
				// head
				snakeParts.set(0, new Ellipse2D.Double(snakeParts.get(0)
						.getMinX() + 20, snakeParts.get(0).getMinY(),
						XSIZE, YSIZE));
			if (snakeParts.get(0).getMaxX() > component.getBounds().getMaxX()) {
				over = true;		
			}
				break;
				
			default:
				new Exception("Unexcepted Direction value!").printStackTrace();
				break;
		}
	}
	
	/**
	 * Get <Code>List</Code> of the current snake parts
	 * @return
	 */
	public List<Ellipse2D.Double> getParts() {
		if (snakeParts != null) return snakeParts;
		else return null;
	}
	
	/**
	 * Checks if snake ate apple or ate itself
	 * @param apple
	 */
	public void check(Apple apple, ScorePanel score) {
		Ellipse2D.Double head = snakeParts.get(0);

		// Ate itself
		for (int i = 1; i < snakeParts.size(); i++) {
			if (head.getMinX() == snakeParts.get(i).getMinX() && 
					head.getMinY() == snakeParts.get(i).getMinY()) {
				over = true;
				return;
			}
		}
		// Ate apple
		if (head.getMinX() == apple.getShape().getMinX() &&
				head.getMinY() == apple.getShape().getMinY()) {
			score.addPoints(10);
			apple.next(this);
			snakeParts.add(ass);
		}
	}
	
	public boolean gameOver() {
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
	
	public void initDefaults() {
		snakeParts = Collections
				.synchronizedList(new ArrayList<Ellipse2D.Double>());
		snakeParts.add(new Ellipse2D.Double(260, 260, 20, 20));
		snakeParts.add(new Ellipse2D.Double(260, 280, 20, 20));
		snakeParts.add(new Ellipse2D.Double(260, 300, 20, 20));
		snakeParts.add(new Ellipse2D.Double(260, 320, 20, 20));	
	}
}
