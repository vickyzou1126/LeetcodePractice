import kotlin.math.abs


fun main(args: Array<String>) {
    var nums:IntArray= intArrayOf(-1,0,1,0)
    println(threeSum(nums))

}
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
