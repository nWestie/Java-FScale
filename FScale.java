package FScale;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Component;
import java.awt.Frame;
/**
 * A utility to scale Java AWT Components based on display size<br>
 * Works with swing JFrames, JPanels and all other subclasses of Frame
 */
public class FScale {
	private Dimension outSize, canvasSize;
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
		outSize = Toolkit.getDefaultToolkit().getScreenSize();
		double scaleX = outSize.getWidth()/w;
		double scaleY = outSize.getHeight()/h;
		canvasSize = new Dimension(w, h);
		scale = Math.min(scaleX, scaleY);
		scale = Math.max(minScale, Math.min(maxScale, scale)); //clamping scale
		Dimension d = new Dimension((int)(w*scale), (int)(h*scale));
		if(f instanceof JFrame)f.setSize(d);	        //seems to work for frames
		else f.setPreferredSize(d);	//seems to work for subcomponets
									//when scaling on subcomponents, JFrame should be packed
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
	public void maximize(Frame f, boolean max) {
		f.setExtendedState(Frame.MAXIMIZED_BOTH);
		double scaleX = f.getWidth()/w;
		double scaleY = f.getHeight()/h;
		scale = Math.min(scaleX, scaleY);
	}
	/**
	 * @return the size
	 */
	public Dimension getSize() {
		return (Dimension)outSize.clone();
	}
	/**
	 * @return the scale
	 */
	public double getScale() {
		return scale;
	}
}
