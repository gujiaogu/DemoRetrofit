package com.example.gujiao.demoretrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface ApiService {

    @Headers({
            "Accept: application/json;charset=UTF-8",
    })
    @GET("service/getIpInfo.php")
    Call<ResultEntity<IpEntity>> getIpInfo(@Query("ip")String ip);

}
