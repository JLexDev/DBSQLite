package jlexdev.com.dbsqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btnInsertar, btnBuscar;
    private EditText etCodigo, etNombre, etApellido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInsertar = (Button)findViewById(R.id.btn_insertar);
        btnBuscar = (Button)findViewById(R.id.btn_buscar);

        etCodigo = (EditText)findViewById(R.id.et_codigo);
        etNombre = (EditText)findViewById(R.id.et_nombre);
        etApellido = (EditText)findViewById(R.id.et_apellido);


        // Instancia
        final AlumnoDBHelper helper = new AlumnoDBHelper(this);


        // Evento INSERTAR
        btnInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Permite escribir información en la DB
                SQLiteDatabase db = helper.getWritableDatabase();

                // Crear un nuevo mapa de valores, donde los nombres de las columnas son las llaves
                ContentValues values = new ContentValues();
                values.put(AlumnoDB.AlumnoEntrada.CODIGO_COLUMNA1, etCodigo.getText().toString());
                values.put(AlumnoDB.AlumnoEntrada.NOMBRE_COLUMNA2, etNombre.getText().toString());
                values.put(AlumnoDB.AlumnoEntrada.APELLIDO_COLUMNA3, etApellido.getText().toString());

                // Insertar la nueva fila, retornar el valor de la llave primaria de la nueva fila
                long newRowId = db.insert(AlumnoDB.AlumnoEntrada.TABLA_NOMBRE, null, values);

                // Toast
                Toast.makeText(getApplicationContext(), "Se guardó el registro con clave: " + newRowId, Toast.LENGTH_SHORT).show();
            }
        });

        // Evento BUSCAR
        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Permite leer información de la DB
                SQLiteDatabase db = helper.getReadableDatabase();

                // Arreglo que define la proyección que especifica que columnas de la DB vamos a usar en nuestra consulta
                String[] proyeccion = {
                        /* No coloco CODIGO porque lo usaré como "CAMPO DE CRITERIO" */
                        AlumnoDB.AlumnoEntrada.NOMBRE_COLUMNA2,
                        AlumnoDB.AlumnoEntrada.APELLIDO_COLUMNA3
                };

                // Filtrado de Registros (Consulta preparada)
                String seleccion = AlumnoDB.AlumnoEntrada.CODIGO_COLUMNA1 + " = ?";

                // Arreglo que guarda los Criterios
                String[] seleccionArgs = {
                    etCodigo.getText().toString()
                };

                try {

                    // Cursor (Query = Consulta)
                    Cursor cursor = db.query(
                            AlumnoDB.AlumnoEntrada.TABLA_NOMBRE,            // Tabla a consultar
                            proyeccion,                                     // Columnas a retornar
                            seleccion,                                      // Columnas que tienen el CRITERIO
                            seleccionArgs,                                  // Valores de donde viene el CRITERIO
                            null,                                  // No agrupar filas
                            null,                                   // No filtrar por grupos de fila
                            null                                   // Establece ordenamiento
                    );

                    // Mover cursor al primer registro
                    cursor.moveToFirst();

                    // Donde leer la información
                    etNombre.setText(cursor.getString(0));
                    etApellido.setText(cursor.getString(1));
                    
                } catch (Exception e){

                    Toast.makeText(MainActivity.this, "No se encontró registro", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }


}
