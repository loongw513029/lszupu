package Entity;

/**
 * Created by Administrator on 2016/11/18 0018.
 */
public class LoginModelView {
    private long userId;
    private String userName;
    private String sex;
    private String realName;
    private String nickName;
    private String headLogo;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getHeadLogo() {
        return headLogo;
    }

    public void setHeadLogo(String headLogo) {
        this.headLogo = headLogo;
    }
}
