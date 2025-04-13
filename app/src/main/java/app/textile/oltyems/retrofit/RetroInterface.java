package app.textile.oltyems.retrofit;

import app.textile.oltyems.common.FetchProductList;
import app.textile.oltyems.model.CreateCustomer;
import app.textile.oltyems.model.CreateOrder;
import app.textile.oltyems.model.FetchOrderList;
import app.textile.oltyems.model.FetchShipmentList;
import app.textile.oltyems.model.FetchShipmentResponse;
import app.textile.oltyems.pojo.CustomerReq;
import app.textile.oltyems.pojo.LoginReq;
import app.textile.oltyems.pojo.LoginResponse;
import app.textile.oltyems.pojo.ProductReq;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RetroInterface {

    @POST("login")
    Call<LoginResponse> loginApi(@Body LoginReq user);

    @GET("fetch-shipment-data/{ship_id}")
    Call<FetchShipmentResponse> pendingDataList(@Header("Authorization") String token,
                                                @Path("ship_id") String ship_id);


    @GET("fetch-shipment-order/{ship_id}")
    Call<FetchShipmentResponse> fetchShipmentOrder(@Header("Authorization") String token,
                                                @Path("ship_id") String ship_id);

    @GET("fetch-product")
    Call<FetchProductList> fetchProductList(@Header("Authorization") String token);

    @GET("fetch-open-order")
    Call<FetchOrderList> fetchOrderList(@Header("Authorization") String token);

    @GET("fetch-shipment")
    Call<FetchShipmentList> fetchShipmentList(@Header("Authorization") String token);

    @POST("create-customer")
    Call<CreateCustomer> createCustomer(@Body CustomerReq createCustomer, @Header("Authorization") String token);

    @POST("create-order")
    Call<CreateOrder> createOrder(@Body ProductReq createCustomer, @Header("Authorization") String token);

    @GET("get-shipment-product/{ship_id}")
    Call<app.textile.oltyems.model.FetchProductList> fetchShipmentProduct(@Header("Authorization") String token,
                                                                          @Path("ship_id") String ship_id);
}
