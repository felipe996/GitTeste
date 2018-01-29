package br.com.git

import android.app.Application
import android.util.Log
import java.lang.IllegalStateException

class GitApplication : Application() {
    private val TAG = "GitApplication"

    // Chamado quando o Android criar o processo da aplicação
    override fun onCreate() {
        super.onCreate()
        // Salva a instância para termos acesso como Singleton
        appInstance = this
    }

    companion object {
        // Singleton da classe Application
        private var appInstance: GitApplication? = null

        fun getInstance(): GitApplication {
            if (appInstance == null) {
                throw IllegalStateException("Configure a classe de Application no AndroidManifest.xml")
            }
            return appInstance!!
        }
    }

    // Chamado quando o Android finalizar o processo da aplicação
    override fun onTerminate() {
        super.onTerminate()
        Log.d(TAG, "GitApplication.onTerminate()")
    }
}
