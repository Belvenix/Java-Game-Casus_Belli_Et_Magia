package com.venissed.Casus_Belli_Et_Magia.entity.mob;

import java.util.List;

import com.venissed.Casus_Belli_Et_Magia.Game;
import com.venissed.Casus_Belli_Et_Magia.entity.Entity;
import com.venissed.Casus_Belli_Et_Magia.entity.projectile.Projectile;
import com.venissed.Casus_Belli_Et_Magia.entity.projectile.WizardProjectile;
import com.venissed.Casus_Belli_Et_Magia.graphics.AnimatedSprite;
import com.venissed.Casus_Belli_Et_Magia.graphics.Screen;
import com.venissed.Casus_Belli_Et_Magia.graphics.Sprite;
import com.venissed.Casus_Belli_Et_Magia.graphics.SpriteSheet;
import com.venissed.Casus_Belli_Et_Magia.input.Keyboard;
import com.venissed.Casus_Belli_Et_Magia.input.Mouse;

public class Player extends Mob {

	private Keyboard input;
	private Sprite sprite;
	private int anim = 0;
	private AnimatedSprite down = new AnimatedSprite(SpriteSheet.player_down, 16, 16, 4);
	private AnimatedSprite right = new AnimatedSprite(SpriteSheet.player_right, 16, 16, 4);
	private AnimatedSprite up = new AnimatedSprite(SpriteSheet.player_up, 16, 16, 4);
	private AnimatedSprite left = new AnimatedSprite(SpriteSheet.player_left, 16, 16, 4);

	private AnimatedSprite animSprite = down;

	private int fireRate = 0;

	public Player(Keyboard input) {
		this.input = input;
		animSprite = down;
		sprite = animSprite.getSprite();
	}

	// public Player()

	//pixel precision
	public Player(int x, int y, Keyboard input) {
		this.x = x;
		this.y = y;
		this.input = input;
		animSprite = down;
		sprite = animSprite.getSprite();
		fireRate = WizardProjectile.FIRE_RATE;
	}

	public void update() {
		if(walking) animSprite.update();
		else animSprite.setFrame(0);
		
		if (fireRate > 0) fireRate--;
		double dx = 0, dy = 0;
		double speed = 1.2;
		if (input.left) {
			dx -= speed;
			animSprite = left;
		} 
		if (input.right) {
			dx += speed;
			animSprite = right;
		}
		if (input.up) {
			dy -= speed;
			animSprite = up;
		}
		if (input.down) {
			dy += speed;
			animSprite = down;
		}
		if (dx != 0 || dy != 0) {
			move(dx, dy);
			walking = true;
		} else
			walking = false;
		clear();
		updateShooting();

	}

	private void clear() {
		for (int i = 0; i < level.getProjectiles().size(); i++) {
			Projectile p = level.getProjectiles().get(i);
			if (p.isRemoved()) {
				level.getProjectiles().remove(i);
				// System.out.println("Deleting Projectile!");
			}
		}

	}

	private void updateShooting() {
		if (Mouse.getButton() == 1 && fireRate <= 0) {
			double dx = Mouse.getX() - (Game.getWindowWidth()) / 2;
			double dy = Mouse.getY() - (Game.getWindowHeight()) / 2;
			double dir = Math.atan2(dy, dx);
			shoot(x, y, dir);
			fireRate = WizardProjectile.FIRE_RATE;
		}
	}

	public void render(Screen screen) {
		sprite = animSprite.getSprite();
		screen.renderTransparentSprite((int)x, (int)y, this.sprite, true);
		screen.renderDebug((int)x, (int)y, 0xffff0000);
	}

}
