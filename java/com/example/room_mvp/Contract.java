package com.example.room_mvp;

import com.example.room_mvp.room.MyData;

import java.util.List;

interface Contract {
    //Presenter
    interface IPresenter{
        void getData();
        void dataCallBack(List<MyData>Data);
        void insertData(MyData Data);
        void upData(MyData data);
        void deleteAllData();
    }
    //Adapter
    interface IMyadapter{
        void dataView(List<MyData>myData);

    }
    //Model
    interface IMyModel{
        void inserImplement(Contract.IPresenter callback,MyData data);
        void upImplement(Contract.IPresenter callback,MyData data);
        void deleteAllImplement(Contract.IPresenter callback);
    }
    //View
    interface IMain{
        void myadapterImplement(List<MyData> myData);
        void dataModify(MyData data);
    }
}
