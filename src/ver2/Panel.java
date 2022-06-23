package ver2;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.util.Random;

import javax.swing.JPanel;

public class Panel extends JPanel implements Runnable {
	
	Dimension screenSize;
	Robot robot;
	Image screenImage;
	Thread thread;

	Random rand;
	Bubble[] bubble;
	static int NUM = 10;
	
	public Panel() {
		super();
		
		try {
			robot = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		screenImage = robot.createScreenCapture(new Rectangle(screenSize));
		setPreferredSize(screenSize);
		
		rand = new Random();
		bubble = new Bubble[NUM];
		for (int i = 0; i < NUM; i++) {
			bubble[i] = new Bubble();
			bubble[i].vx = rand.nextInt(20) - i;
			bubble[i].vy = rand.nextInt(18) - i;
			bubble[i].maxWidth = screenSize.width;
			bubble[i].maxHeight = screenSize.height;
		}
		
		thread = new Thread(this);
		thread.start();
	}

	@Override
	public void run() {
		while (true) {
			update();
			repaint();
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void update() {
		for (int i = 0; i < NUM; i++) {
			bubble[i].update();
		}
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(screenImage, 0, 0, this);
		for (int i = 0; i < NUM; i++) {
			bubble[i].draw(g);
		}
	}
}
