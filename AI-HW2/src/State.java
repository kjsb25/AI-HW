
public class State {
	private Room[][] layout;
	private int vacuumX;
	private int vacuumY;
	
	
	public State(Room[][] layout, int vacuumX, int vacuumY) {
		super();
		this.layout = layout;
		this.vacuumX = vacuumX;
		this.vacuumY = vacuumY;
	}
	public Room[][] getLayout() {
		return layout;
	}
	public void setLayout(Room[][] layout) {
		this.layout = layout;
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
