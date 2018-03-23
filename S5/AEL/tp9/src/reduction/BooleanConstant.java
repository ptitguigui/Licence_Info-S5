package reduction;

public class BooleanConstant extends BooleanExpression{

	public BooleanConstant(boolean value) {
        super();
        if(value){
		    this.image = "true";
        }else {
		    this.image = "false";
        }
	}
	// à compléter

}
