package com.example.final_test_01.ui.setting_notification

import android.os.Build.ID
import androidx.lifecycle.ViewModel
import androidx.work.Data
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequest
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.final_test_01.util.Const.DATA_NAME
import com.example.final_test_01.worker.MyWorker
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class SettingFragmentViewModel @Inject constructor() : ViewModel() {
    var time = "3"
    lateinit var workManager: WorkManager
    private lateinit var periodicWork: PeriodicWorkRequest
    val data = Data.Builder().putString(DATA_NAME, time)

    fun setTime() {
        periodicWork = PeriodicWorkRequestBuilder<MyWorker>(time.toLong(), TimeUnit.HOURS)
            .setInputData(data.build())
            .build()

        setWorkManager()
    }

    fun startWorker() {
        periodicWork = PeriodicWorkRequestBuilder<MyWorker>(3, TimeUnit.HOURS)
            .setInputData(data.build())
            .build()

        setWorkManager()
    }

    fun stopWorker() {
        workManager.cancelUniqueWork(ID)
    }


    /**
     * برای آپدیت کردن نوتفیکیشن
     */
    private fun setWorkManager() {
        workManager.enqueueUniquePeriodicWork(
            ID,
            ExistingPeriodicWorkPolicy.UPDATE,
            periodicWork
        )
    }
}