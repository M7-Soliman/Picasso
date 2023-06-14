/**
 * 
 */
package picasso.parser.language.expressions;

import java.util.HashMap;
import java.util.Map;

import picasso.parser.language.ExpressionTreeNode;

/**
 * @author colinwhiting
 *
 */
public class Assignment extends ExpressionTreeNode {
	
	String var;
	ExpressionTreeNode exp;
	
	public static Map <String, ExpressionTreeNode> Assignment_Map = new HashMap<String, ExpressionTreeNode>();

	/**
	 * @param name expression
	 * 
	 */
	public Assignment(String name, ExpressionTreeNode expression) {
		this.var = name;
		this.exp = expression;
		Assignment_Map.put(name, exp);
	}
	

	@Override
	public RGBColor evaluate(double x, double y) {
		RGBColor result = exp.evaluate(x, y);

		return result;
	
	}
	
	public String toString() {
		return this.getName();
	}
	
	public String getName() {
		return this.var;
	}
	
	public ExpressionTreeNode getExpression() {
		return this.exp;
	}
	
	public boolean equals(Assignment o) {
		
		if (this.getName() == o.getName() && this.getExpression() == o.getExpression()) {
			return true;
		}
		
		else {
			return false;
		}
		
	}	


}
