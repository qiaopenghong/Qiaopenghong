package com.example.qiao.qiaopenghong;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button bt_get;
    private Button bt_post;
    private static OkHttpClient mOkHttpClient= new OkHttpClient();;
    private Request request;
    private TextView tv;
    private TextView tv1;
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt_get = (Button) findViewById(R.id.bt_get);
        bt_post = (Button) findViewById(R.id.bt_post);
        tv = (TextView) findViewById(R.id.tv);
        tv1 = (TextView) findViewById(R.id.tv1);
        bt_get.setOnClickListener(this);
        bt_post.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_get:
                    request = new Request.Builder().get().url("http://www.yulin520.com/a2a/impressApi/news/mergeList?sign=C7548DE604BCB8A17592EFB9006F9265&pageSize=10&gender=2&ts=1871746850&page=1").build();
                    Call call=mOkHttpClient.newCall(request);
                    mOkHttpClient.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {

                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            final String str = response.body().string();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    tv.setText(str);
                                }
                            });
                        }
                    });


                break;
            case R.id.bt_post:
                FormBody formBody = new  FormBody.Builder().add("name","大鹏").build();
                final Request request = new Request.Builder()
                        .url("http://www.wooyun.org")
                        .post(formBody)
                        .build();
                Call call1 = mOkHttpClient.newCall(request);
                call1.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }
                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        final String str = response.body().string();
                        runOnUiThread(new Runnable() {
                               @Override
                               public void run() {
                                   tv1.setText(str);
                               }
                           });
                    }
                });
                break;
        }
    }

}
