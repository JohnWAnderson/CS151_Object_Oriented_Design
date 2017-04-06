import javax.swing.*;


import java.awt.*;

import javax.swing.event.*;
/**
* Created 29 October 2015
* @author John Anderson
* @version 1.0
* 
* */
public class Slider{
	private JSlider slider;
	private final int DEFAULT_SIZE = 50;
	public Slider()
	{
		final Icon car = new CarIcon(DEFAULT_SIZE);
		JFrame frame = new JFrame();
		frame.setLayout(new BorderLayout());
		frame.setSize(500,500);
		final JLabel label = new JLabel(car);
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(510,275));
		panel.add(label);
		frame.add(panel, BorderLayout.NORTH);
		slider = new JSlider(JSlider.HORIZONTAL, 0, 10, 1);
		slider.setMajorTickSpacing(2);
		slider.setPaintTicks(true);
		frame.add(slider, BorderLayout.SOUTH);
		slider.addChangeListener( new ChangeListener() {
		public void stateChanged(ChangeEvent e){
			JSlider source = (JSlider)e.getSource();
			int change = (int)source.getValue();
			Icon car2 = new CarIcon(DEFAULT_SIZE * change + 1);
			label.setIcon(car2);
			label.repaint();
			}
		});		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
}
