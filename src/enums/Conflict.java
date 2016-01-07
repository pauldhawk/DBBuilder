package enums;

public enum Conflict {
	rollback, abort, fail, ignore, replace, none;
	
	public String conflict() {
		if (this == none) return "";
		return "ON CONFLICT " + super.toString().toUpperCase();
	}
}
