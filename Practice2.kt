import kotlin.math.abs
import kotlin.math.pow


fun numberOfSteps (num: Int): Int {
    if(num==0) return 0
    var temp=num
    var step=0;
    while(temp>0){
        if(temp%2==1)
            temp --
        else
            temp=temp/2

        step++
    }
    return step
}

//Jewels and Stones
fun numJewelsInStones(J: String, S: String): Int {
    return S.filter { x-> J.contains(x) }.length
}
//Self Dividing Numbers
fun selfDividingNumbers(left: Int, right: Int): List<Int> {
    var map = hashMapOf<Int,Boolean>()
    var res = mutableListOf<Int>()
    var converts = hashMapOf<Char,Int>()
   for(i in '1'..'9'){
       converts.put(i,Character.getNumericValue(i))
   }
    for(i in left..right)
    {
        var chars = i.toString()
        if(!chars.contains('0')){
            var abs = abs(i)
            if(i!=0 && abs<=12){
                res.add(i)
            }else{
                if(map.containsKey(abs)){
                    if(map[abs]!!){
                        res.add(i)
                    }
                }else{
                    var toadd = isSelfDeviding(abs,chars,converts)
                    map.put(abs,toadd)
                    if(toadd){
                        res.add(i)
                    }
                }
            }
        }
    }
    return res
}

fun isSelfDeviding(num:Int,chars:String,converts:HashMap<Char,Int>):Boolean{
    for (n in chars){
        if(num%converts[n]!! !=0){
            return false
        }
    }
    return true
}

/*
Given a valid (IPv4) IP address, return a defanged version of that IP address.

A defanged IP address replaces every period "." with "[.]".
 */
fun defangIPaddr(address: String): String {
    return address.replace(".","[.]")
}

/*
Given an array nums of integers, return how many of them contain an even number of digits.
 */
fun findNumbers(nums: IntArray): Int {
    if(nums.size==0) return 0
    var newNums = nums.toList().sorted()
    var lastValue = newNums[0]
    var lastValueStrLen = lastValue.toString().length
    var res=0
    var temp = Math.pow(10.0,lastValueStrLen*1.0)
    if (lastValueStrLen%2>0)
    {
        newNums=newNums.filter { x-> x >= temp}
    }


    for (i in 0..newNums.size-1){
        if(temp < newNums[i]*10 &&  newNums[i]<temp){
            res++
        }else  {
            if(newNums[i].toString().length%2==0){
                res++
                temp = Math.pow(10.0,newNums[i].toString().length*1.0)
            }else{
                temp = Math.pow(10.0,newNums[i].toString().length*1.0+1)
            }
        }
    }
    return res

}


//https://leetcode.com/problems/how-many-numbers-are-smaller-than-the-current-number/
fun smallerNumbersThanCurrent(nums: IntArray): IntArray {
   /* var res= mutableListOf<Int>()
    if (nums.size==0) return res.toIntArray()
    var newNums=nums.toList().sorted()
    var map = hashMapOf<Int,Int>()

    for (i in 0..nums.size-1){
        if(!map.containsKey(nums[i])){
            map.put(nums[i],newNums.indexOf(nums[i]))
        }
        res.add(map[nums[i]]!!)
    }
    return res.toIntArray()*/
    val newArray = IntArray(nums.size)
    for (i in 0 until nums.size) { newArray[i] = nums.count { nums[i] > it } }
    return newArray
}

//https://leetcode.com/problems/decompress-run-length-encoded-list/
fun decompressRLElist(nums: IntArray): IntArray {
    var res = mutableListOf<Int>()
    for(i in 0..(nums.size-1)/2){

        var fre = nums[2*i]
        var value =nums[2*i+1]
        repeat(fre){
            res.add(value)
        }
    }
    return res.toIntArray()
}

//https://leetcode.com/problems/subtract-the-product-and-sum-of-digits-of-an-integer/
fun subtractProductAndSum(n: Int): Int {
    var str= n.toString()
    var pd=1;
    var sd=0;
    for (v in str){
        var value = v.toString().toInt()
        pd*=value
        sd+=value
    }
    return pd-sd
}

//https://leetcode.com/problems/create-target-array-in-the-given-order/
fun createTargetArray(nums: IntArray, index: IntArray): IntArray {
    var res = mutableListOf<Int>()
    if (nums.size==0) return res.toIntArray()
    for(i in 0..nums.size-1){
        res.add(index[i],nums[i])
    }
    return res.toIntArray()
}

//https://leetcode.com/problems/convert-binary-number-in-a-linked-list-to-integer/
fun getDecimalValue(head: ListNode?): Int {

    if (head==null) return 0
    var res = mutableListOf<Int>()
    var value=0.0
    res.add(head.`val`)
    var node = head.next
    while (node!=null){
        res.add(0,node.`val`)
        node=node.next
    }
    if (res.size==0) return 0
    for (i in 0..res.size-1){
        if(res[i]==1){
            value+=Math.pow(2.0, (i).toDouble())
        }
    }
    return value.toInt()
}

fun maximum69Number (num: Int): Int {
    var str = num.toString()
    var str2=""
    for(i in 0..str.length-1){
        if (str[i]=='6')
        {
            str2+="9"
            if(i!=str.length-1){
                str2+=str.subSequence(i+1,str.length)
            }
            return str2.toInt()
        }else{
            str2+=str[i]
        }
    }
    return str2.toInt()

}
fun main(args: Array<String>) {


}

