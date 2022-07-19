package com.example.hust;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btnADDStudent;
    ListView listView;
    ArrayList<Subject>arrayList;
    ImageView imageSearch;
    Button Study;
    public static Database database;
    SubjectAdapter subjectAdapter;
    ImageView imageSua;
    EditText editText;
    private int pos=-1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhxa();
        arrayList = new ArrayList<>();
        subjectAdapter =new SubjectAdapter(this, R.layout.dong, arrayList);
        listView.setAdapter(subjectAdapter);
        //tạo database
        database = new Database(this, "Management1.sqlite", null, 1);
        database.QuerryData("CREATE TABLE IF NOT EXISTS Subject1(Id INTEGER PRIMARY KEY AUTOINCREMENT, Ten VARCHAR(150), Mota VARCHAR(300))");

        getData();
        btnADDStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(i);
            }
        });
        imageSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editText.getText().toString().trim();
                if (TextUtils.isEmpty(name))
                {
                    Toast.makeText(MainActivity.this, "Bạn chưa nhập gì !", Toast.LENGTH_SHORT).show();
                    return;
                }
                //đây là CURSOR chỉ chọn dữ liệu search
                Cursor cursor = database.getData("SELECT*FROM Subject1 WHERE Ten LIKE '%"+name+"%' OR Mota LIKE '%"+name+"%' ");
                arrayList.clear();
                while (cursor.moveToNext())
                {
                    arrayList.add(new Subject(cursor.getInt(0),
                            cursor.getString(1),
                            cursor.getString(2)));


                }
                subjectAdapter.notifyDataSetChanged();

            }
        });
        //tạo sự kiện khi click vào các phần tử trong listview
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, arrayList.get(i).getTen(), Toast.LENGTH_SHORT).show();
                editText.setText((arrayList.get(i).getTen()));
                pos = i;
            }
        });
        Study.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, MainActivity3.class);
                startActivity(i);
            }
        });

    }
    public void dialogUpdate(int id, String ten, String mota)
    {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialogsua);
        EditText edtSuaTen = (EditText)dialog.findViewById(R.id.edtSuaTen);
        EditText edtSuaMota = (EditText) dialog.findViewById(R.id.edtSuaMota);

        Button btnSua = (Button) dialog.findViewById(R.id.btnSua);
        Button btnHuySua = (Button) dialog.findViewById(R.id.btnHuySua);
        //truyền vào những cái hiện có
        edtSuaTen.setText(ten);
        edtSuaMota.setText(mota);


        dialog.show();
        btnHuySua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tenmoi = edtSuaTen.getText().toString().trim();
                String mota = edtSuaMota.getText().toString().trim();
                if(TextUtils.isEmpty(tenmoi)||TextUtils.isEmpty(mota))
                {
                    Toast.makeText(MainActivity.this, "Chưa có nội dung", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    return;
                }

                database.QuerryData("UPDATE Subject1 SET Ten = '"+tenmoi+"', Mota = '"+mota+"' " +
                        "WHERE Id = '"+id+"' ");
                dialog.dismiss();
                getData();
            }
        });
    }
    //hàm xóa
    //khi mà set sự kiện click ở SubjectAdapter
    //thì hàm dialogDelete sẽ được gọi
    public void dialogDelete(String ten, int id)
    {

        AlertDialog.Builder builder =new AlertDialog.Builder(this);
        builder.setMessage("Bạn có đồng ý xóa "+ten+" ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                database.QuerryData("DELETE FROM Subject1 WHERE Id = '"+id+"' ");
                getData();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.show();
    }
    public void anhxa()
    {
        btnADDStudent = (Button) findViewById(R.id.btn);
        listView = (ListView) findViewById(R.id.lv);
        Study = (Button) findViewById(R.id.btnStudy);
        imageSearch = (ImageView) findViewById(R.id.search);
        editText = (EditText) findViewById(R.id.edt);
    }
    private void getData()
    {  //đây là cursor chọn hết dữ liệu
        Cursor cursor = database.getData("SELECT * FROM Subject1");
        arrayList.clear();
        while (cursor.moveToNext())
        {
            arrayList.add(new Subject(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2)

            ));
        }
        subjectAdapter.notifyDataSetChanged();
    }


}