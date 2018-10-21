package com.venissed.Casus_Belli_Et_Magia.util;

public class Vector2i {
	
	private int x, y;
		
	public Vector2i() {
		set(0,0);
	}
	
	public Vector2i(int x, int y) {
		set(x,y);
	}
		
	public Vector2i(Vector2i v) {
		set(v.getX(),v.getY());
	}
		
	public void set(int x, int y) {
		this.x = x;
		this.y = y;
	}
		
		
	public int getX() {
		return this.x;
	}
		
	public int getY() {
		return this.y;
	}
		
	public Vector2i add(Vector2i v){
		this.x += v.getX();
		this.y += v.getY();
		return this;
	}
		
	public Vector2i substract(Vector2i v){
		this.x -= v.getX();
		this.y -= v.getY();
		return this;
	}
		
	public double getDistance(Vector2i v) {
		int dx = this.getX() - v.getX();
		int dy = this.getY() - v.getY();
		return Math.sqrt((dx * dx) + (dy * dy));
	}
		
	public boolean equals(Object object) {
		if(!(object instanceof Vector2i))return false;
		Vector2i vec = (Vector2i) object;
		if(vec.getX() == this.getX() && vec.getY() == this.getY()) return true;
		return false;
	}
	
	public Vector2i setX(int x) {
		this.x = x;
		return this;
	}
	
	public Vector2i setY(int y) {
		this.y = y;
		return this;
	}
	
}
