package FScale;

import java.awt.*;

import javax.swing.Box;
import javax.swing.Box.Filler;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.Component;
import java.awt.Frame;
/**
 * A utility to scale Java AWT Components based on display size<br>
 * Works with swing JFrames, JPanels and all other subclasses of Frame
 */
public class FScale extends Container{
	private Dimension outSize, canvasSize;
	private Point offset;
	private double scale;
	
	/**
	 * Automatically scales a JFrame to fit the screen.<br>
	 * Width and Height should be the dimensions the JFrame is designed at.
	 * @param f JFrame to be scaled
	 * @param w	Canvas Width
	 * @param h	Canvas Height
	 */
	public FScale(JFrame f, int w, int h) {
		f.setUndecorated(true);
		//set window to full size allowed
		GraphicsDevice screen = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		if(screen.isFullScreenSupported()) {
			screen.setFullScreenWindow(f);
		}else if(Toolkit.getDefaultToolkit().isFrameStateSupported(Frame.MAXIMIZED_BOTH)){
			f.setExtendedState(Frame.MAXIMIZED_BOTH);
		}else {
			f.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		}
		//finding scale value
		outSize = new Dimension(f.getWidth(), f.getHeight());
		canvasSize = new Dimension(w, h);
		double scaleX = outSize.getWidth()/w;
		double scaleY = outSize.getHeight()/h;
		scale = Math.min(scaleX, scaleY);
		//setting up inner panel
		this.setPreferredSize(new Dimension((int)(w*scale), (int)(h*scale)));
		this.setLocation(new Point(0,0));
		setBackground(Color.red);
		f.add(this);

	}
	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		g2d.scale(scale, scale);
//		g2d.setColor(Color.gray);
//		g2d.fillRect(0, 0, 800,600);
		
	}
	/**
	 * Scales a Graphics enviroment according to the scale determined in initScale()<br>
	 * Objects drawn to the graphics enviroment after this call will be scaled
	 * @param g Graphics object to scale
	 * @return Converted Graphics2D object
	 */
	public Graphics2D scale(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		g2d.scale(scale, scale);
		return g2d;
	}
	
	/**
	 * @return the size
	 */
	public Dimension getCanvasSize() {
		return (Dimension)canvasSize.clone();
	}
	/**
	 * @return the scale
	 */
	public double getScale() {
		return scale;
	}
}
