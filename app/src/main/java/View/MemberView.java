package View;

import java.util.List;

import Entity.LoginModelView;
import Entity.SearchModelView;

/**
 * Created by Administrator on 2016/11/18 0018.
 */
public interface MemberView extends BaseView{
    void GetLoginInfo(LoginModelView loginModelView);
    void GetVaildCode(String validCode);
    void RegisteResult(boolean isRegisteSuccess);
    void GetMembers(List<SearchModelView> list);

}
