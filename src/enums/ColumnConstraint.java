package enums;

public enum ColumnConstraint {
	Unique, PK, FK, NN, Default, Collate;
	
	public String toString(){
		switch(this) {
		case PK: return "PRIMARY KEY";
		case FK: return "FOREIN KEY";
		case NN: return "Not Null";
		default: return super.toString().toUpperCase();
		}
	}
	public String shortName() { 
		return super.toString().toLowerCase();
	}
}
