package com.venissed.Casus_Belli_Et_Magia.entity.mob;

import com.venissed.Casus_Belli_Et_Magia.graphics.AnimatedSprite;
import com.venissed.Casus_Belli_Et_Magia.graphics.Screen;
import com.venissed.Casus_Belli_Et_Magia.graphics.Sprite;
import com.venissed.Casus_Belli_Et_Magia.graphics.SpriteSheet;

public class DummyDemon extends Mob{

	private int dx = 0, dy = 0;
	private int time = 0;
	
	private AnimatedSprite down = new AnimatedSprite(SpriteSheet.demon_down, 16, 16, 4);
	private AnimatedSprite up = new AnimatedSprite(SpriteSheet.demon_up, 16, 16, 4);
	private AnimatedSprite left = new AnimatedSprite(SpriteSheet.demon_left, 16, 16, 4);
	private AnimatedSprite right = new AnimatedSprite(SpriteSheet.demon_right, 16, 16, 4);
	
	private AnimatedSprite animSprite = down;
	
	public DummyDemon(int x, int y) {
		this.x = x << 4;// * 16
		this.y = y << 4;// * 16
		animSprite = down;
		sprite = animSprite.getSprite();
		this.dir = Direction.NOWHERE;
	}

	public void update() {
		time++;
		if(time > 10000) time = 0;
		dir = Direction.NOWHERE;
		if(walking) animSprite.update();
		else animSprite.setFrame(0);
		
		if(time % (45 + random.nextInt(30)) == 0) {
			dx = random.nextInt(3) - 1;
			dy = random.nextInt(3) - 1;
			
		}
		if(random.nextInt(60) == 0) {
			dx = 0;
			dy = 0;
		}
		
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

	}

	public void render(Screen screen) {
		sprite = animSprite.getSprite();
		screen.renderTransparentSprite((int)x, (int)y, sprite, true);
	}
}
