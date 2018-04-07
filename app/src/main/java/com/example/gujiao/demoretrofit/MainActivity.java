package com.example.gujiao.demoretrofit;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Call<ResultEntity<IpEntity>> call = apiService.getIpInfo("171.217.40.43");
        call.enqueue(new Callback<ResultEntity<IpEntity>>() {
            @Override
            public void onResponse(Call<ResultEntity<IpEntity>> call, Response<ResultEntity<IpEntity>> response) {
                IpEntity entity = response.body().getData();
                Toast.makeText(MainActivity.this, entity.getCountry(), Toast.LENGTH_SHORT).show();
                Log.d("==========", entity.getCity());
            }

            @Override
            public void onFailure(Call<ResultEntity<IpEntity>> call, Throwable t) {
                Log.d("==========", t.getMessage());
            }
        });

        Observer<String[]> subscriber = new Observer<String[]>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String[] strings) {
                for(int i = 0; i < strings.length; i ++) {
                    Log.d("==========", strings[i]);
                }
            }

            @Override
            public void onError(Throwable e) {
                Log.d("=====", e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.d("=====", "onComplete");
            }
        };
        String[] words = {"冯提莫", "陈一发儿"};
        Observable.just(words)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
}
