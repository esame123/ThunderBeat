package audioplay;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

import net.java.frej.fuzzy.Fuzzy;
import request.RequestValues;


public class Cerca  {


	private Fuzzy fs=new Fuzzy();
	//ArrayList<String> str = new ArrayList<String>();
	String str[];
	String sub[];
	ArrayList<Double> value = new ArrayList<Double>();
	ArrayList<String> name = new ArrayList<String>();
	double vector[];
	double min, max;
	String sub2[];
	int y;
	Init init;
	String cerca;
	ArrayList<RequestValues> values;

	public Cerca(String cerca) {
		this.cerca=cerca;
	}

	public ArrayList<RequestValues> getSong() throws IOException {
	
		//String ar[]=new String[4];
		String urlLink="https://thunderbeat.altervista.org/cerca.php?intent=song";
		
		ArrayList<RequestValues>list = find(urlLink);

		return list;
	}
	
	public String findSong() throws IOException {
		
		//String ar[]=new String[4];
		String urlLink="https://thunderbeat.altervista.org/cerca.php?intent=song";
		
		ArrayList<RequestValues>list = find(urlLink);

		return list.get(0).getName();
	}
	
	public ArrayList<RequestValues> getArtist() throws IOException{
		String urlLink="https://thunderbeat.altervista.org/cerca.php?intent=artist";
		
		ArrayList<RequestValues> list = find(urlLink);
	
		return list;
	}

	public ArrayList<RequestValues> getUser() throws IOException{
		String urlLink="https://thunderbeat.altervista.org/cerca.php?intent=user";
		
		ArrayList<RequestValues> list = find(urlLink);
	
		return list;
	}
	
	
	public ArrayList<RequestValues> find(String urlLink) throws IOException {
		ArrayList<String> al = new ArrayList<String>();
		values = new ArrayList<RequestValues>();
		URL url = new URL(urlLink);

		HttpsURLConnection conn= (HttpsURLConnection)url.openConnection();
		conn.setRequestMethod("GET");

		BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		StringBuffer sb=new StringBuffer();
		String line;

		while((line=in.readLine())!=null) {
			sb.append(line);
		}
		in.close();

		str=sb.toString().split("<br>");
		vector=new double[str.length];
		sub2=cerca.split(" ");

		for(int i=0;i<str.length;i++) {
			sub=str[i].split(" ");
			min=3;
			for (int j=0;j<sub.length;j++) {
				for(int k=0;k<sub2.length;k++) {
					max=fs.similarity(sub[j].toLowerCase(),sub2[k].toLowerCase());
					if(min>max) {
						min=max;
					}
				}
			}
			vector[i]=min;
		}
		quickSort(vector,str, 0, vector.length-1);

		for(int i=0;i<str.length;i++) {
			str[i]=str[i].replace("'", "\\'");
		}



		for(int i=0;i<str.length;i++) {
			if(vector[i]>0.5) {
				break;
			}else {
				al.add(str[i]);
				
			}
		}
		for(int i = 0; i < al.size(); i++) {
			RequestValues temp = new RequestValues(al.get(i), vector[i]);
			values.add(temp);
		}
		//al.add(String.valueOf(vector[0]));
		return values;

	}
	static int partition(double[] array,String[] str, int begin, int end) {
		int pivot = end;

		int counter = begin;
		for (int i = begin; i < end; i++) {
			if (array[i] < array[pivot]) {
				double temp = array[counter];
				array[counter] = array[i];
				array[i] = temp;
				String str1=str[counter];
				str[counter]=str[i];
				str[i]=str1;
				counter++;
			}
		}
		double temp = array[pivot];
		array[pivot] = array[counter];
		array[counter] = temp;
		String str1=str[pivot];
		str[pivot]=str[counter];
		str[counter]=str1;
		return counter;
	}

	public static void quickSort(double[] array,String[] str ,int begin, int end) {
		if (end <= begin) return;
		int pivot = partition(array,str, begin, end);
		quickSort(array,str, begin, pivot-1);
		quickSort(array,str, pivot+1, end);
	}


	static boolean isSubSequence(String str1, String str2, int m, int n) 
	{ 
		// Base Cases 
		if (m == 0)  
			return true; 
		if (n == 0)  
			return false; 

		// If last characters of two strings are matching 
		if (str1.charAt(m-1)== str2.charAt(n-1)) 
			return isSubSequence(str1, str2, m-1, n-1); 

		// If last characters are not matching 
		return isSubSequence(str1, str2, m, n-1); 
	} 

}
