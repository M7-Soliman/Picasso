/**
 * 
 */
package picasso.parser.language.expressions;

import picasso.parser.language.ExpressionTreeNode;

/**
 * @author drewlarsen
 *
 */
public class Wrap extends UnaryFunction {
	
	public static double red;
	public static double blue;
	public static double green;

	/**
	 * @param param
	 */
	public Wrap(ExpressionTreeNode param) {
		super(param);
	}
	

	@Override
	public RGBColor evaluate(double x, double y) {
		RGBColor result = param.evaluate(x, y);
		double red = wrap(result.getRed());
		double green = wrap(result.getGreen());
		double blue = wrap(result.getBlue());
		return new RGBColor(red,green,blue);
	}

	public static double wrap(double x) {
		double result = x;	
		if (x>=1) {
			while (result > 1) {
				result = result -2;
			}	
		} 
		else if (x<=-1) {
			while (result <-1) {
				result = result + 2;
			}
		}
		return result;
	}
	@Override
    public String toString() {
        return "wrap(" + this.param + ")";
    }
}
