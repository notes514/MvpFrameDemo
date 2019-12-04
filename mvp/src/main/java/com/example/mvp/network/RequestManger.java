package com.example.mvp.network;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.example.mvp.app.InitApp;

import java.util.HashMap;
import java.util.Map;

/**
 * <pre>
 *     author : laodai
 *     e-mail : 851559442@qq.com
 *     time   : 2019/12/04
 *     desc   : Volley网络请求管理类
 *     version: 1.0
 * </pre>
 */
public class RequestManger {
    private RequestQueue queue;
    private static volatile RequestManger instance;

    /**
     * 管理构造
     * 程序已启动就进行网络请求的初始化操作
     */
    private RequestManger() {
        this.queue = Volley.newRequestQueue(InitApp.getInstance());
    }

    /**
     * 双重校验锁（请求单例）
     * @return
     */
    public static RequestManger getInstance() {
        if (instance == null) {
            synchronized (RequestManger.class) {
                if (instance == null) {
                    instance = new RequestManger();
                }
            }
        }
        return instance;
    }

    public RequestQueue getRequestQueue() {
        return queue;
    }

    /**
     * Volley GET 请求
     * @param url       地址
     * @param clazz     javaBean
     * @param listener  请求回调
     * @param <T>       泛型
     */
    public <T> void sendGet(String url, Class<T> clazz, final MyListener<T> listener) {
        MyRequest<T> request = new MyRequest<>(Request.Method.GET, url, clazz, new Response.Listener<T>() {
            @Override
            public void onResponse(T response) {
                listener.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onError(error.getMessage());
            }
        });
        addToRequestQueue(request);
    }

    /**
     * Volley POST 请求
     * @param url       地址
     * @param clazz     javaBean
     * @param map       传入的Map参数
     * @param listener  请求回调
     * @param <T>       泛型
     */
    public <T> void sendPost(String url, Class<T> clazz, final HashMap<String, String> map,
                             final MyListener<T> listener) {
        MyRequest<T> request = new MyRequest<T>(Request.Method.POST, url, clazz, new Response.Listener<T>() {
            @Override
            public void onResponse(T response) {
                listener.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onError(error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return map;
            }
        };
        addToRequestQueue(request);
    }

    public <T> void addToRequestQueue(Request<T> request) {
        getRequestQueue().add(request);
    }

}
