package com.example.room_mvp;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.room_mvp.room.DataBase;
import com.example.room_mvp.room.MyData;

import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.Maybe;
import io.reactivex.MaybeObserver;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableMaybeObserver;
import io.reactivex.schedulers.Schedulers;

public class MyModel implements Contract.IMyModel{
    private List<MyData> arrayList;
    private Context context;
    public MyModel(Context context) {
        this.context = context;
    }
    private CompositeDisposable disposables = new CompositeDisposable();


    public void showData(Contract.IPresenter callback) {
//        new Thread(()->{
//            arrayList = DataBase.getInstance(context).getDataUao().displayAll();
//            ((Activity)context).runOnUiThread(()->{
//                callback.dataCallBack(arrayList);
//            });
//        }).start();



        disposables.add(
                Maybe.fromCallable(new Callable<List<MyData>>() {
                    @Override
                    public List<MyData> call() throws Exception {
                        arrayList = DataBase.getInstance(context).getDataUao().displayAll();
                        return arrayList;
                    }
                })
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableMaybeObserver<List<MyData>>() {

                            @Override
                            public void onSuccess(@NonNull List<MyData> myData) {
                                Log.e("TEST", "????????????! ! !");
                                callback.dataCallBack(myData);
                            }

                            @Override
                            public void onError(@NonNull Throwable e) {
                                Log.e("TEST", "????????????! ! !");
                            }

                            @Override
                            public void onComplete() {
                                Log.e("TEST", "???????????????! ! !");
                            }
                        }));


//        DataBase.getInstance(context).getDataUao().displayAll()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new MaybeObserver<List<MyData>>() {
//                    @Override
//                    public void onSubscribe(@NonNull Disposable d) {
//                        Log.e("TEST", "?????? subscribe");
//                    }
//
//                    @Override
//                    public void onSuccess(@NonNull List<MyData> myData) {
//                        Log.e("TEST", "????????????! ! !");
//                        callback.dataCallBack(myData);
//                    }
//
//                    @Override
//                    public void onError(@NonNull Throwable e) {
//                        Log.e("TEST", "????????????! ! !");
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        Log.e("TEST", "???????????????! ! !");
//                    }
//                });
    }

    @Override
    public void inserImplement(Contract.IPresenter callback, MyData data) {
//        new Thread(()->{
//            DataBase.getInstance(context).getDataUao().insertData(data);
//            showData(callback);
//
//        }).start();

        disposables.add(
                Completable.fromAction(new Action() {
                    @Override
                    public void run() throws Exception {
                        DataBase.getInstance(context).getDataUao().insertData(data);
                    }
                })
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableCompletableObserver() {
                            @Override
                            public void onComplete() {
                                Log.e("TEST", "????????????! ! !");
                                showData(callback);
                            }

                            @Override
                            public void onError(Throwable e) {
                            Log.e("TEST", "????????????! ! !");
                            }
                        }));


        Log.e("TEST", "inserImplement: " );

//    disposables.add(
//        DataBase.getInstance(context).getDataUao().insertData(data)
//                    .subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe(new Action() {
//                        @Override
//                        public void run() throws Exception {
//                            Log.e("TEST", "????????????! ! !");
//                            showData(callback);
//                        }
//                    }, new Consumer<Throwable>() {
//                        @Override
//                        public void accept(Throwable throwable) throws Exception {
//                            Log.e("TEST", "????????????! ! !");
//                        }
//                    }));



//        DataBase.getInstance(context).getDataUao().insertData(data)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new CompletableObserver() {
//
//                    @Override
//                    public void onSubscribe(@NonNull Disposable d) {
//                        Log.e("TEST", "?????? subscribe");
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        Log.e("TEST", "????????????! ! !");
//                        showData(callback);
//                    }
//
//                    @Override
//                    public void onError(@NonNull Throwable e) {
//                        Log.e("TEST", "????????????! ! !");
//                    }
//                });
//        disposables.clear();
    }


    @Override
    public void upImplement(Contract.IPresenter callback, MyData data) {
//        new Thread(()->{
//            DataBase.getInstance(context).getDataUao().updateData(data);
//            showData(callback);
//        }).start();

        DataBase.getInstance(context).getDataUao().updateData(data)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.e("TEST", "?????? subscribe");
                    }

                    @Override
                    public void onComplete() {
                        Log.e("TEST", "????????????! ! !");
                        showData(callback);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e("TEST", "????????????! ! !");
                    }
                });
    }

    @Override
    public void deleteAllImplement(Contract.IPresenter callback) {
//        new Thread(()->{
//            DataBase.getInstance(context).getDataUao().deleteAllData();
//            showData(callback);
//        }).start();
        DataBase.getInstance(context).getDataUao().deleteAllData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.e("TEST", "?????? subscribe");
                    }

                    @Override
                    public void onComplete() {
                        Log.e("TEST", "????????????! ! !");
                        showData(callback);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e("TEST", "????????????! ! !");
                    }
                });
    }

}
