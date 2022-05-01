package com.example.inno_sampleproject.Retrofit;

import android.app.ProgressDialog;
import android.content.Context;

public class KSProgressDialogUtility {
    private ProgressDialog progressDialog;

    public KSProgressDialogUtility() {
    }

    public void showProgressDialog(Context context) {
        progressDialog = new ProgressDialog(context);
        this.progressDialog.setCancelable(false);

        progressDialog.setMessage("Loading...");

        this.progressDialog.show();
    }

    public void dismissProgressDialog() {
        try {
            if (this.progressDialog != null && this.progressDialog.isShowing()) {
                this.progressDialog.dismiss();
            }
        } catch (Exception var2) {
        }

    }
}

