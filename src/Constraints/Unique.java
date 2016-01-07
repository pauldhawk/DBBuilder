package Constraints;

import java.util.List;
import enums.*;

import Columns.Column;

public interface Unique {
	List<Column> columns();
	Conflict conflict();
	
}
