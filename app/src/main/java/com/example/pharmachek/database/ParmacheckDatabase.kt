package com.example.pharmachek.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.pharmachek.model.request.check_drug.CheckDrugRequestData
import com.example.pharmachek.model.request.save_drug.SaveDrugRequestData
import com.example.pharmachek.model.request.search_drug.SearchDrugRequestData
import com.example.pharmachek.model.response.check_drug.CheckDrugResponseData
import com.example.pharmachek.model.response.save_drug.SaveDrugResponseData
import com.example.pharmachek.model.response.search_drug.SearchDrugResponseData
import com.example.pharmachek.utils.Constants

@Database(
    entities = [
        ParmacheckEntity::class
    ],
    version = 2
)
abstract class ParmacheckDatabase : RoomDatabase() {

    abstract fun parmacheckDao(): ParmacheckDao

    companion object {
        @Volatile
        private var INSTANCE: ParmacheckDatabase? = null

        fun createDatabase(context: Context): ParmacheckDatabase {
            if (INSTANCE != null) return INSTANCE!!

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ParmacheckDatabase::class.java,
                    Constants.DATABASE_NAME
                ).fallbackToDestructiveMigration().allowMainThreadQueries().build()

                INSTANCE = instance
                return instance
            }
        }
    }
}