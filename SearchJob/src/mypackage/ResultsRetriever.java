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
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;
import java.util.regex.Pattern;



public class ResultsRetriever {

	public static void main(String a[]) throws IOException{
		//SearchJob.search(args);
		/*FileSystem fs = FileSystem.get(new Configuration());
		FileStatus[] status = fs.listStatus(new Path("/home/omkar/result/"));
		for (int i=0;i<status.length;i++){*/
		FileReader in = new FileReader("/home/omkar/result/part-00000");
	    BufferedReader br = new BufferedReader(in);
	    System.out.println("inside bla");
            String line;
            line=br.readLine();
            HashSet<String> prev=new HashSet<String>();
            if(line!=null){
            	String[] keyValue=line.split("\t");
            	String values[]=keyValue[1].split(Pattern.quote("|"));
            	prev.addAll(Arrays.asList(values));
            	line=br.readLine();
            }
            while (line != null){
            	String[] keyValue=line.split("\t");
                    prev=intersect(prev,keyValue[1]);
                    line=br.readLine();
            }
            for(String str: prev){
            	System.out.println(str);
            }
   // }
	}

	private static HashSet<String> intersect(HashSet<String> prev, String line) {
		System.out.println("inside intersect"+ line);
		StringTokenizer st= new StringTokenizer(line,"|");
		HashSet<String> current = new HashSet<String>();
		while(st.hasMoreTokens()){
			String str= st.nextToken().trim();
			if(prev.contains(str))
				current.add(str);
		}
		return current;
	}
}
