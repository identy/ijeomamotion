package ijeoma.motion.tween.test;

import ijeoma.motion.Motion;
import ijeoma.motion.tween.Tween;
import ijeoma.motion.tween.Sequence;
import processing.core.PApplet;

public class Ani_Sequence_Basics extends PApplet {
	public float x, y, diameter;
	// AniSequence seq;
	Sequence ts;

	@Override
	public void setup() {
		size(512, 512);
		smooth();
		noStroke();
		textAlign(CENTER);
		background(255);

		x = 50;
		y = 50;
		diameter = 50;

		// seq = new AniSequence(this);
		// seq.beginSequence();
		//
		// // step 0
		// seq.add(Ani.to(this, 1, "diameter", 55));
		//
		// // step 1
		// seq.add(Ani.to(this, 2, "x:400,y:100"));
		//
		// // step 2
		// seq.add(Ani.to(this, 1, "x:450,y:400"));
		//
		// // step 3
		// seq.add(Ani.to(this, 1, "x:100,y:450"));
		//
		// // step 4
		// seq.beginStep();
		// seq.add(Ani.to(this, 1, "x:50,y:50"));
		// seq.add(Ani.to(this, 2, "diameter", 5));
		// seq.endStep();
		//
		// seq.endSequence();

		Motion.setup(this);

		ts = new Sequence();
		ts.add(new Tween(100).add(this, "diameter", 50));
		ts.add(new Tween(100).add(this, "x", 400).add(this, "y", 100));
		ts.add(new Tween(100).add(this, "x", 450).add(this, "y", 400));
		ts.add(new Tween(100).add(this, "x", 100).add(this, "y", 450));
		ts.add(new Tween(100).add(this, "x", 50).add(this, "y", 50)
				.add(this, "diameter", 5));
		ts.play();
	}

	@Override
	public void draw() {
		background(255);
		// fill(255, 5);
		// rect(0, 0, width, height);
		// ts.update();

		fill(0);
		ellipse(x, y, diameter, diameter);

		String time = (int) ts.getTime() + " / " + (int) ts.getDuration();
		text(time, width - textWidth(time) - 10, height - 10);
	}

	@Override
	public void keyPressed() {
		ts.play();
	}

	@Override
	public void mousePressed() {
		ts.pause();
	}

	@Override
	public void mouseReleased() {
		ts.resume();
	}

	@Override
	public void mouseDragged() {
		ts.seek((float) mouseX / width);
	}
}
