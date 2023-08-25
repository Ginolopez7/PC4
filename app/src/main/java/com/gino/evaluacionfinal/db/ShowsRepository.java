package com.gino.evaluacionfinal.db;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.gino.evaluacionfinal.model.ShowEntity;

import java.util.List;

public class ShowsRepository {

    private ShowDao dao;

    private ECDatabase db;

    public ShowsRepository(Application application) {
        db = ECDatabase.getInstance(application);
        dao = db.showDao();
    }

    public void addShow(ShowEntity showEntity) {
        ECDatabase.dataBaseWriteExecutor.execute(() ->
                dao.addShow(showEntity));
    }

    // obtener la lista
    public LiveData<List<ShowEntity>> getAll() {
        return dao.getAll();
    }
}
