package Constraints;

import enums.Conflict;

public interface PKBuilder extends ConstraintBuilder {
	ConstraintBuilder onConflict(Conflict conflict);
}
