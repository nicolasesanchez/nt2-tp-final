package ort.nt2.tpfinal.util;

import android.widget.LinearLayout;

public class Utils {

    public static String parsePersonalId(int dni) {
        String dniString = String.valueOf(dni);
        return String.format("%s.%s.%s", dniString.substring(0,2), dniString.substring(2, 5), dniString.substring(5, 8));
    }

    public static LinearLayout.LayoutParams getWrapContent() {
        return new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    }

}
