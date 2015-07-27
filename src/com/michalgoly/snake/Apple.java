package com.michalgoly.snake;

import java.awt.geom.Ellipse2D;

/**
 * This class represents an apple which can be ate by the snake. It appears
 * randomly somewhere within the game field. 
 * 
 * @author Michal Goly
 */
public class Apple {
	
	public static final int XSIZE = 20;
	public static final int YSIZE = 20;
	
	private double x;
	private double y;
	
	/**
	 * Creates an apple with given coordinates
	 * @param x The x coordinate of apple's upper left corner
	 * @param y The y coordinate of apple's upper left corner
	 */
	public Apple(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Generates new coordinates for the apple, so it can appear
	 * somewhere else
	 * @param snake The snake 
	 */
	public void next(Snake snake) {
		for (Ellipse2D.Double e : snake.getParts()) {
			while (x == e.getMinX() && y == e.getMinY()) {
				x = getNew();
				y = getNew();
			}
		}
	}
	
	/*
	 * Generates a random number which can be used as a valid coordinate
	 */
	private double getNew() {
		double d = 1111;
		while (d >= 400 || d % 20 != 0) {
			d = Math.random() * 1000;
			d = (int) d;
		}
		return d;
	}
	
	/**
	 * @return The shape of the apple
	 */
	public Ellipse2D.Double getShape() {
		return new Ellipse2D.Double(x, y, XSIZE, YSIZE);
	}
}
