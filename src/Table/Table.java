package Table;
import java.util.List;

import enums.DataType;
import Columns.Column;
import Constraints.Constraint;
import Constraints.ConstraintBuilder;

public interface Table {
	String name();
	List<Column> columns();
	List<Constraint> contraints();
	void addColumn(Column c);
	void addConstraint(Constraint c);
	Column newColumn(String name, DataType dataType);
	ConstraintBuilder addPK(Column column);
}
