package tt.emumba.di

import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import tt.emumba.data.repository.NoteRepositoryImpl
import tt.emumba.data.source.NoteDataSource
import tt.emumba.data.source.RoomDataSource
import tt.emumba.data.database.NoteDatabase
import tt.emumba.data.database.NoteDatabase.Companion.getNoteTableDao
import tt.emumba.domain.repository.NoteRepository
import tt.emumba.domain.usecase.DeleteNoteUseCase
import tt.emumba.domain.usecase.GetNotesUseCase
import tt.emumba.domain.usecase.InsertNoteUseCase
import tt.emumba.presentation.viewmodels.NotesViewModel

/**
 * modules for dependency injection where [single] represents singleton class
 */
var databaseModule = module {
    // Provide Context
    single {
        NoteDatabase.getDatabase(androidContext())
    }
    single {
        getNoteTableDao(get())
    }
    single<NoteDataSource> { RoomDataSource(get()) }
    single<NoteRepository> { NoteRepositoryImpl(get()) }
    // use cases.
    single { GetNotesUseCase(get()) }
    single { InsertNoteUseCase(get()) }
    single { DeleteNoteUseCase(get()) }
}

var viewModels = module {
//    viewModel { NotesViewModel(getNotesUseCase = get(),insertNoteUseCase = get()) }
//    viewModel { NotesViewModel(get(),get(),get()) } // TODO viewmodel isnt working
    single { NotesViewModel(get(),get(),get()) }
}
var repositories = module {
    single<NoteRepository> { NoteRepositoryImpl(get()) }
}

var utils = module {
//    factory { getRandomNumber() }
}