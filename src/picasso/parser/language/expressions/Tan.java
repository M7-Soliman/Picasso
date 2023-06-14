package picasso.parser.language.expressions;

import picasso.parser.language.ExpressionTreeNode;


/**
 * 
 * 
 * expresses the tangent function as an image
 */

public class Tan extends UnaryFunction {
	
	/**
	 *
	 * @param 
	 */
	public Tan(ExpressionTreeNode param) {
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
		
		if ((x % (Math.PI/2) == 0.0) && (x % Math.PI != 0.0)) {
			throw new IllegalArgumentException("Invalid values for tan");
					}

		double red = Math.tan(result.getRed());
		double green = Math.tan(result.getGreen());
		double blue = Math.tan(result.getBlue());
		
		if (red > 1) {
			red = 1;
		}
		if (green > 1) {
			green = 1;
		}
		if (blue > 1) {
			blue = 1;
		}

		if (red < -1) {
			red = -1;
		}
		if (green < -1) {
			green = -1;
		}
		if (blue < -1) {
			blue = -1;
		}

		return new RGBColor(red, green, blue);
	}
	
	@Override
    public String toString() {
        return "tan(" + this.param + ")";
    }

}