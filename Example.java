package FScale;

import java.awt.*;
import javax.swing.*;

public class Example extends JPanel{
	private Example() {
		setPreferredSize(new Dimension(800, 600));
	}
	public static void main(String[] args) {
		JFrame frame = new JFrame("JFrame Scaling Demo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		FScale s = new FScale(frame, 800, 600); //set up scaling, will appear to coder as a panel of 800x600
		Example ex = new Example();
		s.add(ex);
		frame.setVisible(true);
	}
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		//s.scale(g); //must be called, scales graphics from 800x600 to actual screen size
		
		//paint & draw as if your screen was 800x600
		setBackground(Color.green);
		g.setColor(Color.gray);
		g.fillRect(100, 100, 600, 400);
	}
}
