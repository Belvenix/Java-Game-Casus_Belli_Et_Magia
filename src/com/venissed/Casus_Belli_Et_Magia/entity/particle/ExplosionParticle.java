package com.venissed.Casus_Belli_Et_Magia.entity.particle;

import com.venissed.Casus_Belli_Et_Magia.graphics.AnimatedSprite;
import com.venissed.Casus_Belli_Et_Magia.graphics.Screen;
import com.venissed.Casus_Belli_Et_Magia.graphics.SpriteSheet;

public class ExplosionParticle extends Particle{
	
	private AnimatedSprite animation;
	private int time = 0;
	
	public ExplosionParticle(int x, int y) {
		super(x, y, MAX_PARTICLE_LIFESPAN);
		animation = new AnimatedSprite(SpriteSheet.explosionAnimationProper, 16, 16, 8);
		sprite = animation.getSprite();
	}
	
	public void update() {
		time++;
		if(time > MAX_PARTICLE_LIFESPAN)time = 0;
		if(time > life || animation.hasLooped())remove();
		animation.update();
		sprite = animation.getSprite();
		super.update();
	}
	
	public void render(Screen screen) {
		screen.renderTransparentSprite((int) particleMath_xx, (int) particleMath_yy - (int) particleMath_zz, sprite, true);
	}

}
