package com.example.usuario.proyectofinal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class ConexionSQLiteHelper extends SQLiteOpenHelper {
    public ConexionSQLiteHelper( Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
       db.execSQL("create table if not exists marca(Id_Marca integer not null PRIMARY KEY autoincrement, Nombre_Marca text);");
        String CrearTablaUsuarios="create table if not exists usuario(Id_Usuario  integer not null  PRIMARY KEY autoincrement , " +
                "Nombre_Usuario text, Apellido_P text, Apellido_M text," +
                "User_Usuario text,Contraseña_Usuario text, saldo real);";
        String CrearTablaAdministrador="create table if not exists administrador(Id_Administrador integer not null PRIMARY KEY autoincrement,User_Admin text, " +
                "Contraseña_Admin text,Nombre_Admin text);";
        String CrearTablaProducto="create table if not exists producto(Id_Producto integer not null PRIMARY KEY, " +
                "Nombre_Producto text,Precio real, ruta_imagen text, id_marca text, foreign key (id_marca) references marca (id_marca) ON DELETE CASCADE);";
        db.execSQL("create table if not exists compra(Id_compra integer not null primary key autoincrement, Fecha_y_hora text, Id_Usuario integer, " +
        "Id_Producto integer, FOREIGN KEY (Id_usuario) REFERENCES" +
        " usuario(Id_Usuario) ON DELETE CASCADE, FOREIGN KEY (Id_Producto) REFERENCES producto(Id_Producto) ON DELETE CASCADE );");
        db.execSQL("create table if not exists informe(Id_informe integer not null primary key autoincrement,Descripcion text, Id_producto integer,Id_Administrador integer, " +
               "FOREIGN KEY (Id_Producto) REFERENCES producto(Id_Producto) ON DELETE CASCADE, FOREIGN KEY (Id_Administrador) REFERENCES administrador(Id_Administrador) ON DELETE CASCADE);");
        db.execSQL(CrearTablaUsuarios);
        db.execSQL(CrearTablaAdministrador);
        db.execSQL(CrearTablaProducto);
        ContentValues values=new ContentValues();
        values.put("Nombre_Marca","Coca");
        db.insert("marca", null,values);
        ContentValues values10=new ContentValues();
        values10.put("Nombre_Marca","Pepsi");
        db.insert("marca", null,values10);
        ContentValues values20=new ContentValues();
        values20.put("Nombre_Marca","Jumex");
        db.insert("marca", null,values20);
        ContentValues valores= new ContentValues();
        valores.put("User_Admin","admin");
        valores.put("Contraseña_Admin","admin");
        valores.put("Nombre_Admin","admin");
        db.insert("administrador", null,valores);
        ContentValues valores2=new ContentValues();
        valores2.put("Nombre_Producto","Coca Cola");
        valores2.put("Precio",20.00);
        valores2.put("ruta_imagen","coca");
        valores2.put("id_marca",1);
        db.insert("producto", null,valores2);
        ContentValues valores3=new ContentValues();
        valores3.put("Nombre_Producto","Agua");
        valores3.put("Precio",15.00);
        valores3.put("ruta_imagen","agua");
        valores3.put("id_marca",1);
        db.insert("producto", null,valores3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAntigua, int versionNueva) {
        db.execSQL("drop table if exists usuario");
        db.execSQL("drop table if exists administrador");
        db.execSQL("drop table if exists producto");
        db.execSQL("drop table if exists compra");
        db.execSQL("drop table if exists marca");
        db.execSQL("create table if not exists marca(Id_Marca integer not null PRIMARY KEY autoincrement, Nombre_Marca text);");
        String CrearTablaUsuarios="create table if not exists usuario(Id_Usuario  integer not null  PRIMARY KEY autoincrement , " +
                "Nombre_Usuario text, Apellido_P text, Apellido_M text," +
                "User_Usuario text,Contraseña_Usuario text, saldo real);";
        String CrearTablaAdministrador="create table if not exists administrador(Id_Administrador integer not null PRIMARY KEY autoincrement,User_Admin text, " +
                "Contraseña_Admin text,Nombre_Admin text);";
        String CrearTablaProducto="create table if not exists producto(Id_Producto integer not null PRIMARY KEY, Nombre_Producto text," +
                "Precio real, ruta_imagen text, id_marca text, foreign key (id_marca) references marca (id_marca) ON DELETE CASCADE);";
        db.execSQL("create table if not exists compra(Id_compra integer not null primary key autoincrement, Fecha_y_hora text, Id_Usuario integer, " +
                "Id_Producto integer, FOREIGN KEY (Id_usuario) REFERENCES" +
                " usuario(Id_Usuario) ON DELETE CASCADE, FOREIGN KEY (Id_Producto) REFERENCES producto(Id_Producto) ON DELETE CASCADE );");
        ContentValues values=new ContentValues();
        values.put("Nombre_Marca","Coca");
        db.insert("marca", null,values);
        ContentValues values10=new ContentValues();
        values10.put("Nombre_Marca","Pepsi");
        db.insert("marca", null,values10);
        ContentValues values20=new ContentValues();
        values20.put("Nombre_Marca","Jumex");
        db.insert("marca", null,values20);
        ContentValues valores= new ContentValues();
        valores.put("User_Admin","admin");
        valores.put("Contraseña_Admin","admin");
        valores.put("Nombre_Admin","admin");
        db.insert("administrador", null,valores);
        ContentValues valores2=new ContentValues();
        valores2.put("Nombre_Producto","Coca Cola");
        valores2.put("Precio",20.00);
        valores2.put("ruta_imagen","coca");
        valores2.put("id_marca",1);
        db.insert("producto", null,valores2);
        ContentValues valores3=new ContentValues();
        valores3.put("Nombre_Producto","Agua");
        valores3.put("Precio",15.00);
        valores3.put("ruta_imagen","agua");
        valores3.put("id_marca",1);
        db.insert("producto", null,valores3);
        db.execSQL(CrearTablaUsuarios);
        db.execSQL(CrearTablaAdministrador);
        db.execSQL(CrearTablaProducto);
    }
    public void abrir(){
        this.getWritableDatabase();
    }
    public void cerrar(){
        this.close();
    }
    public void InsertUsuario(String nombre,String apellido_p, String apellido_m,String user, String password, Double saldo){
        ContentValues valores= new ContentValues();
        valores.put("Nombre_Usuario",nombre);
        valores.put("Apellido_P",apellido_p);
        valores.put("Apellido_M",apellido_m);
        valores.put("User_Usuario",user);
        valores.put("Contraseña_Usuario",password);
        valores.put("saldo",saldo);
        this.getWritableDatabase().insert("usuario", null,valores);
    };
    public void InsertAdmin(String user,String password, String nombre){
        ContentValues valores= new ContentValues();
        valores.put("User_Admin",user);
        valores.put("Contraseña_Admin",password);
        valores.put("Nombre_Admin",nombre);
        this.getWritableDatabase().insert("administrador", null,valores);

    };
    public void InsertProducto(String nombre,float precio, String img,Integer Id,Integer Marca){
        ContentValues valores= new ContentValues();
        valores.put("Id_Producto",Id);
        valores.put("Nombre_Producto",nombre);
        valores.put("Precio",precio);
        valores.put("ruta_imagen",img);
        valores.put("id_marca",Marca);
        this.getWritableDatabase().insert("producto", null,valores);
    }
    public void InsertCompra(String FechayHora, Integer IdUser, Integer IdProduct){
        ContentValues valores= new ContentValues();
        valores.put("Fecha_y_hora",FechayHora);
        valores.put("Id_Usuario",IdUser);
        valores.put("Id_Producto",IdProduct);
        this.getWritableDatabase().insert("compra", null,valores);
    }
    public void InsertInforme(String Desc,Integer IdAdm, Integer IdProduct){
        ContentValues valores= new ContentValues();
        valores.put("Descripcion",Desc);
        valores.put("Id_Administrador",IdAdm);
        valores.put("Id_Producto",IdProduct);
        this.getWritableDatabase().insert("informe", null,valores);
    }
    public Cursor ConsultarPassword(String user,String pass)throws SQLException {
    Cursor cursor = null;
    cursor=this.getReadableDatabase().query("usuario",new String[]{"Id_Usuario","Nombre_Usuario","Apellido_P","Apellido_M","User_Usuario","Contraseña_Usuario","saldo"},
            "User_Usuario like '"+user+"' and Contraseña_Usuario like '"+pass+"'",null,null,null,null);
    return cursor;
    }
    public Cursor VerificarAdmin(String user, String pass){
        Cursor cursor = null;
        cursor=this.getReadableDatabase().query("administrador",new String[]{"Id_Administrador","User_Admin","Contraseña_Admin","Nombre_Admin"},
                "User_Admin like '"+user+"' and Contraseña_Admin like '"+pass+"'",null,null,null,null);
        return cursor;
    }
    public Cursor ConsultarProducto(){
        Cursor cursor=null;
        return cursor;
    }
    public void InicializarDb(){
        ContentValues values=new ContentValues();
        values.put("Nombre_Marca","Coca");
        this.getWritableDatabase().insert("marca", null,values);
        ContentValues values10=new ContentValues();
        values10.put("Nombre_Marca","Pepsi");
        this.getWritableDatabase().insert("marca", null,values10);
        ContentValues values20=new ContentValues();
        values20.put("Nombre_Marca","Jumex");
        this.getWritableDatabase().insert("marca", null,values20);
        ContentValues valores= new ContentValues();
        valores.put("User_Admin","admin");
        valores.put("Contraseña_Admin","admin");
        valores.put("Nombre_Admin","admin");
        this.getWritableDatabase().insert("administrador", null,valores);
        ContentValues valores2=new ContentValues();
        valores2.put("Nombre_Producto","Coca Cola");
        valores2.put("Precio",20.00);
        valores2.put("ruta_imagen","coca");
        valores2.put("id_marca",1);
        this.getWritableDatabase().insert("producto", null,valores2);
        ContentValues valores3=new ContentValues();
        valores3.put("Nombre_Producto","Agua");
        valores3.put("Precio",15.00);
        valores3.put("ruta_imagen","agua");
        valores3.put("id_marca",1);
        this.getWritableDatabase().insert("producto", null,valores3);

    }
}
