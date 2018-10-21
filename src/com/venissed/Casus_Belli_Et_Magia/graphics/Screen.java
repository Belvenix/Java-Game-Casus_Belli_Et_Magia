package com.venissed.Casus_Belli_Et_Magia.graphics;

import java.util.Random;

import com.venissed.Casus_Belli_Et_Magia.entity.mob.Mob;
import com.venissed.Casus_Belli_Et_Magia.entity.projectile.Projectile;
import com.venissed.Casus_Belli_Et_Magia.level.tile.Tile;

public class Screen {

	private int width;
	private int height;
	private final int tileSize = 16;
	private final int tileBitSize = 4;
	private final int mapSize = 64;
	public int[] pixels;
	public int xOffset, yOffset;
	public int[] tiles = new int[mapSize * mapSize];

	public int getTileBitSize() {
		return this.tileBitSize; 
	}


	private Random random = new Random();

	public Screen(int width, int height) {
		this.width = width;
		this.height = height;
		pixels = new int[width * height];

		for (int i = 0; i < mapSize * mapSize; i++) {
			tiles[i] = random.nextInt(0xffffff);
		}
	}

	public void clear() {
		for (int i = 0; i < height * width; i++) {
			pixels[i] = 0x000000;
		}
	}

	
	
	public void renderSheet(int xp, int yp, SpriteSheet sheet, boolean fixed) {
		if (fixed) {
			xp -= xOffset;
			yp -= yOffset;
		}
		for (int y = 0; y < sheet.getHeight(); y++) {
			int ya = y + yp;
			for(int x = 0; x < sheet.getWidth(); x++) {
				int xa = x + xp;
				if(xa < 0 || xa >= width || ya < 0 || ya >= height) continue;
				//pixels[xa + ya * width] = sprite.pixels[x + y * sprite.getWidth()];
				int col = sheet.pixels[x + y * sheet.getWidth()];
				//We have to add first FF because of the alpha channel
				if(col != 0xFFFFFF00)
					pixels[xa + ya * width] = col;
			}
		}
	}
	
	public void renderSprite(int xp, int yp, Sprite sprite, boolean fixed) {
		if (fixed) {
			xp -= xOffset;
			yp -= yOffset;
		}
		for (int y = 0; y < sprite.getHeight(); y++) {
			int ya = y + yp;
			for(int x = 0; x < sprite.getWidth(); x++) {
				int xa = x + xp;
				if(xa < 0 || xa >= width || ya < 0 || ya >= height) continue;
				pixels[xa + ya * width] = sprite.pixels[x + y * sprite.getWidth()];
/*				int col = sprite.pixels[x + y * sprite.getWidth()];
				//We have to add first FF because of the alpha channel
				if(col != 0xFFFFFF00)
					pixels[xa + ya * width] = col;*/
			}
		}
	}
	
	public void renderTransparentSprite(int xp, int yp, Sprite sprite, boolean fixed) {
		if (fixed) {
			xp -= xOffset;
			yp -= yOffset;
		}
		for (int y = 0; y < sprite.getHeight(); y++) {
			int ya = y + yp;
			for(int x = 0; x < sprite.getWidth(); x++) {
				int xa = x + xp;
				if(xa < 0 || xa >= width || ya < 0 || ya >= height) continue;
				//pixels[xa + ya * width] = sprite.pixels[x + y * sprite.getWidth()];
				int col = sprite.pixels[x + y * sprite.getWidth()];
				//We have to add first FF because of the alpha channel
				if(col != 0xFFFFFF00)
					pixels[xa + ya * width] = col;
			}
		}
	}

	public void renderDebug(int xp, int yp, int col) {
		xp -= xOffset;
		yp -= yOffset;
		if(xp < 0 || xp >= width || yp < 0 || yp >= height) return;
		else {
			pixels[xp + yp * width] = col;
			
		}
	}
	
	public void renderMob(int xp, int yp, Mob mob) {
		xp -= xOffset;
		yp -= yOffset;
		for (int y = 0; y < mob.getSprite().getHeight(); y++) {
			int ya = y + yp;
			for(int x = 0; x < mob.getSprite().getWidth(); x++) {
				int xa = x + xp;
				if(xa < 0 || xa >= width || ya < 0 || ya >= height) continue;
				int col = mob.getSprite().pixels[x + y * mob.getSprite().getWidth()];
				if(col != 0xFFFFFF00)
					pixels[xa + ya * width] = col;
			}
		}
	}
	/*public void renderPlayer(int xp, int yp, Sprite sprite, boolean fixed) {
		if (fixed) {
			xp -= xOffset;
			yp -= yOffset;
		}
		for (int y = 0; y < sprite.getHeight(); y++) {
			int ya = y + yp;
			for (int x = 0; x < sprite.getWidth(); x++) {
				int xa = x + xp;
				if (xa < -sprite.getSize() || xa >= width || ya < 0 || ya >= height) break;
				if (xa < 0) xa = 0;
				int col = sprite.pixels[x + y * sprite.getWidth()];
				//We have to add first FF because of the alpha channel
				if(col != 0xFFFFFF00)
					pixels[xa + ya * width] = col;
			}
		}
	}*/
	
	public void renderTile(int xp, int yp, Tile tile) {
		xp -= xOffset;
		yp -= yOffset;
		for (int y = 0; y < tile.sprite.getSize(); y++) {
			int ya = y + yp;
			for (int x = 0; x < tile.sprite.getSize(); x++) {
				int xa = x + xp;
				if (xa < -tile.sprite.getSize() || xa >= width || ya < 0 || ya >= height) break;
				if (xa < 0) xa = 0;
				pixels[xa + ya * width] = tile.sprite.pixels[x + y * tile.sprite.getSize()];
			}
		}
	}

	public void renderProjectile(int xp, int yp, Projectile p) {
		xp -= xOffset + ((p.getSpriteSize() - this.tileSize )>>1);
		yp -= yOffset + ((p.getSpriteSize() - this.tileSize )>>1);
		for (int y = 0; y < p.getSpriteSize(); y++) {
			int ya = y + yp;
			for (int x = 0; x < p.getSpriteSize(); x++) {
				int xa = x + xp;
				if (xa < -p.getSpriteSize() || xa >= width || ya < 0 || ya >= height) break;
				if (xa < 0) xa = 0;
				int col = p.getSprite().pixels[x + y * p.getSpriteSize()];
				//We have to add first FF because of the alpha channel
				if(col != 0xFFFFFF00)
					pixels[xa + ya * width] = col;
			}
		}
	}
	
	public void setOffset(int xOffset, int yOffset) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}

	public int getWidth() {
		return this.width;
	}

	public int getHeight() {
		return this.height;
	}

}
