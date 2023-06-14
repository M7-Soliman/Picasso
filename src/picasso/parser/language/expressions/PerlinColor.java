/**
 * 
 */
package picasso.parser.language.expressions;

import picasso.model.ImprovedNoise;
import picasso.parser.language.ExpressionTreeNode;

/**
 * @author drewlarsen
 *
 */
public class PerlinColor extends ExpressionTreeNode {

	ExpressionTreeNode param_left;
	ExpressionTreeNode param_right;
	
	/**
	 * 
	 */
	public PerlinColor(ExpressionTreeNode param_left, ExpressionTreeNode param_right) {
		this.param_left = param_left;
		this.param_right = param_right;
	}

	@Override
	public RGBColor evaluate(double x, double y) {
		RGBColor left = param_left.evaluate(x, y);
		RGBColor right = param_right.evaluate(x, y);
		double red = ImprovedNoise.noise(left.getRed() + 0.3, right.getRed() + 0.3, 0);
		double blue = ImprovedNoise.noise(left.getBlue() + 0.1, right.getBlue() + 0.1, 0);
		double green = ImprovedNoise.noise(left.getGreen() - 0.8, right.getGreen() - 0.8, 0);
		return new RGBColor(red, green, blue);
	}
	
	@Override
    public String toString() {
        return "perlinColor(" + this.param_left + "," + this.param_right + ")";
    }
	
	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}

		if (!(o instanceof PerlinColor)) {
			return false;
		}

		// Make sure the objects are the same type

		if (o.getClass() != this.getClass()) {
			return false;
		}

		PerlinColor uf = (PerlinColor) o;

		// check if their parameters are equal
		if (!this.param_right.equals(uf.param_right) || !this.param_left.equals(uf.param_left)) {
			return false;
		}
		return true;
	}
	
}
