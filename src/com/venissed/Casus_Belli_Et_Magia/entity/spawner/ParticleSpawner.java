package com.venissed.Casus_Belli_Et_Magia.entity.spawner;

import com.venissed.Casus_Belli_Et_Magia.entity.particle.Particle;
import com.venissed.Casus_Belli_Et_Magia.level.Level;

public class ParticleSpawner extends Spawner{

	private int life;
	
	public ParticleSpawner(int x, int y,int life, int amount, Level level) {
		super(x, y, Type.PARTICLE, amount, level);
		this.life = life;
		for(int i = 0; i < amount; i++) {
			level.add(new Particle(x, y, life));
		}
		this.remove();
	}

}
