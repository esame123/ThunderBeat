package provagui;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

import net.java.frej.fuzzy.*;

public class Cerca  {


	private Fuzzy 		fs=new Fuzzy();
	ArrayList<Integer> 	l = new ArrayList<Integer>();
	String 				str[];
	String 				sub[];
	double 				vector[];
	double				min, max;
	String 				sub2[];
	int 				y;

	Init 				init;
	String 				cerca;

	public Cerca(String cerca) {
		this.cerca=cerca;
	}

	public String[] prova() throws IOException {
	
		String ar[]=new String[2];
		String urlLink1="https://thunderbeat.altervista.org/cerca.php?intent=song";
		String urlLink2="https://thunderbeat.altervista.org/cerca.php?intent=artist";

		ArrayList<String> l1=find(urlLink1);
		ArrayList<String> l2=find(urlLink2);

	//	System.out.println("song, x: "+l1.get(1)+" artist y: "+l2.get(1));
		if (Double.parseDouble(l1.get(1))<Double.parseDouble(l2.get(1))) {
			ar[0]=l1.get(0);
			ar[1]="Canzone";
		}else {
			ar[0]=l2.get(0);
			ar[1]="Artista";
		}	
		return ar;
	}
	
	
	
	public ArrayList<String> find(String urlLink) throws IOException {
		ArrayList<String> al=new ArrayList<String>();
		URL url=new URL(urlLink);

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
					if(min>max) min=max;
				}
			}
			vector[i]=min;
		}
		quickSort(vector,str, 0, vector.length-1);

		for(int i=0;i<str.length;i++) {
			str[i]=str[i].replace("'", "\\'");
		}



		for(int i=0;i<str.length;i++) {
			if(l.contains(i)) continue;
			if(vector[i]>0.8) break;
		//	System.out.println(str[i]+" : "+vector[i]);
		}
		
		al.add(str[0]);
		al.add(String.valueOf(vector[0]));
		return al;

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
