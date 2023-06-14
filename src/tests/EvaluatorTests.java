/**
 * 
 */
package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import picasso.parser.ExpressionTreeGenerator;
import picasso.parser.language.ExpressionTreeNode;
import picasso.parser.language.expressions.*;

/**
 * Tests of the evaluation of x
 * 
 * @author Sara Sprenkle
 * 
 */
public class EvaluatorTests {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	public void setUp() throws Exception {
	}

	@Test
	public void testConstantEvaluation() {
		ExpressionTreeNode e = new RGBColor(1, -1, 1);
		for (int i = -1; i <= 1; i++) {
			assertEquals(new RGBColor(1, -1, 1), e.evaluate(i, i));
		}
	}

	@Test
	public void testXEvaluation() {
		X x = new X();
		for (int i = -1; i <= 1; i++) {
			assertEquals(new RGBColor(i, i, i), x.evaluate(i, i));
		}
	}

	@Test
	public void testFloorEvaluation() {
		Floor myTree = new Floor(new X());

		// some straightforward tests
		assertEquals(new RGBColor(0, 0, 0), myTree.evaluate(.4, -1));
		assertEquals(new RGBColor(0, 0, 0), myTree.evaluate(.999, -1));
		assertEquals(new RGBColor(-1, -1, -1), myTree.evaluate(-.7, -1));

		// test the ints; remember that y's value doesn't matter
		for (int i = -1; i <= 1; i++) {
			assertEquals(new RGBColor(i, i, i), myTree.evaluate(i, -i));
			assertEquals(new RGBColor(i, i, i), myTree.evaluate(i, i));
		}

		double[] tests = { -.7, -.00001, .000001, .5 };

		for (double testVal : tests) {
			double floorOfTestVal = Math.floor(testVal);
			assertEquals(new RGBColor(floorOfTestVal, floorOfTestVal, floorOfTestVal), myTree.evaluate(testVal, -1));
			assertEquals(new RGBColor(floorOfTestVal, floorOfTestVal, floorOfTestVal),
					myTree.evaluate(testVal, testVal));
		}
	}

	
	@Test
	public void testSinEvaluation() {
		Sin myTree = new Sin(new X());
		
		assertEquals(new RGBColor(0.49999999999999994, 0.49999999999999994, 0.49999999999999994), myTree.evaluate(Math.PI/6, -1));
		assertEquals(new RGBColor(0, 0, 0), myTree.evaluate(0, -1));
		assertEquals(new RGBColor(1, 1, 1), myTree.evaluate(Math.PI/2, -1));
		assertEquals(new RGBColor(-0.5000000000000004, -0.5000000000000004, -0.5000000000000004), myTree.evaluate((2*(Math.PI))-(Math.PI/6), -1));

	}

	@Test
	public void testCosEvaluation() {
		Cos myTree = new Cos(new X());

		assertEquals(new RGBColor(-1, -1, -1), myTree.evaluate(Math.PI, -1));
		assertEquals(new RGBColor(0.5000000000000001, 0.5000000000000001, 0.5000000000000001), myTree.evaluate(Math.PI/3, -1));
		assertEquals(new RGBColor(6.123233995736766E-17, 6.123233995736766E-17, 6.123233995736766E-17), myTree.evaluate(Math.PI/2, -1));
		assertEquals(new RGBColor(0.5000000000000001, 0.5000000000000001, 0.5000000000000001), myTree.evaluate(-Math.PI/3, -1));

	}
	
	@Test
	public void testTanEvaluation() {
		Tan myTree = new Tan(new X());

		assertEquals(new RGBColor(-1.2246467991473532E-16, -1.2246467991473532E-16, -1.2246467991473532E-16), myTree.evaluate(Math.PI, -1));
		assertEquals(new RGBColor(0.9999999999999999, 0.9999999999999999, 0.9999999999999999), myTree.evaluate(Math.PI/4, -1));
		
		assertThrows(IllegalArgumentException.class, () -> {
			myTree.evaluate(Math.PI/2, -1);
		});		
		assertThrows(IllegalArgumentException.class, () -> {
			myTree.evaluate(3*Math.PI/2, -1);
		});		
	}
	
