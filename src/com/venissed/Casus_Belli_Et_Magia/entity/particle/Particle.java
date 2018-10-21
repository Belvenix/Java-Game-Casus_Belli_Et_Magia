package com.venissed.Casus_Belli_Et_Magia.entity.particle;

import com.venissed.Casus_Belli_Et_Magia.entity.Entity;
import com.venissed.Casus_Belli_Et_Magia.graphics.Screen;
import com.venissed.Casus_Belli_Et_Magia.graphics.Sprite;

public class Particle extends Entity {

	public static int MAX_PARTICLE_LIFESPAN = 10000;

	protected int life;
	private int time = 0;

	protected double particleMath_xx, particleMath_yy, particleMath_zz;
	protected double particleMath_xa, particleMath_ya, particleMath_za;

	public Particle(int x, int y, int life) {
		this.x = x;
		this.y = y;
		this.particleMath_xx = x;
		this.particleMath_yy = y;
		this.life = life + (random.nextInt(22) - 11);
		sprite = Sprite.lightParticle;

		this.particleMath_xa = random.nextGaussian();
		this.particleMath_ya = random.nextGaussian();
		this.particleMath_zz = random.nextFloat() + 2.0;
	}
	
	public Particle(int x, int y, int life, Sprite sprite) {
		this(x,y,life);
		this.sprite = sprite;
	}

	public void update() {
		time++;
		if (time > MAX_PARTICLE_LIFESPAN) time = 0;
		if (time > life) remove();
		particleMath_za -= 0.1;

		if (particleMath_zz < 0) {
			particleMath_zz = 0;
			particleMath_za *= -0.5;
			particleMath_xa *= 0.4;
			particleMath_ya *= 0.4;
		}

		move(particleMath_xx + particleMath_xa, (particleMath_yy + particleMath_ya) +
				(particleMath_zz + particleMath_za));

	}

	private void move(double x, double y) {
		if(collision(x,y)) {
			this.particleMath_xa *= -0.5;
			this.particleMath_ya *= -0.5;
			this.particleMath_za *= -0.5;
		}
		this.particleMath_xx += particleMath_xa;
		this.particleMath_yy += particleMath_ya;
		this.particleMath_zz += particleMath_za;
	}
	
	public boolean collision(double x, double y) {
		boolean solid = false;
		for(int c = 0; c < 4; c++) {
			double tx = (x - c % 2 * 16) / 16;
			double ty = (y - c / 2 * 16) / 16;
			int ix = (int)Math.ceil(tx);
			int iy = (int)Math.ceil(ty);
			if(c % 2 == 0)ix = (int)Math.floor(tx);
			if(c / 2 == 0)iy = (int)Math.floor(ty);
			if(level.getTile(ix, iy).solid()) solid = true;
		}
		return solid;
	}

	public void render(Screen screen) {
		screen.renderSprite((int) particleMath_xx, (int) particleMath_yy - (int) particleMath_zz, sprite, true);
	}
}
