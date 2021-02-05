package Repo.remote;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ShibeService {

    @GET("/api/shibes")
    Call<List<String>> getShibes(@Query("count") int count, @Query("urls") boolean urls, @Query("httpsUrls") boolean https);
    @GET("/api/cats")
    Call<List<String>> getCats(@Query("count") int count, @Query("urls") boolean urls, @Query("httpsUrls") boolean https);
    @GET("/api/birds")
    Call<List<String>> getBirds(@Query("count") int count, @Query("urls") boolean urls, @Query("httpsUrls") boolean https);

}
