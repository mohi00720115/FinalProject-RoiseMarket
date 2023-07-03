package com.example.final_test_01.worker

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.graphics.BitmapFactory
import android.icu.text.SimpleDateFormat
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.NavDeepLinkBuilder
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.final_test_01.R
import com.example.final_test_01.data.model.dto.product.ProductsItemsDto
import com.example.final_test_01.ui.detail.DetailsFragmentArgs
import java.util.Calendar

@RequiresApi(Build.VERSION_CODES.O)
class MyWorker(
    private val appContext: Context, params: WorkerParameters) :
    CoroutineWorker(appContext, params) {

    private lateinit var notificationManager: NotificationManager
    private lateinit var notificationChannel: NotificationChannel
    private lateinit var builder: Notification.Builder
    private lateinit var pendingIntent: PendingIntent

    override suspend fun doWork(): Result {
        val inputData = inputData.getString("DATA_NAME") ?: "3"
        val date = createCustomTime(inputData.toInt())
        val result = WorkerNetworkManager.service.getNewProductList(date!!)
        if (result.isSuccessful) {
            result.body()?.let {
                navDeepLink(it[0].id)
            }
        }
        val product = result.body()?.get(0)
        notificationManagerInit()
        notification(product)
        return Result.success()
    }

    private fun navDeepLink(detailItems: Int) {
        pendingIntent = NavDeepLinkBuilder(appContext)
            .setGraph(R.navigation.my_nav)
            .setDestination(R.id.detailsDialog)
            .setArguments(DetailsFragmentArgs(detailItems).toBundle())
            .createPendingIntent()
    }

    private fun notificationManagerInit() {
        notificationManager =
            appContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }

    private fun notification(product: ProductsItemsDto?) {
        notificationChannel =
            NotificationChannel("CHANNEL_ID", "DESCRIPTION", NotificationManager.IMPORTANCE_HIGH)
        notificationManager.createNotificationChannel(notificationChannel)

        builder = Notification.Builder(appContext, "CHANNEL_ID")
            .setSmallIcon(R.mipmap.ic_launcher_round)
            .setContentIntent(pendingIntent)
            .setContentTitle(product?.name)
            .setContentText(if (product?.price!!.isNotBlank()) product.price else product.salePrice)
            .setAutoCancel(true)
            .setLargeIcon(
                BitmapFactory.decodeResource(appContext.resources,R.mipmap.ic_launcher_round)
            )
        notificationManager.notify(1001, builder.build())
    }

    @SuppressLint("SimpleDateFormat")
    private fun createCustomTime(time: Int): String? {
        val currentTime = Calendar.getInstance()
        currentTime.add(Calendar.DAY_OF_WEEK_IN_MONTH, -time)
        val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        return formatter.format(currentTime.time)
    }
}