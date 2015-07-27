package com.michalgoly.snake;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JPanel;

/**
 * GameField represents a black, rectangular area where the snake can move. 
 * It is also responsible for drawing the snake and the apple. 
 * 
 * @author Michal Goly
 */
public class GameField extends JPanel {
	
	public static final int PANEL_WIDTH = 400;
	public static final int PANEL_HEIGHT = 400;
	
	private List<Ellipse2D.Double> snakeParts;
	private Apple apple;
	
	/**
	 * Constructs the game field, which is the rectangular area where snake can
	 * move
	 */
	public GameField() {
		setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
		setBackground(Color.BLACK);
		initDefaults();
	}
	
	/**
	 * Initializes the default snake and the apple
	 */
	public void initDefaults() {
		apple = new Apple(100, 100);
		snakeParts = Collections
				.synchronizedList(new ArrayList<Ellipse2D.Double>());
		snakeParts.add(new Ellipse2D.Double(260, 260, 20, 20));
		snakeParts.add(new Ellipse2D.Double(260, 280, 20, 20));
		snakeParts.add(new Ellipse2D.Double(260, 300, 20, 20));
		snakeParts.add(new Ellipse2D.Double(260, 320, 20, 20));		
	}
	
	public void setSnakeParts(List<Ellipse2D.Double> snakeParts) {
		this.snakeParts = snakeParts;
	}
	
	public void setApple(Apple apple) {
		this.apple = apple;
	}
	
	public Apple getApple() {
		return apple;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
				RenderingHints.VALUE_ANTIALIAS_ON);
		
		// Draw the apple
		g2.setPaint(Color.WHITE);
		g2.fillOval((int) apple.getShape().getMinX() + 5, (int) apple.getShape()
				.getMinY() + 5, 10, 10);
		
		// Draw the snake parts
		g2.setPaint(new Color(34, 136, 215)); // BLUE
		for (Ellipse2D e : snakeParts) {
			g2.fill(e);
		}
		
		// Draw the head of the snake
		g2.setPaint(new Color(215, 34, 38));  // RED
		g2.fill(snakeParts.get(0));
	}
}
