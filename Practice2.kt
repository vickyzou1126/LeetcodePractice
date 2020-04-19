fun main(args: Array<String>) {
    numberOfSteps(8)
}

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