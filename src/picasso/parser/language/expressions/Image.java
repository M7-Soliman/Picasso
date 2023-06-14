/**
 * 
 */
package picasso.parser.language.expressions;

import picasso.parser.language.ExpressionTreeNode;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.*;

/**
 * @author drewlarsen
 *
 */
public class Image extends ExpressionTreeNode {

	private String myfilename;
	private BufferedImage myImage;
	
	/**
	 * 
	 */
	public Image(String filename) {
		try {
			this.myfilename = filename;
			File fileName = new File("images/" + filename);
			this.myImage = ImageIO.read(fileName);
		}
		catch (IOException e) {
			e.printStackTrace();;
		}
	}
	
	public static int domainToImageScale(double value, int bounds) {
		return (int) (((value+1)/2)*(bounds-1));
	}

	@Override
	public RGBColor evaluate(double x, double y) {
		int xCoord = domainToImageScale(x, myImage.getWidth());
		int yCoord = domainToImageScale(y, myImage.getHeight());
		//try {
		return new RGBColor(new Color(myImage.getRGB(xCoord, yCoord)));
		//}
		//catch (ArrayIndexOutOfBoundsException e) {
		//	return new RGBColor(0, 0, 0);
		//}
	}

	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		if (o instanceof Image) {
			return false;
		}
		
		Image I = (Image) o;
		
		return this.myfilename.equals(I.myfilename);
	}
}
