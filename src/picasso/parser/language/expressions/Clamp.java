/**
 * 
 */
package picasso.parser.language.expressions;

import picasso.parser.language.ExpressionTreeNode;

/**
 * @author drewlarsen
 *
 */
public class Clamp extends UnaryFunction {

	/**
	 * Creates a clamp function that takes as a parameter the given expression
	 * 
	 * @param param
	 */
	public Clamp(ExpressionTreeNode param) {
		super(param);
		
	}
	
	public static double clamp(double x) {
		double result = x;
		if (x>1) {
			result = 1;
		}
		else if (x<-1) {
			result = -1;
		}
		return result;
	}

	@Override
	public RGBColor evaluate(double x, double y) {
		RGBColor result = param.evaluate(x, y);
		double red = clamp(result.getRed());
		double green = clamp(result.getGreen());
		double blue = clamp(result.getBlue());
		return new RGBColor(red,green,blue);
	}
	
	@Override
    public String toString() {
        return "clamp(" + this.param + ")";
    }

}
