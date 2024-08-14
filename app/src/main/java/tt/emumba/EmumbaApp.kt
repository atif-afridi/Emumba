package tt.emumba

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import tt.emumba.di.networkModule
import tt.emumba.di.repositories
import tt.emumba.di.useCaseModule
import tt.emumba.di.viewModels


class EmumbaApp : Application() {

    override fun onCreate() {
        super.onCreate()
        setUpKoin()
    }

    private fun setUpKoin() {
        startKoin {
            printLogger(Level.DEBUG)
            androidContext(this@EmumbaApp)
            modules(
                networkModule,
                viewModels,
                repositories,
                useCaseModule,
            )
        }
    }
}