package com.number4.dogbreeds.DataBase;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.number4.dogbreeds.Models.DogModel;

import java.util.List;

public class DogRepository {
    private DogDao dogDao;
    private LiveData<List<DogModel>> allDog;

    public DogRepository(Application application) {
        DogDatabase database = DogDatabase.getInstance(application);
        dogDao = database.dogDao();
        allDog = dogDao.getAll();
    }
    public void insert (DogModel dogModel){
        new InsertDogAsyncTask(dogDao).execute(dogModel);
    }
    public void update(DogModel dogModel) {
        new UpdateDogAsyncTask(dogDao).execute(dogModel);
    }
    public void delete(DogModel dogModel) {
        new DeleteDogAsyncTask(dogDao).execute(dogModel);
    }
    public void updateFavorite(String name, boolean favorite) {
        dogDao.updateFavorite(name, favorite);
    }
    public void deleteAll() {
        new DeleteAllDogAsyncTask(dogDao).execute();
    }
    public LiveData<List<DogModel>> getAll() {
        return allDog;
    }

    private static class InsertDogAsyncTask extends AsyncTask<DogModel, Void, Void> {
        private DogDao dogDao;
        private InsertDogAsyncTask(DogDao dogDao){
            this.dogDao = dogDao;
        }

        @Override
        protected Void doInBackground(DogModel... dogModels) {
            dogDao.insert(dogModels[0]);
            return null;
        }
    }

    private static class UpdateDogAsyncTask extends AsyncTask<DogModel, Void, Void> {
        private DogDao dogDao;
        private UpdateDogAsyncTask(DogDao dogDao){
            this.dogDao = dogDao;
        }

        @Override
        protected Void doInBackground(DogModel... dogModels) {
            dogDao.update(dogModels[0]);
            return null;
        }
    }

    private static class DeleteDogAsyncTask extends AsyncTask<DogModel, Void, Void> {
        private DogDao dogDao;
        private DeleteDogAsyncTask(DogDao dogDao){
            this.dogDao = dogDao;
        }

        @Override
        protected Void doInBackground(DogModel... dogModels) {
            dogDao.delete(dogModels[0]);
            return null;
        }
    }

    private static class DeleteAllDogAsyncTask extends AsyncTask<Void, Void, Void> {
        private DogDao dogDao;
        private DeleteAllDogAsyncTask(DogDao dogDao){
            this.dogDao = dogDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            dogDao.deleteAll();
            return null;
        }
    }
}
