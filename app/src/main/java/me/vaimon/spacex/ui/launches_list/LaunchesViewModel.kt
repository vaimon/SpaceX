package me.vaimon.spacex.ui.launches_list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import me.vaimon.spacex.data.models.LaunchData
import me.vaimon.spacex.mapper.Mapper
import me.vaimon.spacex.ui.models.DetailedLaunch
import me.vaimon.spacex.ui.models.Launch
import me.vaimon.spacex.ui.repository.LaunchRepository
import me.vaimon.spacex.utils.SingleLiveEvent
import javax.inject.Inject

class LaunchesViewModel @Inject constructor(
    launchRepository: LaunchRepository,
    private val launchAppDataMapper: Mapper<Launch, LaunchData>,
    private val detailedLaunchAppDataMapper: Mapper<DetailedLaunch, LaunchData>
) : ViewModel() {
    val eventBus = SingleLiveEvent<Event>()

    init {
        val launchSubscription = launchRepository.launches
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ launchesList ->
                _launches.value = launchesList.map { launchAppDataMapper.from(it) }
            }, {
                Log.e("Launches fetch", it.localizedMessage ?: it.toString())
                eventBus.value = Event.Error(it.localizedMessage)
            })
    }

    private val _launches = MutableLiveData<List<Launch>>()
    val launches: LiveData<List<Launch>> = _launches


    fun onLaunchClick(item: Launch) {
        eventBus.value = Event.DetailsNavigationRequired(item.id)
    }

    sealed interface Event {
        data class DetailsNavigationRequired(val targetId: Int) : Event
        data class Error(val message: String?) : Event
    }
}