package enums;

public enum FKDeferrable {
	 initiallyDeferred("INITIALLY DEFERRED"),  
	 initallyImmediate ("INITIALLY IMMEDIATE");
	 
	 String deferrable;
	 FKDeferrable(String def){
		 this.deferrable  = def;
	 }
	 void deferrable(String deferrable ) {
		 this.deferrable = deferrable;
	 }
	 String deferrable() {
		 return this.deferrable;
	 }
	 
	 public String toString () {
		 return deferrable;
	 }
}
