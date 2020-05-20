package com.example.usuario.proyectofinal;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ConsultaUsuario extends AppCompatActivity {
Button Regresar,Menu;
TextView Id_usuarioC,NombreC,apellidop,ApellidoMC,UsuarioC,SaldoC;
    ConexionSQLiteHelper helper = new ConexionSQLiteHelper(this,"BDProject",null,1);
String cadena="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_usuario);
        Regresar=findViewById(R.id.Regresar);
        Regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle myBundle=new Bundle();
                myBundle.putString("Usuario",cadena);
                Intent intent=new Intent(ConsultaUsuario.this,ProcesoActivity.class);
                intent.putExtras(myBundle);
                finish();
                startActivity(intent);
            }
        });
        Menu=findViewById(R.id.Menu);
        Menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ConsultaUsuario.this,MainActivity.class);
                finish();
                startActivity(intent);
            }
        });
        Id_usuarioC=findViewById(R.id.Id_usuarioC);
        NombreC=findViewById(R.id.NombreC);
        apellidop=findViewById(R.id.ApellidoPC);
        ApellidoMC=findViewById(R.id.ApellidoMC);
        UsuarioC=findViewById(R.id.UsuarioC);
        SaldoC=findViewById(R.id.SaldoC);
        Bundle bundle=this.getIntent().getExtras();
        cadena=bundle.getString("Usuario");
        SQLiteDatabase bd = helper.getWritableDatabase();
        Cursor fila = bd.rawQuery("select Id_usuario,Nombre_Usuario,Apellido_P, Apellido_M,User_Usuario,saldo from usuario where User_usuario='"+cadena+"'", null);
        if(fila.moveToFirst()){
            Id_usuarioC.setText("Id: "+fila.getString(0));
                NombreC.setText("Nombre: "+fila.getString(1));
            apellidop.setText(  "Apellido Paterno: "+fila.getString(2));
            ApellidoMC.setText( "Apellido Materno: "+fila.getString(3));
            UsuarioC.setText(   "Usuario: "+fila.getString(4));
            SaldoC.setText(     "Saldo: $"+fila.getString(5));
        }
        bd.close();

    }
}