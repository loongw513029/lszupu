package RxJava;

import android.util.Log;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;

/**
 * Created by Administrator on 2016/11/18 0018.
 */
public class SubscriberCallBack<T> extends Subscriber<T> {
    private ApiCallback<T> apiCallback;
    private int code;

    public SubscriberCallBack(ApiCallback<T> apiCallback) {
        this.apiCallback = apiCallback;
    }
    @Override
    public void onCompleted() {
        apiCallback.onCompleted();
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        Log.e("请求失败",e.toString());
        if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            //httpException.response().errorBody().string()
            code = httpException.code();
            String msg = httpException.getMessage();
            if (code == 504) {
                msg = "网络不给力..";
            }
            apiCallback.onFailure(code, msg);
        } else{
            apiCallback.onFailure(code,"网络或服务器异常..");
        }
        apiCallback.onCompleted();
    }

    @Override
    public void onNext(T t) {
        apiCallback.onSuccess(t);
    }
}
