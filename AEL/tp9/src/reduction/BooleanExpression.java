package reduction;

public class BooleanExpression {
    protected String image;

    public BooleanExpression() {
    }

    public String getImage() {
        return image;
    }

    public BooleanExpression(String image) {
        this.image = image;
    }

    public String toString() {
        return image;
    }

    /**
     * @return right composition by AND
     */
    public BooleanExpression rightAnd(BooleanExpression other) {
		if(this.image.equals("true")){
        }

        return null; // a compléter
    }

    /**
     * @return right composition by OR
     */
    public BooleanExpression rightOr(BooleanExpression other) {
        // à completer
        return null; // à enlever
    }

    /**
     * @return negation of this expression
     */
    public BooleanExpression not() {
        // à compléter
        return null; // à enlever
    }

    /**
     * @return wrap expression with brackets
     */
    public BooleanExpression wrap() {
        // à compléter
        return null; // à enlever
    }
}
