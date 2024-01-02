import java.awt.Color;
import java.util.ArrayList;

public class Lane {
	// height refers to the distance from the previous lane going up to the next lane
	// |     |    the height would be 2
	// |     |
	private int speed, mX;
	private Color color;
	private boolean facingLeft, hasObstacles;
	private int numItems = 0;
	private FroggerView view;
	private FroggerModel model;
	private Shape shape;

	// height of car needs to be less than this
	private int laneHeight;


	// dynamic dispatch
	// treat the items class like its abstract 
	private ArrayList<Item> items = new ArrayList<Item>();

	//access the model's final height and width things and use that to determine length of lanes 

	public Lane(FroggerView view, int mmX, int speed, int numItems,int laneHeight,
			boolean facingLeft, boolean hasObstacles, Shape shape, Color color, FroggerModel model) 
	{
		this.shape = shape;
		this.view = view;
		this.speed = speed;
		this.numItems = numItems;
		this.color = color;
		this.facingLeft = facingLeft;
		this.hasObstacles = hasObstacles;
		this.laneHeight = laneHeight;
		// mmX is the variable being passed in
		this.mX = mmX;
		int widthDistance = FroggerView.panelWidth / FroggerView.numItems;
		double randMultiplier = (Double)(Math.random() + 0.5);
		int x = (int)(randMultiplier *  widthDistance) - 150;
		for(int i = 0; i < this.numItems; i++) {
			if(hasObstacles) {
				//***** FIX CONSTRUCTOR
				//SOME CHARACTERSTICS DONT MAKE SENSE IN THE CONSTRUCTOR
				//LIKE WHY WOULD HASTOUCHED EVEN BE THERE WHEN ITS ALMOST ALWAYS FALSE
				//AND HALF OF THOSE VARIABLES ARENT EVEN IN THIS CLASS SO WTF
				if(x >= 600) {
					x = 530;
				}
				items.add(new Obstacle(x, shape, this, model));
			} else {
				if(x >= 600) {
					x = 530;
				}
				items.add(new Helper(x, shape, this, model));
			}
			x += widthDistance;
		}
	}

	public void ifItemShouldReappear() {
		for(int i = 0; i < items.size(); i++) {
			if(items.get(i).hasTouchedEnd(this)) {
				if(facingLeft) {
					items.get(i).setX(0);
				} else {
					items.get(i).setX(FroggerView.panelWidth);
				}
			}
		}
	}

	//getters and setters
	public boolean isFacingLeft() {
		return facingLeft;
	}

	public void setFacingLeft(boolean facingLeft) {
		this.facingLeft = facingLeft;
	}
	
	public boolean isHasObstacles() {
		return hasObstacles;
	}

	public void setHasObstacles(boolean hasObstacles) {
		this.hasObstacles = hasObstacles;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public ArrayList<Item> getItems() {
		return items;
	}

	public void setItems(ArrayList<Item> items) {
		this.items = items;
	}

	public FroggerView getView() {
		return view;
	}

	public void setView(FroggerView view) {
		this.view = view;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public int getLaneHeight() {
		return laneHeight;
	}

	public void setLaneHeight(int laneHeight) {
		this.laneHeight = laneHeight;
	}

	public Shape getShape() {
		return shape;
	}

	public void setShape(Shape shape) {
		this.shape = shape;
	}
}
