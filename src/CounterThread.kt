class CounterThread(private var callBack: WriteToFileCallBack, var threadName: String): Thread() {

    companion object{
        val MIN = 1
        val MAX = 100
    }
    override fun run() {
        for(i in MIN..MAX){
            callBack.writeToLog(threadName, i)
        }
    }
}