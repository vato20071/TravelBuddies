package core;

public class Dislike {
	private boolean allowPets, allowSmoke;
	private int id;
	public boolean isAllowPets() {
		return allowPets;
	}
	public void setAllowPets(boolean allowPets) {
		this.allowPets = allowPets;
	}
	public boolean isAllowSmoke() {
		return allowSmoke;
	}
	public void setAllowSmoke(boolean allowSmoke) {
		this.allowSmoke = allowSmoke;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}
