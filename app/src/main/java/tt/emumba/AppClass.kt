package tt.emumba

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import tt.emumba.di.databaseModule
import tt.emumba.di.repositories
import tt.emumba.di.viewModels


class AppClass : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            printLogger(Level.DEBUG)
            androidContext(this@AppClass)
            modules(
                databaseModule,
                viewModels,
                repositories,) // Add more modules here if needed
        }
    }
}