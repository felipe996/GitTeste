package br.com.git.domain.dao

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import br.com.git.domain.entity.items
import br.com.git.domain.entity.owner

// Define as classes que precisam ser persistidas e a versão do banco
@Database(entities = [(items::class)], version = 1)

abstract class GitDatabase : RoomDatabase() {

    abstract fun gitDAO(): GitDAO

}
