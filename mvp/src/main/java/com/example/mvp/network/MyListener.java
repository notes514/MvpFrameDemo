package com.example.mvp.network;

/**
 * <pre>
 *     author : laodai
 *     e-mail : 851559442@qq.com
 *     time   : 2019/12/04
 *     desc   : 处理网络请求响应数据的接口
 *     version: 1.0
 * </pre>
 */
public interface MyListener<T> {

    /**
     * 成功时的回调接口
     * @param result 回调一个java对象
     */
    void onSuccess(T result);

    /**
     * 失败时的回调接口
     * @param errorMsg 回调的错误信息
     */
    void onError(String errorMsg);

}
