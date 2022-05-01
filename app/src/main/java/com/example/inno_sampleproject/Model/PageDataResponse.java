
package com.example.inno_sampleproject.Model;


import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class PageDataResponse {
    public PageDataResponse()
    {

    }

    public PageDataResponse(List<Datum> mData, Long mPage, Long mPerPage, Support mSupport, Long mTotal, Long mTotalPages) {
        this.mData = mData;
        this.mPage = mPage;
        this.mPerPage = mPerPage;
        this.mSupport = mSupport;
        this.mTotal = mTotal;
        this.mTotalPages = mTotalPages;
    }

    @SerializedName("data")
    private List<Datum> mData;
    @SerializedName("page")
    private Long mPage;
    @SerializedName("per_page")
    private Long mPerPage;
    @SerializedName("support")
    private Support mSupport;
    @SerializedName("total")
    private Long mTotal;
    @SerializedName("total_pages")
    private Long mTotalPages;

    public List<Datum> getData() {
        return mData;
    }

    public void setData(List<Datum> data) {
        mData = data;
    }

    public Long getPage() {
        return mPage;
    }

    public void setPage(Long page) {
        mPage = page;
    }

    public Long getPerPage() {
        return mPerPage;
    }

    public void setPerPage(Long perPage) {
        mPerPage = perPage;
    }

    public Support getSupport() {
        return mSupport;
    }

    public void setSupport(Support support) {
        mSupport = support;
    }

    public Long getTotal() {
        return mTotal;
    }

    public void setTotal(Long total) {
        mTotal = total;
    }

    public Long getTotalPages() {
        return mTotalPages;
    }

    public void setTotalPages(Long totalPages) {
        mTotalPages = totalPages;
    }

}
