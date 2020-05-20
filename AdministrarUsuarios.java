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

public class AdministrarUsuarios extends AppCompatActivity {
    ListView output;
    Button ConsultaUsuarios,EliminarUsuarios,gotomenu,BuscarUsuario,CambiarContraseña,Regresar,SumarSaldo;
    ConexionSQLiteHelper helper = new ConexionSQLiteHelper(this,"BDProject",null,1);
        ArrayList<String> consulta = new ArrayList<>();
    ArrayAdapter<String> adapter;
    EditText CajaUsuario,Pass,AgregarSaldo;
    String cadena="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrar_usuarios);
        Bundle bundle=this.getIntent().getExtras();
        cadena=bundle.getString("Usuario");
        AgregarSaldo=findViewById(R.id.AgregarSaldo);
        SumarSaldo=findViewById(R.id.SumarSaldo);
        SumarSaldo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(adapter!=null) {
                    consulta.clear();
                    adapter.notifyDataSetChanged();
                    output.setAdapter(adapter);
                }
                Double resultado = 0.00, sald = 0.00, recarga =Double.valueOf(AgregarSaldo.getText().toString());
                SQLiteDatabase bd = helper.getReadableDatabase();
                Cursor fila100 = bd.rawQuery("select saldo  from usuario where User_usuario='" + CajaUsuario.getText().toString() + "'", null);
                if (fila100.moveToFirst()) {
                    sald = (Double) Double.valueOf(fila100.getString(0).toString());
                } else
                    Toast.makeText(AdministrarUsuarios.this, "No se encontró el saldo", Toast.LENGTH_SHORT).show();
                bd.close();
                fila100.close();
                resultado = sald +recarga;
                bd = helper.getWritableDatabase();
                bd.execSQL("UPDATE usuario set saldo = " + resultado + " where User_usuario = '" + CajaUsuario.getText().toString() + "' ;");
                bd.close();
                Toast.makeText(AdministrarUsuarios.this, "Se recargó el saldo con éxito", Toast.LENGTH_SHORT).show();
            }
        });
        Regresar=findViewById(R.id.Regresar);
        Regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle myBundle=new Bundle();
                myBundle.putString("Usuario",cadena);
                Intent intent=new Intent(AdministrarUsuarios.this,Administrador.class);
                intent.putExtras(myBundle);
                finish();
                startActivity(intent);
            }
        });
        CambiarContraseña=findViewById(R.id.CambiarContraseña);
        Pass=findViewById(R.id.Pass);
        CajaUsuario=findViewById(R.id.CajaUsuario);
        gotomenu=findViewById(R.id.gotomenu);
        output=findViewById(R.id.output);
        ConsultaUsuarios=findViewById(R.id.ConsultaUsuarios);
        EliminarUsuarios=findViewById(R.id.EliminarUsuarios);
        BuscarUsuario=findViewById(R.id.BuscarUsuario);

        Pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        CambiarContraseña.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CajaUsuario.setEnabled(true);
                SQLiteDatabase bd = helper.getWritableDatabase();
                bd.execSQL("UPDATE usuario set Contraseña_Usuario='"+Pass.getText().toString()+"'where User_usuario= '"+CajaUsuario.getText().toString()+"';");
                bd.close();
                Toast.makeText(AdministrarUsuarios.this, "Contraseña cambiada con éxito",Toast.LENGTH_SHORT).show();

            }
        });

        BuscarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(adapter!=null) {
                    consulta.clear();
                    adapter.notifyDataSetChanged();
                    output.setAdapter(adapter);
                }
                String cadena=String.valueOf(CajaUsuario.getText());
                   SQLiteDatabase bd = helper.getWritableDatabase();
             Cursor fila = bd.rawQuery("select Id_usuario,  Nombre_Usuario , Apellido_P , Apellido_M ,User_usuario, saldo   from usuario where User_usuario='"+cadena+"'", null);
                if(fila.moveToFirst()){
                    CajaUsuario.setEnabled(false);
                    do{
                        consulta.add("Id: "+fila.getString(0) + "\nNombre: " + fila.getString(1)+"\nApellido Paterno: "+
                                fila.getString(2)
                                +"\nApellido Materno: "+fila.getString(3)+"\nUsuario: "+fila.getString(4)+ "\nSaldo: " +fila.getString(5));
                    }while(fila.moveToNext());
                }
                else
                    Toast.makeText(AdministrarUsuarios.this, "No existe este usuario",Toast.LENGTH_SHORT).show();
                bd.close();
                adaptador();
                output.setAdapter(adapter);




            }
        });

        gotomenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdministrarUsuarios.this,MainActivity.class);
                finish();
                startActivity(intent);
            }
        });

        ConsultaUsuarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(adapter!=null){
                    consulta.clear();
                    adapter.notifyDataSetChanged();
                    output.setAdapter(adapter);
                }
                CajaUsuario.setEnabled(true);
                output.setAdapter(null);
                SQLiteDatabase bd = helper.getWritableDatabase();
                Cursor fila = bd.rawQuery("select Id_usuario,  Nombre_Usuario , Apellido_P , Apellido_M ,User_usuario, saldo   from usuario", null);
                if(fila.moveToFirst()){
                    do{
                        consulta.add("Id: "+fila.getString(0) + "\nNombre: " + fila.getString(1)+"\nApellido Paterno: "+
                                fila.getString(2)
                                +"\nApellido Materno: "+fila.getString(3)+"\nUsuario: "+fila.getString(4)+ "\nSaldo: " +
                                fila.getString(5)+"\n--------------------------------------------");

                    }while(fila.moveToNext());
                }
                else
                Toast.makeText(AdministrarUsuarios.this, "Tabla vacía",Toast.LENGTH_SHORT).show();

                bd.close();
                adaptador();

                output.setAdapter(adapter);
            }
        });

        EliminarUsuarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(adapter!=null){
                    consulta.clear();
                    adapter.notifyDataSetChanged();
                    output.setAdapter(adapter);
                }
                CajaUsuario.setEnabled(true);
                SQLiteDatabase bd = helper.getWritableDatabase();
                bd.execSQL("DELETE FROM usuario where User_usuario= '"+CajaUsuario.getText().toString()+"';");
                bd.close();
                Toast.makeText(AdministrarUsuarios.this, "Usuario eliminado con éxito",Toast.LENGTH_SHORT).show();
            }
        });

    }
    public void adaptador(){
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, consulta);
    }


}
