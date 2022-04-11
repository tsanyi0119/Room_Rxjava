package com.example.room_mvp;


import android.content.Context;
import android.util.Log;

import com.example.room_mvp.room.MyData;

import java.util.List;

public class Presenter implements Contract.IPresenter{
    private Contract.IMain callback;//屬性
    public Presenter(Contract.IMain callback) {
        this.callback = callback;
        myModel = new MyModel((Context) callback);
    }

    private MyModel myModel ;
    @Override
    public void getData(){
        myModel.showData(this);
    }

    @Override
    public void dataCallBack(List<MyData> arrayList) {
        callback.myadapterImplement(arrayList);

    }

    @Override
    public void insertData( MyData data){
        myModel.inserImplement(this,data);
    }

    @Override
    public void upData(MyData data) {
        myModel.upImplement(this,data);
    }

    @Override
    public void deleteAllData() {
        myModel.deleteAllImplement(this);
    }

}
