package picasso.parser.language.expressions;

import picasso.parser.language.ExpressionTreeNode;

public class Random extends ExpressionTreeNode {
	
	double min = -1.000;
	double max = 1.000;
	double red;
	double green;
	double blue;

	public Random() {
		red = (double) Math.round((Math.random()*(this.max-this.min)+this.min) * Math.pow(10, 2)) / Math.pow(10, 2);
		green = (double) Math.round((Math.random()*(this.max-this.min)+this.min) * Math.pow(10, 2)) / Math.pow(10, 2);
		blue = (double) Math.round((Math.random()*(this.max-this.min)+this.min) * Math.pow(10, 2)) / Math.pow(10, 2);
	}

	@Override
	public RGBColor evaluate(double x, double y) {
		
		return new RGBColor(red, green, blue);
	}
	
	@Override
    public String toString() {
        return "[" + red + ", " + green  + ", " + blue + "]";
    }

}
