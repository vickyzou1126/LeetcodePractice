public class LengthOfLongestSubstring {
	public int lengthOfLongestSubstring(String s) {
	       int len=0;
			String str="";
			for(char ch:s.toCharArray()){
				int index = str.indexOf(ch);
				if(index >= 0){
					len = Math.max(len,str.length());
					str = str.substring(index+1);
				}
				str+=ch;
			}
			return Math.max(len,str.length());
	 }
}
