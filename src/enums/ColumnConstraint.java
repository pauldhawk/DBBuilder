package enums;

public enum ColumnConstraint {
	Unique ("Unique"), 
	PK ("PRIMARY KEY"), 
	FK ("FOREIN KEY"), 
	NN ("Not Null"), 
	Default ("Default"), 
	Collate ("Collate");
	
    String constraint;

    ColumnConstraint(String constraint) {
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
