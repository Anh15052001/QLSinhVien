package com.example.hust;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class MainActivity2 extends AppCompatActivity {
    EditText edtMSSV,edtHoten;

    Button btnAdd, btnCancel;
    EditText edtMail, edtDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Mapping();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //lấy tên, mota
                String mssv = edtMSSV.getText().toString().trim();
                String hoten = edtHoten.getText().toString().trim();
                if(TextUtils.isEmpty(mssv)||TextUtils.isEmpty(hoten))
                {
                    Toast.makeText(MainActivity2.this, "Bạn nhập thiếu", Toast.LENGTH_LONG).show();
                    return;
                }

                MainActivity.database.INSERT_STUDY(
                        edtMSSV.getText().toString().trim(),
                        edtHoten.getText().toString().trim()



                );
                Toast.makeText(MainActivity2.this, "Đã thêm", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity2.this, MainActivity.class));


            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity2.this, MainActivity.class);
                startActivity(i);

            }
        });

    }


    public void Mapping(){
        edtMSSV = (EditText) findViewById(R.id.edtMSSV);
        edtHoten = (EditText) findViewById(R.id.edtHoten);
        edtMail = (EditText)findViewById(R.id.edtEmail);
        edtDate = (EditText)findViewById(R.id.edtDate);
        btnAdd = (Button) findViewById(R.id.btnThem);
        btnCancel = (Button) findViewById(R.id.btnHuy);
    }
}