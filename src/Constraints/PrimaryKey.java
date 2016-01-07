package Constraints;

import java.util.List;

import Columns.Column;
import enums.*;

public interface PrimaryKey extends Constraint{
	boolean SingleCol();
	List<Column> columns();
	Conflict conflict();
}
