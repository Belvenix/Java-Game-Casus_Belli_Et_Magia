package com.venissed.Casus_Belli_Et_Magia.level;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.venissed.Casus_Belli_Et_Magia.entity.mob.Chaser;
import com.venissed.Casus_Belli_Et_Magia.entity.mob.Dummy;
import com.venissed.Casus_Belli_Et_Magia.entity.mob.DummyDemon;
import com.venissed.Casus_Belli_Et_Magia.entity.mob.StarZombie;

public class SpawnLevel extends Level{
	
	public SpawnLevel(String path) {
		super(path);
	}

	protected void loadLevel(String path) {
		try {
			BufferedImage image = ImageIO.read(SpawnLevel.class.getResource(path));
			this.width = image.getWidth();
			this.height = image.getHeight();
			tiles = new int[width * height];
			image.getRGB(0, 0, width, height, tiles, 0, width);
		}catch(IOException e) {
			e.printStackTrace();
			System.out.println("Error occured while loading Spawn Level!");
		}
		for(int i = 0; i < 1; i++) {
			add(new Dummy(19,59));
			add(new DummyDemon(21, 61));
			add(new Chaser(23, 63));
			add(new StarZombie(26, 66));
		}
	}
	
	//0xFF00A000 grass
	//0xFF00F000 shortGrass
	//0xFF007800 tallGrass
	//0xFF00C8FF water
	//0xFF654321 rock
	protected void generateLevel() {

		}
	
	}
	

