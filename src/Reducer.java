import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Reducer.Context;

public class Reducer extends Reducer<Text, IntWritable, Text, IntWritable> {

    private IntWritable result = new IntWritable();
    // private Map<Text, IntWritable> countMap = new HashMap<Text, IntWritable>();

    public void reduce(Text key, Iterable<IntWritable> values,
            Context context) throws IOException, InterruptedException {
        int sum = 0;
        for (IntWritable val : values) {
            sum += val.get();
        }
        result.set(sum);
        context.write(key, result);
        // countMap.put(new Text(key), new IntWritable(sum));
    }

    // public void cleanup(Context context) throws IOException, InterruptedException
    // {
    // List<Entry<Text, IntWritable>> countList = new ArrayList<Entry<Text,
    // IntWritable>>(countMap.entrySet());
    // Collections.sort( countList, new Comparator<Entry<Text, IntWritable>>(){
    // public int compare( Entry<Text, IntWritable> o1, Entry<Text, IntWritable> o2
    // ) {
    // return (o2.getValue()).compareTo( o1.getValue() );
    // }
    // } );
    // for(Entry<Text, IntWritable> entry: countList) {
    // context.write(entry.getKey(), entry.getValue());
    // }
    // }
}
