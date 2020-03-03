/*
 * You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

Example:

Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 0 -> 8
Explanation: 342 + 465 = 807.
 */
package AddTwoNumbers;

import java.math.BigInteger;
import java.util.*;

public class AddTwoNumbers {
	/*public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		BigInteger v1=new BigInteger(GetValue(l1));
		BigInteger v2=new BigInteger(GetValue(l2));
		BigInteger sum=v1.add(v2);
			
		String number = String.valueOf(sum);
		ListNode node=null;
		if(number.length() > 0){
			node = new ListNode(Character.digit(number.charAt(number.length()-1), 10));
			GetNode(number,node);
		}
		return node;
    }
	private String GetValue(ListNode l){
		String str="";
		List<Integer> list = new ArrayList<Integer>();
		while(l!=null){
			list.add(l.val);
			l=l.next;			
		}
		Collections.reverse(list);
		for(int i=0;i<list.size();i++){
			str+=Integer.toString(list.get(i)); 
		}
		return str;		
	}
	
	private ListNode GetNode(String number,ListNode node){
		for(int i=number.length()-2;i>=0;i--){
			node.next =new ListNode(Character.digit(number.charAt(i), 10));
			node=node.next;
		}
		return node;
	}*/
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		int sum=l1.val+l2.val;
		ListNode node=new ListNode(sum%10);		
		GetNode(node,l1.next,l2.next,sum>=10);
		return node;
		
	}
	private ListNode GetNode(ListNode node,ListNode l1, ListNode l2, boolean carry){
		int sum=0;
		while(l1 != null || l2!=null){
			if(l1!=null && l2!=null){
				sum=l1.val+l2.val;
				if(carry){
					sum++;
				}
			}else if(l1!=null){
				sum=carry ? l1.val +1 : l1.val;
			}else{
				sum=carry ? l2.val +1 : l2.val;
			}
			carry = sum>=10;
			node.next = new ListNode(sum%10);
			node=node.next;
			if(l1!=null) l1=l1.next;
			if(l2!=null) l2=l2.next;
		}
		if(carry)
		{
			node.next = new ListNode(1);
		}
		return node;
	}
}
