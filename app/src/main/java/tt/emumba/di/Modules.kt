package tt.emumba.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import tt.emumba.data.repository.NoteRepositoryImpl
import tt.emumba.domain.repository.NoteRepository
import tt.emumba.domain.usecase.GetCategoriesUseCase
import tt.emumba.domain.usecase.GetProductsUseCase
import tt.emumba.presentation.viewmodels.NotesViewModel

var useCaseModule = module {
    single { GetCategoriesUseCase(get()) }
    single { GetProductsUseCase(get()) }
}

var viewModels = module {
    viewModel { NotesViewModel(get(),get()) }
}
var repositories = module {
    single<NoteRepository> { NoteRepositoryImpl(get()) }
}
