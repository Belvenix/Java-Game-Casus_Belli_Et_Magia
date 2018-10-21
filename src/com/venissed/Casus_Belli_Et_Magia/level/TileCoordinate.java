package com.venissed.Casus_Belli_Et_Magia.level;

public class TileCoordinate {

	private int x, y;
	private final int TILE_SIZE = 16;
	
	public TileCoordinate(int x, int y) {
		this.x = x * TILE_SIZE;
		this.y = y * TILE_SIZE;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public int[] getXY() {
		int[] r = new int [2];
		r[0] = this.x;
		r[1] = this.y;
		return r;
	}
	
}
