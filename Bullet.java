import processing.core.*;

public class Bullet{// Declare bullet class + global variables
	PApplet parent;
	int x;
	int y;
	int direction;
	boolean dead;

	public Bullet(PApplet parent, int x, int y , int direction) { //Bullet constructor
		this.parent = parent;
		this.x = x;
		this.y = y;
		this.direction = direction;
	}

	public void draw() { //Draw bullet
		parent.noStroke();
		parent.fill(0,255,0);
		parent.rect(x, y, 10, 20);
	}

	public void update(){ //Bullet movement
		y += 10 * direction;
	}
}