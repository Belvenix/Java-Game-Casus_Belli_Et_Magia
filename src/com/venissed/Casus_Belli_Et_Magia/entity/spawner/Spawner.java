package com.venissed.Casus_Belli_Et_Magia.entity.spawner;

import com.venissed.Casus_Belli_Et_Magia.entity.Entity;
import com.venissed.Casus_Belli_Et_Magia.level.Level;

public class Spawner extends Entity{
 
	public enum Type{
		MOB, PARTICLE;
	}
	
	private Type type;
	
	public Spawner(int x, int y, Type type, int amount, Level level) {
		init(level);
		this.x = x;
		this.y = y;
		this.type = type;
		
	}
	
}
