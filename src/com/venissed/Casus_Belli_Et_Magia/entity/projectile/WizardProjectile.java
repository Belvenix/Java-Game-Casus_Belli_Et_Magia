package com.venissed.Casus_Belli_Et_Magia.entity.projectile;

import com.venissed.Casus_Belli_Et_Magia.entity.spawner.ExplosionSpawner;
import com.venissed.Casus_Belli_Et_Magia.entity.spawner.ParticleSpawner;
import com.venissed.Casus_Belli_Et_Magia.graphics.Screen;
import com.venissed.Casus_Belli_Et_Magia.graphics.Sprite;

public class WizardProjectile extends Projectile {

	public static final int FIRE_RATE = 15;
	
	public WizardProjectile(double x, double y, double angle) {
		super(x, y, angle);
		range = 100 + random.nextInt(50);
		speed = 4;
		damage = 20;
		sprite = Sprite.projectileFireball;
		nx = Math.cos(angle) * speed;
		ny = Math.sin(angle) * speed;
	}

	public void update() {
		if(level.tileCollision((int)(x + nx), (int)(y + ny), 6, 5, 5))
		{
			level.add(new ExplosionSpawner((int)(x), (int)(y), 30, level));
			level.add(new ParticleSpawner((int)x, (int)y, 44*4, 50, level));
			remove();			
		}
		move();
	}

	public void move() {
		x += nx;
		y += ny;
		
		//System.out.println("Distance: " + distance());
		if(distance() > range)remove();
	}

	private double distance() {
		double dist = 0;
		dist = Math.sqrt((this.xOrigin - x) * (this.xOrigin - x) + (this.yOrigin - y) * (this.yOrigin - y));
		return dist;		
	}

	public void render(Screen screen) {
		screen.renderProjectile((int) x, (int) y, this);
	}
	
	

}
