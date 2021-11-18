import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Component;
import java.awt.Frame;
import javax.swing.JFrame;

/**
 * A utility to scale Java AWT Components based on display size<br>
 * Works with swing JFrames, JPanels and all other subclasses of Frame
 */
public class FScale {
	private Dimension size;
	private double scale;
	public static void main(String[] args) {
		JFrame frame = new JFrame("JFrame Scaling Demo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		FScale s = new FScale();
		s.initScale(frame, 200, 200);
		System.out.println(Toolkit.getDefaultToolkit().isFrameStateSupported(JFrame.MAXIMIZED_BOTH));
		//maximize(frame);
		frame.setVisible(true);
		Container pane = frame.getContentPane();
	}
	/**
	 * Automatically scales a JFrame to fit the screen
	 * Width and Height should be the dimensions the JFrame is designed at.
	 * @param f JFrame to be scaled
	 * @param w	Canvas Width
	 * @param h	Canvas Height
	 * @param maxScale Maximum scaling size
	 * @param minScale Minimun Scaling size
	 */
	public void initScale(Component f, int w, int h, double maxScale, double minScale) {
		initScale(f,w,h);
		scale = Math.max(minScale, Math.min(maxScale, scale)); //clamping scale
		f.setSize(new Dimension((int)(w*scale), (int)(h*scale)));	

	}
	/**
	 * Automatically scales a JFrame to fit the screen.<br>
	 * Width and Height should be the dimensions the JFrame is designed at.
	 * @param f JFrame to be scaled
	 * @param w	Canvas Width
	 * @param h	Canvas Height
	 */
	public void initScale(Component f, int w, int h) {
		size = Toolkit.getDefaultToolkit().getScreenSize();
		double scaleX = size.getWidth()/w;
		double scaleY = size.getHeight()/h;
		scale = Math.min(scaleX, scaleY);
		scale = scale>1.2?1.2:scale;
		f.setSize(new Dimension((int)(w*scale), (int)(h*scale)));	
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
}
