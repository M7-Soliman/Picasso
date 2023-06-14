package picasso.view.commands;


import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JTextField;

import picasso.model.Pixmap;
import picasso.parser.ExpressionTreeGenerator;
import picasso.parser.language.ExpressionTreeNode;
import picasso.util.Command;
import javax.swing.JOptionPane;

/**
 * Evaluate an expression for each pixel in a image.
 * 
 * @author Robert C Duvall
 * @author Sara Sprenkle
 */
public class Evaluator implements Command<Pixmap> {
	public static final double DOMAIN_MIN = -1;
	public static final double DOMAIN_MAX = 1;
	public static boolean rand;
	
	JTextField textfield;
	
	public Evaluator(JTextField tf) {
		this.textfield = tf;
	}
	

	/**
	 * Evaluate an expression for each point in the image.
	 */
	public void execute(Pixmap target) {
		try {
		// create the expression to evaluate just once
		ExpressionTreeNode expr = createExpression();
		// evaluate it for each pixel
		Dimension size = target.getSize();
		for (int imageY = 0; imageY < size.height; imageY++) {
			double evalY = imageToDomainScale(imageY, size.height);
			for (int imageX = 0; imageX < size.width; imageX++) {
				double evalX = imageToDomainScale(imageX, size.width);
				Color pixelColor = expr.evaluate(evalX, evalY).toJavaColor();
				target.setColor(imageX, imageY, pixelColor);
				}
			}
		}

		catch (NullPointerException e) {
			Evaluator.errorBox("Please enter an expression");
		}
	}

	/**
	 * Convert from image space to domain space.
	 */
	protected static double imageToDomainScale(int value, int bounds) {
		double range = DOMAIN_MAX - DOMAIN_MIN;
		return ((double) value / bounds) * range + DOMAIN_MIN;
	}

	/**
	 * 
	 * A place holder for a more interesting way to build the expression.
	 */
	
	//will call on this method every time an invalid input shows//
	public static void errorBox(String message){
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.INFORMATION_MESSAGE);
	}
	
	
	private ExpressionTreeNode createExpression() {
		// Note, when you're testing, you can use the ExpressionTreeGenerator to
		// generate expression trees from strings, or you can create expression
		// objects directly (as in the commented statement below).
    
		try {
		String input = textfield.getText(); //PLACE GUI ERROR HANDLING TEST HERE
		
		if (HistoryFolder.HistoryList == null) {
			new HistoryFolder();
		}
		
		HistoryFolder.addToList(input);
	

			ExpressionTreeGenerator expTreeGen = new ExpressionTreeGenerator();
			return expTreeGen.makeExpression(input);

		}

		//ExpressionTreeGenerator expTreeGen = new ExpressionTreeGenerator();
		//return expTreeGen.makeExpression(input);	
		
		catch (ExceptionInInitializerError e) {
			Evaluator.errorBox("A problem occurred while evaluating input");
			return null;
		}
		catch (IllegalArgumentException e) {
			Evaluator.errorBox("The input is incorrect. Enter a valid expression");
			return null;
		}
		catch (RuntimeException e) {
			Evaluator.errorBox("A problem has occurred. Check invalid expression.");
			e.printStackTrace();
			return null;
		}
	}
}
