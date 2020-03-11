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
}
