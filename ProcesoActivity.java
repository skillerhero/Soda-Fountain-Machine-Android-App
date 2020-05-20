package com.example.usuario.proyectofinal;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.app.Activity;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;


public class ProcesoActivity extends Activity {
    private Handler handler =new Handler();

    TextView Seleccionar, NombreP1,NombreP2,PrecioP1,PrecioP2,Saldo;
    ImageView ImagenP1,ImagenP2;
    Button ServirButton,MenuPrincipal,Consultar_Info;
    String bandera="nada";
    String cadena="";
    int H=0;
    ConexionSQLiteHelper helper = new ConexionSQLiteHelper(this,"BDProject",null,1);



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proceso);
        Seleccionar=findViewById(R.id.Seleccionar);
        NombreP1=findViewById(R.id.NombreP1);
        NombreP2=findViewById(R.id.NombreP2);
        PrecioP1=findViewById(R.id.PrecioP1);
        PrecioP2=findViewById(R.id.PrecioP2);
        Saldo=findViewById(R.id.SaldoC);
        ImagenP1=findViewById(R.id.ImagenP1);
        ImagenP1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bandera.equals("busy")){
                    Toast.makeText(ProcesoActivity.this, "Espere a que termine de servir.",Toast.LENGTH_SHORT).show();
                }
                else{
                    bandera=NombreP1.getText().toString();
                    Toast.makeText(ProcesoActivity.this, "Has seleccionado "+NombreP1.getText().toString()+".",Toast.LENGTH_SHORT).show();
                }
            }
        });
        ImagenP2=findViewById(R.id.ImagenP2);
        ImagenP2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bandera.equals("busy")){
                    Toast.makeText(ProcesoActivity.this, "Espere a que termine de servir.",Toast.LENGTH_SHORT).show();
                }
                else {
                    bandera = NombreP2.getText().toString();
                    Toast.makeText(ProcesoActivity.this, "Has seleccionado "+NombreP2.getText().toString()+".", Toast.LENGTH_SHORT).show();
                }
            }
        });
        ServirButton=findViewById(R.id.ServirButton);
        ServirButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if (bandera.equals("busy")) {
                        Toast.makeText(ProcesoActivity.this, "Espere a que termine de servir.", Toast.LENGTH_SHORT).show();
                    } else {
                        if (bandera.equals("nada"))
                            Toast.makeText(ProcesoActivity.this, "Seleccione una imagen", Toast.LENGTH_SHORT).show();




                            /*
                             * Servir producto 1
                             *
                             *
                             * */
                        else if (bandera.equals(NombreP1.getText().toString())) {

                            Double resultado = 0.00, sald = 0.00, price = 0.00;
                            SQLiteDatabase bd = helper.getReadableDatabase();
                            Cursor fila100 = bd.rawQuery("select saldo  from usuario where User_usuario='" + cadena + "'", null);
                            if (fila100.moveToFirst()) {
                                sald = (Double) Double.valueOf(fila100.getString(0).toString());
                            } else
                                Toast.makeText(ProcesoActivity.this, "No se encontró el saldo", Toast.LENGTH_SHORT).show();
                            bd.close();
                            fila100.close();
                            SQLiteDatabase bd1 = helper.getReadableDatabase();
                            Cursor fila200 = bd1.rawQuery("select Precio from producto where Id_Producto=1", null);
                            if (fila200.moveToFirst()) {
                                price = (Double) Double.valueOf(fila200.getString(0).toString());
                            } else
                                Toast.makeText(ProcesoActivity.this, "No se encontró el precio", Toast.LENGTH_SHORT).show();
                            bd1.close();
                            fila200.close();
                            resultado = sald - price;
                            if(resultado>=0) {
                                bandera = "busy";
                                Toast.makeText(ProcesoActivity.this, "Sirviendo " + NombreP1.getText().toString() + ".", Toast.LENGTH_SHORT).show();
                                handler.postDelayed(Esperar, 10000);
                                bd = helper.getWritableDatabase();
                                bd.execSQL("UPDATE usuario set saldo = " + resultado + " where User_usuario = '" + cadena + "' ;");
                                bd.close();
                                SQLiteDatabase bd3;
                                bd3 = helper.getWritableDatabase();
                                Cursor fila = bd3.rawQuery("select saldo  from usuario where User_usuario='" + cadena + "'", null);
                                if (fila.moveToFirst()) {
                                    Saldo.setText("Saldo: $" + fila.getString(0));
                                }
                                bd3.close();
                                bd = helper.getReadableDatabase();
                                Cursor filaH = bd.rawQuery("select Id_Usuario  from usuario where User_usuario='" + cadena + "'", null);
                                if (filaH.moveToFirst()) {
                                   H = (int) Integer.parseInt(filaH.getString(0).toString());
                                }
                                String TiempoActual = DateFormat.getDateTimeInstance().format(new Date());
                                helper.InsertCompra(TiempoActual, H,1);
                                bd.close();
                            }
                            else
                                Toast.makeText(ProcesoActivity.this, "Saldo insuficiente", Toast.LENGTH_SHORT).show();
                            /*
                             *
                             *
                             * Servir producto 2
                             *
                             *
                             *
                             * */
                        } else if (bandera.equals(NombreP2.getText().toString())) {
                            Double resultado = 0.00, sald = 0.00, price = 0.00;
                            SQLiteDatabase bd = helper.getReadableDatabase();
                            Cursor fila100 = bd.rawQuery("select saldo  from usuario where User_usuario='" + cadena + "'", null);
                            if (fila100.moveToFirst()) {
                                sald = (Double) Double.valueOf(fila100.getString(0).toString());
                            } else
                                Toast.makeText(ProcesoActivity.this, "No se encontró el saldo", Toast.LENGTH_SHORT).show();
                            bd.close();
                            fila100.close();
                            SQLiteDatabase bd1 = helper.getReadableDatabase();
                            Cursor fila200 = bd1.rawQuery("select Precio from producto where Id_Producto=2", null);
                            if (fila200.moveToFirst()) {
                                price = (Double) Double.valueOf(fila200.getString(0).toString());
                            } else
                                Toast.makeText(ProcesoActivity.this, "No se encontró el precio", Toast.LENGTH_SHORT).show();
                            bd1.close();
                            fila200.close();
                            resultado = sald - price;

                            if(resultado>=0) {
                                bandera = "busy";
                                Toast.makeText(ProcesoActivity.this, "Sirviendo " + NombreP2.getText().toString() + ".", Toast.LENGTH_SHORT).show();
                                handler.postDelayed(Esperar, 10000);
                                bd = helper.getWritableDatabase();
                                bd.execSQL("UPDATE usuario set saldo = " + resultado + " where User_usuario = '" + cadena + "' ;");
                                bd.close();
                                SQLiteDatabase bd3;
                                bd3 = helper.getWritableDatabase();
                                Cursor fila = bd3.rawQuery("select saldo  from usuario where User_usuario='" + cadena + "'", null);
                                if (fila.moveToFirst()) {
                                    Saldo.setText("Saldo: $" + fila.getString(0));
                                }
                                bd3.close();
                                bd = helper.getReadableDatabase();
                                Cursor filaH = bd.rawQuery("select Id_Usuario  from usuario where User_usuario='" + cadena + "'", null);
                                if (filaH.moveToFirst()) {
                                    H = (int) Integer.parseInt(filaH.getString(0).toString());
                                }
                                String TiempoActual = DateFormat.getDateTimeInstance().format(new Date());
                                helper.InsertCompra(TiempoActual, H,2);
                                bd.close();
                            }
                            else
                                Toast.makeText(ProcesoActivity.this, "Saldo insuficiente", Toast.LENGTH_SHORT).show();
                        }
                    }

            }
        });
        MenuPrincipal=findViewById(R.id.MenuPrincipal);
        MenuPrincipal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bandera.equals("busy")){
                    Toast.makeText(ProcesoActivity.this, "Espere a que termine de servir.",Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent = new Intent(ProcesoActivity.this, MainActivity.class);
                    finish();
                    finish();
                    startActivity(intent);
                }
            }
        });
        Consultar_Info=findViewById(R.id.Consultar_info);
        Consultar_Info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bandera.equals("busy")){
                    Toast.makeText(ProcesoActivity.this, "Espere a que termine de servir.",Toast.LENGTH_SHORT).show();
                }
                else {
                    Bundle myBundle=new Bundle();
                    myBundle.putString("Usuario",cadena);
                    Intent intent = new Intent(ProcesoActivity.this, ConsultaUsuario.class);
                    intent.putExtras(myBundle);
                    finish();
                    startActivity(intent);
                }
            }
        });
        Bundle bundle=this.getIntent().getExtras();
        cadena=bundle.getString("Usuario");
        SQLiteDatabase bd = helper.getReadableDatabase();
        Cursor fila = bd.rawQuery("select saldo  from usuario where User_usuario='"+cadena+"'", null);
        if(fila.moveToFirst()){
            Saldo.setText("Saldo: $"+fila.getString(0));
        }
        bd.close();
        bd = helper.getReadableDatabase();
        fila=bd.rawQuery("select ruta_imagen from producto where Id_Producto=1",null);
        if(fila.moveToFirst()){
            String chain=fila.getString(0);
            int id=getResources().getIdentifier("com.example.usuario.proyectofinal:"+"mipmap/"+chain,null,null);
            ImagenP1.setImageResource(id);
        }
        bd.close();
        bd = helper.getReadableDatabase();
        fila=bd.rawQuery("select ruta_imagen from producto where Id_Producto=2",null);
        if(fila.moveToFirst()){
            String chain=fila.getString(0);
            int id=getResources().getIdentifier("com.example.usuario.proyectofinal:"+"mipmap/"+chain,null,null);
            ImagenP2.setImageResource(id);
        }
        bd.close();


        bd = helper.getReadableDatabase();
        fila=bd.rawQuery("select Nombre_Producto from producto where Id_Producto=1",null);
        if(fila.moveToFirst()){
            String chain=fila.getString(0);
            NombreP1.setText(chain);
        }
        bd.close();

        bd = helper.getReadableDatabase();
        fila=bd.rawQuery("select Nombre_Producto from producto where Id_Producto=2",null);
        if(fila.moveToFirst()){
            String chain=fila.getString(0);
            NombreP2.setText(chain);
        }
        bd.close();

        bd = helper.getReadableDatabase();
        fila=bd.rawQuery("select Precio from producto where Id_Producto=1",null);
        if(fila.moveToFirst()){
            String chain=fila.getString(0);
            PrecioP1.setText("$"+chain);
        }
        bd.close();

        bd = helper.getReadableDatabase();
        fila=bd.rawQuery("select Precio from producto where Id_Producto=2",null);
        if(fila.moveToFirst()){
            String chain=fila.getString(0);
            PrecioP2.setText("$"+chain);
        }
        bd.close();
    }

    private Runnable Esperar = new Runnable() {
        @Override
        public void run() {
            bandera="nada";
            Intent intent = new Intent(ProcesoActivity.this, MainActivity.class);
            finish();
            finish();
            startActivity(intent);
        }
    };


    //------------------------------------------------------------------------------------


}
