package com.example.inno_sampleproject.viewModel;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.inno_sampleproject.General.ConnectivityManager;
import com.example.inno_sampleproject.General.CustomMsg;
import com.example.inno_sampleproject.Interface.InterfaceAPIRequests;
import com.example.inno_sampleproject.Model.PageDataResponse;
import com.example.inno_sampleproject.R;
import com.example.inno_sampleproject.Model.Datum;
import com.example.inno_sampleproject.Retrofit.KSRetroClientInstance;
import com.example.inno_sampleproject.Retrofit.KSRetrofitCallback;

import java.util.ArrayList;

import retrofit2.Call;

public class PageDataAppViewModel extends ViewModel
{

    MutableLiveData<ArrayList<Datum>> userLiveData;
    ArrayList<Datum> userArrayList;

    public PageDataAppViewModel() {
        userLiveData = new MutableLiveData<>();

    }

    public MutableLiveData<ArrayList<Datum>> getUserMutableLiveData(){
        return userLiveData;
    }

    public void init(Context context,ProgressBar progressBar,int page,int limit)
    {
        if(ConnectivityManager.isNetworkAvailable(context))
        {
            callPageDataAPI(context,progressBar,page,limit);
        }
        else
        {
            progressBar.setVisibility(View.GONE);
            CustomMsg.CustomPopup(context,context.getString(R.string.no_network));
        }
    }


    private void callPageDataAPI(Context context,ProgressBar progressBar,int page,int limit)
    {
        InterfaceAPIRequests apiInterface = KSRetroClientInstance.getRetrofitInstance(context).create(InterfaceAPIRequests.class);

        Call<PageDataResponse> userLoginCall = apiInterface.GetPageData(page,limit);
        userLoginCall.enqueue(new KSRetrofitCallback<PageDataResponse>(context, false) {
            @Override
            public void onSuccess(PageDataResponse response) {
                try{
                    if (response!=null) {
                        progressBar.setVisibility(View.GONE);
                        userArrayList = new ArrayList<>();
                        PageDataResponse pageDataResponse=new PageDataResponse();
                        pageDataResponse.setPage(response.getPage());
                        userArrayList.addAll(response.getData());
                        Log.e("list_size", String.valueOf(userArrayList.size()));
                        userLiveData.setValue(userArrayList);
                    }
                    else
                    {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(context, R.string.smthing_wrong, Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e)
                {
                    Log.e("error",""+e);
                }

            }
        });
    }

}
