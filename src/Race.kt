import CounterThread.Companion.MAX
import java.io.File

class Race {

    private var raceIsFinish = false

    fun start(){
        val nameFile = "race_logs.txt"
        val file = File(nameFile)
        file.printWriter()
        raceIsFinish
        val callBack = object : WriteToFileCallBack{
            override fun writeToLog(threadName: String, i: Int) {
                if(i == MAX) {
                    synchronized(this){
                        val result: String = if (raceIsFinish) "Проиграл" else "Выиграл"
                        raceIsFinish = true
                        file.appendText("$threadName: $i \n")
                        file.appendText("$threadName: $result \n")
                    }
                }  else {
                    file.appendText("$threadName: $i \n")
                }
            }
        }

        val thread1 = CounterThread(callBack,"Поток А")
        val thread2 = CounterThread(callBack,"Поток Б")
        thread1.start()
        thread2.start()
    }
}

interface WriteToFileCallBack{
    fun writeToLog(threadName: String, i: Int)
}
