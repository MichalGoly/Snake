package com.michalgoly.snake;

import java.awt.EventQueue;
import java.awt.GridBagLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.horstmann.corejava.GBC;

/**
 * SnakeFrame initializes and lays out other GUI componnets within itself. It
 * also constructs the snake object and prepares the game thread, which will be 
 * started later on, when a user presses one of the arrow keys. 
 * 
 * @author Michal Goly
 */
public class SnakeFrame extends JFrame {
	
	private ScorePanel scorePanel;
	private GameField gameField;
	private Thread thread;
	private Snake snake;
	
	// Current direction of the snake
	private Direction direction = Direction.UP;
	
	private boolean started = false;
	
	/**
	 * This constructor initializes GUI components, game thread and the frame
	 * itself.
	 */
	public SnakeFrame() {
		initComponents();
		initGame();
		initFrame();	
	}
	
	private void initComponents() {
		setLayout(new GridBagLayout());
		addKeyListener(new KeyboardHandler());
		
		scorePanel = new ScorePanel();
		add(scorePanel, new GBC(0, 8, 8, 1));
		
		gameField = new GameField();
		add(gameField, new GBC(0, 0, 8, 8));

	}
	
	private void initGame() {
		snake = new Snake(gameField, scorePanel);
		Runnable r = new Game(gameField, snake, this);
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
	
	/**
	 * Starts a new game
	 */
	public void newGame() {
		started = true;
		thread.start();
	}
	
	/**
	 * Ends the game and enables the user to decide whether he wants to continue
	 * to play by displaying a dialog box. 
	 */
	public void gameOver() {		
		int returnValue = JOptionPane.showConfirmDialog(this, 
				"Do you want to start a new game?", "GAME OVER!", JOptionPane
				.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
		
		switch (returnValue) {
			case JOptionPane.OK_OPTION:
				direction = Direction.UP;
				started = false;
				snake = new Snake(gameField, scorePanel);
				scorePanel.clear();
				gameField.initDefaults();
				scorePanel.repaint();
				gameField.repaint();
				Runnable r = new Game(gameField, snake, this);
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
		/*
		 * Because Swing is not thread safe we have to run the code within the
		 * event dispatch thread. You can find out more 
		 * <a href="https://docs.oracle.com/javase/tutorial/uiswing/concurrency/dispatch.html">here</a>
		 */
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				new SnakeFrame();
			}
		});
	}
	
	/*
	 * Acts as a KeyListener and responds to the events generated by one of the 4
	 * arrow keys, whenever they are pressed.
	 */
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
