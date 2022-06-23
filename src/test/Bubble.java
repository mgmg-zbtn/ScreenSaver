package test;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MultipleGradientPaint;
import java.awt.RadialGradientPaint;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Bubble extends JPanel {
	
	BufferedImage image;
	
	public Bubble() {
		this.setPreferredSize(new Dimension(400, 400));
		
		createHightRight2();
		
		JFrame frame = new JFrame();
		frame.add(this);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	public void createHightRight() {
		image = new BufferedImage(400, 400, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = image.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int diameter = 400;

        AffineTransform af = new AffineTransform();
        af.translate(diameter / 20, diameter / 10);
        af.rotate(-45 * Math.PI / 180);
        g.setTransform(af);
        
        g.setColor(Color.WHITE);
		g.fill(new Ellipse2D.Double(0, 0, diameter / 10, diameter / 25));
	}
	
	public void createHightRight2() {
		image = new BufferedImage(400, 400, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = image.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int diameter = 400;
        
        g.setPaint(new Color(255, 255, 255, 100));
        g.fill(new Ellipse2D.Double(0, 0, diameter, diameter / 2));
        
        g.setPaint(new Color(0, 0, 0, 0));
        g.fill(new Ellipse2D.Double(0, 20, diameter, diameter / 2));

	}
	
	public void createShadow() {
		image = new BufferedImage(400, 400, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = image.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	
//		g2.setColor(Color.BLACK);
//		g2.fillRect(0, 0, 400, 400);
		
		
        float[] dist = {0.0f, 1.0f};
        Color[] colors = {
                new Color(0, 0, 0, 255),
                new Color(0, 0, 0, 255),
        };
        int size = 400;
        RadialGradientPaint grad = new RadialGradientPaint(
            size/2,
            size/2,
            size/2,
            dist,
            colors,
            MultipleGradientPaint.CycleMethod.NO_CYCLE
        );

        g2.setPaint(grad);
        g2.fillOval(0, 0, size, size);

        float[] kernel = new float[3*3];
        for (int i = 0; i < 3*3; i++) {
        	kernel[i] = 1.0f /  (3*3);
        }
        BufferedImageOp bio = new ConvolveOp(new Kernel(3, 3, kernel));

        for (int i = 0; i < 100; i++) {
            image = bio.filter(image, null);
        }
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponents(g);
        g.drawImage(image, 0, 0, null);
	}

	public static void main(String[] args) {
		new Bubble();
	}
}
