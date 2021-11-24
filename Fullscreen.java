package FScale;

import java.awt.*;
import javax.swing.*;

public class Fullscreen extends JPanel{
	private FScale s;
	private Fullscreen() {
		s = new FScale(this, 800, 600); //set up scaling, will appear to coder as a panel of 800x600
	}
	public static void main(String[] args) {
		JFrame frame = new JFrame("JFrame Scaling Demo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container cont = frame.getContentPane();
		Fullscreen ex = new Fullscreen();
		cont.add(ex);
		frame.setUndecorated(true);
		frame.pack();
		//FScale.maximize(frame);
		frame.setVisible(true);
	}
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		s.scale(g); //must be called, scales graphics from 800x600 to actual screen size
		
		//paint & draw as if your screen was 800x600
		setBackground(Color.black);
		g.setColor(Color.gray);
		g.fillRect(100, 100, 600, 400);
		g.drawRect(1, 1, 798, 598);
	}
}
