package com.bs.picsum

import androidx.lifecycle.LiveData
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main


object PictureRepository{
    var job: CompletableJob? = null //more control on asynchronous job
    fun getPictureData(): LiveData<ArrayList<Picture>> {
        job = Job()

        return object : LiveData<ArrayList<Picture>>(){
            override fun onActive() {
                super.onActive() //when the method is called, I want to get the value

                job?.let{theJob->
                    CoroutineScope(IO + theJob).launch{
                        var data = RetrofitSingleton.instanceAllPicture.getAllPicture()
                        val filter = ArrayList<Picture>()
                        for(i in data){

                            if(i!!.width!!.toInt() < 4000){
                                filter.add(i)
                            }
                        }
                        data = filter
                        withContext(Main){
                            value = data //setValue in live data
                            theJob.complete()
                        }
                    }
                }
            }
        }
    }

    fun cancelJobs(){
        job?.cancel() //later in the view model, when the activity is destroyed, we want to cancel

    }
}