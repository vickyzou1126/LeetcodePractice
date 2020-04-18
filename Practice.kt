import kotlin.math.abs

/*
Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.
 */
fun threeSum(nums: IntArray): List<List<Int>> {
    var res:MutableList<List<Int>> = emptyList<List<Int>>().toMutableList()
    var len = nums.size
    if(len<3) return res
    if(len==3){
        if(nums.sum()==0)
        {res.add(nums.toList())}
        return res
    }
    var only0=nums.distinct().size == 1 && nums.get(0)==0
    if(only0 || nums.filter { x->x==0 }.size>2) {
        var temp:List<Int> = listOf(0,0,0)
        res.add(temp)
        if(only0)
            return res
    }
    nums.sort()
    var newNums = nums.filter { x -> x <= 0 }.toList()
    var max = nums.get(len-1)
    var nummin =nums.get(0)
    for(i in newNums.size-2 downTo 0)
    {
        for(j in newNums.size-1 downTo  i+1){
            var absV =abs(newNums.get(i)+newNums.get(j))
            if(absV > max){
                break
            }
           if(absV > 0 && nums.contains(absV)){
               var temp:List<Int> = listOf(newNums.get(i),newNums.get(j),absV)
               temp = temp.sorted()
               res.add(temp)
            }
        }
    }

    newNums = nums.filter { x -> x > 0 }.toList()

    for (i in 0 .. newNums.size-2) {
        for(j in i+1 .. newNums.size-1){
            var min =(newNums.get(i)+newNums.get(j)) * -1
            if(min < nummin){
                break
            }
            if(nums.contains(min)){
                var temp:List<Int> = listOf(newNums.get(i),newNums.get(j),min)
                temp = temp.sorted()
                res.add(temp)
            }
        }
    }

    return res.distinct()
}

/*
Given an array nums of n integers and an integer target, find three integers in nums such that the sum is closest to target. Return the sum of the three integers. You may assume that each input would have exactly one solution.
 */
fun threeSumClosest(nums: IntArray, target: Int): Int {
    var len = nums.size
    if(len<=3)
        return nums.sum()
    nums.sort()
    var sum:Int=Int.MAX_VALUE
    var res=Int.MAX_VALUE
    for (i in 0 .. len-3){
        loop@ for(j in i+1 .. len-2) {
            for (k in j + 1 .. len - 1){
                var tempsum=nums.get(i)+nums.get(j)+nums.get(k)
                var temp =abs(tempsum-target)
                if(temp<sum){
                    sum=temp;
                    res=tempsum
                }else if(tempsum > target){
                    continue@loop
                }
                if(temp==0) return target
            }
        }
    }
    return res
}

/*
Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 */

fun isValid(s: String): Boolean {
    var chars  = charArrayOf( '(', ')', '{', '}', '[' , ']')
    var newString :String=s.filter { x->chars.contains(x) }

    var len=newString.length
    if(len%2 ==1) return false;
    var newStrings=""
    var temp: Char = ' '
    for(c in newString){
        when (c) {
            '(' -> temp = 'a'
            ')' -> temp = 'A'
            '{' -> temp = 'b'
            '}' -> temp = 'B'
            '[' -> temp = 'c'
            ']' -> temp = 'C'
        }
        if(temp.isUpperCase()){
            var index = newStrings.lastIndexOf(temp.toLowerCase())
            if(index <0  || index != newStrings.length-1)return false
            newStrings=newStrings.substring(0,newStrings.length-1)
        }else if(newStrings.length>len/2){
            return false
        }else{
            newStrings += temp
        }
    }
    return newStrings.length==0
}

/*
You are given a string, s, and a list of words, words, that are all of the same length. Find all starting indices of substring(s) in s that is a concatenation of each word in words exactly once and without any intervening characters.
 */
