package com.skushnaryov.lighttask.lighttask.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query

@Dao
interface GroupDao : BaseDao<Group> {

    @Query("SELECT * FROM groups")
    fun getAllGroups(): LiveData<List<Group>>
}