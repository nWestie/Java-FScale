package FScale;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Component;
import java.awt.Frame;
/**
 * A utility to scale Java AWT Components based on display size<br>
 * Works with swing JFrames, JPanels and all other subclasses of Frame
 */
public class FScale {
	private Dimension size;
	private double scale;
	/**
	 * Instantiate without initializing the Scale
	 */
	public FScale() {
		
	}
	/**
	 * Automatically scales a JFrame to fit the screen
	 * Width and Height should be the dimensions the JFrame is designed at.
	 * @param f JFrame to be scaled
	 * @param w	Canvas Width
	 * @param h	Canvas Height
	 * @param maxScale Maximum scaling size
	 * @param minScale Minimum Scaling size
	 */
	public FScale(Component f, int w, int h, double maxScale, double minScale) {
		initScale(f, w, h, maxScale, minScale);
	}
	/**
	 * Automatically scales a JFrame to fit the screen.<br>
	 * Width and Height should be the dimensions the JFrame is designed at.
	 * @param f JFrame to be scaled
	 * @param w	Canvas Width
	 * @param h	Canvas Height
	 */
	public FScale(Component f, int w, int h) {
		initScale(f, w, h);
	}
	/**
	 * Automatically scales a JFrame to fit the screen
	 * Width and Height should be the dimensions the JFrame is designed at.
	 * @param f JFrame to be scaled
	 * @param w	Canvas Width
	 * @param h	Canvas Height
	 * @param maxScale Maximum scaling size
	 * @param minScale Minimum Scaling size
	 */
	public void initScale(Component f, int w, int h, double minScale, double maxScale) {
		size = Toolkit.getDefaultToolkit().getScreenSize();
		double scaleX = size.getWidth()/w;
		double scaleY = size.getHeight()/h;
		scale = Math.min(scaleX, scaleY);
		scale = Math.max(minScale, Math.min(maxScale, scale)); //clamping scale
		Dimension d = new Dimension((int)(w*scale), (int)(h*scale));
		f.setPreferredSize(d);	//seems to work for panels
		f.setSize(d);	        //seems to work for frames
	}
	/**
	 * Automatically scales a JFrame to fit the screen.<br>
	 * Width and Height should be the dimensions the JFrame is designed at.
	 * @param f JFrame to be scaled
	 * @param w	Canvas Width
	 * @param h	Canvas Height
	 */
	public void initScale(Component f, int w, int h) {
		initScale(f, w, h, 0, Double.POSITIVE_INFINITY);
	}
	/**
	 * Scales a Graphics object according to the scale determined in initScale()<br>
	 * Designed to be used in the object's paintComponent, right after the super.paintComponent() is called.
	 * @param g Graphics object to scale
	 * @return Converted Graphics2D object
	 */
	public Graphics2D scale(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		g2d.scale(scale, scale);
		return g2d;
	}
	/**
	 * Maximizes a Frame to fill display
	 * @param f
	 */
	public static void maximize(Frame f) {
		f.setExtendedState(Frame.MAXIMIZED_BOTH);
	}
	/**
	 * @return the size
	 */
	public Dimension getSize() {
		return (Dimension)size.clone();
	}
	/**
	 * @return the scale
	 */
	public double getScale() {
		return scale;
	}
}
