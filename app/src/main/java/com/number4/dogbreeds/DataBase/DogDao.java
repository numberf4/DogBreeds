package com.number4.dogbreeds.DataBase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;


import com.number4.dogbreeds.Models.DogModel;

import java.util.List;

@Dao
public interface DogDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(DogModel dogModel);

    @Update
    void update(DogModel dogModel);

    @Delete
    void delete(DogModel dogModel);

    @Query("DELETE FROM favorite_dog_table")
    void deleteAll();

    @Query("update favorite_dog_table set isFavorite = :favorite  where name = :name")
    void updateFavorite(String name, boolean favorite);

    @Query("SELECT * FROM favorite_dog_table ORDER BY name ASC")
    LiveData<List<DogModel>> getAll();

}
