import java.util.HashMap;

/*
Given an array of integers, return indices of the two numbers such that they add up to a specific target.
You may assume that each input would have exactly one solution, and you may not use the same element twice.

Example:
Given nums = [2, 7, 11, 15], target = 9,
Because nums[0] + nums[1] = 2 + 7 = 9,
return [0, 1].
*/

public class TwoSum {
	 public int[] twoSum(int[] nums, int target) {	 	
	       /* for(int i=0;i<nums.length-1;i++){
	        	for(int j=i+1; j<nums.length;j++){
	        		if(nums[i]+nums[j] == target){
	        			return new int[]{i,j};
	        		}
	        	}
	        }*/
		 HashMap <Integer, Integer> map  = new HashMap<Integer, Integer>();
		 int complement;
		 for(int i=0;i<nums.length;i++){
			 complement = target - nums[i];
			 if(map.containsKey(complement)){
				 return new int[] {map.get(complement),i};
			 }
			 map.put(nums[i], i);
		 }
		
	     return new int[2];
	 }
}
