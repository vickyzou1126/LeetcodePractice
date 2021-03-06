import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

class Solution {

    public boolean isPalindrome(int x) {
    	if(x<0) return false;
    	if(x<10) return true;
    	//StringBuilder value =new StringBuilder(Integer.toString(x));
    	//return IsPalindro(value.toString());
    	return IsPalindro(Integer.toString(x));
   	
    }
    private boolean IsPalindro(String s){
		 for(int i=0;i<s.length()/2;i++){
			 if(s.charAt(i) != s.charAt(s.length()-1-i)){
				 return false;
			 }
		 }
		 return true;
	 }
    
    /*
     Write a function to find the longest common prefix string amongst an array of strings.

	 If there is no common prefix, return an empty string "".
     */
    public  String longestCommonPrefix(String[] strs) {
		if(strs.length == 0 ) return "";
		if(strs.length == 1 ) return strs[0];

		List<String> list = Arrays.asList(strs);
		list.sort(Comparator.comparingInt(String::length));
		String str = list.get(0);
		
		for(int j=str.length();j>0;j--){
			String s =str.substring(0, j);
			if(Contains(list,s)){
				return s;
			}
		}
        return "";
    }
	private  boolean Contains(List<String> list,String s){
		for(String str:list){
			if(!str.startsWith(s)) return false;
		}
		return true;
	}
}

