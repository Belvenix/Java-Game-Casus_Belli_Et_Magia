package com.venissed.Casus_Belli_Et_Magia.graphics;

public class AnimatedSprite extends Sprite {

	private int frame = 0;
	private Sprite sprite;
	private int rate = 5;
	private int time = 0;
	private int length = -1;
	private boolean looped = false;

	public AnimatedSprite(SpriteSheet sheet, int width, int height, int length) {
		super(sheet, width, height);
		this.length = length;
		if(length > sheet.getSprites().length) System.err.println("Error! Length of animation is too long!");
		sprite = sheet.getSprites()[0];
	}
	
	public AnimatedSprite(SpriteSheet sheet, int width, int height, int length, int rate) {
		this(sheet, width, height,length);
		this.rate = rate;
	}

	public void update() {
		time++;
		if(time % rate == 0) {
			if (frame >= length - 1){
				looped = true;
				frame = 0;
			}
			else
				frame++;
			sprite = sheet.getSprites()[frame];
		}
		//System.out.println(sprite + ", Frame: " + frame + ".");
	}

	public Sprite getSprite() {
		return sprite;
	}

	public void setFrameRate(int frame) {
		rate = frame;
	}

	public void setFrame(int index) {
		if (index > sheet.getSprites().length - 1) {
			System.err.println("Index out of bounds in " + this);
			return;
		}
		sprite = sheet.getSprites()[index];
		
	}

	public int getLength() {
		return length;
	}
	
	public boolean hasLooped() {
		return looped;
	}
}
