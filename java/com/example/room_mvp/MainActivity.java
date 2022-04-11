package com.example.room_mvp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.room_mvp.room.MyData;

import java.util.List;

public class MainActivity extends AppCompatActivity implements Contract.IMain{
    List<MyData> arrayList;
    Myadapter myAdapter;
    RecyclerView mRecyclerView;
    MyData data;

    Button button_Create,button_Modify,button_Clear;
    TextView editText_Name,editText_Phone,editText_Hobby,editText_else;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recyclerView);
        myAdapter = new Myadapter(this);
        mRecyclerView.setAdapter(myAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        init();

        Presenter presenter = new Presenter(this);

        presenter.getData();
        button_Create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editText_Name.getText().toString().matches("")&&editText_Phone.getText().toString().matches("")&& editText_Hobby.getText().toString().matches("") && editText_else.getText().toString().matches("")) {
                    /**輸入框是空值*/
                }
                else{
                    data = new MyData(editText_Name.getText().toString(),
                            editText_Phone.getText().toString(),
                            editText_Hobby.getText().toString(),
                            editText_else.getText().toString()
                    );
                    clear();
                }
                presenter.insertData(data);
            }
        });
        button_Modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editText_Name.getText().toString().matches("")&&editText_Phone.getText().toString().matches("")&& editText_Hobby.getText().toString().matches("") && editText_else.getText().toString().matches("")) {
                    /**輸入框是空值*/
                }
                else{
                    data = new MyData(data.getId(),editText_Name.getText().toString(),
                            editText_Phone.getText().toString(),
                            editText_Hobby.getText().toString(),
                            editText_else.getText().toString()
                    );
                    clear();
                }
                presenter.upData(data);
            }
        });
        button_Clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.deleteAllData();
                clear();
            }
        });
    }

    @Override
    public void myadapterImplement(List<MyData> arrayList) {
        this.arrayList = arrayList;
        myAdapter.dataView(arrayList);

    }

    @Override
    public void dataModify(MyData data) {
        this.data = data;
        editText_Name.setText(data.getName());
        editText_Phone.setText(data.getPhone());
        editText_Hobby.setText(data.getHobby());
        editText_else.setText(data.getElseInfo());
    }
    private  void clear(){
        editText_Name.setText("");
        editText_Phone.setText("");
        editText_Hobby.setText("");
        editText_else.setText("");
    }

    private void init(){
        button_Create = findViewById(R.id.button_Create);
        button_Modify = findViewById(R.id.button_Modify);
        button_Clear = findViewById(R.id.button_Clear);
        editText_Name = findViewById(R.id.editText_Name);
        editText_Phone = findViewById(R.id.editText_Phone);
        editText_Hobby = findViewById(R.id.editText_Hobby);
        editText_else  = findViewById(R.id.editText_else);
    }
}