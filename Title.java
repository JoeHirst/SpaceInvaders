import processing.core.*;

public class Title {
	PApplet parent;

	public Title(PApplet parent) { //Title constructor
		this.parent = parent;
	}

	public void update(){ //Update title screen
		titleScreen();
	}

	public void titleScreen(){ //Draw title screen
		parent.textSize(20);
		parent.textAlign(PConstants.CENTER, PConstants.CENTER);
		parent.fill(0, 0, 0);
		parent.rect(0, 0, parent.width, parent.height);
		parent.fill(255, 255, 255);
		parent.text("Select difficulty with number keys to start!", parent.width / 2, parent.height / 2);
		parent.text("1 = Easy", parent.width / 2, 400);
		parent.text("2 = Medium", parent.width / 2, 430);
		parent.text("3 = Hard", parent.width / 2, 460);
		parent.text("4 = Very Hard", parent.width / 2, 490);
		parent.fill(155, 0, 0);
		parent.text("5 = INSANE", parent.width / 2, 520);
		parent.fill(0, 255, 0);
		parent.text("Move with the arrow keys and shoot with space.", parent.width / 2, parent.height / 4);
	}


}
