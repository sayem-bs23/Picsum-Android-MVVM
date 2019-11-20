package com.bs.picsum

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class PictureViewModel: ViewModel(){
    val pictures: LiveData<ArrayList<Picture>> =  PictureRepository.getPictureData()

}