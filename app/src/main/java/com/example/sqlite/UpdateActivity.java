package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {
    protected Cursor cursor;
    Database database;
    EditText nama, nim, prodi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        database = new Database(this);
        nama = findViewById(R.id.nama);
        nim = findViewById(R.id.nim);
        prodi = findViewById(R.id.prodi);

        SQLiteDatabase db = database.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM mahasiswa WHERE nama='" +
                getIntent().getStringExtra("nama")+"'", null);
        cursor.moveToFirst();

        if (cursor.getCount() > 0) {
            cursor.moveToPosition(0);
            nama.setText(cursor.getString(0).toString());
            nim.setText(cursor.getString(1).toString());
            prodi.setText(cursor.getString(2).toString());
        }
    }

    @SuppressLint("ShowToast")
    public void edit(View view) {
        String valNama = nama.getText().toString();
        String valNim = nim.getText().toString();
        String valProdi = prodi.getText().toString();
        if (valNama.equals("") || valNim.equals("") || valProdi.equals("")) {
            Toast.makeText(this, "Data Gagal Diubah", Toast.LENGTH_SHORT).show();
        } else {
            SQLiteDatabase db = database.getWritableDatabase();
            db.execSQL("UPDATE mahasiswa SET nama = '" +
                    nama.getText().toString()+"', nim= '" +
                    nim.getText().toString()+"', prodi= '" +
                    prodi.getText().toString()+"' WHERE nama = '" +
                    getIntent().getStringExtra("nama")+"'");
            Toast.makeText(this, "Data Berhasil Diubah", Toast.LENGTH_SHORT).show();
            MainActivity.ma.RefreshList();
            finish();
        }
    }
}