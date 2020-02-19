package xyz.net7.sampleroom.model;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.IGNORE;


@Dao
public interface DataDAO {

    //Insertar un artículo
    @Insert(onConflict = IGNORE)
    void insertItem(DataItem item);

    //Eliminar un elemento
    @Delete
    void deleteItem(DataItem person);

    //Eliminar un elemento por id
    @Query("DELETE FROM dataitem WHERE id = :itemId")
    void deleteByItemId(long itemId);

    // Obtenga todos los artículos
    @Query("SELECT * FROM DataItem")
    LiveData<List<DataItem>> getAllData();

    //Eliminar todos
    @Query("DELETE FROM DataItem")
    void deleteAll();
}
