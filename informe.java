package com.example.usuario.proyectofinal;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class informe extends AppCompatActivity {
    ListView MyListview;
    Button Regresar,Menu;
    ConexionSQLiteHelper helper = new ConexionSQLiteHelper(this,"BDProject",null,1);
    ArrayList<String> consulta = new ArrayList<>();
    ArrayAdapter<String> adapter=null;
    String cadena="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informe);
        Bundle bundle=this.getIntent().getExtras();
        cadena=bundle.getString("Usuario");
        MyListview=findViewById(R.id.MyListView);
        Regresar=findViewById(R.id.Regresar);
        Regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle myBundle=new Bundle();
                myBundle.putString("Usuario",cadena);
                Intent intent=new Intent(informe.this,Administrador.class);
                intent.putExtras(myBundle);
                finish();
                startActivity(intent);
            }
        });
        Menu=findViewById(R.id.Menu);
        Menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(informe.this,MainActivity.class);
                finish();
                startActivity(intent);
            }
        });
        if(adapter!=null){
            consulta.clear();
            adapter.notifyDataSetChanged();
            MyListview.setAdapter(adapter);
        }
        MyListview.setAdapter(null);
        SQLiteDatabase bd = helper.getReadableDatabase();
        Cursor fila = bd.rawQuery("select administrador.Id_Administrador,administrador.User_Admin,administrador.Nombre_Admin, informe.Descripcion" +
                ",producto.Nombre_Producto from informe inner join administrador on informe.Id_Administrador = administrador.Id_Administrador" +
                " inner join producto on informe.Id_Producto = producto.Id_Producto", null);
        if(fila.moveToFirst()){
            do{
                consulta.add("Id: "+fila.getString(0) + "\nUsuario: " + fila.getString(1)+"\nNombreAdm: "+fila.getString(2)
                        +"\nAcción: "+fila.getString(3)+"\nNombre Producto: "+fila.getString(4)+"\n--------------------------------------------");
            }while(fila.moveToNext());
        }
        else
            Toast.makeText(informe.this, "Tabla vacía",Toast.LENGTH_SHORT).show();

        bd.close();
        adaptador();
        MyListview.setAdapter(adapter);

    }
    public void adaptador(){
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, consulta);
    }
}
