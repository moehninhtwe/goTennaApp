package mhh.com.gotennaapp.api;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIService {
    private static HttpUrl endPoint = HttpUrl.parse("https://annetog.gotenna.com");

    private static Retrofit provideAdapter() {
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();

        Retrofit.Builder retrofitBuilder = new Retrofit.Builder().client(builder.build())
            .baseUrl(endPoint)
            .addConverterFactory(GsonConverterFactory.create());
        return  retrofitBuilder.build();
    }

    public static GetPlacesService provideGetPlacesService() {
        return  provideAdapter().create(GetPlacesService.class);
    }
}
