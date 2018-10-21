package com.venissed.Casus_Belli_Et_Magia.level.tile.spawn_level;

import com.venissed.Casus_Belli_Et_Magia.graphics.Screen;
import com.venissed.Casus_Belli_Et_Magia.graphics.Sprite;
import com.venissed.Casus_Belli_Et_Magia.level.tile.Tile;

public class SpawnWaterTile extends Tile {

	public SpawnWaterTile(Sprite sprite) {
		super(sprite);
		// TODO Auto-generated constructor stub
	}
	
	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 4, y << 4, this);
	}

}
