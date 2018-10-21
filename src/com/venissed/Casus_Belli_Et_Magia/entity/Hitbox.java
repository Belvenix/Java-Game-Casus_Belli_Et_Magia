package com.venissed.Casus_Belli_Et_Magia.entity;

import com.venissed.Casus_Belli_Et_Magia.graphics.Screen;

public class Hitbox {

	private double center;
	private int radius;
	private Entity entity;
	
	Hitbox(double center, int radius, Entity entity){
		this.center = center;
		this.radius = radius;
		this.entity = entity;
		
	}
	
	public void update() {
		
	}
	
	public void render(Screen screen) {
		
	}
	
}
