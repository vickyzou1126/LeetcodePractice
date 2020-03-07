package AddTwoNumbers;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LongestPalindrome {
	 public String longestPalindrome(String s) {
		 if(s.length()<=1) return s;
		 Map<Character, List<Integer>> dictionary = new HashMap<Character, List<Integer>>();
	
		 List<Integer> values;
		 for (int i = 0; i < s.length(); i++) { 
			 	values = dictionary.get(s.charAt(i));
	            if(values!=null){
	            	values.add(i);
	            }else{
	            	List<Integer> temp = new ArrayList<Integer>();
	            	temp.add(i);
	            	dictionary.put(s.charAt(i),temp);
	            }
	     } 
		 String longestPalindrom=s.charAt(0)+"";
		 for (List<Integer> indexs : dictionary.values())  	           
		 {
			 if(indexs.size() >1){
				 for(int i=0;i<indexs.size()-1;i++){
					 for(int j = indexs.size()-1;j>=i+1;j--){
						 int longlen=longestPalindrom.length();
						 if((indexs.get(j)-indexs.get(i)) +1 > longlen){
							 String temp =s.substring(indexs.get(i), indexs.get(j)+1);
							 if(IsPalindro(temp)){
								 longestPalindrom = temp;
								 continue;
							 }					 
						 } 
					 }
				 }
			 }
		 }
		 return longestPalindrom;
	 }
	
	 private boolean IsPalindro(String s){
		 if(s.length()==1) return true;
		 for(int i=0;i<s.length()/2;i++){
			 if(s.charAt(i) != s.charAt(s.length()-1-i)){
				 return false;
			 }
		 }
		 return true;
	 }
}
