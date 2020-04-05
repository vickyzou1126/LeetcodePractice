import kotlin.math.abs

fun main(args: Array<String>) {
    var res = findSubstring("barfoothefoobarman", arrayOf("foo","bar"))
    println(res)
}
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