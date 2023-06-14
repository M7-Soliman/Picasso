/**
 * 
 */
package picasso.parser.language.expressions;

import picasso.parser.language.ExpressionTreeNode;

/**
 * @author drewlarsen
 *
 */
public class ImageClip extends ImageFunctions {
	
	/**
	 * 
	 */
	public ImageClip(Image myImage, ExpressionTreeNode param_left, ExpressionTreeNode param_right) {
		super(myImage, param_left, param_right);
	}
	
	@Override
	public RGBColor evaluate(double x, double y) {
		double xCoord = Clamp.clamp((param_left.evaluate(x, y)).getRed());
		double yCoord = Clamp.clamp((param_right.evaluate(x, y)).getRed());

		RGBColor imageColor = myImage.evaluate(xCoord, yCoord);
		return imageColor;
	}


}
