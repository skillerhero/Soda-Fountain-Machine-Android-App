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

public class Consultar_Compras extends AppCompatActivity {

    Button Regresar,Menu;
    ListView MyListview;
    ConexionSQLiteHelper helper = new ConexionSQLiteHelper(this,"BDProject",null,1);
    ArrayList<String> consulta = new ArrayList<>();
    ArrayAdapter<String> adapter=null;
    String cadena="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar__compras);
        Bundle bundle=this.getIntent().getExtras();
        cadena=bundle.getString("Usuario");
        Regresar=findViewById(R.id.Regresar);
        Regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle myBundle=new Bundle();
                myBundle.putString("Usuario",cadena);
                Intent intent=new Intent(Consultar_Compras.this,Administrador.class);
                intent.putExtras(myBundle);
                finish();
                startActivity(intent);
            }
        });
        Menu=findViewById(R.id.Menu);
        Menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Consultar_Compras.this,MainActivity.class);
                finish();
                startActivity(intent);
            }
        });
        MyListview=findViewById(R.id.MyListview);

        if(adapter!=null){
            consulta.clear();
            adapter.notifyDataSetChanged();
            MyListview.setAdapter(adapter);
        }
        MyListview.setAdapter(null);
        SQLiteDatabase bd = helper.getReadableDatabase();
        Cursor fila = bd.rawQuery("select usuario.Id_Usuario, usuario.Nombre_Usuario, producto.Nombre_Producto," +
                " producto.Precio, compra.Fecha_y_hora from compra inner join usuario on compra.Id_Usuario" +
                "= usuario.Id_Usuario inner join producto on compra.Id_Producto = producto.Id_Producto", null);
        if(fila.moveToFirst()){
            do{
                consulta.add("Id: "+fila.getString(0) + "\nNombre: " + fila.getString(1)+"\nProducto: "+fila.getString(2)
                        +"\nPrecio: $"+fila.getString(3)+"\nFecha y hora: "+fila.getString(4)+"\n----------------------------------------------------------------");
            }while(fila.moveToNext());
        }
        else
            Toast.makeText(Consultar_Compras.this, "Tabla vacía",Toast.LENGTH_SHORT).show();

        bd.close();
        adaptador();
        MyListview.setAdapter(adapter);

    }
    public void adaptador(){
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, consulta);
    }

}
