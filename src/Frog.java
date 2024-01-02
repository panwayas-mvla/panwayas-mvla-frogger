import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Frog{
	// has a speed going left and right, coordinate, size, 
	private int speed, lives, x, y;
	public static int horiMove = 15, vertMove = FroggerView.laneDistance;
	private Coordinate coordinate;
	private Shape shape;

	//private final int height = ;  //set values
	//private final int width = ;

	public Frog(int lives, int speed, Shape shape) {
		//set the spawn location

		coordinate = new Coordinate(FroggerView.panelWidth/2, FroggerView.panelHeight);
		this.lives = lives;
		this.speed = speed;
		this.shape = shape;
	}

	//first lane, has to be within the xy and length of the box
	//first lane starts at lane distance
	public boolean frogEnteredWinLocation(WinPlace win) {
		int frogY = getCoordinate().getY();
		int frogXLeft = getCoordinate().getX();
		int frogXRight = getCoordinate().getX() + FroggerModel.frogWidth;

		//win coordinate and widths
		int coordX = win.getCoordinate().getX(), coordY = win.getCoordinate().getY();
		int width = win.getShape().getWidth(), height = win.getShape().getHeight();

		if(frogY >= 0 && frogY <= FroggerView.laneDistance) {
			if(((coordX <= frogXRight) && (coordX +width  >= frogXRight)) || 
					((coordX <= frogXLeft) && (coordX + width >= frogXLeft))) {
				return true;
			} else {

			}
		}
		return false;

	}

	public Coordinate getCoordinate() {
		return coordinate;
	}

	public void setCoordinate(Coordinate coordinate) {
		this.coordinate = coordinate;
	}

	public Shape getShape() {
		return shape;
	}

	public void setShape(Shape shape) {
		this.shape = shape;
	}

	public void moveLeft() {
		if(this.coordinate.getX() > 0) {
			this.coordinate.setX(this.coordinate.getX() - horiMove);
		}
	}

	public void moveRight() {
		if(this.coordinate.getX() < FroggerView.panelWidth - FroggerModel.frogWidth) {
			this.coordinate.setX(this.coordinate.getX() + horiMove);
		}
	}

	public void moveUp() {
		if(this.coordinate.getY() > 0) {
			this.coordinate.setY(this.coordinate.getY() - vertMove);
		}
	}

	public void moveDown() {
		//	System.out.println("frogY = " + this.coordinate.getY() + " panel height = " + FroggerView.panelHeight);
		if(this.coordinate.getY() < FroggerView.panelHeight - FroggerModel.frogHeight) {
			this.coordinate.setY(this.coordinate.getY() + vertMove); 
		}
	}

	
	//keeps the frog from leaving the bounds of the play area
	public boolean hasTouched(Lane lane) {
		if(getCoordinate().getX() >= FroggerView.panelWidth) {
			// keep setting the frogs location back to the edge, or within the region,
			// create some sort of coordinate for the frog cause otherwise i wont know when
			// it goes out of bounds
			this.coordinate.setX(FroggerView.panelWidth);
			return true;
		}

		if(getCoordinate().getX() <= 0) {
			this.coordinate.setX(0);
			return true;
		}
		return false;
	}





}
