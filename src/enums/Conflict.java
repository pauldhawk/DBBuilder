package enums;

public enum Conflict {
	rollback, abort, fail, ignore, replace, none;
	
	public String onConflict() {
		if (this == none) return "";
		return "ON CONFLICT " + this.toString().toUpperCase();
	}
}
