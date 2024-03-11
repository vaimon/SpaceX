package me.vaimon.spacex.ui.launches_list

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.subjects.PublishSubject
import me.vaimon.spacex.ui.models.Launch
import me.vaimon.spacex.ui.repository.LaunchRepository
import javax.inject.Inject

@HiltViewModel
class LaunchesViewModel @Inject constructor(
    private val launchRepository: LaunchRepository
) : ViewModel() {

    val eventBus = PublishSubject.create<Event>()

    val launches = launchRepository.launches.doOnError {
        eventBus.onNext(Event.Error(it.localizedMessage))
    }.onErrorReturnItem(listOf())

    fun onLaunchClick(item: Launch) {
        eventBus.onNext(Event.DetailsNavigationRequired(item.id))
    }

    sealed interface Event {
        data class DetailsNavigationRequired(val targetId: Int) : Event
        data class Error(val message: String?) : Event
    }
}