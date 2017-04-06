import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.SimpleFormatter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
/**
 * Is the View and Controller part of the MVC pattern
 * Created 29 October 2015
 * @author John Anderson
 * @version 1.0
 * 
 */
public class MVCTester
{
	public static void main(String[] args)
	{
		final Model model  = new Model();
		final String formatter = "";
		JFrame frame = new JFrame("viwer");
		JButton addButton = new JButton("Add");
		JTextField addedline = new JTextField(10);
		JTextArea textarea = new JTextArea();
		JScrollPane scroll = new JScrollPane(textarea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(100,200);
		frame.setVisible(true);
		frame.add(addButton, BorderLayout.NORTH);
		frame.add(addedline, BorderLayout.SOUTH);
		frame.add(scroll, BorderLayout.CENTER);
		ChangeListener listener = new ChangeListener()
		{
			public void stateChanged(ChangeEvent e) {
				textarea.setText(model.format(formatter));
				addedline.setText("");
			}
		};
		model.addChangeListener(listener);
		 addButton.addActionListener(new ActionListener()
	     {
	        public void actionPerformed(ActionEvent e)
	        {
	        	String s = addedline.getText().toString();
	        	model.addString(s);      	
	        }
	     });
	}
}
