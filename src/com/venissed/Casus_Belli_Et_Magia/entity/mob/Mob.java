package com.venissed.Casus_Belli_Et_Magia.entity.mob;

import java.util.ArrayList;
import java.util.List;

import com.venissed.Casus_Belli_Et_Magia.entity.Entity;
import com.venissed.Casus_Belli_Et_Magia.graphics.Screen;
import com.venissed.Casus_Belli_Et_Magia.graphics.Sprite;
import com.venissed.Casus_Belli_Et_Magia.level.Level;
import com.venissed.Casus_Belli_Et_Magia.entity.mob.Direction;
import com.venissed.Casus_Belli_Et_Magia.entity.particle.Particle;
import com.venissed.Casus_Belli_Et_Magia.entity.projectile.Projectile;
import com.venissed.Casus_Belli_Et_Magia.entity.projectile.WizardProjectile;

public abstract class Mob extends Entity {

	
	protected Direction dir = Direction.SOUTH;
	protected boolean walking = false;
	protected int health;
	
	protected void move(double dx, double dy) {
		if(dx != 0 && dy != 0) {
			move(dx, 0);
			move(0, dy);
			return;
		}
		
		while(dx != 0) {
			if(Math.abs(dx) > 1) {
				if(!collision(signum(dx),0)) {
					this.x += signum(dx);
				}
				else {
					
				}
				dx -= signum(dx);
			}
			else {
				if(!collision(dx,0)) {
					this.x += dx;
				}
				else {
					collisionDebug(dx,0);
				}
				dx = 0;
			}
		}
		
		while(dy != 0) {
			if(Math.abs(dy) > 1) {
				if(!collision(0,signum(dy))) {
					this.y += signum(dy);
				}
				else {
					collisionDebug(0,signum(dx));
				}
				dy -= signum(dy);
			}
			else {
				if(!collision(0,dy)) {
					this.y += dy;
				}
				else {
					collisionDebug(0,dy);
				}
				dy = 0;
			}
		}

		if (dy < 0) this.dir = Direction.NORTH;
		else if(dx < 0) this.dir = Direction.WEST;
		else if(dy > 0) this.dir = Direction.SOUTH;
		else if(dx > 0) this.dir = Direction.EAST;
	}
	
	private int signum(double value) {
		if(value < 0)return -1;
		else if(value == 0)return 0;
		return 1;
	}
	
	private boolean collision(double dx, double dy) {
		boolean solid = false;
		//System.out.println("I am at(" + this.x / 16 + ", " + this.y / 16 + ")");
		for(int c = 0; c < 4; c++) {
			//corner system, a little bit messy ;/ you have to test how it looks the best in game ;/
			double tx = ((this.x + dx) + c % 2) / 16;
			double ty = ((this.y + dy) + c / 2) / 16;
			double ix = Math.ceil(tx);
			double iy = Math.ceil(ty);
			if(c % 2 == 0)
				ix = Math.floor(tx);
			if(c / 2 == 0)
				iy = Math.floor(ty);
			if(level.getTile((int)ix, (int)iy).solid()) {
				//System.out.println("Collided with tile at (" + (int)ix + ", " + (int)iy + "), (" + (int)tx + ", " + (int)ty + ")");
				solid = true;
			}
			else {
				//System.out.println("Checking tile at (" + (int)ix + ", " + (int)iy + "), (" + (int)tx + ", " + (int)ty + ")");
			}
		}
		return solid;
	}
	
	private boolean collisionDebug(double dx, double dy) {
		boolean solid = false;
		for(int c = 0; c < 4; c++) {
			//corner system, a little bit messy ;/ you have to test how it looks the best in game ;/
			double tx = ((this.x + dx) + c % 2 ) / 16;
			double ty = ((this.y + dy) + c / 2 ) / 16;
			int ix = (int)Math.ceil(tx);
			int iy = (int)Math.ceil(ty);
			if(c % 2 == 0)ix = (int)Math.floor(tx);
			if(c / 2 == 0)iy = (int)Math.floor(ty);
			if(level.getTile(ix, iy).solid()) solid = true;
		}
		return solid;
	}

	public abstract void update();
	
	protected void shoot(double x, double y, double dir) {
		//System.out.println("Angle:" + Math.toDegrees(dir));
		Projectile p = new WizardProjectile(x, y, dir);
		level.add(p);
	}

	public abstract void render(Screen screen);
	
	public void init(Level level) {
		this.level = level;
	}


	
}
