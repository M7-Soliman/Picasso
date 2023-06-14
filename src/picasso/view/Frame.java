package picasso.view;

import java.awt.BorderLayout;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import picasso.model.Pixmap;
import picasso.util.ThreadedCommand;
import picasso.view.commands.*;

/**
 * Main container for the Picasso application
 *
 * @author Robert Duvall (rcd@cs.duke.edu)
 * 
 */
@SuppressWarnings("serial")
public class Frame extends JFrame {
	public Frame(Dimension size) {
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		// create GUI components
		Canvas canvas = new Canvas(this);
		canvas.setSize(size);
		
		JPanel panel = new JPanel();
		JTextField textfield = new JTextField(16);
		JLabel label = new JLabel(" Enter expression: ");

		// add commands to test here
		ButtonPanel commands = new ButtonPanel(canvas);
		commands.add("Open", new Reader());
		commands.add("Evaluate", new ThreadedCommand<Pixmap>(canvas, new Evaluator(textfield)));
		commands.add("Save", new Writer());
		commands.add("Read", new ThreadedCommand<Pixmap>(canvas, new ExpressionReader()));
		commands.add("History", new ThreadedCommand<Pixmap>(canvas, new History(textfield)));
		commands.add("Random", new ThreadedCommand<Pixmap>(canvas, new RandomExpression(textfield)));
		commands.add("Change Size", new ThreadedCommand<Pixmap>(canvas, new SizeChange(textfield, canvas)));
		
		panel.setLayout(new BorderLayout());
		panel.add(commands,BorderLayout.NORTH);
		panel.add(textfield,BorderLayout.CENTER);
	    panel.add(label, BorderLayout.WEST);

		// add our container to Frame and show it
		getContentPane().add(canvas, BorderLayout.CENTER);
		getContentPane().add(panel, BorderLayout.NORTH);
		pack();
	}
}
