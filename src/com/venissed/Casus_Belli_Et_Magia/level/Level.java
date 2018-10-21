package com.venissed.Casus_Belli_Et_Magia.level;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.venissed.Casus_Belli_Et_Magia.entity.Entity;
import com.venissed.Casus_Belli_Et_Magia.entity.mob.Player;
import com.venissed.Casus_Belli_Et_Magia.entity.particle.Particle;
import com.venissed.Casus_Belli_Et_Magia.entity.projectile.Projectile;
import com.venissed.Casus_Belli_Et_Magia.graphics.Screen;
import com.venissed.Casus_Belli_Et_Magia.level.tile.Tile;
import com.venissed.Casus_Belli_Et_Magia.util.Vector2d;
import com.venissed.Casus_Belli_Et_Magia.util.Vector2i;

public class Level {

	protected int width, height;
	protected int[] tilesInt;
	protected int[] tiles;

	private List<Entity> entities = new ArrayList<Entity>();
	private List<Projectile> projectiles = new ArrayList<Projectile>();
	private List<Particle> particles = new ArrayList<Particle>();

	private List<Player> players = new ArrayList<Player>();

	private Comparator<Node> nodeSorter = new Comparator<Node>() {
		public int compare(Node n0, Node n1) {
			if (n1.fCost < n0.fCost) return 1;
			if (n1.fCost > n0.fCost) return -1;
			return 0;
		}
	};

	public static Level spawn = new SpawnLevel("/Levels/spawn.png");

	public Level(int width, int height) {
		this.width = width;
		this.height = height;
		tilesInt = new int[width * height];
		this.generateLevel();
	}

	public Level(String path) {
		loadLevel(path);
		this.generateLevel();
	}

	public List<Projectile> getProjectiles() {
		return this.projectiles;
	}

	protected void loadLevel(String path) {

	}

