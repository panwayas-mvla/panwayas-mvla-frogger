
public class Helper extends Item{
	//this is just an attribute, won't be checked till later
	//so it shouldnt be in constructor
	public Helper(int x, Shape shape, Lane lane,FroggerModel model) {
		super(x, shape, lane, model);

	}

	public boolean hasTouchedEnd(Lane lane) {
		if(lane.isFacingLeft()) {
			if(mX > FroggerView.panelWidth + FroggerModel.helperWidth) {
				// keep setting the frogs location back to the edge, or within the region,
				// create some sort of coordinate for the frog cause otherwise i wont know when
				// it goes out of bounds
				return true;
			}
		} else {
			if(mX < 0 - FroggerModel.helperWidth) {
				return true;
			}
		}
		return false;
	}

	public boolean hasItemEnteredPerimeter() {
		int frogXLeft = mModel.getFrog().getCoordinate().getX();
		int frogXRight = mModel.getFrog().getCoordinate().getX() + FroggerModel.frogWidth;
		int frogY = mModel.getFrog().getCoordinate().getY();
		int frogHeight = mModel.getFrog().getShape().getHeight();

		int helperLaneHeight = FroggerView.laneDistance * FroggerModel.helperLaneStarts;
		if(frogY >= mLane.getLaneHeight() && 
				(frogY + frogHeight) <= mLane.getLaneHeight() + FroggerView.laneDistance) {
			if((mX <= frogXRight && mX + FroggerModel.helperWidth >= frogXRight) ||
					(mX <= frogXLeft && mX + FroggerModel.helperWidth >= frogXLeft)) {
				//System.out.println("on lane");
				return true;
			} else {
				//mModel.resetGame();
				//System.out.println("hit water");
			}
			//if the frog is below the y line of the helperlaneheight
			//it has to be on a helper, or else it has died, 
			//System.out.println("not on lane");
			//mModel.resetGame();

		}
		return false;
	}

	@Override
	public void frogInItemPerimeter() {
		int frogY = mModel.getFrog().getCoordinate().getY();
		int frogXLeft = mModel.getFrog().getCoordinate().getX();
		int frogXRight = mModel.getFrog().getCoordinate().getX() + FroggerModel.frogWidth;
		int helperLaneHeight = FroggerView.laneDistance * FroggerModel.helperLaneStarts;
		if(hasItemEnteredPerimeter()) {
			frogMovingWithHelper();
		} else {
			if(frogY < helperLaneHeight && !(mX <= frogXRight && (mX + FroggerModel.helperWidth) >= frogXRight) ||
					(mX <= frogXLeft && (mX + FroggerModel.helperWidth) >= frogXLeft)) {
				//mModel.resetGame();
			}
		}


	}

	//updates frogs x position to move with the helper
	public void frogMovingWithHelper() {
		int frogX = mModel.getFrog().getCoordinate().getX();
		int frogY = mModel.getFrog().getCoordinate().getY();
		if(mLane.isFacingLeft()) {
			if(mModel.getFrog().getCoordinate().getX() > 0) {
				mModel.getFrog().setCoordinate(new Coordinate(frogX + mLane.getSpeed(), frogY));
			}
		} else {
			if(mModel.getFrog().getCoordinate().getX() < FroggerView.panelWidth) {
				mModel.getFrog().setCoordinate(new Coordinate(frogX - mLane.getSpeed(), frogY));
			}
		}
	}
}
