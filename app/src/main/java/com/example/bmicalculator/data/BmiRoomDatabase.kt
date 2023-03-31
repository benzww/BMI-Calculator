package com.example.bmicalculator.data



import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.bmicalculator.models.BMI

@Database(entities = [BMI::class], version = 1, exportSchema = false)
abstract class BmiRoomDatabase : RoomDatabase() {

    abstract fun bmiDao(): BmiDao

    companion object {
        @Volatile
        private var INSTANCE: BmiRoomDatabase? = null

        fun getDatabase(context: Context): BmiRoomDatabase {
            val temoInstance = INSTANCE

            if (temoInstance != null)
                return temoInstance

            synchronized(BmiRoomDatabase::class) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BmiRoomDatabase::class.java,
                    "bmi_room_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}