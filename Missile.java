import processing.core.PApplet;

public class Missile extends Bullet {

	Missile(PApplet parent, int x, int y , int direction){//Missile constructor
		super( parent, x, y, direction);
	}

	public void draw() { //Draw missile
		parent.noStroke();
		parent.fill(255,0,0);
		parent.rect(x, y, 10, 20);
	}

	public void update(){ //Missile movement
		y += 18 * direction;
	}
}
