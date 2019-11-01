package com.example.content_provider;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    static final String AUTHORITY = "com.example.sqltequantrong";
    static final String CONTENT_PATH = "bookdata";
    static final String URL = "content://"+AUTHORITY + "/" + CONTENT_PATH;
    static final Uri CONTENT_URI = Uri.parse(URL);
    EditText etnhapmaso,etnhaptieude,ettentacgia;
    Button btnselect,btnsave,btndelete,btnupdate;
    GridView gridview1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnsave=(Button)findViewById(R.id.btnsave);
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues values = new ContentValues();
                values.put("id_book",Integer.parseInt(etnhapmaso.getText().toString()));
                values.put("title",etnhaptieude.getText().toString());
                values.put("id_author",Integer.parseInt(ettentacgia.getText().toString()));
                Uri insert_uri = getContentResolver().insert(CONTENT_URI,values);
                Toast.makeText(getApplicationContext(),":Lưu thành công",Toast.LENGTH_SHORT).show();
            }
        });
        btnselect=(Button)findViewById(R.id.btnselect);
        btnselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> list = new ArrayList<>();
                Cursor cursor = getContentResolver().query(CONTENT_URI,
                        null,null,null,"title");
                if(cursor != null){
                    cursor.moveToNext();
                    do {
                        list.add(cursor.getInt(0)+"");
                        list.add(cursor.getString(1)+"");
                        list.add(cursor.getInt(2)+"");
                    }while (cursor.moveToNext());
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,list);
                    gridview1.setAdapter(adapter);
                }
                else
                    Toast.makeText(getApplicationContext(),"Truy vấn không thành công",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
