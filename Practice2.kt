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

fun main(args: Array<String>) {
    selfDividingNumbers(1,22)
}