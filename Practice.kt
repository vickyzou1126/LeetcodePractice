import kotlin.math.abs

fun main(args: Array<String>) {
    var nums:IntArray= intArrayOf(1,2,5,10,11)
    println(threeSumClosest(nums,12))
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