package com.lon2017.lszupu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Entity.LoginModelView;
import Entity.Params.RegisteParams;
import Entity.SearchModelView;
import Presenter.MemberPresenter;
import Utils.ActivityManager;
import View.MemberView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import butterknife.OnFocusChange;

/**
 * Created by Administrator on 2017/1/23.
 */
public class RegisterActivity extends Activity implements MemberView{
    private MemberPresenter memberPresenter;
    @InjectView(R.id.registe_btn)
    Button reg_btn;
    @InjectView(R.id.send_sms)
    Button send_code;
    @InjectView(R.id.reg_username)
    EditText reg_username;
    @InjectView(R.id.reg_password)
    EditText reg_pwd;
    @InjectView(R.id.reg_code)
    EditText reg_code;
    @InjectView(R.id.login_btn)
    Button login_text;
    //短信验证码时间倒计时
    private int i = 120;
    private TimeOut timeOut;
    private String phone="^1[34578]\\d{9}$" ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.registe_activity);
        super.onCreate(savedInstanceState);
        ButterKnife.inject(this);
        ActivityManager.getInstance().addActivity(this);
        if(memberPresenter==null){
            memberPresenter=new MemberPresenter(this,this);
        }
        timeOut=new TimeOut(120000,1000);
    }

    @OnClick({R.id.registe_btn,R.id.login_btn,R.id.send_sms})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.login_btn:
                Intent intent=new Intent(this,LoginActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
                break;
            case R.id.registe_btn:
                RegisteParams params=new RegisteParams();
                String telephone=(reg_username.getText()+"").trim();
                String pwd=(reg_pwd.getText()+"").trim();
                String code=(reg_code.getText()+"").trim();
                if(telephone.equals("")||pwd.equals("")||code.equals("")){
                    Toast.makeText(RegisterActivity.this,"请完整输入信息",Toast.LENGTH_SHORT).show();
                    return;
                }
                params.setTelephone(telephone);
                params.setPassword(pwd);
                params.setVaildcode(code);
                memberPresenter.Registe(params);
                break;
            case R.id.send_sms:
                String telephone2=(reg_username.getText()+"").trim();
                if(telephone2.equals("")){
                    Toast.makeText(RegisterActivity.this,"请输入手机号码",Toast.LENGTH_SHORT).show();
                    return;
                }
                Pattern pattern = Pattern.compile(phone);
                Matcher matcher = pattern.matcher(telephone2);
                if(!matcher.matches()) {
                    Toast.makeText(RegisterActivity.this,"手机号码格式不正确",Toast.LENGTH_SHORT).show();
                    reg_username.setText("");
                }
                memberPresenter.SendSMS(telephone2);
                break;
            default:
                break;
        }
    }
    @OnFocusChange(R.id.reg_username)
    public void onFocusChange(View v){
        switch (v.getId()){
            case R.id.reg_username:
                String telephone=(reg_username.getText()+"").trim();
                if(telephone.equals(""))
                    return;
                Pattern pattern = Pattern.compile(phone);
                Matcher matcher = pattern.matcher(telephone);
                if(!matcher.matches()) {
                    Toast.makeText(RegisterActivity.this,"手机号码格式不正确",Toast.LENGTH_SHORT).show();
                    reg_username.setText("");
                }
                break;
            default:
                break;
        }
    }

    class TimeOut extends CountDownTimer {


        /**
         * @param millisInFuture    The number of millis in the future from the call
         *                          to
         *                          {@link #start()} until the countdown is done and {@link #onFinish()}
         *                          is called.
         * @param countDownInterval The interval along the way to receive
         *                          {@link #onTick(long)} callbacks.
         */
        public TimeOut(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            send_code.setText("剩余"+millisUntilFinished / 1000 + "秒");
        }

        @Override
        public void onFinish() {
            send_code.setText("获取验证码");
            send_code.setClickable(true);
        }
    }
    @Override
    public void GetLoginInfo(LoginModelView loginModelView) {

    }

    @Override
    public void GetVaildCode(String validCode) {
        send_code.setClickable(false);
        Toast.makeText(RegisterActivity.this,"短信发送成功",Toast.LENGTH_SHORT).show();
        timeOut.start();
    }

    @Override
    public void RegisteResult(boolean isRegisteSuccess) {
        if(isRegisteSuccess)
        {
            Toast.makeText(RegisterActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
            login_text.performClick();
        }
    }

    @Override
    public void GetMembers(List<SearchModelView> list) {

    }

    @Override
    public void OnError(String error) {
        Log.e("RegisterError",error);
    }
}
