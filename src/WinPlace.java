
public class WinPlace {
	private Coordinate coordinate;
	private Shape shape;
	private FroggerModel model;
	
	
	public WinPlace() {
		coordinate = new Coordinate(FroggerView.panelWidth/2, FroggerView.laneDistance);
		shape = new Shape(FroggerModel.frogWidth + 10, FroggerModel.frogHeight);
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
	
	
}
