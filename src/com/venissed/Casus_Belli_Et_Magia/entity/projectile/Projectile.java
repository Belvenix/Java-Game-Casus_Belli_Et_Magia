package com.venissed.Casus_Belli_Et_Magia.entity.projectile;

import java.util.Random;

import com.venissed.Casus_Belli_Et_Magia.entity.Entity;
import com.venissed.Casus_Belli_Et_Magia.graphics.Sprite;

public abstract class Projectile extends Entity{
	
	protected final double xOrigin, yOrigin;
	protected double angle;
	protected double x, y;
	protected double distance;
	protected double nx, ny;
	protected double speed, range, damage;
	
	protected final Random random = new Random();
	
	public Projectile(double x, double y, double angle) {
		xOrigin = x;
		yOrigin = y;
		this.x = x;
		this.y = y;
		this.angle = angle;
		
	}
	
	public int getSpriteSize() {
		return this.sprite.getSize();
	}
	
	public Sprite getSprite(){
		return this.sprite;
	}
	
	protected void move(){
		
	}

}
