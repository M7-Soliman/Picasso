/**
 * 
 */
package picasso.view.commands;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import picasso.model.Pixmap;
import picasso.util.Command;
import picasso.view.Canvas;
import java.awt.Dimension;

/**
 *
 */


public class SizeChange implements Command<Pixmap>{
	JTextField textfield;
	Canvas canvas;
	
	public SizeChange(JTextField tf, Canvas c) {
		this.textfield = tf;
		this.canvas = c;
	}

	//creates a new dimension and updates pixmap and canvas
	@Override
	/*
	 * @param target - pixmap to draw on
	 */
	public void execute(Pixmap target) {
		Dimension new_size = scaling();
		target.setSize(new_size);
		//updates canvas
		canvas.refresh();
		Evaluator t = new Evaluator(this.textfield);
		t.execute(target);
		
	}
	//prompts user with input for new dimension
	/*
	 * @return a new dimension for canvas and pixmap
	 */
	public Dimension scaling() {
		Dimension new_dim = new Dimension();
		double scaleH = Integer.parseInt(JOptionPane.showInputDialog("Enter a dimension for the height. Must be between 100-737."));
		double scaleW = Integer.parseInt(JOptionPane.showInputDialog("Enter a dimension for the width. Must be between 560-1536."));
		//-------------------------evaluating if height is correct
		try { 
		if (scaleH > 737.0) {
			Evaluator.errorBox("The input of " + scaleH + " is too large of a dimension. Dimension has been adjusted to maximum.");
			scaleH = 737.0;
		}
		else if (scaleH < 100.0) {
			Evaluator.errorBox("The input of " + scaleH + " is too small of a dimension. Dimension has been adjusted to minimum.");
			scaleH = 100.0;
		}
		else if (scaleH >= 100.0 || scaleH <= 737.0) {
			System.out.println("");
		}
		else {
			Evaluator.errorBox("An issue has occurred with the given input");
		}
		//-------------------------evaluating if width is correct
		if (scaleW > 1536.0) {
			Evaluator.errorBox("The input of " + scaleW + " is too large of a dimension. Dimension has been adjusted to maximum.");
			scaleW = 1536.0;
		}
		else if (scaleW < 560.0) {
			Evaluator.errorBox("The input of " + scaleW + " is too small of a dimension. Dimension has been adjusted to minimum.");
			scaleW = 560.0;
		}
		else if (scaleW >= 560.0 || scaleH <= 1536.0) {
			System.out.println("");
		}
		else {
			Evaluator.errorBox("An issue has occurred with the given input");
		}
		}
		catch (NumberFormatException e) {
			Evaluator.errorBox("Enter a valid input for dimension(s)");
		}
		//---------------------------set size to new dimension
		new_dim.setSize(scaleW, scaleH);
		return new_dim;

	}
}
	