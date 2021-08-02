package kg.geektech.hwfilmapiroom.frameforks;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import kg.geektech.hwfilmapiroom.data.model.Films;


@Dao
public interface FilmDao {

    @Query("SELECT * FROM Films")
    LiveData<List<Films>> getAll();

    @Insert
    void insert(Films films);

}