package com.example.inno_sampleproject.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.inno_sampleproject.Adapter.AdapterPageData;
import com.example.inno_sampleproject.Model.Datum;
import com.example.inno_sampleproject.R;
import com.example.inno_sampleproject.viewModel.PageDataAppViewModel;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity   {

    MainActivity context;
    PageDataAppViewModel viewModel;
    SwipeRefreshLayout nested_scrollView;
    RecyclerView recyclerview_Data;
    AdapterPageData adapterPageData;
    TextView text_pageNo,text_noresfound;
    ProgressBar progress_circular;
    ArrayList<Datum> arrlist_data;
    int pageNo = 1, limit = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();
        setupListUpdate();

        viewModel= ViewModelProviders.of(this).get(PageDataAppViewModel.class);

        viewModel.getUserMutableLiveData().observe(this, (Observer<List<Datum>>) datumList -> {
            if(datumList!=null) {
                arrlist_data= (ArrayList<Datum>) datumList;
                adapterPageData.updateUserList(datumList);
                text_noresfound.setVisibility(View.GONE);
                text_pageNo.setVisibility(View.VISIBLE);
            }
            if(datumList==null || datumList.size()==0)
            {
                recyclerview_Data.setVisibility(View.GONE);
                text_noresfound.setVisibility(View.VISIBLE);
                progress_circular.setVisibility(View.GONE);
                text_pageNo.setVisibility(View.GONE);
            }
        });
        viewModel.init(context,progress_circular,pageNo,limit);
        text_pageNo.setText(getString(R.string.pageno) + pageNo);

        nested_scrollView.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                nested_scrollView.setRefreshing(false);
                pageNo = pageNo + 1;
                //progress_circular.setVisibility(View.VISIBLE);
                viewModel.init(context,progress_circular,pageNo,limit);
                text_pageNo.setText(getString(R.string.pageno) +" "+ pageNo);

            }
        });

    }

    private void initUI()
    {
        context = this;
        nested_scrollView = findViewById(R.id.nested_scrollView);
        recyclerview_Data = findViewById(R.id.recyclerview_Data);
        text_noresfound = findViewById(R.id.text_noresult);
        text_pageNo = findViewById(R.id.text_pageNo);
        progress_circular = findViewById(R.id.progress_circular);
        arrlist_data = new ArrayList<>();
    }

    private void setupListUpdate()
    {
        LinearLayoutManager manager = new LinearLayoutManager(
                this, LinearLayoutManager.VERTICAL, false);
        recyclerview_Data.setLayoutManager(manager);
        recyclerview_Data.setHasFixedSize(true);
        recyclerview_Data.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        adapterPageData=new AdapterPageData(this,arrlist_data);
        recyclerview_Data.setAdapter(adapterPageData);

    }
}