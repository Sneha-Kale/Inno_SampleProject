package com.example.inno_sampleproject.Retrofit;

import android.content.Context;
import android.util.Log;

import com.example.inno_sampleproject.Interface.InterfaceConstants;
import com.google.gson.JsonSyntaxException;

import org.json.JSONException;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class KSRetrofitCallback<T> implements Callback<T> {
    private KSProgressDialogUtility progressDialog;
    private Context context;
    private boolean validateResponse = true;

    public KSRetrofitCallback(Context context, boolean validateResponse) {
        progressDialog = new KSProgressDialogUtility();
        if (validateResponse)
        progressDialog.showProgressDialog(context);
        this.context = context;
        this.validateResponse = validateResponse;
    }

    public abstract void onSuccess(T arg0);
    @Override
    public void onResponse(Call<T> call, Response<T> response) {
                progressDialog.dismissProgressDialog();
       if (response.isSuccessful() ) {
            onSuccess(response.body());
        } else {
         //   Toast.makeText(context, response.message(), Toast.LENGTH_SHORT).show();
          Log.e("response ",""+response.message());
        }
    }
    @Override
    public void onFailure(Call<T> call, Throwable error) {
        Log.e("onFailure",""+error.getMessage());
        progressDialog.dismissProgressDialog();
        if (!validateResponse)
            return;
        String errorMsg;
        error.printStackTrace();

        if (error instanceof SocketTimeoutException) {
            errorMsg = InterfaceConstants.apiValidateResponse.connection_timeout;
        } else if (error instanceof UnknownHostException) {
            errorMsg = InterfaceConstants.apiValidateResponse.no_internet;
            errorMsg=error.getMessage();
        } else if (error instanceof ConnectException) {
            errorMsg = InterfaceConstants.apiValidateResponse.server_not_responding;
        } else if (error instanceof JSONException || error instanceof JsonSyntaxException) {
            errorMsg = InterfaceConstants.apiValidateResponse.parse_error;
        } else if (error instanceof IOException) {
            errorMsg = error.getMessage();
        } else {
            errorMsg = InterfaceConstants.apiValidateResponse.oppose_something_went_wrong;
        }
        if (progressDialog != null ) {

                progressDialog.dismissProgressDialog();

        }
        Log.e("errorMsg",""+errorMsg);
    }

    /*protected void  showOfflinErrorDialog(Context mActivity)
    {
        OfflinErrorDialog dialog = new OfflinErrorDialog(mActivity);
        dialog.setCancelable(false);
        dialog.show();

    }*/
}