package spark.examples;

import java.io.Serializable;
import java.util.HashMap;

import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;

public abstract class Transformer implements Serializable {

	private static final long serialVersionUID = -1468496580118915593L;
	
	public Row put(Row row, Object value) {
		int size = row.schema().size();
		Object[] newObjRow = new Object[size + 1];
        for (int itr = 0; itr < size; itr++) {
            if(row.apply(itr)!=null) {
                newObjRow[itr] = row.apply(itr);
            }
        }
        newObjRow[size] = value;
        return RowFactory.create(newObjRow);
	}

	public abstract Row transform(Row row, HashMap<String, Object> metadata);
	
}
