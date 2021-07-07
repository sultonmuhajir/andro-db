package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CreateActivity extends AppCompatActivity {
    protected Cursor cursor;
    Database database;
    EditText nama, nim, prodi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        database = new Database(this);
        nama = findViewById(R.id.nama);
        nim = findViewById(R.id.nim);
        prodi = findViewById(R.id.prodi);
    }

    public void add(View view) {
        String valNama = nama.getText().toString();
        String valNim = nim.getText().toString();
        String valProdi = prodi.getText().toString();
        if (valNama.equals("") || valNim.equals("") || valProdi.equals("")) {
            Toast.makeText(this, "Data Gagal Ditambahkan", Toast.LENGTH_SHORT).show();
        } else {
            SQLiteDatabase db = database.getWritableDatabase();
            db.execSQL("insert into mahasiswa(nama, nim, prodi) values('"+
                    nama.getText().toString()+"','" +
                    nim.getText().toString()+"','" +
                    prodi.getText().toString()+"')");
            Toast.makeText(this, "Data Berhasil Ditambahkan", Toast.LENGTH_SHORT).show();
            MainActivity.ma.RefreshList();
            finish();
        }
    }
}