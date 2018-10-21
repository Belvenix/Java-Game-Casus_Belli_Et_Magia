package com.venissed.Casus_Belli_Et_Magia.util;

public class Vector2d {

	private double x, y;
	
	public Vector2d() {
		set(0,0);
	}
	
	public Vector2d(double x, double y) {
		set(x,y);
	}
	
	public Vector2d(Vector2d v) {
		set(v.getX(),v.getY());
	}
	
	public void set(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	
	public double getX() {
		return this.x;
	}
	
	public double getY() {
		return this.y;
	}
	
	public Vector2d add(Vector2d v){
		this.x += v.getX();
		this.y += v.getY();
		return this;
	}
	
	public Vector2d substract(Vector2d v){
		this.x -= v.getX();
		this.y -= v.getY();
		return this;
	}
	
	public double getDistance(Vector2d v) {
		double dx = this.getX() - v.getX();
		double dy = this.getY() - v.getY();
		return Math.sqrt((dx * dx) + (dy * dy));
	}
	
	public boolean equals(Object object) {
		if(!(object instanceof Vector2d))return false;
		Vector2d vec = (Vector2d) object;
		if(vec.getX() == this.getX() && vec.getY() == this.getY()) return true;
		return false;
	}
	
	public Vector2d setX(double x) {
		this.x = x;
		return this;
	}
	
	public Vector2d setY(double y) {
		this.y = y;
		return this;
	}
	
}
