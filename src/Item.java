import java.awt.Color;

//SUPERCLASS
public abstract class Item {
	protected int mX; // subclasses may use, no one else can
	private Shape mShape;
	protected Lane mLane;
	protected FroggerModel mModel;

	public Item(int x, Shape shape, Lane lane, FroggerModel model) {
		this.mShape = shape;
		this.mX = x;
		this.mLane = lane;
		this.mModel = model;
	}

	public void updatePosition() {
		if (mLane.isFacingLeft()) {
			mX += mLane.getSpeed();
		} else {
			this.mX -= mLane.getSpeed();
		}
	}

	public abstract boolean hasTouchedEnd(Lane lane);

	public abstract boolean hasItemEnteredPerimeter();

	public abstract void frogInItemPerimeter();

	public int getX() {
		return mX;
	}

	public void setX(int x) {
		this.mX = x;
	}
}
