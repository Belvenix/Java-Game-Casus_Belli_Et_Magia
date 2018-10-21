package com.venissed.Casus_Belli_Et_Magia.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {

	private String path;
	private final int SIZE;
	private final int WIDTH, HEIGHT;

	public int[] pixels;
	
	private Sprite[] sprites;

	public static SpriteSheet tiles = new SpriteSheet("/Textures/SpriteSheet.png", 256);
	public static SpriteSheet player = new SpriteSheet("/Textures/PlayerSpriteSheet.png", 128);
	public static SpriteSheet spawnLevelTiles = new SpriteSheet("/Textures/SpawnLevelSpriteSheet.png", 48);
	public static SpriteSheet projectiles = new SpriteSheet("/Textures/Projectiles/SpriteSheetProjectiles.png", 48);
	public static SpriteSheet explosionAnimation = new SpriteSheet("/Textures/Projectiles/SpriteSheetExplosionAnimation.png", 32, 64);
	
	
	public static SpriteSheet player_up = new SpriteSheet(player,0,0,1,4,16);
	public static SpriteSheet player_left = new SpriteSheet(player,1,0,1,4,16);
	public static SpriteSheet player_down = new SpriteSheet(player,2,0,1,4,16);
	public static SpriteSheet player_right = new SpriteSheet(player,3,0,1,4,16);
	
	public static SpriteSheet explosionAnimationProper = new SpriteSheet(explosionAnimation, 0, 0, 2, 4, 16);
	public static SpriteSheet slimeAnimation = new SpriteSheet(tiles, 0, 7, 1, 4, 16);
	public static SpriteSheet chaserAnimation = new SpriteSheet(tiles, 1, 7, 1, 4, 16);
	
	public static SpriteSheet demon_up = new SpriteSheet(player,4,4,1,4,16);
	public static SpriteSheet demon_left = new SpriteSheet(player,5,4,1,4,16);
	public static SpriteSheet demon_down = new SpriteSheet(player,6,4,1,4,16);
	public static SpriteSheet demon_right = new SpriteSheet(player,7,4,1,4,16);
	
	public static SpriteSheet zombie_up = new SpriteSheet(player,0,4,1,4,16);
	public static SpriteSheet zombie_left = new SpriteSheet(player,1,4,1,4,16);
	public static SpriteSheet zombie_down = new SpriteSheet(player,2,4,1,4,16);
	public static SpriteSheet zombie_right = new SpriteSheet(player,3,4,1,4,16);
	
	// width and height is in tile precision, whilst h and w is in pixel
	// precision... x is the X on the sheet in tile precision and Y is the same
	//Shitty method developed by theCherno, oh well
	public SpriteSheet(SpriteSheet sheet, int x, int y, int width, int height, int spriteSize) {
		int xx = x * spriteSize;
		int yy = y * spriteSize;
		int h = height * spriteSize;
		int w = width * spriteSize;
		if (width == height)
			this.SIZE = width;
		else
			this.SIZE = -1;
		this.WIDTH = w;
		this.HEIGHT = h;
		pixels = new int[w * h];
		for (int y0 = 0; y0 < h; y0++) {
			int yp = yy + y0;
			for (int x0 = 0; x0 < w; x0++) {
				int xp = xx + x0;
				pixels[x0 + y0 * w] = sheet.pixels[xp + yp * sheet.WIDTH];
			}
		}
		int frame = 0;
		sprites = new Sprite[width*height];
		for (int ya = 0; ya < height; ya++) {
			for (int xa = 0; xa < width; xa++) {
				int [] spritePixels = new int[spriteSize * spriteSize];
				for (int y0 = 0; y0 < spriteSize; y0++) {
					for (int x0 = 0; x0 < spriteSize; x0++) {
						//System.out.println(spritePixels.length + ", "+ x0 + y0 * spriteSize + ", " + pixels.length);
						spritePixels[x0 + y0 * spriteSize] = pixels[(x0 + xa * spriteSize) + (y0 + ya * spriteSize) * WIDTH];
					}
				}
				Sprite sprite = new Sprite(spritePixels, spriteSize, spriteSize);
				sprites[frame++] = sprite;
			}
		}
	}

	public SpriteSheet(String path, int size) {
		this.path = path;
		this.SIZE = size;
		this.WIDTH = this.HEIGHT = size;
		pixels = new int[SIZE * SIZE];
		load();
	}

	public SpriteSheet(String path, int width, int height) {
		this.path = path;
		this.SIZE = -1;
		this.WIDTH = width;
		this.HEIGHT = height;
		pixels = new int[width * height];
		load();
	}
	
	public Sprite[] getSprites() {
		return sprites;
	}

	private void load() {
		try {
			BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));
			int w = image.getWidth();
			int h = image.getHeight();
			image.getRGB(0, 0, w, h, pixels, 0, w);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public int getSize() {
		return this.SIZE;
	}
	
	public int getWidth() {
		return WIDTH;
	}
	
	public int getHeight() {
		return HEIGHT;
	}
}
