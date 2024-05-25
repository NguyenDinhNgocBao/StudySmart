package com.example.studysmart.presentation.session

import androidx.compose.material3.SnackbarDuration
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studysmart.domain.model.Session
import com.example.studysmart.domain.repository.SessionRepository
import com.example.studysmart.domain.repository.SubjectRepository
import com.example.studysmart.util.SnackBarEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.Instant
import javax.inject.Inject

@HiltViewModel
class SessionViewModel @Inject constructor(
    subjectRepository: SubjectRepository,
    private val sessionReponsitory: SessionRepository
): ViewModel() {
    private val _state = MutableStateFlow(SessionState())
    val state = combine(
        _state,
        subjectRepository.getAllSubjects(),
        sessionReponsitory.getAllSessions()
    ){state, subjects, sessions ->
        state.copy(
            subjects = subjects,
            sessions = sessions
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000),
        initialValue = SessionState()
    )

    private val _snackbarEventFlow = MutableSharedFlow<SnackBarEvent>()
    val snackbarEventFlow = _snackbarEventFlow.asSharedFlow()

    fun onEvent(event: SessionEvent){
        when(event){
            SessionEvent.NotifyToUpdateSubject -> notifyToUpdateSubject()
            SessionEvent.DeleteSession -> deleteSession()
            is SessionEvent.OnDeleteSessionButtonCLick -> {
                _state.update {
                    it.copy(session = event.session)
                }
            }
            is SessionEvent.OnRelatedSubjectChange -> {
                _state.update {
                    it.copy(
                        relatedToSubject = event.subject.name,
                        subjectId = event.subject.subjectId
                    )
                }
            }
            is SessionEvent.SaveSession -> insertSession(event.duration)
            is SessionEvent.UpdateSubjectIdAndRelatedSubject -> {
                _state.update {
                    it.copy(
                        relatedToSubject = event.relatedToSubject,
                        subjectId = event.subjectId
                    )
                }
            }
        }
    }

    private fun notifyToUpdateSubject() {
        viewModelScope.launch {
            if(state.value.subjectId == null || state.value.relatedToSubject == null){
                _snackbarEventFlow.emit(
                    SnackBarEvent.ShowSnackbar(
                        message = "Please select subject related to the session"
                    )
                )
            }
        }
    }

    private fun deleteSession(){
        viewModelScope.launch {
            try {
                state.value.session?.let {
                    sessionReponsitory.deleteSession(it)
                    _snackbarEventFlow.emit(
                        SnackBarEvent.ShowSnackbar(message = "Session deleted successfully")
                    )
                }
            }catch (e:Exception){
                SnackBarEvent.ShowSnackbar(
                    message = "Couldn't delete session. ${e.message}",
                    duration = SnackbarDuration.Long
                )
            }
        }
    }

    private fun insertSession(duration: Long) {
            viewModelScope.launch {
                if(duration < 36){
                    _snackbarEventFlow.emit(
                        SnackBarEvent.ShowSnackbar(
                            message = "Single session can not be less than 36 seconds"
                        )
                    )
                    return@launch
                }
                try {
                    sessionReponsitory.insertSession(
                        session = Session(
                            sessionSubjectId = state.value.subjectId ?: -1,
                            relatedToSubject = state.value.relatedToSubject ?: "",
                            date = Instant.now().toEpochMilli(),
                            duration = duration
                        )
                    )
                    _snackbarEventFlow.emit(
                        SnackBarEvent.ShowSnackbar(
                            message = "Session save successfully"
                        )
                    )
                }catch (e: Exception){
                    _snackbarEventFlow.emit(
                        SnackBarEvent.ShowSnackbar(
                            message = "Couldn't save session. ${e.message}",
                            duration = SnackbarDuration.Long
                        )
                    )
                }

            }
    }
}