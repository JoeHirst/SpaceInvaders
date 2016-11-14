import processing.core.*;
import java.util.ArrayList;

public class SpaceInvaderz extends PApplet {
	Defender defender;
	Title title = new Title(this);
	ArrayList<Bullet> bullets = new ArrayList<>(); // Initialize array lists
	ArrayList<Bullet> invaderBullets = new ArrayList<>();
	ArrayList<Missile> invaderMissiles = new ArrayList<>();
	Invader[] invaders = new Invader[33]; //Declare array and define size
	final int space = 64; //Global variables
	int x = 20;
	int y = 20;
	int i = 0;
	int deathCount = 0;
	int lives = 3;
	int arrayCount = 0;
	int arrayCount2 = 0;
	int arrayCount3 = 0;
	boolean end = false;
	boolean win = false;
	boolean titl = true;

	public static void main(String[] args) {
		PApplet.main(new String[] { "SpaceInvaderz"  });
	}

	public void reset() { //Resets variables and arrays
		defender = new Defender(this, width/2 - 20, height - 50, bullets);

		bullets.clear();
		invaderBullets.clear();
		invaderMissiles.clear();

		x = 20;
		y = 20;
		i = 0;
		deathCount = 0;
		lives = 3;
		arrayCount = 0;
		arrayCount2 = 0;
		arrayCount3 = 0;
		end = false;
		win = false;
		titl = true;

		for(int i = 0; i < 33; i++)//Creates all the invaders and positions them
		{
			invaders[i] = new Invader(this, x + space*arrayCount, y, invaderBullets, invaderMissiles);
			arrayCount++;
			if(i >= 11){
				invaders[i] = new Invader(this, x + space*arrayCount2, y + space, invaderBullets, invaderMissiles);
				arrayCount2++;
			}
			if(i >= 22){
				invaders[i] = new Invader(this, x + space*arrayCount3, y + space *2, invaderBullets, invaderMissiles);
				arrayCount3++;
			}
		}
	}

	public void keyPressed(){ 
		defender.keyPressed(); //Calls defender keyPressed method

		if(titl == true){ //Starts game when difficulty selected
			for(int i = 0; i < invaders.length; i++){
				if (key == '1') {
					invaders[i].difficulty = 1;
					titl = false;
				}
				if (key == '2') {
					invaders[i].difficulty = 2;
					titl = false;
				}
				if (key == '3') {
					invaders[i].difficulty = 3;
					titl = false;
				}
				if (key == '4') {
					invaders[i].difficulty = 4;
					titl = false;
				}
				if (key == '5') {
					invaders[i].difficulty =10;
					titl = false;
				}
			}
		}

		if (keyCode == ENTER) {//Calls reset function when ENTER key is pressed	
			if(end == true){ 
				reset();
			}
		}

		if (key == 'm') {
			defender.cheating = !defender.cheating;
		}
	}

	public void keyReleased(){
		defender.keyReleased(); // Calls defender keyRealeased method
	}

	public void settings(){
		size(800, 700); //Sets height and width
	}

	public void destroyInvader(){ //Destroy invader when it collides with defender bullets
		for (Bullet bullet : bullets) {
			for (Invader invader : invaders) {
				if (!bullet.dead && !invader.dead) {
					if (!(bullet.x >  invader.x + 32 || bullet.y > invader.y + 32 || bullet.x + 10 < invader.x || bullet.y + 20 < invader.y)) {
						invader.dead = true;
						bullet.dead = true;
						deathCount++;
					}
				}
			}

		}

		if(deathCount == invaders.length){ //Ends game when all invaders are dead
			end = true;
			win = true;
		}
	}

