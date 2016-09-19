

public class Room {
	int x;
	int y;
	private Boolean clean=true;

	public Room(int x, int y, Boolean clean) {
		super();
		this.x = x;
		this.y = y;
		this.clean = clean;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Room(Boolean clean) {
		super();
		this.clean = clean;
	}

	public Boolean getClean() {
		return clean;
	}

	@Override
	public String toString() {
		return "Room [x=" + x + ", y=" + y + ", clean=" + clean + "]";
	}

	public void setClean(Boolean clean) {
		this.clean = clean;
	}
	
	
	
}
