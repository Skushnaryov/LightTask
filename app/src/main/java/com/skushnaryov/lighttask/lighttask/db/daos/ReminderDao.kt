package com.skushnaryov.lighttask.lighttask.db.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.skushnaryov.lighttask.lighttask.db.entities.Reminder

@Dao
interface ReminderDao : BaseDao<Reminder> {
    @Query("SELECT * FROM reminders")
    fun getAllReminders(): LiveData<List<Reminder>>

    @Query("UPDATE reminders SET isOn=:isOn WHERE id=:id")
    fun updateIsOnById(id: Int, isOn: Boolean)
}