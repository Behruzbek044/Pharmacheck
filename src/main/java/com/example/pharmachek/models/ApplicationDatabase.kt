package com.example.pharmachek.models

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.pharmachek.database.DrugDao
import com.example.pharmachek.utils.Constants

@Database(
    entities = [DrugData::class],
    version = 3
)
abstract class ApplicationDatabase : RoomDatabase() {

    abstract fun drugDao() : DrugDao

    companion object {
        @Volatile
        private var INSTANCE: ApplicationDatabase? = null

        fun createDatabase(context: Context): ApplicationDatabase {
            if (INSTANCE != null)
                return INSTANCE!!

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ApplicationDatabase::class.java,
                    Constants.DATABASE_NAME
                )
                    .createFromAsset(Constants.DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()

                INSTANCE = instance
                return instance
            }
        }
    }
}