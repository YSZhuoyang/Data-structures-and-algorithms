/**
 * Definition of Column:
 */
public class Column {
    public int key;
    public String value;
    public Column(int key, String value) {
        this.key = key;
        this.value = value;
    }
}


public class MiniCassandra {
    private HashMap<String, SortedMap<Integer, String>> data;


    public MiniCassandra() {
        data = new HashMap<>();
    }

    /*
     * @param raw_key: a string
     * @param column_key: An integer
     * @param column_value: a string
     * @return: nothing
     */
    public void insert(String row_key, int column_key, String value) {
        if (!data.containsKey(row_key)) data.put(row_key, new TreeMap<Integer, String>());
        
        SortedMap<Integer, String> shard = data.get(row_key);
        shard.put(column_key, value);
    }

    /*
     * @param row_key: a string
     * @param column_start: An integer
     * @param column_end: An integer
     * @return: a list of Columns
     */
    public List<Column> query(String row_key, int column_start, int column_end) {
        if (!data.containsKey(row_key)) return new ArrayList<Column>();

        List<Column> res = new ArrayList<>();
        SortedMap<Integer, String> shard = data.get(row_key);
        SortedMap<Integer, String> sub = shard.subMap(column_start, column_end + 1);
        for (Map.Entry<Integer, String> e : sub.entrySet())
            res.add(new Column(e.getKey(), e.getValue()));

        return res;
    }
}
