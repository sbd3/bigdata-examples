package spark.examples;

import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.apache.spark.sql.Row;

public class ConcatTransform extends Transformer {

	private static final long serialVersionUID = -6704168745996169319L;

	@SuppressWarnings("unchecked")
	@Override
	public Row transform(Row row, HashMap<String, Object> metadata) {
		for (Entry<String, Object> ruleEntry: metadata.entrySet()) {
			List<String> rulStr = (List<String>) ruleEntry.getValue();
			StringBuilder sb = new StringBuilder();
			for (String col : rulStr) {
				sb.append(row.get(row.fieldIndex(col)).toString());
			}
			return put(row, sb.toString());
		}
		return row;
	}

}
