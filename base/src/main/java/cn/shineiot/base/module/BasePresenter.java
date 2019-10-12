package cn.shineiot.base.module;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * @author GF63
 */
public abstract class BasePresenter<T> {
    public T mView;//View
    protected CompositeSubscription mCompositeSubscription;  //使用compositesubcription 管理Subcription

    public void attachView(T view) {
        this.mView = view;
   }


    public void detachView() {

        this.mView = null;
        onUnsubscribe();
    }


    /**
     *  RXjava取消注册，以避免内存泄露
     */
    public void onUnsubscribe() {
        if (mCompositeSubscription != null && mCompositeSubscription.hasSubscriptions()) {
            mCompositeSubscription.unsubscribe();
            mCompositeSubscription = null;
        }
    }


    public void addSubscription(Observable observable, Subscriber subscriber) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(observable
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber));
    }


    public boolean isViewAttached(){
        return mView!= null;
    }


}

