package com.venissed.Casus_Belli_Et_Magia.level.tile;

import com.venissed.Casus_Belli_Et_Magia.graphics.Screen;
import com.venissed.Casus_Belli_Et_Magia.graphics.Sprite;
import com.venissed.Casus_Belli_Et_Magia.graphics.SpriteSheet;
import com.venissed.Casus_Belli_Et_Magia.level.tile.spawn_level.SpawnFloorTile;
import com.venissed.Casus_Belli_Et_Magia.level.tile.spawn_level.SpawnGrassTile;
import com.venissed.Casus_Belli_Et_Magia.level.tile.spawn_level.SpawnRockTile;
import com.venissed.Casus_Belli_Et_Magia.level.tile.spawn_level.SpawnWallTile;
import com.venissed.Casus_Belli_Et_Magia.level.tile.spawn_level.SpawnWaterTile;

public class Tile {
	
	public int x, y;
	public Sprite sprite;
	
	public static Tile voidTile = new VoidTile(Sprite.voidTile);
	public static Tile grass = new GrassTile(Sprite.grass);
	public static Tile shortGrass = new GrassTile(Sprite.shortGrass);
	public static Tile tallGrass = new GrassTile(Sprite.tallGrass);
	public static Tile water = new WaterTile(Sprite.water);
	public static Tile rock = new RockTile(Sprite.rock);
	
	public static Tile spawnGrass = new SpawnGrassTile(Sprite.spawnGrass);
	public static Tile spawnCrackedWall = new SpawnWallTile(Sprite.spawnCrackedWall);
	public static Tile spawnWall = new SpawnWallTile(Sprite.spawnWall);
	public static Tile spawnTallGrass = new SpawnGrassTile(Sprite.spawnTallGrass);
	public static Tile spawnRock = new SpawnRockTile(Sprite.spawnRock);
	public static Tile spawnCobbleFloor = new SpawnFloorTile(Sprite.spawnCobbleFloor);
	public static Tile spawnLeaves = new SpawnGrassTile(Sprite.spawnLeaves);
	public static Tile spawnWater = new SpawnWaterTile(Sprite.spawnWater);
	
	//remember alpha channel!
	public final static int colorSpawnGrass = 0xFF63C74D;
	public final static int colorSpawnCrackedWall = 0xFF8B9BB4;
	public final static int colorSpawnWall = 0xFF3A4466;
	public final static int colorSpawnTallGrass = 0xFF3E8948;
	public final static int colorSpawnRock = 0xFF262B44;
	public final static int colorSpawnCobbleFloor = 0xFFC0CBDC;
	public final static int colorSpawnLeaves = 0xFF000000; //unused
	public final static int colorSpawnWater = 0xFF2CE8F5;
	
	public static Tile abyss = new GrassTile(Sprite.abyss);
	
	
	
	public Tile(Sprite sprite) {
		this.sprite = sprite;
	}
	
	public void render(int x, int y, Screen screen) {
	}
	
	public boolean solid() {
		return false;
	}
}
