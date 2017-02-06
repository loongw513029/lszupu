package com.lon2017.lszupu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextClock;
import android.widget.TextView;

import Utils.ActivityManager;
import Widget.CircleImageView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/1/23.
 */
public class LoginActivity extends Activity {

    @InjectView(R.id.login_username)
    EditText login_username;
    @InjectView(R.id.login_password)
    EditText login_password;
    @InjectView(R.id.login_btn)
    Button login_btn;
    @InjectView(R.id.logo_header_login)
    CircleImageView logo_header_login;
    @InjectView(R.id.login_findpwd)
    TextView login_findpwd;
    @InjectView(R.id.login_reg)
    Button login_reg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.login_activity);
        super.onCreate(savedInstanceState);
        ButterKnife.inject(this);
        ActivityManager.getInstance().addActivity(this);
    }

    @OnClick({R.id.login_reg,R.id.login_findpwd,R.id.login_btn})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.login_reg:
                Intent intent=new Intent(this,RegisterActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
                break;
            case R.id.login_findpwd:

                break;
            case R.id.login_btn:
                Intent intent2=new Intent(this,MainActivity.class);
                startActivity(intent2);
                finish();
                overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
                break;
            default:
                break;
        }
    }


}
