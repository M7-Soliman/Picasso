package tests;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

import picasso.parser.ExpressionTreeGenerator;
import picasso.parser.language.ExpressionTreeNode;
import picasso.parser.language.expressions.*;

/**
 * Tests of creating an expression tree from a string expression. Will have
 * compiler errors until some code is created.
 * 
 * @author Sara Sprenkle
 * 
 */
public class ParseExpressionTreeTests {

	private ExpressionTreeGenerator parser;

	@BeforeEach
	public void setUp() throws Exception {
		parser = new ExpressionTreeGenerator();
	}

	@Test
	public void constantExpressionTests() {
		ExpressionTreeNode e = parser.makeExpression("[1,-1, 1]");
		assertEquals(new RGBColor(1, -1, 1), e);
	}

	@Test
	public void variableExpressionTests() {
		ExpressionTreeNode e = parser.makeExpression("x");
		assertEquals(new X(), e);
	}

	@Test
	public void additionExpressionTests() {
		ExpressionTreeNode e = parser.makeExpression("x + y");
		assertEquals(new Plus(new X(), new Y()), e);
		
		// no spaces!
		e = parser.makeExpression("x+y");
		assertEquals(new Plus(new X(), new Y()), e);

		e = parser.makeExpression("[1,.3,-1] + y");
		assertEquals(new Plus(new RGBColor(1, .3, -1), new Y()), e);
		
		e = parser.makeExpression("x + y + [ -.51, 0, 1]");
		assertEquals(new Plus(new Plus(new X(), new Y()), new RGBColor(-.51, 0, 1)), e);
	}
	
	@Test
	public void SubtractionExpressionTests() {
		ExpressionTreeNode e = parser.makeExpression("x - y");
		assertEquals(new Minus(new X(), new Y()), e);
		
		// no spaces!
		//e = parser.makeExpression("x-y");
		//assertEquals(new Minus(new X(), new Y()), e);

		e = parser.makeExpression("[1,.3,-1] - y");
		assertEquals(new Minus(new RGBColor(1, .3, -1), new Y()), e);
		
		e = parser.makeExpression("x - y - [ -.51, 0, 1]");
		assertEquals(new Minus(new Minus(new X(), new Y()), new RGBColor(-.51, 0, 1)), e);
	}
	
	@Test
	public void MultiplyExpressionTests() {
		ExpressionTreeNode e = parser.makeExpression("x * y");
		assertEquals(new Multiply(new X(), new Y()), e);
		
		// no spaces!
		e = parser.makeExpression("x*y");
		assertEquals(new Multiply(new X(), new Y()), e);

		e = parser.makeExpression("[1,.3,-1] * y");
		assertEquals(new Multiply(new RGBColor(1, .3, -1), new Y()), e);
		
		e = parser.makeExpression("x * y * [ -.51, 0, 1]");
		assertEquals(new Multiply(new Multiply(new X(), new Y()), new RGBColor(-.51, 0, 1)), e);
	}
	
	@Test
	public void DivideExpressionTests() {
		ExpressionTreeNode e = parser.makeExpression("x / y");
		assertEquals(new Divide(new X(), new Y()), e);
		
		// no spaces!
		e = parser.makeExpression("x/y");
		assertEquals(new Divide(new X(), new Y()), e);

		e = parser.makeExpression("[1,.3,-1] / y");
		assertEquals(new Divide(new RGBColor(1, .3, -1), new Y()), e);
		
		e = parser.makeExpression("x / y / [ -.51, 0, 1]");
		assertEquals(new Divide(new Divide(new X(), new Y()), new RGBColor(-.51, 0, 1)), e);
	}
	
	@Test
	public void parenthesesExpressionTests() {
		ExpressionTreeNode e = parser.makeExpression("( x + y )");
		assertEquals(new Plus(new X(), new Y()), e);

		e = parser.makeExpression("( x + (y + [ 1, 1, 1] ) )");
		assertEquals(new Plus(new X(), new Plus(new Y(), new RGBColor(1, 1, 1))), e);
	}

	@Test
	public void floorFunctionTests() {
		ExpressionTreeNode e = parser.makeExpression("floor( x )");
		assertEquals(new Floor(new X()), e);

		e = parser.makeExpression("floor( x + y )");
		assertEquals(new Floor(new Plus(new X(), new Y())), e);
	}
	
	@Test
	public void ceilFunctionTests() {
		ExpressionTreeNode e = parser.makeExpression("ceil( x )");
		assertEquals(new Ceil(new X()), e);

		e = parser.makeExpression("ceil( x + y )");
		assertEquals(new Ceil(new Plus(new X(), new Y())), e);
	}
	
	@Test
	public void AssignmentExpressionTests() {
		Assignment e = (Assignment) parser.makeExpression("a = x + y");
		assertEquals((new Assignment("a", new Plus(new X(), new Y()))).getName(), e.getName());
		assertEquals((new Assignment("a", new Plus(new X(), new Y()))).getExpression(), e.getExpression());
	}	
	
	@Test
	public void OrderOfOperationsTests() {
		ExpressionTreeNode e = parser.makeExpression("x + y / x + y * x ");
		assertEquals(new Plus(new Plus(new Multiply(new Y(), new X()), new Divide(new Y(), new X())), new X()), e);
	}		

	@Test
	public void wrapFunctionTest() {
		ExpressionTreeNode e = parser.makeExpression("wrap( x + x )");
		assertEquals(new Wrap(new Plus(new X(), new X())), e);
	}
	
	@Test
	public void imageWrapFunctionTest() {
		ExpressionTreeNode e = parser.makeExpression("imageWrap(\"vortex.jpg\", x + x, y)");
		assertEquals(new ImageWrap(new Image("vortex.jpg"), new Plus(new X(), new X()), new Y()), e);
	}
	
	@Test
	public void imageClipFunctionTest() {
		ExpressionTreeNode e = parser.makeExpression("imageClip(\"vortex.jpg\", x + x, y)");
		assertEquals(new ImageClip(new Image("vortex.jpg"), new Plus(new X(), new Y()), new Y()), e);
	}
	
	@Test
	public void PerlinColorTest() {
		ExpressionTreeNode e = parser.makeExpression("perlinColor( x , y )");
		assertEquals(new PerlinColor(new X(), new Y()), e);
	}
	
	@Test
	public void PerlinBWtest() {
		ExpressionTreeNode e = parser.makeExpression("perlinBW( x , y )");
		assertEquals(new PerlinBW(new X(), new Y()), e);
	}
	
	@Test
	public void Bangtest() {
		ExpressionTreeNode e = parser.makeExpression(" ! x ");
		assertEquals(new Bang(new X()), e);
	}
	
	
}
