package jlexdev.com.dbsqlite;

import android.provider.BaseColumns;

/**
 * Created by JLex on 28/10/17.
 */

public class AlumnoDB {

    // Constructor --Mismo nombre que la clase--
    private AlumnoDB() {
    }

    // Clase interna que define el contenido de la TABLA
    public static class AlumnoEntrada implements BaseColumns {
        public static final String TABLA_NOMBRE = "alumno";

        public static final String CODIGO_COLUMNA1 = "codigo";
        public static final String NOMBRE_COLUMNA2 = "nombre";
        public static final String APELLIDO_COLUMNA3 = "apellido";
    }


    // Tipo de Texto
    private static final String TIPO_TEXTO = " TEXT";
    // Separador de Campos
    private static final String SEPARADOR = ",";

    // Cte. para CREAR TABLA                                                                         // Es recomendable que la llave primaria sea BaseColumns._ID, ya que el Framework de Android usa esta referencia internamente en varios procesos.
    public static final String SQL_CREAR_ENTRADAS =
            "CREATE TABLE " + AlumnoEntrada.TABLA_NOMBRE + " (" +
                    AlumnoEntrada._ID + " INTEGER PRIMARY KEY," +
                    AlumnoEntrada.CODIGO_COLUMNA1 + TIPO_TEXTO + SEPARADOR +
                    AlumnoEntrada.NOMBRE_COLUMNA2 + TIPO_TEXTO + SEPARADOR +
                    AlumnoEntrada.APELLIDO_COLUMNA3 + TIPO_TEXTO + " )";

    // Cte. para ELIMINAR TABLA
    public static final String SQL_ELIMINAR_ENTRADAS =
            "DROP TABLE IF EXIST " + AlumnoEntrada.TABLA_NOMBRE;                                     // Eliminar o Actualizar TABLA si ya existe

}
