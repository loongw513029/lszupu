package Utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2017/1/23.
 */
public class UserUtils {
    private Context context;
    public UserUtils(Context context){
        this.context=context;
    }
    public long getUserId(){
        SharedPreferences sharedPreferences=context.getSharedPreferences("users", Context.MODE_PRIVATE);
        long userid = sharedPreferences.getLong("userid", 0);
        return userid;
    }
}
