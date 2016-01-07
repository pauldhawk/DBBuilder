package enums;

public enum FKOn {
	setNull, setDefault, cascasde, restrict, noAction;
	
	public String toString() {
		switch(this) {
		case setNull: 		return "SET NULL";
		case setDefault: 	return "SET DEFAULT";
		case noAction: 		return "NO ACTION";
		default:			return super.toString()
										.toUpperCase();
		
		}
	}
}
