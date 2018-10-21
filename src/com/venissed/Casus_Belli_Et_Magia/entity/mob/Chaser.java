package com.venissed.Casus_Belli_Et_Magia.entity.mob;

import java.util.List;

import com.venissed.Casus_Belli_Et_Magia.graphics.AnimatedSprite;
import com.venissed.Casus_Belli_Et_Magia.graphics.Screen;
import com.venissed.Casus_Belli_Et_Magia.graphics.Sprite;
import com.venissed.Casus_Belli_Et_Magia.graphics.SpriteSheet;

public class Chaser extends Mob{
	
	private AnimatedSprite chaserAnimation = new AnimatedSprite(SpriteSheet.chaserAnimation, 16, 16, 4, 10);

	private AnimatedSprite animSprite = chaserAnimation;

	private Direction chosenDirection = Direction.NOWHERE;
	private int time = 0;
	private double speed = 0.8;
	private int range = 75;

	public Chaser(int x, int y) {
		this.x = x << 4;// * 16
		this.y = y << 4;// * 16
		sprite = chaserAnimation.getSprite();
	}

	private void move() {
		double dy = 0, dx = 0;		
		List<Player> players = level.getPlayersInRange(this, range);
		if(players.size() > 0) {
			Player player = players.get(0);
			if(player != null) {
				if(this.x < player.getX())dx += Math.min(speed, Math.abs(player.getX() - this.x));
				if(this.x > player.getX())dx -= Math.min(speed, Math.abs(player.getX() - this.x));
				if(this.y < player.getY())dy += Math.min(speed, Math.abs(player.getY() - this.y));
				if(this.y > player.getY())dy -= Math.min(speed, Math.abs(player.getY() - this.y));			
			}
		}		
		
		if (dx != 0 || dy != 0) {
			
			move(dx, dy);
			walking = true;
		} else
			walking = false;
	}
	
	public void update() {
		move();
		animSprite.update();
	}

	public void render(Screen screen) {
		sprite = animSprite.getSprite();
		screen.renderMob((int)x, (int)y, this);
	}

}
