package Utils;

import android.content.Context;

/**
 * Created by Administrator on 2016/11/18 0018.
 */
public class DialogUtil {
    private CustomProgressDialog progressDialog;
    Context context;
    public DialogUtil(Context context){
        this.context=context;
    }


    /**
     * 打开Loading窗口
     */
    public CustomProgressDialog startProgressDialog() {
        if (progressDialog == null) {
            progressDialog = CustomProgressDialog.createDialog(context);
            progressDialog.show();
        }
        return progressDialog;
    }

    /**
     * 关闭Loading窗口
     */
    public void stopProgressDialog() {
        if (progressDialog != null&& progressDialog.isShowing()) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }
}
