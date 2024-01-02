
import java.awt.*;
import java.awt.event.*;
import java.awt.event.KeyListener;

import javax.swing.*;

public class FroggerView extends JFrame {
	public static int panelHeight = 600;
	public static int panelWidth = 600;
	public static final int numLanes = 12;
	public static final int laneDistance = panelHeight / numLanes;
	public static final int numItems = 3;

	public static int score = 0;
	public final static int maxLives = 5;
	public static int lives = maxLives; //SUBJECT TO CHANGE
	public static int horiFrogSpeed = 5;

	//CHANGE TO ADD VALUES TO THE AMOUNT OF OBSTACLES AND HELPERS
	private CardLayout cardLayout;
	private AnimationPanel animationPanel; 
	private WelcomePanel welcomePanel;
	private GameOverPanel gameOverPanel;
	private GameWonPanel gameWonPanel;
	private JPanel gamePanel, mainPanel;
	private JLabel scoreLabel, livesLabel;
	private FroggerModel model;
	private FroggerController controller;
	private JButton startButton, restartButton, restartButton2;


	public FroggerView() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		cardLayout = new CardLayout();
		mainPanel = new JPanel(cardLayout);
		gamePanel = new JPanel(new BorderLayout());
		//cardLayout.addLayoutComponent(welcomePanel, );

		welcomePanel = new WelcomePanel();
		mainPanel.add(welcomePanel, "WelcomePanel");
		mainPanel.add(gamePanel, "GamePanel");

		gameOverPanel = new GameOverPanel();
		mainPanel.add(gameOverPanel, "GameOverPanel");
		JPanel restartPanel = new JPanel();
		gameOverPanel.add(restartPanel);
		restartButton = new JButton("Restart");
		restartPanel.add(new JLabel("Press to restart"));
		restartPanel.add(restartButton);
		
		gameWonPanel = new GameWonPanel();
		mainPanel.add(gameWonPanel, "GameWonPanel");
		//error in creating restartpanel when called by 2 different panels
		JPanel restartGamePanel = new JPanel();
		restartButton2 = new JButton("Restart ");
		gameWonPanel.add(restartGamePanel);
		restartGamePanel.add(new JLabel("Press to restart"));
		restartGamePanel.add(restartButton2);


		JPanel buttonPanel = new JPanel();
		welcomePanel.add(buttonPanel);
		buttonPanel.add(new JLabel("Press to begin game"));
		startButton = new JButton("Start");
		buttonPanel.add(startButton);
		this.setContentPane(mainPanel);
		mainPanel.requestFocusInWindow();

		JPanel statsPanel = new JPanel();
		gamePanel.add(statsPanel, BorderLayout.NORTH);
		scoreLabel = new JLabel("Score: " + score);
		livesLabel = new JLabel("Lives: " + lives);
		statsPanel.add(scoreLabel);
		statsPanel.add(livesLabel);

		animationPanel = new AnimationPanel();
		gamePanel.add(animationPanel, BorderLayout.CENTER);

		//animationPanel.setFocusable(true);
		//animationPanel.requestFocusInWindow();
		cardLayout.show(mainPanel, "WelcomePanel");
		
