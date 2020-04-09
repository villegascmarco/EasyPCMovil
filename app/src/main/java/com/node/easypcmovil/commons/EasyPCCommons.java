package com.node.easypcmovil.commons;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.view.View;

import com.node.easypcmovil.ActivityLogin;
import com.node.easypcmovil.R;

public class EasyPCCommons {

    private static Dialog dialog;

    //public static final String URL_SERVER = "http://192.168.1.70:8080/EasyPC/api/";
    public static final String URL_SERVER = "http://192.168.0.15:8084/EasyPC/api/";

    public static final String URL_USUARIO = URL_SERVER + "usuario/";

    public static void callLoadingScreen(View view) {
        dialog = new ProgressDialog(view.getContext());
        dialog.show();
        dialog.setContentView(R.layout.progress_dialog);
        dialog.getWindow().setBackgroundDrawableResource(
                android.R.color.transparent
        );
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
    }

    public static void dismissLoadingScreen() {
        // Dismiss ProgressBar
        dialog.dismiss();
    }

    public static Dialog getDialog() {
        return dialog;
    }
}
