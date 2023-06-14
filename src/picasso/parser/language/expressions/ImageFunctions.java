/**
 * 
 */
package picasso.parser.language.expressions;

import picasso.parser.language.ExpressionTreeNode;

/**
 * @author drewlarsen
 *
 */
public abstract class ImageFunctions extends ExpressionTreeNode {
	ExpressionTreeNode param_left;
	ExpressionTreeNode param_right;
	Image myImage;
	
	/**
	 *@param myImage 
	 *@param param_left 
	 *@param param_right
	 *
	 */
	public ImageFunctions(Image myImage, ExpressionTreeNode param_left, ExpressionTreeNode param_right) {
		this.myImage = myImage;
		this.param_left = param_left;
		this.param_right = param_right;
	}
	
	@Override
	public String toString() {
		String classname = this.getClass().getName();
		return classname.substring(classname.lastIndexOf(".")) + "(" + myImage + "," + param_left + "," + param_right + ")";
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}

		if (!(o instanceof ImageFunctions)) {
			return false;
		}

		// Make sure the objects are the same type

		if (o.getClass() != this.getClass()) {
			return false;
		}

		ImageFunctions image = (ImageFunctions) o;
		
		if (!this.param_left.equals(image.param_left)) {
			return false;
		}
		if (!this.param_right.equals(image.param_right)) {
			return false;
		}
		return true;
	}
	@Override
	public RGBColor evaluate(double x, double y) {
		return null;
	}

}
