package com.venissed.Casus_Belli_Et_Magia.level;

import com.venissed.Casus_Belli_Et_Magia.util.Vector2d;
import com.venissed.Casus_Belli_Et_Magia.util.Vector2i;

public class Node {

	public Vector2i tile;
	public Node parent;
	public double fCost, gCost, hCost;
	
	public Node(Vector2i start, Node parent, double gCost, double hCost) {
		this.tile = start;
		this.parent = parent;
		this.gCost = gCost;
		this.hCost = hCost;
		this.fCost = this.gCost + this.hCost;
	}
	
}
