package com.example.usuario.proyectofinal;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class Administrador extends AppCompatActivity {
Button Administrar_Usuarios,Administrar_Productos,Menu,AgregarAdmin,EliminarTablas,Consultar_Compras,Informe,RegistrarButton;
    ConexionSQLiteHelper helper = new ConexionSQLiteHelper(this,"BDProject",null,1);
    String cadena="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrador);
        Bundle bundle=this.getIntent().getExtras();
        cadena=bundle.getString("Usuario");
        RegistrarButton= findViewById(R.id.RegistrarButton);
        RegistrarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle myBundle=new Bundle();
                myBundle.putString("Usuario",cadena);
                Intent intent=new Intent(Administrador.this,RegistrarActivity.class);
                intent.putExtras(myBundle);
                finish();
                startActivity(intent);
            }
        });
        Informe=findViewById(R.id.Informe);
        Informe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle myBundle=new Bundle();
                myBundle.putString("Usuario",cadena);
                Intent intent=new Intent(Administrador.this,informe.class);
                intent.putExtras(myBundle);
                finish();
                startActivity(intent);
            }
        });
        Consultar_Compras=findViewById(R.id.Consultar_Compras);
        Consultar_Compras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle myBundle=new Bundle();
                myBundle.putString("Usuario",cadena);
                Intent intent=new Intent(Administrador.this,Consultar_Compras.class);
                intent.putExtras(myBundle);
                finish();
                startActivity(intent);
            }
        });
        Administrar_Usuarios=findViewById(R.id.Administrar_Usuarios);
        Administrar_Usuarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle myBundle=new Bundle();
                myBundle.putString("Usuario",cadena);
                Intent intent=new Intent(Administrador.this,AdministrarUsuarios.class);
                intent.putExtras(myBundle);
                finish();
                startActivity(intent);
            }
        });
        Administrar_Productos=findViewById(R.id.Administrar_Productos);
        Administrar_Productos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle myBundle=new Bundle();
                myBundle.putString("Usuario",cadena);
                Intent intent=new Intent(Administrador.this,Productos.class);
                intent.putExtras(myBundle);
                finish();
                startActivity(intent);

            }
        });
        Menu=findViewById(R.id.Menu);
        Menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Administrador.this,MainActivity.class);
                finish();
                startActivity(intent);
            }
        });
        AgregarAdmin=findViewById(R.id.AgregarAdmin);
        AgregarAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle myBundle=new Bundle();
                myBundle.putString("Usuario",cadena);
                Intent intent=new Intent(Administrador.this,AgregarAdministradores.class);
                intent.putExtras(myBundle);
                finish();
                startActivity(intent);
            }
        });
        EliminarTablas=findViewById(R.id.EliminarTablas);
        EliminarTablas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = helper.getWritableDatabase();
                db.execSQL("Delete from compra;");
                db.execSQL("Delete from informe;");
                db.execSQL("Delete from usuario;");
                db.execSQL("Delete from administrador;");
                db.execSQL("Delete from producto;");
                db.close();
                helper.InicializarDb();
                Intent intent=new Intent(Administrador.this,MainActivity.class);
                finish();
                startActivity(intent);
                Toast.makeText(Administrador.this, "Todas las tablas han sido eliminadas.",Toast.LENGTH_LONG).show();

            }
        });

    }
}
