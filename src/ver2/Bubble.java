package ver2;

import java.awt.Color;
import java.awt.Graphics;

public class Bubble {
	
	int maxWidth;
	int maxHeight;
	
	double x;
	double y;
	double vx;
	double vy;
	
	int r;
	int g;
	int b;
	int a;
	int n;

	int size;
	
	public Bubble() {
		r = 0;
		g = 255;
		b = 255;
		a = 50;
		n = 1;
		
		size = 200;
	}
	
	/*
	 * red green blue  n
	 * 	 0   255  255  1
	 * 255   255    0  2
	 * 255     0  255  3
	 */
	public void update() {
		
		x += vx;
		y += vy;
		
		if (x < 0  || maxWidth - size < x) {
			vx = -vx;
		}
		
		if (y < 0 || maxHeight - size < y) {
			vy = -vy;
		}
		
		switch (n) {
		case 1:
			r += 5;
			b -= 5;
			if (r == 255) n = 2;
			break;
		case 2:
			b += 5;
			g -= 5;
			if (b == 255) n = 3;
			break;
		case 3:
			g += 5;
			r -= 5;
			if (g == 255) n = 1;
		}
	}
	
	public void draw(Graphics gd) {
		gd.setColor(new Color(r, g, b, a));
		gd.fillOval((int)x, (int)y, size, size);
	}
}
