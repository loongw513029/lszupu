package Utils;

import java.security.MessageDigest;

/**
 * Created by Administrator on 2016/11/18 0018.
 */
public class Utils {



    public static String MD5(String inStr) {
        MessageDigest md5 = null;
        byte[] byteArray = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byteArray = inStr.getBytes("utf-8");
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }

       /* char[] charArray = inStr.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++)
            byteArray[i] = (byte) charArray[i];*/
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16)
                hexValue.append("0");
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();

    }
}
