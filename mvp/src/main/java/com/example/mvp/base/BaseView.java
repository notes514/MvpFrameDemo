package com.example.mvp.base;

/**
 * <pre>
 *     author : laodai
 *     e-mail : 851559442@qq.com
 *     time   : 2019/12/04
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public interface BaseView {

    /**
     * 显示加载
     */
    void showLoading();

    /**
     * 隐藏加载
     */
    void hideLoading();

    /**
     * 加载失败
     */
    void showError();

}