fun findSubstring(s: String, words: Array<String>): List<Int> {
    var index = listOf<Int>().toMutableList()
    if(words.size ==0 || s.length==0) return index
    var times = hashMapOf<String,Int>()
    for(word in words){
        var value = times.get(word)
        times[word]= (value?:0) + 1
    }

    var num = words[0].length;
    var total = words.size * num;

    for (i in 0..s.length - total) {
        for(j in i..s.length-total step total){
            if(!index.contains(j)){
                var temp = s.substring(j, j + total)
                if(ContainAll(times,temp,num)){
                    print("add in for ${temp} \n")
                    index.add(j)
                }
            }
        }
    }
    return index
}
fun ContainAll(times: HashMap<String,Int>,sentence:String,num:Int):Boolean{
   // println("sentence is ${sentence} and times is ${times}")
    var temp_times = HashMap(times)
    var i=0;
    while(i+num <= sentence.length){
        var word:String = sentence.substring(i,i+num)
       // println("word is ${word}")
        if(!temp_times.containsKey(word)) return false
        var time = temp_times[word]
        if(time==1)
            temp_times.remove(word)
        else
            temp_times[word] =time!!-1
        i=i+num
    }
    return true
}

/*
Given a set of candidate numbers (candidates) (without duplicates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.

The same repeated number may be chosen from candidates unlimited number of times.
 */
fun combinationSum(candidates: IntArray, target: Int) : List<List<Int>>{

    var result = mutableListOf<List<Int>>()
    var tempCandidates = candidates.filter {  x->x<=target }
    for(num in tempCandidates) {
        var remainder = target % num;
        var round = target / num
        if (remainder == 0) {
            var tempList = mutableListOf<Int>()
            repeat(round) {
                tempList.add(num)
            }
            result.add(tempList.toList())
            round -= 1
        }
        var restList = tempCandidates.filter { x -> x != num}.toIntArray()
        for (i in 1..round) {
            var newTarget=target - i * num
            var temp = combinationSum(restList, newTarget)
            for (t in temp.filter { x -> x.sum() == newTarget }) {
                var tempt = t.toMutableList()
                repeat(i) {
                    tempt.add(num)
                }
                result.add(tempt.sorted())
            }
        }
    }
    return result.distinct()
}

/*Given a 32-bit signed integer, reverse digits of an integer.*/
fun reverse(x: Int): Int {
    var str = Math.abs(x).toString().reversed()
    var num=str.toIntOrNull()
    if(num==null) return 0

    if(x<0){
        return (-1)*num
    }
    return num
}

//https://leetcode.com/problems/roman-to-integer/
fun romanToInt(s: String): Int {
    if (s.length==1) return GetNumber(s)
    var numbers="IVXLCDM"
    var str=s[0].toString()
    var res=0
    var dic = mutableMapOf<String, Int>()
    for(i in 1..s.length-1){
        if(numbers.indexOf(s[i])>=numbers.indexOf(s[i-1])){
            str+=s[i]
        }else{
            if(!dic.containsKey(str)){
                dic.put(str,GetNumber(str.reversed()))
            }
            res+=dic.get(str)!!
            str=s[i].toString()
        }
        if(i==s.length-1){
            if(!dic.containsKey(str)){
                dic.put(str,GetNumber(str.reversed()))
            }
            res+=dic.get(str)!!
        }
    }

    return res
}

fun GetNumber(str:String):Int{
    var sum =  GetRomanNumber(str[0])

    if(str.length>1){
        var pre = str[0]
        var temp = str.subSequence(1,str.length)
        while (temp.length>0){
            var num = GetRomanNumber(temp[0])
            if(pre == temp[0]){
                sum+=num
            }else{
                sum-=num
            }
            if(temp.length==1) break
            pre=temp[0]
            temp=temp.subSequence(1,temp.length)
        }
    }
    return sum
}
fun GetRomanNumber(c:Char):Int{
    when (c){
        'I' -> return 1
        'V' -> return 5
        'X' -> return 10
        'L' -> return 50
        'C' -> return 100
        'D' -> return 500
        else ->  return 1000
    }
}

fun main(args: Array<String>) {
    var res = romanToInt("D")
    println(res)
}