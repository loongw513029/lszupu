package Retrofit;


import java.util.List;

import Entity.BaseModel;
import Entity.LoginModelView;
import Entity.Params.LoginParams;
import Entity.Params.RegisteParams;
import Entity.RegisteModelView;
import Entity.SearchModelView;
import Entity.SmsModelView;
import retrofit2.http.*;
import rx.Observable;

/**
 * Created by Administrator on 2016/11/18 0018.
 */
public interface ApiStores {
    String SERVER_URL="http://api.lszupu.cn/";

    /**
     * 登陆
     * @param loginParams
     * @return
     */
    @POST("api/Accounts/Login")
    Observable<BaseModel<LoginModelView>> Login(@Body LoginParams loginParams);

    /**
     * 发送短信验证码
     * @param telephone 电话号码
     * @return
     */
    @GET("api/Accounts/SendSMS")
    Observable<SmsModelView> SendSMS(@Query("telephone") String telephone);

    /**
     * 注册
     * @param registeParams
     * @return
     */
    @POST("api/Accounts/Registe")
    Observable<BaseModel<RegisteModelView>> Registe(@Body RegisteParams registeParams);

    /**
     * 搜索
     * @param keywords 搜索关键字
     * @return
     */
    @GET("api/Accounts/Search")
    Observable<BaseModel<List<SearchModelView>>> Search(@Query("keywords") String keywords);

}
