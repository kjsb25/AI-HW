
public class Node {
	Room[][] state;
	int vacuumX;
	int vacuumY;
	public Node(Room[][] state, int vacuumX, int vacuumY) {
		super();
		this.state = state;
		this.vacuumX = vacuumX;
		this.vacuumY = vacuumY;
	}
	public Room[][] getState() {
		return state;
	}
	public void setState(Room[][] state) {
		this.state = state;
	}
	public int getVacuumX() {
		return vacuumX;
	}
	public void setVacuumX(int vacuumX) {
		this.vacuumX = vacuumX;
	}
	public int getVacuumY() {
		return vacuumY;
	}
	public void setVacuumY(int vacuumY) {
		this.vacuumY = vacuumY;
	}
	
	
}
