package Utils;

import android.app.Activity;

import java.lang.ref.WeakReference;
import java.util.LinkedList;
import java.util.List;

/**
 * 用于处理退出程序时可以退出所有的activity，而编写的通用类
 *
 * @author
 * @date 2012-9-9
 */
public class ActivityManager {

    private List<WeakReference<Activity>> activityList = new LinkedList<>();
    private static ActivityManager instance;

    private ActivityManager() {

    }

    // 单例模式中获取唯一的MyApplication实例
    public static ActivityManager getInstance() {
        if (null == instance) {
            instance = new ActivityManager();
        }
        return instance;
    }

    // 添加Activity到容器中
    public void addActivity(Activity activity) {
        activityList.add(new WeakReference<Activity>(activity));
    }

    // 遍历所有Activity并finish
    public void exit() {
        for (WeakReference<Activity> activity : activityList) {
            Activity act = activity.get();
            if (act != null) {
                act.finish();
            }
        }
        activityList.clear();
//        System.exit(0);
    }
}