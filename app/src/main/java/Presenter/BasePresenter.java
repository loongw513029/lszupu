package Presenter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import Retrofit.ApiStores;
import Retrofit.AppClient;
import Utils.DialogUtil;
import Utils.Utils;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;



/**
 * Created by Administrator on 2016/11/18 0018.
 */
public class BasePresenter {
    public ApiStores apiStores = AppClient.retrofit().create(ApiStores.class);
    private CompositeSubscription mCompositeSubscription;
    private DialogUtil dialogUtil;

    /**
     * 签名
     * @return
     */
    public static String MakeSign(String rfid,String secertkey,String pwd){
        String signkey="";
//        Map<String,String> map=new HashMap<>();
//        map.put("rfid",rfid);
//        map.put("secretkey",secertkey);
//        ArrayList<String> pvalues = new ArrayList<String>();
//        pvalues.add("rfid");
//        pvalues.add("secretkey");
//        Collections.sort(pvalues);
//
        signkey="rfid="+rfid+"&secretkey="+secertkey;
        String rskey=Utils.MD5(signkey);
        String sign=Utils.MD5(rskey+pwd);
        return sign;
    }

    public void addSubscription(Observable observable, Subscriber subscriber) {

        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(
                observable
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(subscriber));

    }

    public void onUnsubscribe() {
        if (mCompositeSubscription != null && mCompositeSubscription.hasSubscriptions()) {
            mCompositeSubscription.unsubscribe();
        }
    }
    public void showToast(Context context, String str){
        Toast.makeText(context, ""+str, Toast.LENGTH_SHORT).show();
    }

    public void showCustomDialog(Context context){
        if(dialogUtil==null){
            dialogUtil = new DialogUtil(context);
        }
        dialogUtil.startProgressDialog();

    }

    public void dismissCustomDialog(){
        if(dialogUtil!=null){
            dialogUtil.stopProgressDialog();
        }
    }
}

