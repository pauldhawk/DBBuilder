package Constraints;
import enums.*;
import java.util.List;

import Table.Table;
import Columns.Column;

public interface ForeignKey extends Constraint{
	String name(String name);
	List<Column> columns();
	Column refColumn();
	Table refTable();
	String onUpdate(Conflict conflict);
	String onDelete(Conflict conflict );
	String deferrable(FKDeferrable deferrable );
	
}
