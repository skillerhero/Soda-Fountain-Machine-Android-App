package com.example.usuario.proyectofinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AgregarAdministradores extends AppCompatActivity {
Button Agregar,Menu,Regresar;
EditText Nombre,CajaUsuario,Contraseña;
ConexionSQLiteHelper helper =new ConexionSQLiteHelper(this,"BDProject",null,1);
String cadena="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_administradores);
        Bundle bundle=this.getIntent().getExtras();
        Regresar=findViewById(R.id.Regresar);
        Regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle myBundle=new Bundle();
                myBundle.putString("Usuario",cadena);
                Intent intent=new Intent(AgregarAdministradores.this,Administrador.class);
                intent.putExtras(myBundle);
                finish();
                startActivity(intent);
            }
        });
        cadena=bundle.getString("Usuario");
        Agregar=findViewById(R.id.Agregar);
        Agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Nombre.getText().length()<3)
                    Toast.makeText(AgregarAdministradores.this, " No Inserte Campos Menores a 3 caracteres",Toast.LENGTH_LONG).show();
                else if(CajaUsuario.getText().length()<3)
                    Toast.makeText(AgregarAdministradores.this, "No Inserte Campos Menores a 3 caracteres",Toast.LENGTH_LONG).show();
                else if(Contraseña.getText().length()<3)
                    Toast.makeText(AgregarAdministradores.this, "No Inserte Campos Menores a 3 caracteres",Toast.LENGTH_LONG).show();
                else{
                helper. InsertAdmin(CajaUsuario.getText().toString(),Contraseña.getText().toString() , Nombre.getText().toString() );
                Nombre.setText("");
                CajaUsuario.setText("");
                Contraseña.setText("");
                    Toast.makeText(AgregarAdministradores.this, "Administrador añadido con éxito",Toast.LENGTH_SHORT).show();
                }
            }
        });
        Menu=findViewById(R.id.Menu);
        Menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AgregarAdministradores.this,MainActivity.class);
                finish();
                startActivity(intent);
            }
        });
        Nombre=findViewById(R.id.NombreC);
        CajaUsuario=findViewById(R.id.CajaUsuario);
        Contraseña=findViewById(R.id.Contraseña);

    }
}
