/**
 * 
 */
package picasso.view.commands;

import picasso.view.commands.HistoryFolder;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JOptionPane;

import picasso.util.FileCommand;
import javax.swing.JTextField;


import picasso.model.Pixmap;
import picasso.parser.ExpressionTreeGenerator;
import picasso.parser.language.ExpressionTreeNode;
import picasso.util.Command;
import java.util.*;

import java.awt.Color;
import java.awt.Dimension;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

/**
 * @author drewlarsen
 *
 */


public class ExpressionReader implements Command<Pixmap>{
	
	public static File myObj;
	public static String data;
	public static final double DOMAIN_MIN = -1;
	public static final double DOMAIN_MAX = 1;
	
	/**
	 * Creates a Reader object, which prompts users for image files to open
	 */
	
	public ExpressionReader() {
	}

	@Override
	public void execute(Pixmap target) {

		ExpressionTreeNode expr = createExpression();

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
	
	public File getFile() {
		JFileChooser chooser = new JFileChooser(System.getProperties().getProperty("user.dir"));
	    FileNameExtensionFilter filter = new FileNameExtensionFilter(
	        "exp files", "exp");
	    chooser.setFileFilter(filter);
	    int returnVal = chooser.showOpenDialog(null);
	    //if(returnVal == JFileChooser.APPROVE_OPTION) {
	       //System.out.println("You chose to open this file: " +
	       //     chooser.getSelectedFile().getName());
	    //}
	    myObj = chooser.getSelectedFile();
	    return myObj;
	    
	}
	
	
	public String getExpressionText() {
		try {
		File fileObj = this.getFile();
		Scanner myReader = new Scanner(fileObj);
		while (myReader.hasNextLine()) {
			data = myReader.nextLine();
		}
        
		
        myReader.close();
		return data;
        }
		catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		      return null;
		}
		
	}
	
	public static void errorBox(String message) {
		 JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.INFORMATION_MESSAGE);
	}
		    
	private ExpressionTreeNode createExpression() {
		ExpressionTreeGenerator expTreeGen = new ExpressionTreeGenerator();
		String test = this.getExpressionText();
		if (HistoryFolder.HistoryList == null) {
			new HistoryFolder();
		}
		HistoryFolder.addToList(test);
		//System.out.println(HistoryFolder.HistoryList);

		try {
			return expTreeGen.makeExpression(test);
		}
		catch (ExceptionInInitializerError e) {
			this.errorBox("A problem occured while evaluating input");
			return null;
		}
		catch (IllegalArgumentException e) {
			this.errorBox("The input is incorrect. Check invalid expression in file.");
			return null;
		}
	
		catch (RuntimeException e) {
			this.errorBox("A problem has occured. Check invalid expression in file.");
			return null;
		}
			
	}
	
	/**
	 * Convert from image space to domain space.
	 */
	protected double imageToDomainScale(int value, int bounds) {
		double range = DOMAIN_MAX - DOMAIN_MIN;
		return ((double) value / bounds) * range + DOMAIN_MIN;
	}
	
}
	
