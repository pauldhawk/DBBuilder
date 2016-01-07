package SQLite;
import Columns.*;
import Table.*;
import Constraints.*;

import java.util.*;

import enums.DataType;

public class SQLiteTable implements Table{
	String name;
	List<Column> columns = new ArrayList<Column>();
	List<Constraint> constraints = new ArrayList<Constraint>();
	List<ConstraintBuilder> constraintBuilders = new ArrayList<ConstraintBuilder>();
	
	String comma = null;

	SQLiteTable(String name) { 
		this.name = name; 
	}
	
	@Override
	public List<Column> columns() {
		return columns;
	}

	@Override
	public List<Constraint> contraints() {
		return constraints;
	}
	
	@Override
	public String name() {
		return name;
	}

	@Override
	public void addColumn(Column c) {
		this.columns.add(c);
	}

	@Override
	public void addConstraint(Constraint c) {
		this.constraints.add(c);
	}
	
	public String getComma() {
		if (comma == null) { comma = "\t, "; return "\t"; } 
		return comma;
	}
	@Override
	public String toString() {
		String ret = "Create Table " + this.name + " (\n";
		for (Column c : this.columns) {
			ret += getComma() + c.toString() + "\n";
		}
		for (Constraint c : this.constraints) {
			ret += getComma() + c.toString() + "\n";
		}
		ret += ");";
		
		return ret;
	}

	@Override
	public Column newColumn(String name, DataType dataType) {
		Column c = new SQLiteColumn(name, dataType);
		this.addColumn(c);
		return c;
	}

	@Override
	public ConstraintBuilder addPK(Column column) {
		ConstraintBuilder pk = new SQLiteConstraint.PK(this, column);
		//this.addConstraint(pk);
		return pk;
		
	}
}
