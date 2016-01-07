package SQLite;

public enum aa {

		Unique ("Unique"), 
		PK ("PRIMARY KEY"), 
		FK ("FOREIN KEY"), 
		NN ("Not Null"), 
		Default ("Default"), 
		Collate ("Collate");
		
	    String constraint;

	    aa(String constraint) {
	        this.constraint = constraint;
	    }

	    public String constraint() {
	        return constraint;
	    }
	    
	    @Override
	    public String toString() {
	        return this.constraint();
	    }

}
