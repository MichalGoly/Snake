package com.michalgoly.snake;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

/**
 * This panel is responsible for displaying the current score of thr user. 
 *  
 * @author Michal Goly
 */
public class ScorePanel extends JPanel {
	
	public static final int PANEL_WIDTH = 400;
	public static final int PANEL_HEIGHT = 50;
	
	private final Font FONT;
	private final String SCORE_LABEL = "SCORE:";
	private String score;
	
	public ScorePanel() {
		setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
		setBackground(Color.DARK_GRAY);
		
		score = "0";
		FONT = new Font("SansSerif", Font.BOLD, 20);
	}
	
	/**
	 * Update current score.
	 * @param points The amount of points
	 */
	public void addPoints(int points) {
		int oldValue = Integer.parseInt(score);
		oldValue += points;
		score = new String(oldValue + "");
		repaint();
	}
	
	/**
	 * Clears the score back to its intial value of 0
	 */
	public void clear() {
		score = "0";
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
		g2.setFont(FONT);
		g2.setPaint(new Color(34, 136, 215)); // BLUE
		g2.drawString(SCORE_LABEL, 15, 32);
		g2.setPaint(new Color(215, 34, 38)); // RED
		g2.drawString(score, 105, 32);
	}
}
