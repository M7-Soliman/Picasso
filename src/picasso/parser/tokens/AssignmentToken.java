package picasso.parser.tokens;

/**
 * Represents the equals sign in the Picasso programming language
 */
public class AssignmentToken extends Token {

	public AssignmentToken() {
		super("Assignment Token: =");
	}

	public boolean equals(Object o) {
		if( o == this ) {
			return true;
		}
		if (!(o instanceof AssignmentToken)) {
			return false;
		}
		AssignmentToken rhs = (AssignmentToken) o;
		return rhs.getName().equals("Assignment Token: =");
	}

	public String getName() {
		return "Assignment Token: =";
	}

	public String toString() {
		return "Assignment Token: =";
	}

	@Override
	public boolean isConstant() {
		return false;
	}

	@Override
	public boolean isFunction() {
		return false;
	}

}


