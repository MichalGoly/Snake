package com.michalgoly.snake;

import java.awt.geom.Ellipse2D;

public class Apple {
	
	public static final int XSIZE = 20;
	public static final int YSIZE = 20;
	
	private double x;
	private double y;
	
	public Apple(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public void next(Snake snake) {
		for (Ellipse2D.Double e : snake.getParts()) {
			while (x == e.getMinX() && y == e.getMinY()) {
				x = getNew();
				y = getNew();
			}
		}
	}
	
	private double getNew() {
		double d = 1111;
		while (d >= 400 || d % 20 != 0) {
			d = Math.random() * 1000;
			d = (int) d;
		}
		return d;
	}
	
	
	public Ellipse2D.Double getShape() {
		return new Ellipse2D.Double(x, y, XSIZE, YSIZE);
	}
}
