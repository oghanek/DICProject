package mypackage;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

public class SearchMapper extends MapReduceBase implements
org.apache.hadoop.mapred.Mapper<Text, Text, Text, Text> {

	private List<String> wordsList;
	
	@Override
	public void configure(JobConf conf){
		wordsList=Arrays.asList(conf.getStrings("query"));
	}
	
	@Override
	public void map(Text key, Text val, OutputCollector<Text, Text> output,
			Reporter reporter) throws IOException {
		if(wordsList.contains(key.toString().toLowerCase())){
			output.collect(key,val);
		}
		
	}

}
