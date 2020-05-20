package com.example.usuario.proyectofinal;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button EntrarButton;
    EditText Usuario,Password;
    ConexionSQLiteHelper helper = new ConexionSQLiteHelper(this,"BDProject",null,1);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EntrarButton=findViewById(R.id.EntrarButton);
        EntrarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor= helper.ConsultarPassword(String.valueOf(Usuario.getText()),String.valueOf(Password.getText()));
                Cursor cursor2=helper.VerificarAdmin(String.valueOf(Usuario.getText()),String.valueOf(Password.getText()));
                if (cursor.getCount()>0){
                    Bundle myBundle=new Bundle();
                    myBundle.putString("Usuario",Usuario.getText().toString());
                    Intent intent=new Intent(MainActivity.this,ProcesoActivity  .class);
                    intent.putExtras(myBundle);
                    finish();
                    startActivity(intent);
                }
                else if(cursor2.getCount()>0){
                    Bundle myBundle=new Bundle();
                    myBundle.putString("Usuario",Usuario.getText().toString());
                    Intent intent=new Intent(MainActivity.this,Administrador.class);
                    intent.putExtras(myBundle);
                    finish();
                    startActivity(intent);
                }
                else {
                    Toast.makeText(MainActivity.this, "Usuario y/o contraseña incorrectos", Toast.LENGTH_LONG).show();
                }

            }
        });
        Usuario=findViewById(R.id.CajaUsuario);
        Password=findViewById(R.id.Password);

    }
}
