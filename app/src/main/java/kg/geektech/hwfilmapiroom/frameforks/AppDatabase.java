package kg.geektech.hwfilmapiroom.frameforks;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import kg.geektech.hwfilmapiroom.data.model.Films;

@Database(entities = {Films.class},version = 1)
public abstract class AppDatabase extends RoomDatabase{
        public abstract FilmDao filmDao();

}
