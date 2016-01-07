package SQLite;


import Columns.Column;
import Constraints.Constraint;
import Constraints.ConstraintBuilder;
import Table.Table;
import enums.*;


public class SQLiteConstraint implements Constraint {
	Conflict conflict;
	Column column; 
	Table table;
	TableConstraint tblConstraint;
	ColumnConstraint colConstraint;
	
	// pk col 
	String PkOrder = "";
	String PKAutoInc = "";
	
	// fk vars
	Table fkTableRef;
	Column fkColRef;
	FKOn fkOnUpdate;
	FKOn fkOnDelete;
	boolean fkDeferrable;
	FKDeferrable fkDefAction;
	// helper 
	static final String space = " ";
	
	SQLiteConstraint(){}
	SQLiteConstraint(PK pk){
		this.conflict = pk.conflict;
		this.tblConstraint = pk.constraint;
		this.column = pk.column;
		this.table = pk.table;
	}
	SQLiteConstraint(PKColumn pk){
		this.conflict = pk.conflict;
		this.colConstraint = pk.constraint;
		this.column = pk.column;
		this.table = pk.table;
		if (pk.autoInc == true) this.PKAutoInc = "AUTOINCREMENT";
		if (pk.orderAsc == false) this.PkOrder = "DESC";
	}
	SQLiteConstraint(Unique uk){
		this.conflict = uk.conflict;
		this.tblConstraint = uk.constraint;
		this.column = uk.column;
		this.table = uk.table;
	}
	
	SQLiteConstraint(FK fk){
		this.tblConstraint = fk.constraint;
		this.table = fk.table;
		this.column = fk.column;
		this.fkColRef = fk.fkColRef;
		this.fkTableRef = fk.fkTableRef;
		this.fkOnDelete = fk.fkOnDelete;
		this.fkOnUpdate = fk.fkOnUpdate;
		this.fkDeferrable = fk.fkDeferrable;
		this.fkDefAction = fk.fkDefAction;
	}
	
	private String getName() {
		return table.name().toLowerCase() + "_" + column.name().toLowerCase() 
				+  "_" + tblConstraint.shortName() + space;
	}
	private String FKToString(String retString) {
		retString += "FOREIGN KEY(" + column.name() + ") REFERENCES " + fkTableRef.name() + "(" + fkColRef.name() + ") ";
		if (fkOnUpdate != null) retString += "ON UPDATE " + fkOnUpdate.toString() + space;
		if (fkOnDelete != null) retString += "ON DELETE " + fkOnDelete.toString() + space;
		if (fkDefAction != null) {
			if (fkDeferrable != true) retString += "NOT ";
			retString += "DEFERRABLE " + fkDefAction.toString();
		}	
		return retString;
	}
	private String colToString(String retString){
		retString += colConstraint.toString() + space + PkOrder + space + conflict.onConflict() + PKAutoInc;
		return retString;	
	}
	@Override 
	public String toString() {
		String retString = "Constraint " + getName();
		if (this.tblConstraint == TableConstraint.FK) 
			return FKToString(retString);
		if (	   this.colConstraint == ColumnConstraint.PK 
				|| this.colConstraint == ColumnConstraint.Unique 
				|| this.colConstraint == ColumnConstraint.NN) {
			return colToString(retString); }
		return retString += tblConstraint.toString().toUpperCase() 
			+ "(" + column.name() + ")" + space + conflict.onConflict();
	}
	
	public static class PK implements ConstraintBuilder {
		final Column column; 
		final Table table;
		final TableConstraint constraint = TableConstraint.PK;
		
		Conflict conflict = Conflict.none;
		boolean orderAsc = true;

		
		public PK(Table table, Column column){
			this.table = table;
			this.column = column;
		}
		public PK onConflict(Conflict conflict) {
			this.conflict = conflict;
			return this;
		}
		public Constraint Build(){ 
			return new SQLiteConstraint(this); 
		}
		@Override
		public String name() {
			// TODO Auto-generated method stub
			return null;
		}
		@Override
		public String Type() {
			// TODO Auto-generated method stub
			return null;
		}
		@Override
		public String Column() {
			// TODO Auto-generated method stub
			return null;
		}
	}
	public static class PKColumn {
		final Column column; 
		final Table table;
		final ColumnConstraint constraint = ColumnConstraint.PK;
		
		Conflict conflict = Conflict.none;
		boolean orderAsc = true;
		boolean autoInc = false;
		
		public PKColumn(Table table, Column column){
			this.table = table;
			this.column = column;
		}
		public PKColumn orderDesc(){
			orderAsc = false;
			return this;
		}
		public PKColumn onConflict(Conflict conflict) {
			this.conflict = conflict;
			return this;
		}
		public PKColumn autoIncrement() {
			autoInc = true;
			return this;
		}
		public Constraint Build(){ 
			return new SQLiteConstraint(this); 
		}
	}
	
	public static class Unique {	
		Conflict conflict = Conflict.none;
		final Column column; 
		TableConstraint constraint = TableConstraint.Unique;
		final Table table;
		
		public Unique(Table table, Column column){
			this.table = table;
			this.column = column;
		}
		public Unique(Table table, Column column, TableConstraint constraint){
			this.table = table;
			this.column = column;
			this.constraint = constraint;
		}
		public Unique onConflict(Conflict conflict) {
			this.conflict = conflict;
			return this;
		}
		public Constraint Build(){ 
			return new SQLiteConstraint(this); 
		}
	}
	
	public static class FK {
		final Table fkTableRef;
		final Column fkColRef;
		final Column column;
		final Table table;
		final TableConstraint constraint;
		FKOn fkOnUpdate;
		FKOn fkOnDelete;
		boolean fkDeferrable;
		FKDeferrable fkDefAction;
		
		public FK(Table table, Column col, Table tableRef, Column colRef) {
			this.fkTableRef  = tableRef;
			this.fkColRef 	 = colRef;
			this.column 	 = col;
			this.table 		 = table;
			this.constraint  = TableConstraint.FK;
		}
		public FK OnDelete(FKOn on){ 
			this.fkOnDelete = on;
			return this;
		}
		public FK OnUpdate(FKOn on){ 
			this.fkOnUpdate = on;
			return this;
		}
		public FK Deferrable(boolean bool,FKDeferrable action) {
			this.fkDeferrable = bool;
			this.fkDefAction = action;
			return this;
		}

		public Constraint Build(){
			return new SQLiteConstraint(this);
		}

		
	}
}
