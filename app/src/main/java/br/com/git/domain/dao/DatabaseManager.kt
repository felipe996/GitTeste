package br.com.git.domain.dao

import android.arch.persistence.room.Room
import br.com.git.GitApplication


object DatabaseManager {
    // Singleton do Room: banco de dados
    private var dbInstance: GitDatabase

    init {
        val appContext = GitApplication.getInstance().applicationContext

        // Configura o Room
        dbInstance = Room.databaseBuilder(
                appContext,
                GitDatabase::class.java,
                "git.sqlite")
                .build()
    }

    fun getGitDAO(): GitDAO {
        return dbInstance.gitDAO()
    }


}