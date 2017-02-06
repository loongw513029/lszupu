package Presenter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

import Entity.BaseModel;
import Entity.LoginModelView;
import Entity.Params.LoginParams;
import Entity.Params.RegisteParams;
import Entity.RegisteModelView;
import Entity.SearchModelView;
import Entity.SmsModelView;
import RxJava.ApiCallback;
import RxJava.SubscriberCallBack;
import View.MemberView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/11/18 0018.
 */
public class MemberPresenter extends BasePresenter{
    private MemberView memberView;
    private Context context;
    public MemberPresenter(Context context){
        this.context=context;
    }
    public MemberPresenter(Context context,MemberView memberView) {
        this.context=context;
        this.memberView=memberView;
    }
    public void Login(LoginParams loginParams) {
        showCustomDialog(context);
        addSubscription(apiStores.Login(loginParams),
                new SubscriberCallBack(new ApiCallback<BaseModel<LoginModelView>>() {
                    @Override
                    public void onSuccess(BaseModel<LoginModelView> model) {
                        if(!model.isSuccess()){
                            Toast.makeText(context,model.getMsg(),Toast.LENGTH_SHORT).show();
                        }else{
                            memberView.GetLoginInfo(model.getResult());
                        }
                        dismissCustomDialog();
                    }
                    @Override
                    public void onFailure(int code, String msg) {
                        memberView.OnError(msg);
                        dismissCustomDialog();
                    }
                    @Override
                    public void onCompleted() {

                    }
                }));
    }

    public void SendSMS(String telephone){
        showCustomDialog(context);
        addSubscription(apiStores.SendSMS(telephone),
                new SubscriberCallBack(new ApiCallback<SmsModelView>(){

                    @Override
                    public void onSuccess(SmsModelView model) {
                        if(model.isSuccess())
                            memberView.GetVaildCode(model.getMsg());
                        else
                            Toast.makeText(context,model.getMsg(),Toast.LENGTH_SHORT).show();
                        dismissCustomDialog();
                    }

                    @Override
                    public void onFailure(int code, String msg) {
                        dismissCustomDialog();
                        memberView.OnError(msg);
                    }

                    @Override
                    public void onCompleted() {

                    }
                }));
    }

    public void Registe(RegisteParams registeParams){
        showCustomDialog(context);
        addSubscription(apiStores.Registe(registeParams),
                new SubscriberCallBack(new ApiCallback<BaseModel<RegisteModelView>>(){

                    @Override
                    public void onSuccess(BaseModel<RegisteModelView> model) {
                        if(model.isSuccess())
                            memberView.RegisteResult(model.isSuccess());
                        else
                            Toast.makeText(context,model.getMsg(),Toast.LENGTH_SHORT).show();
                        dismissCustomDialog();
                    }

                    @Override
                    public void onFailure(int code, String msg) {
                        dismissCustomDialog();
                        memberView.OnError(msg);
                    }

                    @Override
                    public void onCompleted() {

                    }
                }));
    }

    public void Search(String keywords){
        showCustomDialog(context);
        addSubscription(apiStores.Search(keywords),
                new SubscriberCallBack(new ApiCallback<BaseModel<List<SearchModelView>>>(){

                    @Override
                    public void onSuccess(BaseModel<List<SearchModelView>> model) {
                        if(model.isSuccess())
                            memberView.GetMembers(model.getResult());
                        else
                            Toast.makeText(context,model.getMsg(),Toast.LENGTH_SHORT).show();
                        dismissCustomDialog();
                    }

                    @Override
                    public void onFailure(int code, String msg) {
                        dismissCustomDialog();
                        memberView.OnError(msg);
                    }

                    @Override
                    public void onCompleted() {

                    }
                }));
    }


}
