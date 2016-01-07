package Constraints;

import enums.*;

public interface FKBuilder {
	String name(String name);
	String column();
	String refColumn();
	String refTable();
	String onUpdate(Conflict conflict);
	String onDelete(Conflict conflict );
	String deferrable(FKDeferrable deferrable );
}
