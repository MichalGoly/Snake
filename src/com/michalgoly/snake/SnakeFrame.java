package com.michalgoly.snake;

import java.awt.EventQueue;
import java.awt.GridBagLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.horstmann.corejava.GBC;

public class SnakeFrame extends JFrame {
	
	private ScorePanel score;
	private SnakeComponent snakeComp;
	private Thread thread;
	private Snake snake;
	
	private Direction direction = Direction.UP;
	
	private boolean started = false;
	
	public SnakeFrame() {
		initComponents();
		initGame();
		initFrame();	
	}
	
	private void initComponents() {
		setLayout(new GridBagLayout());
		addKeyListener(new KeyboardHandler());
		
		score = new ScorePanel();
		add(score, new GBC(0, 8, 8, 1));
		
		snakeComp = new SnakeComponent();
		add(snakeComp, new GBC(0, 0, 8, 8));

	}
	
	private void initGame() {
		snake = new Snake();
		Runnable r = new Game(score, snakeComp, snake, this);
		thread = new Thread(r);
	}
	
	private void initFrame() {
		pack();
		setTitle("Snake");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
	}
	
	public void setLost(boolean b) {
		started = b;
	}
	
	/**
	 * Starts new game
	 */
	public void newGame() {
		started = true;
		thread.start();
	}
	
	public void gameOver() {		
		int returnValue = JOptionPane.showConfirmDialog(this, 
				"Do you want to start a new game?", "GAME OVER!", JOptionPane
				.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
		
		switch (returnValue) {
			case JOptionPane.OK_OPTION:
				direction = Direction.UP;
				started = false;
				snake = new Snake();
				score.clear();
				snakeComp.initDefaults();
				score.repaint();
				snakeComp.repaint();
				Runnable r = new Game(score, snakeComp, snake, this);
				thread = null;
				thread = new Thread(r);
				break;
				
			case JOptionPane.CANCEL_OPTION:
				System.exit(0);
				break;
			default:
				JOptionPane.showMessageDialog(getParent(), 
						"Something went wrong :( /n Please relunch app");
				break;
		}
	}
	
	// The entry point of the game
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				new SnakeFrame();
			}
		});
	}
	
	private class KeyboardHandler extends KeyAdapter {
		
		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				if (direction == Direction.DOWN) return;
				if (!started) newGame();
				if (snake != null) {
					snake.changeDirection(Direction.UP);
					direction = Direction.UP;
				}
			}
			
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				if (direction == Direction.UP) return;
				if (!started) newGame();
				if (snake != null) {
					snake.changeDirection(Direction.DOWN);
					direction = Direction.DOWN;
				}
			}
			
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				if (direction == Direction.RIGHT) return;
				if (!started) newGame();
				if (snake != null) {
					snake.changeDirection(Direction.LEFT);
					direction = Direction.LEFT;
				}
			}
			
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				if (direction == Direction.LEFT) return;
				if (!started) newGame();
				if (snake != null) {
					snake.changeDirection(Direction.RIGHT);
					direction = Direction.RIGHT;
				}
			}
		}
	}
}
