package com.example.usuario.proyectofinal;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class RegistrarActivity extends Activity {
    Button RegistrarRegistrar,MenuRegistrar,Regresar;
    EditText Nombre,Apellido_P,Apellido_M,User,ContraseñaRegistrar;
    String cadena="";
    ConexionSQLiteHelper helper = new ConexionSQLiteHelper(this,"BDProject",null,1);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);
        Bundle bundle=this.getIntent().getExtras();
        cadena=bundle.getString("Usuario");
        RegistrarRegistrar=findViewById(R.id.RegistrarRegistrar);
        MenuRegistrar=findViewById(R.id.MenuRegistrar);
        MenuRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(RegistrarActivity.this,MainActivity.class);
                finish();
                startActivity(intent);
            }
        });
        Regresar=findViewById(R.id.Regresar);
        Regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle myBundle=new Bundle();
                myBundle.putString("Usuario",cadena);
                Intent intent=new Intent(RegistrarActivity.this,Administrador.class);
                intent.putExtras(myBundle);
                finish();
                startActivity(intent);
            }
        });
        Nombre=findViewById(R.id.NombreRegistrar);
        Apellido_P=findViewById(R.id.Apellido_P);
        Apellido_M=findViewById(R.id.Apellido_M);
        User=findViewById(R.id.UserRegistrar);
        ContraseñaRegistrar=findViewById(R.id.ContraseñaRegistrar);
        RegistrarRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Nombre.getText().length()<3)
                    Toast.makeText(RegistrarActivity.this, " No Inserte Campos Menores a 3 caracteres",Toast.LENGTH_LONG).show();
                else if(Apellido_P.getText().length()<3)
                    Toast.makeText(RegistrarActivity.this, "No Inserte Campos Menores a 3 caracteres",Toast.LENGTH_LONG).show();
                else if(Apellido_M.getText().length()<3 )
                    Toast.makeText(RegistrarActivity.this, "No Inserte Campos Menores a 3 caracteres",Toast.LENGTH_LONG).show();
                else if(User.getText().length()<3)
                    Toast.makeText(RegistrarActivity.this, "No Inserte Campos Menores a 3 caracteres",Toast.LENGTH_LONG).show();
                else if(ContraseñaRegistrar.getText().length()<3)
                    Toast.makeText(RegistrarActivity.this, "No Inserte Campos Menores a 3 caracteres",Toast.LENGTH_LONG).show();
                else {
                    helper.abrir();
                   helper.InsertUsuario(String.valueOf(Nombre.getText()), String.valueOf(Apellido_P.getText()), String.valueOf(Apellido_M.getText()),
                   String.valueOf(User.getText()), String.valueOf(ContraseñaRegistrar.getText()), 100.00);
                    Toast.makeText(RegistrarActivity.this, " Registro exitoso", Toast.LENGTH_LONG).show();
                    Nombre.setText("");
                    Apellido_P.setText("");
                    Apellido_M.setText("");
                    User.setText("");
                    ContraseñaRegistrar.setText("");
                }
            }
        });
    }
}
