package tt.emumba.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import tt.emumba.domain.model.Note
import tt.emumba.domain.usecase.DeleteNoteUseCase
import tt.emumba.domain.usecase.GetNotesUseCase
import tt.cleanarchmvvm.domain.usecase.InsertNoteUseCase

class NotesViewModel(
    private val getNotesUseCase: GetNotesUseCase,
    private val insertNoteUseCase: InsertNoteUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase,
) : ViewModel() {

    private val _notes = MutableStateFlow<List<Note>>(emptyList())
    val notes: StateFlow<List<Note>> get() = _notes

    init {
//        deleteAll()
        fetchNotes()
    }

    private fun fetchNotes() {
        viewModelScope.launch {
            getNotesUseCase.invoke().collect { noteList ->
                _notes.value = noteList
            }
        }
    }

    fun addNote(note: Note) {
        viewModelScope.launch {
            insertNoteUseCase.execute(note)
        }
    }
    private fun deleteAll() {
        viewModelScope.launch {
            deleteNoteUseCase.execute()
        }
    }
}