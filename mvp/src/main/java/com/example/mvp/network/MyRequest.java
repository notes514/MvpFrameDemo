package com.example.mvp.network;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;

/**
 * <pre>
 *     author : laodai
 *     e-mail : 851559442@qq.com
 *     time   : 2019/12/04
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class MyRequest<T> extends Request<T> {
    private Gson mGson;
    private Class<T> mClass;
    private Response.Listener<T> mListener;

    public MyRequest(String uri, Class<T> tClass, Response.Listener<T> listener,
                     Response.ErrorListener errorListener) {
        this(Method.GET, uri, tClass, listener, errorListener);
    }

    public MyRequest(int method, String url, Class<T> mClass, Response.Listener<T> listener,
                     Response.ErrorListener errorListener) {
        super(method, url, errorListener);
        this.mGson = mGson;
        this.mClass = mClass;
        this.mListener = mListener;
    }

    /**
     * 解析请求到的响应（返回的数据）
     * @param response
     * @return
     */
    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            String json = new String(response.data,
                    HttpHeaderParser.parseCharset(response.headers));
            return Response.success(mGson.fromJson(json, mClass),
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        }
    }

    /**
     * 将响应（解析后的数据）传递给回调接口 mListener
     * @param response
     */
    @Override
    protected void deliverResponse(T response) {
        mListener.onResponse(response);
    }

}
