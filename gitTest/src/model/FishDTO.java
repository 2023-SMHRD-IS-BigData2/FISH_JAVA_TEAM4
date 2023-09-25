package model;

public class FishDTO {
	private String name;
	private double size;
	private int level;
	private int index;
	
	public FishDTO(String name, double size, int level, int index) {
		super();
		this.name = name;
		this.size = size;
		this.level = level;
		this.index = index;
	}
	
	public FishDTO(String name, double size, int level) {
		super();
		this.name = name;
		this.size = size;
		this.level = level;
	} 

	public String getName() {
		return name;
	}

	public double getSize() {
		return size;
	}

	public int getLevel() {
		return level;
	}

	public int getIndex() {
		return index;
	}
	
}