	public void destroyDefender(){ //Reduce defender lives when it collides with invader bullets
		for (Bullet bullet : invaderBullets) {
			if (!defender.dead) {
				if(!bullet.dead){
					if (!(bullet.x >  defender.x + 40 || bullet.y > defender.y + 30 || bullet.x + 10 < defender.x || bullet.y + 20 < defender.y)) {
						bullet.dead = true;
						for(int i = 0; i < 1; i++){
							lives--;
						}
					}
				}
			}
		}

		for (Missile missile : invaderMissiles) {//Reduce defender lives when it collides with invader missiles
			if (!defender.dead) {
				if(!missile.dead){
					if (!(missile.x >  defender.x + 40 || missile.y > defender.y + 30 || missile.x + 10 < defender.x || missile.y + 20 < defender.y)) {
						missile.dead = true;
						for(int i = 0; i < 1; i++){
							lives--;
						}
					}
				}
			}
		}

		if(lives == 0){ //End game when no lives left
			defender.dead = true;
			end = true;
		}

		for(Invader invader : invaders){ //End game when invaders reach bottom of screen
			if(invader.y >= height - 80){
				end = true;
			}
		}
	}

	public void bulletCollide(){ //Defender bullet and invader bullet collision
		for (Bullet bullet : bullets) {
			for (Bullet inBullet : invaderBullets){
				if(!bullet.dead){
					if(!inBullet.dead){
						if (!(bullet.x >  inBullet.x + 10 || bullet.y > inBullet.y + 20 || bullet.x + 10 < inBullet.x || bullet.y + 20 < inBullet.y)) {
							bullet.dead = true;
							inBullet.dead = true;
						}
					}
				}
			}
		}
	}

	public void missileCollide(){ //Defender bullet and invader missile collision
		for (Bullet bullet : bullets) {
			for (Missile missile : invaderMissiles){
				if(!bullet.dead){
					if(!missile.dead){
						if (!(bullet.x >  missile.x + 10 || bullet.y > missile.y + 20 || bullet.x + 10 < missile.x || bullet.y + 20 < missile.y)) {
							bullet.dead = true;
							missile.dead = true;
						}
					}
				}
			}
		}
	}

	public void setup(){ 
		background(0,0,0);

		reset();
	}

	public void drawInvaders(){ //Update all invaders in array
		for(int i = 0; i < invaders.length; i++)
		{
			invaders[i].update();
		}
	}

	public void runGame(){
		defender.update();
		drawInvaders();
		destroyInvader();
		destroyDefender();
		drawScore();
		bulletCollide();
		missileCollide();
	}

	public void drawScore() { //Display score and life counter

		textAlign(PConstants.LEFT, PConstants.TOP);
		textSize(20);
		text("Score: " + deathCount, 5, 5);
		text("Lives: " + lives, 100, 5);
		textSize(9);
		text("cheat mode = m", 715, 680);

	}

	public void draw(){	
		background(g.backgroundColor);
		for (Bullet bullet : bullets) { //Draw and update defender bullets
			if (!bullet.dead) {
				bullet.draw();	
				bullet.update();
			}
		}

		for (Bullet bullet : invaderBullets) { //Draw and update invader bullets
			if (!bullet.dead) {
				bullet.draw();	
				bullet.update();
			}
		}

		for (Missile missile : invaderMissiles) { //Draw and update invader bullets
			if (!missile.dead) {
				missile.draw();	
				missile.update();
			}
		}

		if (end == false){
			if (titl == true){ //Draw title screen
				title.update();
			}else{	//Run game
				runGame();
			}
		} else {
			textAlign(PConstants.CENTER, PConstants.CENTER);

			if (win) {
				if(lives == 1){ //Display win message
					fill(0, 255, 0);
					text("You win! You had " + lives + " life left", width / 2, height / 4);
				}else{
					fill(0, 255, 0);
					text("You win! You had " + lives + " lives left", width / 2, height / 4);
				}

			} else { //Display lose message
				fill(0, 255, 0);
				text("Game over! Your score was " + deathCount, width / 2, height / 4);
			}
			fill(255, 255, 255); //Display title screen message
			textSize(20);
			text("Press ENTER to return to title screen", width / 2, height / 2);
		}
	}
}	