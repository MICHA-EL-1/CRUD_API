package com.example.crud_api;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.database.Cursor;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DataBaseHelper myDb;
    EditText txtid;
    EditText txtname;
    EditText txtsurname;
    EditText txtmark;
    Button btnClick;
    Button btnClick1;
    Button btnClick2;
    Button btnClick3;
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new DataBaseHelper(this);
        txtid = findViewById(R.id.id);
        txtname = findViewById(R.id.name);
        txtsurname = findViewById(R.id.surname);
        txtmark = findViewById(R.id.mark);
        result = findViewById(R.id.textView);
        btnClick = findViewById(R.id.create);
        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClickMe();
            }
        });
        btnClick1 = findViewById(R.id.read);
        btnClick1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                ClickMe1();
            }
        });
        btnClick2 = findViewById(R.id.update);
        btnClick2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                ClickMe2();
            }
        });
        btnClick3 = findViewById(R.id.delete);
        btnClick3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                ClickMe3();
            }
        });
    }

    private void ClickMe() {
        String name = txtname.getText().toString();
        String surname = txtsurname.getText().toString();
        String mark = txtmark.getText().toString();
        Boolean result = myDb.insertData(name, surname, mark);
        if(result == true){
            Toast.makeText(this, "Data added Successfully", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Data Insertion Failed", Toast.LENGTH_SHORT).show();
        }
    }

    private void ClickMe1(){
        Cursor res = myDb.getAllData();
        StringBuffer stringBuffer = new StringBuffer();
        if(res!=null && res.getCount()>0){
            while (res.moveToNext()){
                stringBuffer.append("Id: "+res.getString(0)+"\n");
                stringBuffer.append("Name: "+res.getString(1)+"\n");
                stringBuffer.append("Surname: "+res.getString(2)+"\n");
                stringBuffer.append("Mark: "+res.getString(3)+"\n");
            }
            result.setText(stringBuffer.toString());
            Toast.makeText(this, "Data Retrieved Successfully", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "No Data to Retrieved", Toast.LENGTH_SHORT).show();
        }
    }

    private void ClickMe2() {
        String id = txtid.getText().toString();
        String name = txtname.getText().toString();
        String surname = txtsurname.getText().toString();
        String mark = txtmark.getText().toString();
        Boolean result = myDb.updateData(id, name, surname, mark);
        if(result == true){
            Toast.makeText(this, "Data Updated Successfully", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "No Rows Affected", Toast.LENGTH_SHORT).show();
        }
    }

    private void ClickMe3(){
        String id = txtid.getText().toString();
        int result = myDb.deleteData(id);
        Toast.makeText(this,result+"Rows Affected", Toast.LENGTH_SHORT).show();
    }

}
