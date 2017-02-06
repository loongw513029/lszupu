package RxJava;

/**
 * Created by Administrator on 2016/11/18 0018.
 */
public interface ApiCallback<T> {
    void onSuccess(T model);

    void onFailure(int code, String msg);

    void onCompleted();
}
