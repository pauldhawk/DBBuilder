package enums;

public enum TableConstraint {
	Unique, PK, FK;
	
	public String toString(){
		switch(this) {
		case PK: return "PRIMARY KEY";
		case FK: return "FOREIN KEY";
		default: return super.toString().toUpperCase();
		}
	}
	public String shortName() { 
		return super.toString().toLowerCase();
	}
	
}
