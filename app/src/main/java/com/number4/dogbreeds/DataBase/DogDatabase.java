package com.number4.dogbreeds.DataBase;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.number4.dogbreeds.Models.DogModel;

@Database(entities = {DogModel.class}, version = 1)
public abstract class DogDatabase extends RoomDatabase {
    private static DogDatabase instance;
    public abstract DogDao dogDao();

    public static synchronized DogDatabase getInstance(Context context) {
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    DogDatabase.class, "dog_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }
    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsynctask(instance).execute();
        }
    };


        private static class PopulateDbAsynctask extends AsyncTask<Void, Void, Void> {
            private DogDao dogDao;

            private PopulateDbAsynctask(DogDatabase db) {
                dogDao = db.dogDao();
            }

            @Override
            protected Void doInBackground(Void... voids) {
                dogDao.insert(new DogModel());
                return null;
            }
        }


}
