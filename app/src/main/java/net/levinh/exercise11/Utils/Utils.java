package net.levinh.exercise11.Utils;

import android.text.TextUtils;

/**
 * Created by levinh on 05/08/2016.
 */
public class Utils {

    public static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }
}
