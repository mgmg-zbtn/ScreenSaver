package ver3;

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
 * @see https://exewrap.osdn.jp/
 * @since 2009/9-/11
 */
public class ScreenSaver extends JFrame implements MouseListener,
		MouseMotionListener, KeyListener {

	Panel panel;
	GraphicsDevice device;
	int wait = 2;

	public ScreenSaver() {

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

		// listener
		addKeyListener(this);
		addMouseListener(this);
		addMouseMotionListener(this);

		setVisible(true);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		exit();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		exit();
	}

	@Override
	public void mouseExited(MouseEvent e) {
		exit();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		exit();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		exit();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		exit();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		exit();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		exit();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		exit();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		exit();
	}

	//MouseEnterd and MouseMoved is waiting for 2 count
	private void exit() {

		if (wait < 0) {
			device.setFullScreenWindow(null);
			System.exit(0);
		}
		wait--;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		new ScreenSaver();
	}
}