	@Test
	public void testLogEvaluation() {
		Log myTree = new Log(new X());


		assertEquals(new RGBColor(0, 0, 0), myTree.evaluate(1, -1));
		assertEquals(new RGBColor(1, 1, 1), myTree.evaluate(Math.E, -1));
	}
	@Test
	public void testPlusEvaluation() {
		Plus myTree = new Plus(new X(), new Y());

		assertEquals(new RGBColor(0, 0, 0), myTree.evaluate(1, -1));
		assertEquals(new RGBColor(-2, -2, -2), myTree.evaluate(-1, -1));
		
		double[] tests = { -.3, -.00001, .000001, .5 };

		for (double testVal : tests) {
			double testout = testVal + testVal;
			assertEquals(new RGBColor(testout, testout, testout), myTree.evaluate(testVal, testVal));
			assertEquals(new RGBColor(testout, testout, testout), myTree.evaluate(testVal, testVal));
		}
		}
	@Test
	public void testMinusEvaluation() {
		Minus myTree = new Minus(new X(), new Y());

		assertEquals(new RGBColor(2, 2, 2), myTree.evaluate(1, -1));
		assertEquals(new RGBColor(0, 0, 0), myTree.evaluate(-1, -1));
		
		double[] tests = { -.3, -.00001, .000001, .5 };

		for (double testVal : tests) {
			double testout = testVal - testVal;
			assertEquals(new RGBColor(testout, testout, testout), myTree.evaluate(testVal, testVal));
			assertEquals(new RGBColor(testout, testout, testout), myTree.evaluate(testVal, testVal));
		
		}
	}
	
	@Test
	public void testAssignmentEvaluation() {
		Assignment myTree = new Assignment("a", new Plus(new X(), new Y()));

		assertEquals(new RGBColor(2, 2, 2), myTree.evaluate(1, 1));
		assertEquals(new RGBColor(-2, -2, -2), myTree.evaluate(-1, -1));
		assertEquals(new RGBColor(0, 0, 0), myTree.evaluate(-1, 1));
		assertEquals(new RGBColor(0, 0, 0), myTree.evaluate(1, -1));
		
		double[] tests = { -.3, -.00001, .000001, .5 };

		for (double testVal : tests) {
			double testout = testVal + testVal;
			assertEquals(new RGBColor(testout, testout, testout), myTree.evaluate(testVal, testVal));
			assertEquals(new RGBColor(testout, testout, testout), myTree.evaluate(testVal, testVal));
		
		}
	}
		
	@Test
	public void testAtanEvaluation() {
		Atan myTree = new Atan(new X());

		
		assertEquals(new RGBColor(-0.7853981633974483, -0.7853981633974483, -0.7853981633974483), myTree.evaluate(-1, -1));
		assertEquals(new RGBColor(0, 0, 0), myTree.evaluate(0, -1));
		assertEquals(new RGBColor(-0.6107259643892086, -0.6107259643892086, -0.6107259643892086), myTree.evaluate(-.7, -1));
		
		double[] tests = { -.3, -.00001, .000001, .5 };

		for (double testVal : tests) {
			double testout =  Math.atan(testVal);
			assertEquals(new RGBColor(testout, testout, testout), myTree.evaluate(testVal, testVal));
			assertEquals(new RGBColor(testout, testout, testout), myTree.evaluate(testVal, testVal));
		
		}

	
	}
	
