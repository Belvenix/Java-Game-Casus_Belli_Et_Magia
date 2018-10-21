package com.venissed.Casus_Belli_Et_Magia.graphics;

public class Sprite {

	private final int SIZE;
	private int x, y; // coordinates of the sprite
	private int width, height;
	public int[] pixels; // their look
	protected SpriteSheet sheet;

	public static Sprite voidTile = new Sprite(16, 0, 0, SpriteSheet.tiles);
	public static Sprite abyss = new Sprite(16, 1, 0, SpriteSheet.tiles);
	public static Sprite grass = new Sprite(16, 0, 1, SpriteSheet.tiles);
	public static Sprite shortGrass = new Sprite(16, 0, 2, SpriteSheet.tiles);
	public static Sprite tallGrass = new Sprite(16, 0, 3, SpriteSheet.tiles);
	public static Sprite water = new Sprite(16, 1, 2, SpriteSheet.tiles);
	public static Sprite rock = new Sprite(16, 0, 6, SpriteSheet.tiles);
	
	//spawn level sprites:
	
	public static Sprite spawnGrass = new Sprite(16, 0, 0, SpriteSheet.spawnLevelTiles);
	public static Sprite spawnCrackedWall = new Sprite(16, 1, 0, SpriteSheet.spawnLevelTiles);
	public static Sprite spawnWall = new Sprite(16, 2, 0, SpriteSheet.spawnLevelTiles);
	public static Sprite spawnTallGrass = new Sprite(16, 0, 1, SpriteSheet.spawnLevelTiles);
	public static Sprite spawnRock = new Sprite(16, 1, 1, SpriteSheet.spawnLevelTiles);
	public static Sprite spawnCobbleFloor = new Sprite(16, 2, 1, SpriteSheet.spawnLevelTiles);
	public static Sprite spawnLeaves = new Sprite(16, 0, 2, SpriteSheet.spawnLevelTiles);
	public static Sprite spawnWater = new Sprite(16, 1, 2, SpriteSheet.spawnLevelTiles);
	
	//Projectile Sprites here:
	
	public static Sprite projectileFireball = new Sprite(16,2,0,SpriteSheet.projectiles);
	public static Sprite projectileHugeFireball = new Sprite(32,0,0,SpriteSheet.projectiles);
	
	//Particle Sprites here:
	
	public static Sprite lightParticle = new Sprite(2,0xAAAAAA);
	public static Sprite blueParticle = new Sprite(2,0x2CE8F5);
	public static Sprite redParticle = new Sprite(2, 0xFF0044);
	
	//Mon Sprites here:
	
	public static Sprite slime = new Sprite(16, 0, 7, SpriteSheet.tiles);
	
	
	//Very important - we count the x y via the first number!
	public static Sprite gemRock = new Sprite(32, 3, 0, SpriteSheet.tiles);
	
	public Sprite(int size, int x, int y, SpriteSheet sheet) {
		this.SIZE = 
				this.width = 
				this.height = size;
		pixels = new int[this.SIZE * this.SIZE];
		this.x = x * size;
		this.y = y * size;
		this.sheet = sheet;
		load();
	}
	
	protected Sprite(SpriteSheet sheet, int width, int height) {
		SIZE = width == height ? width : -1;
		this.width = width;
		this.height = height;
		this.sheet = sheet;
	}
	
	public Sprite(int width, int height, int color) {
		SIZE = width == height ? width : -1;
		this.width = width;
		this.height = height;
		pixels = new int[width*height];
		setColor(color);
	}

	public Sprite(int size, int color) {
		this.SIZE = 
				this.width = 
				this.height = size;
		pixels = new int[this.SIZE * this.SIZE];
		setColor(color);
	}

	public Sprite(int[] pixels, int width, int height) {
		SIZE = width == height ? width : -1;
		this.width = width;
		this.height = height;
		this.pixels = pixels;
	}

	private void setColor(int color) {
		for (int i = 0; i < this.width * this.height; i++) {
			this.pixels[i] = color;
		}
	}

	private void load() {
		for (int y = 0; y < this.height; y++) {
			for (int x = 0; x < this.width; x++) {
				pixels[x + y * this.width] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.getWidth()];
			}
		}
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getWidth() {
		return width;
	}

	public int getSize() {
		return this.SIZE;
	}
}
