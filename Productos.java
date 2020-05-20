package com.example.usuario.proyectofinal;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Productos extends AppCompatActivity {
Button Consultar_Productos,Buscar_Producto,Eliminar_Producto,Agregar_Producto,menu,regresar,update;
EditText Nombre_Producto,Precio,Nombre_Imagen,IdProducto,Marca;
ListView output;
    ArrayList<String> consulta = new ArrayList<>();
    ArrayAdapter<String> adapter;
    ConexionSQLiteHelper helper = new ConexionSQLiteHelper(this,"BDProject",null,1);
    String user="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos);
        Bundle bundle=this.getIntent().getExtras();
        user=bundle.getString("Usuario");
        Marca=findViewById(R.id.Marca);
        update=findViewById(R.id.Update);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(adapter!=null) {
                    consulta.clear();
                    adapter.notifyDataSetChanged();
                    output.setAdapter(adapter);
                }
                String cadena=IdProducto.getText().toString();
                String aidi="";
                SQLiteDatabase bd = helper.getWritableDatabase();
                Cursor fila = bd.rawQuery("select   Nombre_Producto ,Precio,ruta_imagen,Id_marca  from producto where Id_Producto="+cadena, null);
                if(fila.moveToFirst()){
                        helper.InsertProducto(fila.getString(0),Float.valueOf(fila.getString(1)), fila.getString(2),null,Integer.parseInt(fila.getString(3)));
                }
                 fila = bd.rawQuery("select Id_Producto from producto", null);
                if(fila.moveToFirst()){
                    do{
                       aidi=fila.getString(0);
                    }while(fila.moveToNext());
                }
                bd.execSQL("UPDATE Compra set Id_Producto= "+aidi+" where Id_Producto= "+cadena+";");
                bd.execSQL("UPDATE producto set Nombre_Producto='"+Nombre_Producto.getText().toString()+"' where Id_Producto="+cadena+";");
                bd.execSQL("UPDATE producto set Precio="+Float.valueOf(Precio.getText().toString())+" where Id_Producto= "+cadena+";");
                bd.execSQL("UPDATE producto set ruta_imagen='"+Nombre_Imagen.getText().toString()+"' where Id_Producto="+cadena+";");
                bd.execSQL("UPDATE producto set Id_Marca='"+Integer.parseInt(Marca.getText().toString())+"' where Id_Producto= "+cadena+";");
                bd.close();
                adaptador();
                output.setAdapter(adapter);
                bd = helper.getReadableDatabase();
                 fila = bd.rawQuery("select   Id_Administrador from administrador where User_Admin='"+user+"'", null);
                if(fila.moveToFirst()){
                    helper.InsertInforme("Update",Integer.parseInt(fila.getString(0)),Integer.parseInt(IdProducto.getText().toString()));
                }
                bd.close();
                Toast.makeText(Productos.this, "Cambios realizados con éxito",Toast.LENGTH_SHORT).show();

        }
        });
        regresar=findViewById(R.id.Regresar);
        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle myBundle=new Bundle();
                myBundle.putString("Usuario",user);
                Intent intent=new Intent(Productos.this,Administrador.class);
                intent.putExtras(myBundle);
                finish();
                startActivity(intent);
            }
        });
        menu=findViewById(R.id.menu);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Productos.this,MainActivity.class);
                finish();
                startActivity(intent);
            }
        });
        IdProducto=findViewById(R.id.IdProducto);
        Consultar_Productos=findViewById(R.id.Consultar_Productos);
        Consultar_Productos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(adapter!=null){
                    consulta.clear();
                    adapter.notifyDataSetChanged();
                    output.setAdapter(adapter);
                }

                output.setAdapter(null);
                SQLiteDatabase bd = helper.getWritableDatabase();
                Cursor fila = bd.rawQuery("select Id_Producto,  Nombre_Producto ,Precio,ruta_imagen  from producto", null);
                if(fila.moveToFirst()){
                    do{
                        consulta.add("Id: "+fila.getString(0) + "\nNombre: " + fila.getString(1)+"\nPrecio: $"+fila.getString(2)
                                +"\nNombre Imagen: "+fila.getString(3)+"\n--------------------------------------------");
                    }while(fila.moveToNext());
                }
                else
                        Toast.makeText(Productos.this, "Tabla vacía",Toast.LENGTH_SHORT).show();

                bd.close();
                adaptador();

                output.setAdapter(adapter);
            }
        });
        Buscar_Producto=findViewById(R.id.Buscar_Producto);
        Buscar_Producto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(adapter!=null) {
                    consulta.clear();
                    adapter.notifyDataSetChanged();
                    output.setAdapter(adapter);
                }
                String cadena=IdProducto.getText().toString();
                SQLiteDatabase bd = helper.getWritableDatabase();
                Cursor fila = bd.rawQuery("select Id_Producto,  Nombre_Producto ,Precio,ruta_imagen  from producto where Id_Producto="+cadena, null);
                if(fila.moveToFirst()){
                    do{
                        consulta.add("Id: "+fila.getString(0) + "\nNombre: " + fila.getString(1)+"\nPrecio: $"+fila.getString(2)
                                +"\nNombre Imagen: "+fila.getString(3)+"\n--------------------------------------------");
                    }while(fila.moveToNext());
                }
                else
                    Toast.makeText(Productos.this, "No existe este producto",Toast.LENGTH_SHORT).show();
                bd.close();
                adaptador();
                output.setAdapter(adapter);


            }
        });
        Eliminar_Producto=findViewById(R.id.Eliminar_Producto);
        Eliminar_Producto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(adapter!=null){
                    consulta.clear();
                    adapter.notifyDataSetChanged();
                    output.setAdapter(adapter);
                }
                String cadena=IdProducto.getText().toString();
                SQLiteDatabase bd = helper.getWritableDatabase();
                bd.execSQL("DELETE FROM producto where Id_Producto="+cadena+"");
                bd.close();
                Toast.makeText(Productos.this, "Producto eliminado con éxito",Toast.LENGTH_SHORT).show();
                adaptador();
                output.setAdapter(adapter);
                bd = helper.getReadableDatabase();
                Cursor fila = bd.rawQuery("select   Id_Administrador from administrador where User_Admin='"+user+"'", null);
                if(fila.moveToFirst()){
                    helper.InsertInforme("Eliminó",Integer.parseInt(fila.getString(0)),Integer.parseInt(IdProducto.getText().toString()));
                }
                bd.close();


            }
        });
        Agregar_Producto=findViewById(R.id.Agregar_Producto);
        Agregar_Producto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Nombre_Producto.getText().length()<1)
                    Toast.makeText(Productos.this, " No Inserte Campos Menores a 1 caracter",Toast.LENGTH_SHORT).show();
                else if(IdProducto.getText().length()<1)
                    Toast.makeText(Productos.this, "No Inserte Campos Menores a 1 caracter",Toast.LENGTH_SHORT).show();
                else if(Precio.getText().length()<1)
                    Toast.makeText(Productos.this, "No Inserte Campos Menores a 1 caracter",Toast.LENGTH_SHORT).show();
                else if(Nombre_Imagen.getText().length()<1)
                    Toast.makeText(Productos.this, "No Inserte Campos Menores a 1 caracter",Toast.LENGTH_SHORT).show();
                else {
                    helper.InsertProducto(Nombre_Producto.getText().toString(),Float.valueOf(Precio.getText().toString()), Nombre_Imagen.getText().toString(),Integer.parseInt(IdProducto.getText().toString()),Integer.parseInt(Marca.getText().toString()));
                    Toast.makeText(Productos.this, "Producto agregado con éxito",Toast.LENGTH_SHORT).show();
                    SQLiteDatabase bd = helper.getReadableDatabase();
                    Cursor fila = bd.rawQuery("select   Id_Administrador from administrador where User_Admin='"+user+"'", null);
                    if(fila.moveToFirst()){
                        helper.InsertInforme("Agregó",Integer.parseInt(fila.getString(0)),Integer.parseInt(IdProducto.getText().toString()));
                    }
                    bd.close();
                }
            }
        });
        Nombre_Producto=findViewById(R.id.Nombre_Producto);
        Precio=findViewById(R.id.Precio);
        Nombre_Imagen=findViewById(R.id.Nombre_Imagen);
        output=findViewById(R.id.output);

    }
    public void adaptador(){
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, consulta);
    }
}