		gamePanel.setFocusable(true);
		gamePanel.requestFocusInWindow();
	}


	public void showPanel(String panelName) {
		cardLayout.show(mainPanel, panelName);
	}
	/*
	public void initializeAnimationPanel() {
		animationPanel = new AnimationPanel();
		gamePanel.add(animationPanel, BorderLayout.CENTER);
	}
	 */
	public FroggerModel getModel() {
		return model;
	}

	public void setModel(FroggerModel model) {
		this.model = model;
	}

	public JPanel getGamePanel() {
		return gamePanel;
	}

	public void setGamePanel(JPanel gamePanel) {
		this.gamePanel = gamePanel;
	}

	public JLabel getScoreLabel() {
		return scoreLabel;
	}
	
	public void setScoreLabel(int score) {
		this.scoreLabel.setText("Score: " + score);
	}
	public void setLivesLabel(int lives) {
		this.livesLabel.setText("Lives: " + lives);
	}


	public JLabel getLivesLabel() {
		return livesLabel;
	}




	public void addActionListener(ActionListener listener) {
		//restartButton.addActionListener(listener);
		//System.out.println("adding actionlistener");
		startButton.addActionListener(listener);
		restartButton.addActionListener(listener);
		restartButton2.addActionListener(listener);
	}

	/*
	public void setStats(FroggerStats stats) {
		score = stats.score;
		scoreLabel.setText("Score: " + score);
		livesLabel.setText("Lives" + lives);
		repaint();
	}
	 */
	public int getAnimationPanelWidth() {
		return animationPanel.getWidth();
	}

	public int getAnimationPanelHeight() {
		return animationPanel.getHeight();
	}

	public void addMouseListener(MouseListener listener) {
		animationPanel.addMouseListener(listener);
	}


	public void setGameToBeOver() {
		//if i show gameoverpanel, it is calling the incorrect parent function
		//however if i called gamepanel, that would be the correct parent
		//however if i do that, it wouldnt work becuase gamepanel is already there, meaning nothing would change 
		setLivesLabel(FroggerView.lives);
		cardLayout.show(mainPanel, "GameOverPanel");
		gamePanel.requestFocusInWindow();
	}
	
	public void setGameToBeWon() {
		FroggerView.score++;
		setScoreLabel(FroggerView.score);
		setLivesLabel(FroggerView.lives);
		cardLayout.show(mainPanel, "GameWonPanel");
		gamePanel.requestFocusInWindow();
		System.out.println("Has won " + FroggerView.score);
	}
	
	


	private class WelcomePanel extends JPanel {
		public void paintComponent(Graphics g) {
			Color customColor = new Color(0,0,128);
			g.setColor(customColor);
			g.fillRect(0,0, panelWidth, panelHeight + 100);
			g.drawRect(0, 0, panelWidth, panelHeight + 100);
			
			g.setColor(Color.WHITE);
			g.setFont(new Font("Courier", Font.BOLD,25));
			g.drawString("Frogger", FroggerView.panelWidth/2 - 60, FroggerView.panelHeight/2);
			g.drawString("One Player Game", FroggerView.panelWidth/2 - 80, FroggerView.panelHeight/3);
			
			repaint();
		}
	}
	
	private class GameWonPanel extends JPanel {
		public void paintComponent(Graphics g) {
			Color customRed = new Color(219, 0, 0); {
				g.setColor(customRed);
				g.setFont(new Font("Courier",Font.BOLD, 60));
				g.drawString("YOU WON", FroggerView.panelWidth / 2 - 130, FroggerView.panelHeight /2);
			}
		}
	}


	private class GameOverPanel extends JPanel {
		public void paintComponent(Graphics g) {
			Color customRed = new Color(219, 0,0); {
				g.setColor(customRed);
				g.setFont(new Font("Courier",Font.BOLD, 60));
				g.drawString("YOU DIED", FroggerView.panelWidth/2 - 130, FroggerView.panelHeight/2);
				g.drawString("GAME OVER", FroggerView.panelWidth/2 - 150, FroggerView.panelHeight/2 + 60);
				//maybe put a button using cardlayout to allow them to restart the game
			}
		}
	}

	public class AnimationPanel extends JPanel {
		@Override
		public void paintComponent(Graphics g) {
			//for painting all of the moving objects
			//gives a random value from 0 to 2 as a multiplier
			//implement something to see if they are a helper or obstacle
			int counter = 0;
			for(Lane lane : model.getLanes()) {
			//	g.drawString("Lane " + counter, FroggerView.panelWidth/2, lane.getLaneHeight());
				if(counter == FroggerModel.helperLaneStarts || counter == FroggerView.numLanes ) { 
					g.setColor(Color.MAGENTA);
					g.fillRect(0, lane.getLaneHeight(), panelWidth, laneDistance);
					g.drawRect(0, lane.getLaneHeight(), panelWidth, laneDistance);
				} else if(counter > 6) { //water lanes
					g.setColor(Color.BLUE);
					g.fillRect(0, lane.getLaneHeight(), panelWidth, laneDistance);
					g.drawRect(0, lane.getLaneHeight(), panelWidth, laneDistance);
				} else if(counter == 0) { //first lane
					Color gold = new Color(255, 215, 0);
					g.setColor(gold);
					g.fillRect(0, lane.getLaneHeight(), panelWidth, laneDistance);
				} else {
					g.setColor(Color.BLACK);
					g.fillRect(0, lane.getLaneHeight(), panelWidth, laneDistance);
					g.drawRect(0, lane.getLaneHeight(), panelWidth, laneDistance);
				}
				//System.out.println(lane.getLaneHeight());
				for(Item item: lane.getItems()) {
					g.setColor(lane.getColor());
					// parameters are x, y, width, height
					g.fillRect(item.getX() , lane.getLaneHeight(), 
							lane.getShape().getWidth(), lane.getShape().getHeight());
					g.setColor(lane.getColor());
					g.drawRect(item.getX(), lane.getLaneHeight(), 
							lane.getShape().getWidth(), lane.getShape().getHeight());
				}
				counter++;

			} 
			//creating frog
			g.setColor(Color.GREEN);
			g.fillRect(model.getFrog().getCoordinate().getX(), model.getFrog().getCoordinate().getY(),
					model.getFrog().getShape().getWidth(), model.getFrog().getShape().getWidth());
			g.drawRect(model.getFrog().getCoordinate().getX(), model.getFrog().getCoordinate().getY(),
					model.getFrog().getShape().getWidth(), model.getFrog().getShape().getWidth());
			
			//creating winlocation
			Color grey = new Color(128,128,128);
			g.setColor(grey);
			g.fillRect(model.getWin().getCoordinate().getX(), model.getWin().getCoordinate().getY(), 
					model.getWin().getShape().getWidth(), model.getFrog().getShape().getHeight());
			g.drawRect(model.getWin().getCoordinate().getX(), model.getWin().getCoordinate().getY(), 
					model.getWin().getShape().getWidth(), model.getFrog().getShape().getHeight());
		}
	}
}


