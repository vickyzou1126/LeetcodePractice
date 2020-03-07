package AddTwoNumbers;


public class ZigZagConvert {
	public String convert(String s, int numRows) {
		if(numRows==1) return s;
		
		char[] chars = s.toCharArray();
		String res="";
		int round  = s.length() / (2*numRows-2),cul=0,index=0,len=s.length(),ivalue=0;
		if(len % (2*numRows-2)>0){
			round++;
		}
		Character[][] arr = new  Character[numRows][round*(numRows-1)];
		boolean top2bottom=true;
		while(index<=len){
			if(top2bottom){
				for(int i=ivalue;i<numRows;i++){
					arr[i][cul]=chars[index];
					index++;
					if(index==len) break;
				}
				cul++;
			}else{
				for(int i=numRows-2;i>=0;i--){
					arr[i][cul]=chars[index];
					if(i>0){
						cul++;
					}else{
						ivalue=1;
					}
					index++;
					if(index==len) break;
				}
			}			
			top2bottom=!top2bottom;
		}
		for(int i=0;i<numRows;i++){
			for(int j=0;j<round*(numRows-1);j++){
				if(arr[i][j]!=null){
					res+=arr[i][j];
				}		
			}
		}
		return res;
    }	
}
