/**
 * 
 */
package picasso.parser.language.expressions;

import picasso.parser.language.ExpressionTreeNode;

/**
 * @author drewlarsen
 *
 */
public class ImageWrap extends ImageFunctions {
	
	/**
	 * @param myImage 
	 * @param param_left 
	 * @param param_right
	 */
	public ImageWrap(Image myImage, ExpressionTreeNode param_left, ExpressionTreeNode param_right) {
		super(myImage, param_left, param_right);
	}
	
	@Override
	public RGBColor evaluate(double x, double y) {
		double xCoord = Wrap.wrap((param_left.evaluate(x, y)).getRed());
		double yCoord = Wrap.wrap((param_right.evaluate(x, y)).getRed());

		RGBColor imageColor = myImage.evaluate(xCoord, yCoord);
		return imageColor;
	}

}
