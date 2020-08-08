package jlexdev.com.dbsqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static jlexdev.com.dbsqlite.AlumnoDB.SQL_CREAR_ENTRADAS;
import static jlexdev.com.dbsqlite.AlumnoDB.SQL_ELIMINAR_ENTRADAS;

/**
 * Created by JLex on 28/10/17.
 */

public class AlumnoDBHelper extends SQLiteOpenHelper {

    public static final int DB_VERSION = 1;
    public static final String DB_NOMBRE = "Alumnos.db";

    // Constructor
    public AlumnoDBHelper(Context context){
        super(context, DB_NOMBRE, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREAR_ENTRADAS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_ELIMINAR_ENTRADAS);
        onCreate(db);
    }

}
