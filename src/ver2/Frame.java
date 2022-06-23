package ver2;

import java.awt.Cursor;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

/**
 * 
 * @author **
 * @since 2012/11/03
 */
public class Frame extends JFrame implements MouseListener,
		MouseMotionListener, KeyListener {

	Panel panel;
	GraphicsDevice device;
	int wait = 2;

	public Frame() {

		panel = new Panel();
		getContentPane().add(panel);
		
		// hidden of window frame
		setUndecorated(true);

		// hidden of mouse pointer
		Cursor cursor = Toolkit.getDefaultToolkit().createCustomCursor(
				new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB),
				new Point(), "");
		setCursor(cursor);

		// full screen
		device = GraphicsEnvironment.getLocalGraphicsEnvironment()
				.getDefaultScreenDevice();
		device.setFullScreenWindow(this);

		addKeyListener(this);
		addMouseListener(this);
		addMouseMotionListener(this);

		setVisible(true);
	}

	@Override
	public void mouseClicked(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {
		exit();
	}

	@Override
	public void mouseReleased(MouseEvent e) {}
	@Override
	public void mouseDragged(MouseEvent e) {}

	@Override
	public void mouseMoved(MouseEvent e) {
		exit();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		exit();
	}

	@Override
	public void keyReleased(KeyEvent e) {}
	@Override
	public void keyTyped(KeyEvent e) {}

	//MouseEnterd and MouseMoved is waiting for 2 count
	private void exit() {
		if (wait < 0) {
			device.setFullScreenWindow(null);
			System.exit(0);
		}
		wait--;
	}
}