	@Test
	public void testAbsEvaluation() {
		Abs myTree = new Abs(new X());
		
		assertEquals(new RGBColor(1, 1, 1), myTree.evaluate(-1, -1));
		assertEquals(new RGBColor(0, 0, 0), myTree.evaluate(0, -1));
		
		
		double[] tests = { -.3, -.00001, .000001, .5 };

		for (double testVal : tests) {
			double testout =  Math.abs(testVal);
			assertEquals(new RGBColor(testout, testout, testout), myTree.evaluate(testVal, testVal));
			assertEquals(new RGBColor(testout, testout, testout), myTree.evaluate(testVal, testVal));
		
		}
	

	
	}
	@Test
	public void testExpEvaluation() {
		Exp myTree = new Exp(new X());
		
		
		assertEquals(new RGBColor(0.36787944117144233, 0.36787944117144233, 0.36787944117144233), myTree.evaluate(-1, -1));
		assertEquals(new RGBColor(1, 1, 1), myTree.evaluate(0, -1));
		assertEquals(new RGBColor(0.4965853037914095, 0.4965853037914095, 0.4965853037914095), myTree.evaluate(-.7, -1));


		
	}

	@Test
	public void testCeilEvaluation() {
		Ceil myTree = new Ceil(new X());

		// some straightforward tests
		assertEquals(new RGBColor(1, 1, 1), myTree.evaluate(.4, -1));
		assertEquals(new RGBColor(1, 1, 1), myTree.evaluate(.999, -1));
		assertEquals(new RGBColor(0, 0, 0), myTree.evaluate(-.7, -1));

		// test the ints; remember that y's value doesn't matter
		for (int i = -1; i <= 1; i++) {
			assertEquals(new RGBColor(i, i, i), myTree.evaluate(i, -i));
			assertEquals(new RGBColor(i, i, i), myTree.evaluate(i, i));
		}

		double[] tests = { -.7, -.00001, .000001, .5 };

		for (double testVal : tests) {
			double floorOfTestVal = Math.ceil(testVal);
			assertEquals(new RGBColor(floorOfTestVal, floorOfTestVal, floorOfTestVal), myTree.evaluate(testVal, -1));
			assertEquals(new RGBColor(floorOfTestVal, floorOfTestVal, floorOfTestVal),
					myTree.evaluate(testVal, testVal));
		}
	}
		
		@Test
		public void testRGBToYcrCbEvaluations() {
			RgbToYCrCb myTree = new RgbToYCrCb(new X());
			
			assertEquals(new RGBColor(14.999999999999998, 0.0015000000000000568, 0.0015000000000000568), myTree.evaluate(15, 1));
			assertEquals(new RGBColor(14.500000000000002, 0.0014500000000001734, 0.0014499999999995072), myTree.evaluate(14.5, -1));
			assertEquals(new RGBColor(-18.0, -0.0017999999999993577, -0.0018000000000000238), myTree.evaluate(-18, -1));

	}

		
		@Test
		public void testYcrCbTSoRGBEvaluations() {
			YCrCbToRGB myTree = new YCrCbToRGB(new X());
			
			assertEquals(new RGBColor(36.033, -0.9015000000000022, 41.565), myTree.evaluate(15, 1));
			assertEquals(new RGBColor(34.8319, -0.8714500000000012, 40.1795), myTree.evaluate(14.5, -1));
			assertEquals(new RGBColor(-43.239599999999996, 1.0818000000000012, -49.878), myTree.evaluate(-18, -1));

	}
		
		@Test
		public void testBangEvaluations() {
			Bang myTree = new Bang(new X());
			
			assertEquals(new RGBColor(-15, -15, -15), myTree.evaluate(15, 1));
			assertEquals(new RGBColor(-14.5, -14.5, -14.5), myTree.evaluate(14.5, -1));
			assertEquals(new RGBColor(18, 18, 18), myTree.evaluate(-18, -1));
			
			for (int i = -1; i <= 1; i++) {
				assertEquals(new RGBColor(-i, -i, -i), myTree.evaluate(i, -i));
				assertEquals(new RGBColor(-i, -i, -i), myTree.evaluate(i, i));
			}

			double[] tests = { -.7, -.00001, .000001, .5 };

			for (double testVal : tests) {
				double floorOfTestVal = - testVal;
				assertEquals(new RGBColor(floorOfTestVal, floorOfTestVal, floorOfTestVal), myTree.evaluate(testVal, -1));
				assertEquals(new RGBColor(floorOfTestVal, floorOfTestVal, floorOfTestVal),
						myTree.evaluate(testVal, testVal));
			}

	}


	

	

	
	

	
}
