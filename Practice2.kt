import kotlin.math.abs



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


fun main(args: Array<String>) {
    println(findNumbers(intArrayOf(12,345,2,6,7896)))

}

