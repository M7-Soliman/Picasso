package picasso.parser.language.expressions;

import picasso.parser.language.ExpressionTreeNode;


/**
 * 
 * expresses the absolute value function as an image
 */

public class Abs extends UnaryFunction {
	
	/**
	 *
	 * @param 
	 */
	public Abs(ExpressionTreeNode param) {
		super(param);
	}

	/**
	 * @param x - x-coordinate of pixel
	 * @param y - y-coordinate of pixel
	 * @return the color of pixel
	 */
	@Override
	public RGBColor evaluate(double x, double y) {
		
		
		RGBColor result = param.evaluate(x, y);
		double red = Math.abs(result.getRed());
		double green = Math.abs(result.getGreen());
		double blue = Math.abs(result.getBlue());

		return new RGBColor(red, green, blue);
	}
	
	@Override
    public String toString() {
        return "abs(" + this.param + ")";
    }

}