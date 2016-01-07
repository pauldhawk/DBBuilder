package enums;

public enum FKDeferrable {
	 initiallyDeferred,  initallyImmediate;
	 
	 public String toString () {
		 switch (this) {
		 case initiallyDeferred: return "INITIALLY DEFERRED";
		 case initallyImmediate: return "INITIALLY IMMEDIATE";
		 default: return "error";
		 }
	 }
}
