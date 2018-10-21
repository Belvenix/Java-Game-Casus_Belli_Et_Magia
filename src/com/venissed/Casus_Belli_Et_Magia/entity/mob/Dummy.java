package com.venissed.Casus_Belli_Et_Magia.entity.mob;

import com.venissed.Casus_Belli_Et_Magia.graphics.AnimatedSprite;
import com.venissed.Casus_Belli_Et_Magia.graphics.Screen;
import com.venissed.Casus_Belli_Et_Magia.graphics.Sprite;
import com.venissed.Casus_Belli_Et_Magia.graphics.SpriteSheet;

public class Dummy extends Mob {

	private AnimatedSprite slimeAnimation = new AnimatedSprite(SpriteSheet.slimeAnimation, 16, 16, 4, 10);

	private AnimatedSprite animSprite = slimeAnimation;

	private Direction chosenDirection = Direction.NOWHERE;
	private int time = 0;
	private int randomWaiting;

	public Dummy(int x, int y) {
		this.x = x << 4;// * 16
		this.y = y << 4;// * 16
		sprite = Sprite.slime;
		randomWaiting = random.nextInt(50) + 75;
	}

	public void update() {
		slimeAI();
		animSprite.update();
	}

	private void slimeAI() {
		this.getRandomDirection(225, 50, 75);
		switch(chosenDirection)	{
		case NOWHERE:
			break;
		case NORTH:
			move(0,-1);
			break;
		case WEST:
			move(-1, 0);
			break;
		case EAST:
			move(1, 0);
			break;
		case SOUTH:
			move(0, 1);
			break;
		case NORTH_WEST:
			move(-1,-1);
			break;
		case SOUTH_WEST:
			move(-1, 1);
			break;
		case SOUTH_EAST:
			move(1, 1);
			break;
		case NORTH_EAST:
			move(1, -1);
			break;
		}
	}
	
	private void getRandomDirection(int minWaitTime, int timeScatter, int maxWalkingTime) {
		time++;
		if(time > 10000)time = 0;
		if(time % maxWalkingTime == 0)
		{
			chosenDirection =  Direction.NOWHERE;
		}
		if(time % randomWaiting == 0) {
			//System.out.println("HI");
			randomWaiting = random.nextInt(timeScatter) + minWaitTime;
			int randomNumber = random.nextInt(8);
			switch(randomNumber) {
			case 0:
				chosenDirection =  Direction.NORTH;	
				break;
			case 1:
				chosenDirection =  Direction.NORTH_WEST;
				break;
			case 2:
				chosenDirection =  Direction.WEST;
				break;
			case 3:
				chosenDirection =  Direction.SOUTH_WEST;
				break;
			case 4:
				chosenDirection =  Direction.SOUTH;
				break;
			case 5:
				chosenDirection =  Direction.SOUTH_EAST;
				break;
			case 6:
				chosenDirection =  Direction.EAST;
				break;
			case 7:
				chosenDirection =  Direction.NORTH_EAST;
				break;
			default:
				break;
			}
		}
	}
	
	public void render(Screen screen) {
		sprite = animSprite.getSprite();
		screen.renderTransparentSprite((int)x, (int)y, sprite, true);
	}

}
