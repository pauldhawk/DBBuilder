package SQLite;

import Table.*;
import enums.*;
import Columns.*;
import Constraints.Constraint;

public class play {

	public static void main(String[] args) {
		
		Table t = new SQLiteTable("Table1");
		Table tb = new SQLiteTable("Table2");
		
		Column c =  new SQLiteColumn("_ID",DataType.Integer ).notNull() ;
		Column d =  new SQLiteColumn("stuff",DataType.Blob ).notNull() ;
		Column e =  new SQLiteColumn("numbers",DataType.Integer );
		Column f =  new SQLiteColumn("text",DataType.Text ) ;
		
		
		Constraint pk = new SQLiteConstraint.PK(t,c).Build();
		Constraint uk = new SQLiteConstraint.Unique(t,c).Build();
		
		Constraint pkc = new SQLiteConstraint.PK(t, f).onConflict(Conflict.abort).Build(); 
		
		t.addColumn(c);
		t.addColumn(d);
		t.addColumn(e);
		t.addColumn(f);
		Column tt = t.newColumn("new", DataType.Text).notNull();
		System.out.println(DataType.Integer);
		Constraint fka = new SQLiteConstraint.FK(t, f, tb, c).OnDelete(FKOn.noAction).Build();
		Constraint fkb = new SQLiteConstraint.FK(t, f, tb, c).OnUpdate(FKOn.setDefault).Build();
		Constraint fkc = new SQLiteConstraint.FK(t, e, tb, c).Deferrable(false, FKDeferrable.initallyImmediate).Build();
			
		t.addConstraint(uk);
		t.addConstraint(pkc);
		t.addConstraint(fkc);
		tb.addColumn(c);
		
		System.out.println(aa.FK);
		
		
//		System.out.println(t.toString());
//		System.out.println(tb.toString());
//		System.out.println(e.toString());
//		System.out.println(f.toString());
//		System.out.println(pk.toString());
//		System.out.println(pkc.toString());
//		System.out.println(uk.toString());
//		System.out.println(fka.toString());
//		System.out.println(fkb.toString());
//		System.out.println(fkc.toString());
		

	}

}
