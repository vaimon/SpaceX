package me.vaimon.spacex.ui.launch_details

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import me.vaimon.spacex.data.models.LaunchData
import me.vaimon.spacex.mapper.Mapper
import me.vaimon.spacex.ui.models.DetailedLaunch
import me.vaimon.spacex.ui.repository.LaunchRepository
import me.vaimon.spacex.utils.SingleLiveEvent
import javax.inject.Inject

class LaunchDetailsViewModel @Inject constructor(
    private val launchRepository: LaunchRepository,
    private val detailedLaunchAppDataMapper: Mapper<DetailedLaunch, LaunchData>,
) : ViewModel() {
    val eventBus = SingleLiveEvent<Event>()

    private val _launchDetails = MutableLiveData<DetailedLaunch>()
    val launchDetails: LiveData<DetailedLaunch> = _launchDetails

//    @AssistedFactory
//    interface MyDataServiceFactory {
//        fun create(launchId: Int): LaunchDetailsViewModel
//    }

    fun startObserving(launchId: Int) {
        val launchSubscription = launchRepository.launches
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ launchesList ->
                val launch = launchesList.find { it.id == launchId }
                    ?: throw IllegalStateException()
                _launchDetails.value = detailedLaunchAppDataMapper.from(launch)
            }, {
                Log.e("Launches fetch", it.localizedMessage ?: it.toString())
                eventBus.value = Event.Error(it.localizedMessage)
            })
    }

    sealed interface Event {
        data class Error(val message: String?) : Event
    }
}