import processing.core.*;
import java.util.ArrayList;

public class Invader{ //Declare invader class + global variables
	PApplet parent;
	int x;
	int y;
	int startX = x;
	int dx = 1;
	int dist = 0;
	int difficulty = 3;
	boolean dead;
	ArrayList<Bullet> invaderBullets; 
	ArrayList<Missile> invaderMissiles;


	PImage airship;
	PImage airshipFlipped;

	public Invader(PApplet parent, int x, int y, ArrayList<Bullet> invaderBullets, ArrayList<Missile> invaderMissiles) { //Invader Constructor
		this.parent = parent;
		this.x = x;
		this.y = y;
		this.invaderBullets = invaderBullets;
		this.invaderMissiles = invaderMissiles;
		airship = parent.loadImage("Airship.png");
		airshipFlipped = parent.loadImage("AirshipFlipped.png");
	}

	public void draw() { //Display invader image
		if (dx < 0) {
			parent.image(airshipFlipped, x, y);
		} else {
			parent.image(airship, x, y);
		}

	}

	void update(){ // Update invader
		if (!dead) {
			draw();
			move();
			shoot();
		}
	}

	void move(){ // Invader movement
		x= x + dx;
		dist++;

		if(dist == 108){
			y += airship.height;
			dx *= -1;
			dist = 0;
		}
	} 

	void shoot(){ //Shoot bullets and missiles randomly
		int shooting = (int) (Math.random()*1000);		
		if (shooting <= difficulty) {
			invaderBullets.add(new Bullet(parent, x , y + 32, 1));

		}
		int missile = (int) (Math.random()*10000);		
		if (missile <= difficulty) {
			invaderMissiles.add(new Missile(parent, x , y + 32, 1));

		}
	}

}
