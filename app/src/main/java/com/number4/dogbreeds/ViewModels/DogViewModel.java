package com.number4.dogbreeds.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.number4.dogbreeds.DataBase.DogRepository;
import com.number4.dogbreeds.Models.DogModel;

import java.util.List;

public class DogViewModel extends AndroidViewModel {
    private DogRepository repository;
    private LiveData<List<DogModel>> allDogs;
    public DogViewModel(@NonNull Application application) {
        super(application);
        repository = new DogRepository(application);
        allDogs = repository.getAll();
    }
    public void insert(DogModel dog) {
        repository.insert(dog);
    }
    public void update(DogModel dog) {
        repository.update(dog);
    }
    public void updateFavorite(String name, boolean favorite) {
        repository.updateFavorite(name, favorite);
    }
    public void delete(DogModel dog) {
        repository.delete(dog);
    }
    public void deleteAllNotes() {
        repository.deleteAll();
    }
    public LiveData<List<DogModel>> getAllDogs() {
        return allDogs;
    }
}
