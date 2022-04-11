package com.example.room_mvp.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {MyData.class},version = 1,exportSchema = false)
public abstract class DataBase extends RoomDatabase {
   public static final String DB_NAME = "RecordData.db";
   private static volatile DataBase instance;

   public static DataBase getInstance(Context context){
      if(instance==null){
         instance = Room.databaseBuilder(context,DataBase.class,DB_NAME)
                 .fallbackToDestructiveMigration()
                 .build();
      }
      return instance;
   }

   abstract public Dao getDataUao();
}
