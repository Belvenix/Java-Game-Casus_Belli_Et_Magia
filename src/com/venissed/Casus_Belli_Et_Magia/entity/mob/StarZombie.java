package com.venissed.Casus_Belli_Et_Magia.entity.mob;

import java.util.List;

import com.venissed.Casus_Belli_Et_Magia.graphics.AnimatedSprite;
import com.venissed.Casus_Belli_Et_Magia.graphics.Screen;
import com.venissed.Casus_Belli_Et_Magia.graphics.SpriteSheet;
import com.venissed.Casus_Belli_Et_Magia.level.Node;
import com.venissed.Casus_Belli_Et_Magia.util.Vector2d;
import com.venissed.Casus_Belli_Et_Magia.util.Vector2i;

public class StarZombie extends Mob{

	
	private AnimatedSprite up = new AnimatedSprite(SpriteSheet.zombie_up, 16, 16, 4, 10);
	private AnimatedSprite down = new AnimatedSprite(SpriteSheet.zombie_down, 16, 16, 4, 10);
	private AnimatedSprite right = new AnimatedSprite(SpriteSheet.zombie_right, 16, 16, 4, 10);
	private AnimatedSprite left = new AnimatedSprite(SpriteSheet.zombie_left, 16, 16, 4, 10);

	private AnimatedSprite animSprite = down;

	private Direction chosenDirection = Direction.NOWHERE;
	private int dx = 0, dy = 0;
	private int time = 0;
	private double speed = 0.8;
	private int range = 75;
	private List<Node> path = null;
	
	
	public StarZombie(int x, int y) {
		this.x = x << 4;// * 16
		this.y = y << 4;// * 16
		sprite = animSprite.getSprite();
	}
	
	private void move(){
		dx = 0;
		dy = 0;
		double px = level.getPlayerAt(0).getX();
		double py = level.getPlayerAt(0).getY();
		Vector2i start = new Vector2i((int)this.getX() / 16, (int)this.getY() / 16);
		Vector2i goal = new Vector2i((int)px / 16, (int)py / 16);
		if(time % 60 == 0) {
			path = level.findPath(start, goal);
			System.out.println(px + ", " + py);
		}
		if(path != null) {
			if(path.size() > 0) {
				Vector2i vec = path.get(path.size() - 1).tile; 
				if (x < (vec.getX() * 16)) dx++;
				if (x > (vec.getX() * 16)) dx--;
				if (y < (vec.getY() * 16)) dy++;
				if (y > (vec.getY() * 16)) dy--;
			}
		}
		else {
			if(time % 60 == 0) {
				//System.out.println("I got lost!");				
			}
		}
		//System.out.println(dx + ", " + dy);
	}
	
	public void update() {
		move();
		time++;
		if(time > 10000) time = 0;
		dir = Direction.NOWHERE;
		if(walking) animSprite.update();
		else animSprite.setFrame(0);		
		if (dx < 0) {
			dir = Direction.WEST;
			animSprite = left;
		} 
		else if (dx > 0) {
			dir = Direction.EAST;
			animSprite = right;
		}
		if (dy < 0) {
			dir = Direction.NORTH;
			animSprite = up;
		}
		else if (dy > 0) {
			dir = Direction.EAST;
			animSprite = down;
		}
		if (dx != 0 || dy != 0) {
			move(dx, dy);
			walking = true;
		} else
			walking = false;
		animSprite.update();
	}

	public void render(Screen screen) {
		sprite = animSprite.getSprite();
		screen.renderMob((int)x, (int)y, this);
	}

	
}
