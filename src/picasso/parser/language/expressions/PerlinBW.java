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
public class PerlinBW extends ExpressionTreeNode {

	ExpressionTreeNode param_left;
	ExpressionTreeNode param_right;
	
	/**
	 * 
	 */
	public PerlinBW(ExpressionTreeNode param_left, ExpressionTreeNode param_right) {
		this.param_left = param_left;
		this.param_right = param_right;
	}

	@Override
	public RGBColor evaluate(double x, double y) {
		RGBColor left = param_left.evaluate(x, y);
		RGBColor right = param_right.evaluate(x, y);
		double grey = ImprovedNoise.noise(left.getRed() + right.getRed(), left.getGreen() + right.getGreen(),
				left.getBlue() + right.getBlue());
		return new RGBColor(grey, grey, grey);
	}
	
	@Override
    public String toString() {
        return "perlinBW(" + this.param_left + ", " + this.param_right + ")";
    }

	
	
	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}

		if (!(o instanceof PerlinBW)) {
			return false;
		}

		// Make sure the objects are the same type

		if (o.getClass() != this.getClass()) {
			return false;
		}

		PerlinBW uf = (PerlinBW) o;

		// check if their parameters are equal
		if (!this.param_right.equals(uf.param_right) || !this.param_left.equals(uf.param_left)) {
			return false;
		}
		return true;
	}
}
