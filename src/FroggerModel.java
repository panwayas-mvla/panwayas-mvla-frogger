import java.awt.Color;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;

public class FroggerModel implements ActionListener{
	// add variables that check the size of the board
	// that will determine if the item has gone out of bounds 
	private ArrayList<Lane> lanes = new ArrayList<Lane>();
	private Timer updatePositionsTimer;
	private Timer itemMoveTimer;
	private boolean isGameOver;
	private FroggerView view;
	private Frog frog;
	private WinPlace win;

	private int score = 0;
	public static final int helperLaneStarts = 6;
	public static final int obstacleWidth = 28, obstacleHeight = 20;
	public static final int helperWidth = 45, helperHeight = 20;
	public static final int frogWidth = 28, frogHeight = 20;

	public FroggerModel(FroggerView view) {
		int speed = 0, laneHeight = FroggerView.panelHeight, numItems = 0;
		boolean facingLeft = false, hasObstacles;
		Shape obstacleShape = new Shape(obstacleWidth, obstacleHeight);
		Shape helperShape = new Shape(helperWidth, helperHeight);
		Shape finShape;
		Color color;
		int endX;
		this.view = view;
		for(int i = 0; i < FroggerView.numLanes; i++) {
			/*
			 * FroggerView view
			 * int speed - be a random number though a range
			 * int numItems - 2 to 3 items is enough
			 * int laneHeight - 
			 * boolean facingLeft
			 * boolean hasObstacles
			 * Shape shape
			 * Color color
			 */
			if(facingLeft) {
				endX = 0;
			} else {
				endX = FroggerView.panelWidth;
			}


			//tells when the lane should will be purple (has no items)
			if(i == 0 || i == helperLaneStarts || i == FroggerView.numLanes - 1) {
				numItems = 0;
			} else {
				numItems = 3;
			}

			// determines what the y coordinate of the lane should be
			if(i == 0) {
				laneHeight = FroggerView.panelHeight;
			} else {
				laneHeight -= FroggerView.laneDistance;
			}
			speed = (int)(Math.random() * 4) + 1;

			//switches the direction the cars are headed every other lane
			if(i % 2 == 0) {
				facingLeft = true;
			} else {
				facingLeft = false;
			}

			//to make the helpers long 
			if(i > helperLaneStarts) {
				finShape = helperShape;
				hasObstacles = false;
			} else {
				finShape = obstacleShape;
				hasObstacles = true;
			}


			lanes.add(new Lane(this.view, endX, speed, numItems, laneHeight, facingLeft, hasObstacles, finShape, Color.PINK, this));
		}
		//lives, speed, shape
		frog = new Frog(FroggerView.lives, FroggerView.horiFrogSpeed, new Shape(frogWidth, frogHeight));
		win = new WinPlace();

		updatePositionsTimer = new Timer(50, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateItemPositions();

				if(frog.frogEnteredWinLocation(win)) {
					view.setGameToBeWon();
					gameOverButWon();
				}  else {

					if(detectFrogOnWater()) {
						//System.out.println("frog on water");
						resetGame();

					}
				}


				if(FroggerView.lives == 0) {
					gameOverButLost();
				}
				//view.setStats(stats);
			}

		});
	}

	//implement timer

	public void setupNewGame(int timeBetweenItemMoves) {
		itemMoveTimer = new Timer(timeBetweenItemMoves, this);
		score = 0;
		isGameOver = false;

	}

	public void startGame() {
		itemMoveTimer.start();
		updatePositionsTimer.start();
	}

	public void resumeGame() {
		if(itemMoveTimer != null) {
			itemMoveTimer.start();
		}
	}

	public void pauseGame() {
		if(itemMoveTimer != null) {
			itemMoveTimer.stop();
		}
	}

	public void gameOverButLost() {
		isGameOver = true;
		itemMoveTimer.stop();
		itemMoveTimer = null;
		updatePositionsTimer.stop();
		view.setGameToBeOver();
	}
	
	public void gameOverButWon() {
		isGameOver = true;
		itemMoveTimer.stop();
		itemMoveTimer = null;
		updatePositionsTimer.stop();
	}

	public void resetGame() {
		FroggerView.lives--;
		view.setLivesLabel(FroggerView.lives);
		frog.setCoordinate(new Coordinate(FroggerView.panelWidth/2, FroggerView.panelHeight));
		view.repaint();
		System.out.println(FroggerView.lives);
		view.repaint();
	}

	public void resetNewGame() {
		FroggerView.lives = FroggerView.maxLives;
		view.setLivesLabel(FroggerView.lives);
		score = 0;
		frog.setCoordinate(new Coordinate(FroggerView.panelWidth/2, FroggerView.panelHeight));
		view.repaint();
	}
	
	//runs through the timer, keeps all items moving and has ifs to keep the game moving
	public void updateItemPositions() {
		for(Lane lane: lanes) {
			for(Item item: lane.getItems()) {
				item.updatePosition();
				lane.ifItemShouldReappear();
				item.frogInItemPerimeter();
				view.repaint();

			}
		}
	}

	public boolean detectFrogOnWater() {
		int frogXLeft = getFrog().getCoordinate().getX();
		int frogXRight = getFrog().getCoordinate().getX() + FroggerModel.frogWidth;
		int frogY = this.getFrog().getCoordinate().getY();
		Lane frogLane = null;
		for(Lane lane: lanes) {
			if(!(lane.isHasObstacles())) {
				if(frogY >= lane.getLaneHeight() && 
						(frogY + frogHeight) <= lane.getLaneHeight() + FroggerView.laneDistance) {
					frogLane = lane;
					break;
				}
			}
		}
		if(frogLane != null) {
			for(Item item: frogLane.getItems()) {
				// if frog in item
				if(((item.getX() <= frogXRight) && (item.getX() + FroggerModel.helperWidth) >= frogXRight) ||
						((item.getX() <= frogXLeft) && (item.getX() + FroggerModel.helperWidth) >= frogXLeft)) {
					return false; //on item
				} 
			}
			return true; // on water
		}
		return false;
	}


	@Override
	public void actionPerformed(ActionEvent e) {

	}



	public ArrayList<Lane> getLanes() {
		return lanes;
	}

	public void setLanes(ArrayList<Lane> lanes) {
		this.lanes = lanes;
	}

	public Frog getFrog() {
		return frog;
	}

	public void setFrog(Frog frog) {
		this.frog = frog;
	}

	public WinPlace getWin() {
		return win;
	}

	public void setWin(WinPlace win) {
		this.win = win;
	}


}
