package com.venissed.Casus_Belli_Et_Magia.entity;

import java.util.Random;
import com.venissed.Casus_Belli_Et_Magia.graphics.Screen;
import com.venissed.Casus_Belli_Et_Magia.graphics.Sprite;
import com.venissed.Casus_Belli_Et_Magia.level.Level;

public abstract class Entity {

	protected double x, y;
	protected Sprite sprite;
	private boolean removed = false;
	protected Level level;
	protected final Random random = new Random();
	
	public void update() {
		
	}
	
	public void render(Screen screen) {
		if(sprite != null) screen.renderSprite((int)x, (int)y, sprite, false);
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public void remove() {
		//Remove from level
		removed = true;
	}
	
	public boolean isRemoved() {
		return this.removed;
	}
	
	public void init(Level level) {
		this.level = level;
	}
	
	public Sprite getSprite() {
		return this.sprite;
	}
}
