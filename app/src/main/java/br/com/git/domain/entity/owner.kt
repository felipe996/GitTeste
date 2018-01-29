package br.com.git.domain.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.io.Serializable


/**
 * Created by Felipe on 26/01/2018.
 */

@Entity
class owner:Serializable {
    @PrimaryKey(autoGenerate = true)
    var Ownerid = 0
    var login = ""
    var avatar_url = ""
    var html_url = ""
    var repos_url = ""
}