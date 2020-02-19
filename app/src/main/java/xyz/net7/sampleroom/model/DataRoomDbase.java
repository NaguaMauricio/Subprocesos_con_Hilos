package xyz.net7.sampleroom.model;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {DataItem.class}, version = 1, exportSchema = false)
public abstract class DataRoomDbase extends RoomDatabase {

    private static DataRoomDbase INSTANCE;

    public abstract DataDAO dataDAO();

    public static DataRoomDbase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), DataRoomDbase.class, DataRoomDbase.class.getName())
            // si quieres crear db solo en la memoria, no en el archivo
            //Habitación en el generador de base de datos de memoria
                     .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}
