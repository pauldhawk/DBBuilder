package SQLite;
import Table.Table;
import enums.Conflict;
import enums.DataType;
import Columns.*;


public class SQLiteColumn implements Column {
	String name; 
	DataType dataType;
	String notNull = "";
	String defaultVal = "";
	String unique = "";
	final static String space =  " ";
	SQLiteConstraint.PKColumn pk;
	
	SQLiteColumn(String name, DataType dataType){
		this.name = name;
		this.dataType = dataType;
	}
	
	@Override
	public Column notNull() {
		notNull = " NOT NULL ";
		return this;	
	}
	public Column notNullWithConflict(Conflict c ) {
		notNull = " NOT NULL " + c.toString();
		return this;	
	}
	public Column defaultVal(String value) {
		defaultVal = " DEFAULT " + value + space;
		return this;	
	}
	public Column PK() {
		Table t = new SQLiteTable("testTable");
		pk = new SQLiteConstraint.PKColumn(t, this);
		return this;	
	}
	public Column PKDesc() {
		pk.orderAsc = false;
		return this;	
	}
	public Column PKConflict(Conflict c) {
		pk.onConflict(c);
		return this;	
	}
	public Column PKAutoInc() {
		pk.autoInc = true;
		return this;	
	}
	@Override
	public Column unique() {
		unique = " UNIQUE ";
		return this;	
	}
	public Column uniqueWithConflict(Conflict c) {
		unique = " UNIQUE " + c.toString() + space;
		return this;	
	}
	@Override
	public String toString () {
		String pkStr = (pk != null) ? (space + pk.Build().toString() + space) : "";
		return  name + space + dataType.toString().toUpperCase() + pkStr + notNull + defaultVal + unique ;
	}

	@Override
	public String name() {
		return name;
	}
}
