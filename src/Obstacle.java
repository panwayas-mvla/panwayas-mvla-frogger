

public class Obstacle extends Item {


	public Obstacle(int x, Shape shape, Lane lane, FroggerModel model) {
		super(x, shape,lane, model);
	}

	public boolean hasTouchedEnd(Lane lane) {
		if(lane.isFacingLeft()) {
			if(mX > FroggerView.panelWidth + FroggerModel.obstacleWidth) {
				// keep setting the frogs location back to the edge, or within the region,
				// create some sort of coordinate for the frog cause otherwise i wont know when
				// it goes out of bounds
				return true;
			}
		} else {
			if(mX < 0 - FroggerModel.obstacleWidth) {
				return true;
			}
		}
		return false;
	}

	//checks if the frog has entered an obstacle's (car) area - which means it dies
	public boolean hasItemEnteredPerimeter() {
		int frogXLeft = mModel.getFrog().getCoordinate().getX();
		int frogXRight = mModel.getFrog().getCoordinate().getX() + FroggerModel.frogWidth;
		int frogY = mModel.getFrog().getCoordinate().getY();
		int frogHeight = mModel.getFrog().getShape().getHeight();
		
		if(frogY >= mLane.getLaneHeight() && 
				(frogY + frogHeight) <= mLane.getLaneHeight() + FroggerView.laneDistance) {
			if((mX <= frogXRight && mX + FroggerModel.obstacleWidth >= frogXRight) ||
					(mX <= frogXLeft && mX + FroggerModel.obstacleWidth >= frogXLeft)) {
				return true;
			}
		}
		return false;
	}

	public void frogInItemPerimeter() {
		if(hasItemEnteredPerimeter()) {
			mModel.resetGame();
			//System.out.println("touched obstacle");
		}
	}
}
