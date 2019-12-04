package com.example.mvp.base;

import android.view.View;

import com.example.mvp.R;

import java.lang.ref.WeakReference;

/**
 * <pre>
 *     author : laodai
 *     e-mail : 851559442@qq.com
 *     time   : 2019/12/04
 *     desc   : 表示器：声明了两个泛型 M 和 V，
 *              M 对应要处理的 Model，V 则对应负责展示的View。
 *              由于 V 一般比较大，这里采用了弱引用的写法，避免内存泄漏。
 *     version: 1.0
 * </pre>
 */
public abstract class BasePresenter<M, V> {
    protected M mModel;
    protected WeakReference<V> mViewRef;

    /**
     * 负责V与P的关联
     * @param model
     * @param view
     */
    protected void onAttach(M model, V view) {
        mModel = model;
        mViewRef = new WeakReference<>(view);
    }

    /**
     * true：getView() 返回对应的View
     * false: null
     * @return
     */
    protected V getView() {
        return isViewAttached() ? mViewRef.get() : null;
    }

    /**
     * 用于检测 V 是否已关联 P
     * @return true：已关联 false：未关联
     */
    protected boolean isViewAttached() {
        return mViewRef != null && mViewRef.get() != null;
    }

    /**
     * 负责V与P的解联
     */
    private void onDetach() {
        if (mViewRef != null) {
            mViewRef.clear();
            mViewRef = null;
        }
    }

}
