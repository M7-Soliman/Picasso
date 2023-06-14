package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import picasso.parser.ParseException;
import picasso.parser.Tokenizer;
import picasso.parser.language.expressions.Image;
import picasso.parser.tokens.*;
import picasso.parser.tokens.chars.*;
import picasso.parser.tokens.functions.*;
import picasso.parser.tokens.operations.*;

public class TokenizerTest {

	Tokenizer tokenizer;
	List<Token> tokens;
	List<Token> tokens2;

	@BeforeEach
	public void setUp() throws Exception {
		tokenizer = new Tokenizer();
	}
	
	/**
	 * Test that parsing an expression with a comment works
	 */
	@Test
	public void testTokenizeComment() {
		String expression = "x // this is a comment";
		List<Token> tokens = tokenizer.parseTokens(expression);
		assertEquals(new IdentifierToken("x"), tokens.get(0));
		assertEquals(1, tokens.size());
		
		expression = "// everything is a comment";
		tokens = tokenizer.parseTokens(expression);
		assertEquals(0, tokens.size());
	}

	/**
	 * Test that parsing a constant works
	 */
	@Test
	public void testTokenizeConstant() {
		String expression = ".324";
		List<Token> tokens = tokenizer.parseTokens(expression);
		assertEquals(new NumberToken(.324), tokens.get(0));

		expression = "-1";
		tokens = tokenizer.parseTokens(expression);
		assertEquals(new NumberToken(-1), tokens.get(0));

		// No problems here; problem will be in next step (Semantic Analysis)
		expression = "-1.2";
		tokens = tokenizer.parseTokens(expression);
		assertEquals(new NumberToken(-1.2), tokens.get(0));
	}

	@Test
	public void testTokenizeColor() {
		String expression = "[1, 1, 1]";
		tokens = tokenizer.parseTokens(expression);
		assertEquals(new ColorToken(1, 1, 1), tokens.get(0));

		expression = "[-1, 0, .5]";
		tokens = tokenizer.parseTokens(expression);
		assertEquals(new ColorToken(-1, 0, .5), tokens.get(0));
	}

	@Test
	public void testTokenizeInvalidColor() {
		String expression = "[1, 1.0001, 1]";

		assertThrows(ParseException.class, () -> {
			tokens = tokenizer.parseTokens(expression);
		});
	}

	@Test
	public void testTokenizeBasicFunctionExpression() {
		String expression = "floor(x)";
		tokens = tokenizer.parseTokens(expression);
		assertEquals(new FloorToken(), tokens.get(0));
		assertEquals(new LeftParenToken(), tokens.get(1));
		assertEquals(new IdentifierToken("x"), tokens.get(2));
		assertEquals(new RightParenToken(), tokens.get(3));
	}
	
	@Test
	public void testTokenizeRandomFunctionExpression() {
		String expression = "random()";
		tokens = tokenizer.parseTokens(expression);
		assertEquals(new RandomToken(), tokens.get(0));
		assertEquals(new LeftParenToken(), tokens.get(1));
		assertEquals(new RightParenToken(), tokens.get(2));
	}

	@Test
	public void testTokenizeCombinedFunctionExpression() {
		String expression = "perlinColor(floor(x), y)";
		List<Token> tokens = tokenizer.parseTokens(expression);
		// TODO: Check the tokens...

		expression = "sin(perlinColor(x, y))";
		tokens = tokenizer.parseTokens(expression);
		// TODO: Check the tokens...
	}
	
	@Test 
	public void testTokenizeAssignment() {
		String expression = "a = x + y";
		List<Token> tokens = tokenizer.parseTokens(expression);
		assertEquals(new AssignmentToken(), tokens.get(1));	
	}
	
	@Test
	public void testTokenizeBinaryOperators() {
		String expression = "x + y / x * y - x";
		List<Token> tokens = tokenizer.parseTokens(expression);
		assertEquals(new PlusToken(), tokens.get(1));
		assertEquals(new DivideToken(), tokens.get(3));
		assertEquals(new MultiplyToken(), tokens.get(5));
		assertEquals(new MinusToken(), tokens.get(7));
	}

	@Test
	public void testTokenizeWrap() {
		String expression = "wrap(x)";
		tokens = tokenizer.parseTokens(expression);
		assertEquals(new WrapToken(), tokens.get(0));
		assertEquals(new LeftParenToken(), tokens.get(1));
		assertEquals(new IdentifierToken("x"), tokens.get(2));
		assertEquals(new RightParenToken(), tokens.get(3));
	}

	
	@Test
	public void testTokenizeImageandImageWrap() {
		String expression = "imageWrap(\"vortex.jpg\", x + x, y)";
		tokens = tokenizer.parseTokens(expression);
		assertEquals(new ImageWrapToken(), tokens.get(0));
		assertEquals(new LeftParenToken(), tokens.get(1));
		assertEquals(new ImageToken("vortex.jpg"), tokens.get(2));
		assertEquals(new IdentifierToken("x"), tokens.get(4));
		assertEquals(new PlusToken(), tokens.get(5));
		assertEquals(new IdentifierToken("x"), tokens.get(6));
		assertEquals(new IdentifierToken("y"), tokens.get(8));
		assertEquals(new RightParenToken(), tokens.get(9));
		
	}

	@Test
	public void testTokenizeImageClip() {
		String expression = "imageClip(\"vortex.jpg\", x + x, y)";
		tokens = tokenizer.parseTokens(expression);
		assertEquals(new ImageClipToken(), tokens.get(0));
		assertEquals(new LeftParenToken(), tokens.get(1));
		assertEquals(new ImageToken("vortex.jpg"), tokens.get(2));
		assertEquals(new IdentifierToken("x"), tokens.get(4));
		assertEquals(new PlusToken(), tokens.get(5));
		assertEquals(new IdentifierToken("x"), tokens.get(6));
		assertEquals(new IdentifierToken("y"), tokens.get(8));
		assertEquals(new RightParenToken(), tokens.get(9));
		
	}
	
	@Test
	public void testPerlinFunctions() {
		String expression = "perlinColor(x,y)";
		tokens = tokenizer.parseTokens(expression);
		String expression2 = "perlinBW(x,y)";
		tokens2 = tokenizer.parseTokens(expression2);
		//test for perlinColor
		assertEquals(new PerlinColorToken(), tokens.get(0));
		assertEquals(new LeftParenToken(), tokens.get(1));
		assertEquals(new IdentifierToken("x"), tokens.get(2));
		assertEquals(new IdentifierToken("y"), tokens.get(4));
		assertEquals(new RightParenToken(), tokens.get(5));
		// test for perlinBW
		assertEquals(new PerlinBWToken(), tokens2.get(0));
		assertEquals(new LeftParenToken(), tokens2.get(1));
		assertEquals(new IdentifierToken("x"), tokens2.get(2));
		assertEquals(new IdentifierToken("y"), tokens2.get(4));
		assertEquals(new RightParenToken(), tokens2.get(5));
		
	}
	
	@Test
	public void testBang() {
		String expression = "! x";
		List<Token> tokens = tokenizer.parseTokens(expression);
		assertEquals(new BangToken(), tokens.get(0));	
	
		
	}
}