	public void update() {
		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).update();
		}
		for (int i = 0; i < projectiles.size(); i++) {
			projectiles.get(i).update();
		}
		for (int i = 0; i < particles.size(); i++) {
			particles.get(i).update();
		}
		for (int i = 0; i < players.size(); i++) {
			players.get(i).update();
		}
		remove();
	}

	private void time() {

	}

	public boolean tileCollision(int x, int y, int size, int xOffset, int yOffset) {
		boolean solid = false;
		for (int c = 0; c < 4; c++) {
			int tx = (x - c % 2 * size + xOffset) >> 4;
			int ty = (y - c / 2 * size + yOffset) >> 4;
			if (getTile(tx, ty).solid()) solid = true;
		}
		return solid;
	}

	public void render(int xScroll, int yScroll, Screen screen) {
		screen.setOffset(xScroll, yScroll);
		// x0 top left corner, y0 top left corner
		// pixel precision to tile precision, screen.getTileBitSize is 4
		int x0 = (xScroll >> screen.getTileBitSize());
		int x1 = ((xScroll + screen.getWidth()) >> screen.getTileBitSize()) + 1;
		int y0 = (yScroll >> screen.getTileBitSize());
		int y1 = ((yScroll + screen.getHeight()) >> screen.getTileBitSize()) + 1;

		for (int y = y0; y < y1; y++) {
			for (int x = x0; x < x1; x++) {
				getTile(x, y).render(x, y, screen);
			}
		}
		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).render(screen);
		}
		for (int i = 0; i < projectiles.size(); i++) {
			projectiles.get(i).render(screen);
		}
		for (int i = 0; i < particles.size(); i++) {
			particles.get(i).render(screen);
		}
		for (int i = 0; i < players.size(); i++) {
			players.get(i).render(screen);
		}
	}

	public void add(Entity e) {
		e.init(this);
		if (e instanceof Particle) {
			particles.add((Particle) e);
		} else if (e instanceof Projectile) {
			projectiles.add((Projectile) e);
		} else if (e instanceof Player) {
			players.add((Player) e);
		} else {
			entities.add(e);
		}
	}

	private void remove() {
		for (int i = 0; i < entities.size(); i++) {
			if (entities.get(i).isRemoved()) entities.remove(i);
		}
		for (int i = 0; i < projectiles.size(); i++) {
			if (projectiles.get(i).isRemoved()) projectiles.remove(i);
		}
		for (int i = 0; i < particles.size(); i++) {
			if (particles.get(i).isRemoved()) particles.remove(i);
		}
		for (int i = 0; i < players.size(); i++) {
			if (players.get(i).isRemoved()) players.remove(i);
		}
	}

	public List<Player> getPlayer() {
		return players;
	}

	public Player getPlayerAt(int index) {
		if (index < players.size()) {
			return players.get(index);
		} else {
			System.out.println("Index out of bounds in " + this + ", " + this.players + ", " + index);
			return null;
		}
	}

	public Player getClientPlayer() {
		return players.get(0);
	}

	// A*Star search algorithm
	public List<Node> findPath(Vector2i start, Vector2i goal) {
		List<Node> openList = new ArrayList<Node>();
		List<Node> closedList = new ArrayList<Node>();
		Node current = new Node(start, null, 0, start.getDistance(goal));
		openList.add(current);
		while (openList.size() > 0) {
			Collections.sort(openList, nodeSorter);
			current = openList.get(0);
			if (current.tile.equals(goal)) {
				List<Node> path = new ArrayList<Node>();
				while(current.parent != null) {
					path.add(current);
					current = current.parent;
				}
				//System.out.println(path);
				openList.clear();
				closedList.clear();
				return path;
			}
			openList.remove(current);
			closedList.add(current);
			for (int i = 0; i < 9; i++) {
				if(i == 4) continue;
				int x = (int) current.tile.getX();
				int y = (int) current.tile.getY();
				int xi = (i % 3) - 1;
				int yi = (i / 3) - 1;
				Tile at = getTile(x + xi, y + yi);
				if(at == null)continue;
				else if(at.solid())continue;
				Vector2i a = new Vector2i(x + xi, y + yi);
				double gCost = current.gCost + current.tile.getDistance(a);
				double hCost = a.getDistance(goal);
				Node node = new Node(a, current, gCost, hCost);
				if(vecInList(closedList, a) && gCost >= node.gCost)continue;
				if(!vecInList(openList, a) || gCost < node.gCost) {
					openList.add(node);
				}
			}
		}
		closedList.clear();
		return null;
	}
	
	private boolean vecInList(List<Node> list, Vector2i v) {
		for (Node n : list) {
			if(n.tile.equals(v))return true;
		}
		return false;
	}

	public List<Entity> getEntitiesInRange(Entity e, int radius) {
		List<Entity> result = new ArrayList<Entity>();
		int ex = (int) e.getX();
		int ey = (int) e.getY();
		for (int i = 0; i < entities.size(); i++) {
			Entity entity = entities.get(i);
			int x = (int) entity.getX();
			int y = (int) entity.getY();
			double dist = Math.sqrt((x - ex) * (x - ex) + (y - ey) * (y - ey));
			if (dist <= radius) {
				result.add(entity);
			}
		}
		return result;
	}

	public List<Player> getPlayersInRange(Entity e, int radius) {
		List<Player> result = new ArrayList<Player>();
		int ex = (int) e.getX();
		int ey = (int) e.getY();
		for (int i = 0; i < players.size(); i++) {
			Player p = players.get(i);
			int x = (int) p.getX();
			int y = (int) p.getY();
			double dist = Math.sqrt((x - ex) * (x - ex) + (y - ey) * (y - ey));
			if (dist <= radius) {
				result.add(p);
			}
		}
		return result;
	}

	// grass = 0xFF00A000
	// shortGrass = 0xFF00F000
	// tallGrass = 0xFF007800
	// rock = 0xFF654321
	// water = 0xFF00C8FF
	public Tile getTile(int x, int y) {
		if (x < 0 || y < 0 || x >= width || y >= height) return Tile.voidTile;
		if (tiles[x + y * width] == Tile.colorSpawnGrass)
			return Tile.spawnGrass;
		else if (tiles[x + y * width] == Tile.colorSpawnLeaves)
			return Tile.spawnLeaves;
		else if (tiles[x + y * width] == Tile.colorSpawnTallGrass)
			return Tile.spawnTallGrass;
		else if (tiles[x + y * width] == Tile.colorSpawnRock)
			return Tile.spawnRock;
		else if (tiles[x + y * width] == Tile.colorSpawnWater)
			return Tile.spawnWater;
		else if (tiles[x + y * width] == Tile.colorSpawnCobbleFloor)
			return Tile.spawnCobbleFloor;
		else if (tiles[x + y * width] == Tile.colorSpawnWall)
			return Tile.spawnWall;
		else if (tiles[x + y * width] == Tile.colorSpawnCrackedWall)
			return Tile.spawnCrackedWall;
		else
			return Tile.voidTile;
	}

	protected void generateLevel() {

	}
}
