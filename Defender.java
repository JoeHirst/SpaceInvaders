import java.util.ArrayList;
import processing.core.PApplet;
import processing.core.PConstants;

public class Defender{ // Declare defender class + global variables
	PApplet parent;
	int x;
	int y;
	int bulletTimer;
	boolean left;
	boolean right;
	boolean firing;
	boolean dead;
	ArrayList<Bullet> bullets; 
	boolean cheating;

	public Defender(PApplet parent, int x, int y, ArrayList<Bullet> bullets) { //Defender constructor
		this.parent = parent;
		this.x = x;
		this.y = y;
		this.bullets = bullets;
	}

	public void draw() { //Draw the defender
		parent.fill(255,0,0);
		parent.stroke(255,0,0);
		parent.rect(x, y, 40, 20);
		parent.rect(x+10, y-10, 20, 20);
	}

	void update(){ //Update the defender
		if(!dead){
			draw();
			move();	
			fire();
		}
	}

	void keyPressed() { //Move and fire
		if (parent.key == PConstants.CODED) {
			if (parent.keyCode == PConstants.LEFT) {
				left = true;
			}

			if (parent.keyCode == PConstants.RIGHT) {
				right = true;
			} 
		}

		if (parent.keyCode == ' ' ){ 
			firing = true;
		}
	}

	void keyReleased() { //Stop moving and firing
		if (parent.key == PConstants.CODED) {
			if (parent.keyCode == PConstants.LEFT) {
				left = false;
			}

			if (parent.keyCode == PConstants.RIGHT) {
				right = false;
			} 
		}

		if (parent.keyCode == ' ') {
			firing = false;
		}
	}

	void move(){ //Defender movement
		if (left) {
			x -= 5;
		}

		if (right) {
			x += 5;
		}

		if(x >= parent.width - 40){
			x = parent.width - 40;
		}

		if(x <= 0){
			x = 0;
		}
	}

	void fire(){ //Defender bullet timer
		if (firing && (bulletTimer <= 0 || cheating)) {
			bullets.add(new Bullet(parent, x + 15, y, -1));
			bulletTimer = 15;
		} else {
			bulletTimer--;
		}
	}

}