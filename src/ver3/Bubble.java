package ver3;

import java.awt.Color;
import java.awt.Dimension;
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
import java.util.Random;

public class Bubble {
	
	static final int NUM = 20;
	Object[] objList;
	Dimension dimension;

	public Bubble(Dimension dimension) {
		this.dimension = dimension;

		objList = new Object[NUM];
		for (int i = 0; i < objList.length; i++) {
			objList[i] = new Object();
		}
	}
	
	public void update() {
		
		for (Object obj : objList) {
			obj.x += obj.vx;
			obj.y += obj.vy;
			
			if (obj.x < 0 || dimension.width - obj.diameter < obj.x) {
				obj.vx *= -1;
			}
			if (obj.y < 0 || dimension.height - obj.diameter < obj.y) {
				obj.vy *= -1;
			}
		}
	}
	
	/*
	 * red green blue  n
	 * 	 0   255  255  1
	 * 255   255    0  2
	 * 255     0  255  3
	 */
	public void changeColor() {
		
//		for (Object obj : objList) {
//			switch (obj.n) {
//			case 0:
//				obj.color[0].count();
//				obj.r += 5;
//				obj.b -= 5;
//				if (255 < obj.r) obj.n = 1;
//			case 1:
//				obj.b += 5;
//				obj.g -= 5;
//				if (obj.b == 255) obj.n = 2;
//			case 2:
//				obj.g += 5;
//				obj.r -= 5;
//				if (obj.g == 255) obj.n = 0;
//			}
//		}
	}
	
	public void draw(Graphics2D g) {
		for (Object obj : objList) {
			obj.draw(g);
		}
	}
	
	private class Object {
	
		Random rand;
		
		int diameter;
		int radius;

		float x;
		float y;
		double vx;
		double vy;
		
		Color color;
		BufferedImage shadowImage;
		
		public Object() {
			rand = new Random();
			
			diameter = dimension.height / 5;
			radius = diameter / 2;
			
			x = rand.nextInt(dimension.width - diameter);
			y = rand.nextInt(dimension.height - diameter);
			vx = rand.nextInt(10) - 5;
			vy = rand.nextInt(10) - 5;
			vx = vx == 0 ? vx + 2 : vx;
			vy = vy == 0 ? vy - 2 : vy;
			
			color = new Color(
					rand.nextInt(255),
					rand.nextInt(255),
					rand.nextInt(255),
					100);
			
			this.createShadow();
		}
		
		private void createShadow() {
			shadowImage = new BufferedImage(diameter, diameter, BufferedImage.TYPE_4BYTE_ABGR);
			Graphics2D g = shadowImage.createGraphics();
	        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	        float[] dist = {0.0f, 1.0f};
	        Color[] colors = {
	                new Color(0, 0, 0, 70),
	                new Color(0, 0, 0, 70),
	        };
	        RadialGradientPaint grad = new RadialGradientPaint(
	        	x + radius,
	        	y + radius,
	        	radius,
	        	dist,
	        	colors,
				MultipleGradientPaint.CycleMethod.NO_CYCLE
			);
			g.setPaint(grad);
			g.fill(new Ellipse2D.Double(0, 0, diameter, diameter));
	        g.dispose();
	        
	        float[] kernel = new float[3*3];
	        for (int i = 0; i < 3*3; i++) {
	        	kernel[i] = 1.0f /  (3*3);
	        }
	        BufferedImageOp bio = new ConvolveOp(new Kernel(3, 3, kernel));

	        for (int i = 0; i < 100; i++) {
	            shadowImage = bio.filter(shadowImage, null);
	        }
	        

		}
		
		public void draw(Graphics2D g) {
	        AffineTransform af = new AffineTransform();
	        af.translate(x, y);
	        g.setTransform(af);

			g.drawImage(shadowImage, null, (int) (radius / 4), (int) (radius / 4));
			
			float[] dist = {0.85f, 1.0f};
			Color[] colors = {
					new Color(0, 0, 0, 0),
					color,
			};
			RadialGradientPaint grad = new RadialGradientPaint(
				radius,
				radius,
				radius,
				dist,
				colors,
				MultipleGradientPaint.CycleMethod.NO_CYCLE
			);
			g.setPaint(grad);
			g.fill(new Ellipse2D.Double(0, 0, diameter, diameter));
			
	        af.translate(diameter / 5, diameter / 5);
	        af.rotate(-45 * Math.PI / 180);
	        g.setTransform(af);
	        
	        g.setPaint(Color.WHITE);
			g.fill(new Ellipse2D.Double(0, 0, diameter / 8, diameter / 25));

		}
	}
}

