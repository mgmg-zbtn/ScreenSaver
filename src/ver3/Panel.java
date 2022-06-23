package ver3;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Robot;
import java.awt.Toolkit;

import javax.swing.JPanel;

public class Panel extends JPanel implements Runnable {

	Robot robot;
	Dimension dimension;
	Image screenImage;
	Thread thread;
	
	Bubble bubble;
	
	public Panel() {
		
		dimension = new Dimension();
		dimension = Toolkit.getDefaultToolkit().getScreenSize();
		setDoubleBuffered(true);

		try {
			robot = new Robot();
			screenImage = robot.createScreenCapture(new Rectangle(dimension));
		} catch(Exception e) {
			e.printStackTrace();
		}

		bubble = new Bubble(dimension);
		thread = new Thread(this);
		thread.start();
	}

	@Override
	public void run() {
		
		while(true) {
			update();
			repaint();
			
			try {
				Thread.sleep(20);
			} catch(InterruptedException ie) {
				ie.printStackTrace();
			}
		}
	}

	public void update() {
		bubble.update();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponents(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.drawImage(screenImage, 0, 0, this);
		bubble.draw(g2);
	}
}
