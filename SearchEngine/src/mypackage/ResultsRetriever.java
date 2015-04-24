package mypackage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;



public class ResultsRetriever {

	public static LinkedHashMap<String, Integer> retrieve(String[] query) throws IOException{
		//SearchJob.search(args);
		/*FileSystem fs = FileSystem.get(new Configuration());
		FileStatus[] status = fs.listStatus(new Path("/home/omkar/result/"));
		for (int i=0;i<status.length;i++){*/
		SearchJob.search(query);
		FileReader in = new FileReader("/home/omkar/result/part-00000");
	    BufferedReader br = new BufferedReader(in);
            String line;
            line=br.readLine();
            LinkedHashMap<String, Integer> prev=new LinkedHashMap<String, Integer>();
            HashMap<String,Integer> residue=new HashMap<String,Integer>();
            if(line!=null){
            	String[] keyValue=line.split("\t");
            	String values[]=(keyValue[1].split(Pattern.quote("|")));
            	for(String posting:values){
            		String postingArray[]=posting.split(";");
            		prev.put(postingArray[0],Integer.parseInt(postingArray[1]));
            	}
            	line=br.readLine();
            }
            while (line != null){
            	String[] keyValue=line.split("\t");
					prev=intersect(residue,prev,keyValue[1]);
                    line=br.readLine();
            }
            List<Map.Entry<String, Integer>> entries = new ArrayList<Map.Entry<String, Integer>>(residue.entrySet());
            Collections.sort(entries, new Comparator<Map.Entry<String, Integer>>(){

				@Override
				public int compare(Entry<String, Integer> o1,
						Entry<String, Integer> o2) {
					return -1*o1.getValue().compareTo(o2.getValue());
				}});
            for(Entry en:entries){
            	prev.put(en.getKey().toString(),(Integer) en.getValue());
            }
            return prev;
            
	}

	private static LinkedHashMap<String, Integer> intersect(HashMap<String, Integer> residue, LinkedHashMap<String, Integer> prev, String line) {
		StringTokenizer st= new StringTokenizer(line,"|");
		LinkedHashMap<String, Integer> current = new LinkedHashMap<String, Integer>();
		while(st.hasMoreTokens()){
			String posting= st.nextToken().trim();
			String[] str=posting.split(";");
			if(prev.containsKey(str[0]))
				{current.put(str[0],Integer.parseInt(str[1]));
			    prev.remove(str[0]);}
			else{
				if(!residue.containsKey(str[0])){
					residue.put(str[0],Integer.parseInt(str[1]));
				}
			}
		}
		for(String name:prev.keySet()){
			if(!residue.containsKey(name))
			residue.put(name,prev.get(name));
		}
		return current;
	}
	
	public static LinkedHashMap<String, Integer> getResults(String a) throws IOException{
		
		LinkedHashMap<String, Integer> results= retrieve(a.split(" "));
		FileUtils.deleteDirectory(new File("/home/omkar/result/"));
		return results;
	}
}
