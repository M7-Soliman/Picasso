/**
 * 
 */
package picasso.parser.tokens;

/**
 * @author drewlarsen
 *
 */
public class ImageToken extends Token {

	private final String myImage;
	
	/**
	 * @param description
	 */
	public ImageToken(String image) {
		super("Image Token");
		this.myImage = image;

	}

	@Override
	public boolean isConstant() {
		return false;
	}

	@Override
	public boolean isFunction() {
		return false;
	}

	public String getName() {
		return this.myImage;
	}
	
	public boolean equals(Object o) {
		if ( o == this ) {
			return true;
		}
		if (!(o instanceof ImageToken)) {
			return false;
		}
		ImageToken imageToken = (ImageToken) o;
		return myImage.equals(imageToken.getName());
	}
}
