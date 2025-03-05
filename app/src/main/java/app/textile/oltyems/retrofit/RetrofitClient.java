package app.textile.oltyems.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static Retrofit retrofit;
    private static String BASE_URL = "https://erp.oltymes.com/api/";
    public static Retrofit getRetrofitInstance() {
//        TokenInterceptor interceptor=new TokenInterceptor();
//
//        OkHttpClient client = new OkHttpClient.Builder()
//                .addInterceptor(interceptor)
//                .build();
        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }


}
