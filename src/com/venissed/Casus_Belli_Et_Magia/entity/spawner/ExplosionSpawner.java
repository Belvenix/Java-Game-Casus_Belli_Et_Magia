package com.venissed.Casus_Belli_Et_Magia.entity.spawner;

import com.venissed.Casus_Belli_Et_Magia.entity.particle.ExplosionParticle;
import com.venissed.Casus_Belli_Et_Magia.entity.particle.Particle;
import com.venissed.Casus_Belli_Et_Magia.level.Level;

public class ExplosionSpawner extends Spawner{

	private int life;

	public ExplosionSpawner(int x, int y, int amount, Level level) {
		// - 3 since the collision isn't pretty neat ;/
		super(x - 3, y - 3, Type.PARTICLE, amount, level);
		this.life = Particle.MAX_PARTICLE_LIFESPAN;
		for(int i = 0; i < amount; i++) {
			level.add(new ExplosionParticle(x, y));			
		}
		this.remove();
	}

	
}
