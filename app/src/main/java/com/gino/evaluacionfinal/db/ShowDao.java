package com.gino.evaluacionfinal.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.gino.evaluacionfinal.model.ShowEntity;

import java.util.List;

@Dao
public interface ShowDao {

    //esto es para insertar
    @Insert
    public void addShow(ShowEntity show);

    //  para buscar un valor a traves de un id
    @Query("SELECT * FROM show WHERE show_name LIKE :name LIMIT 1")
    public ShowEntity getShowByName(String name);

    // traer toda la lista de los shows
    @Query("SELECT * FROM show")
    public LiveData<List<ShowEntity>> getAll();

    // para eliminar
    @Delete
    public void deleteShow(ShowEntity show);
}
