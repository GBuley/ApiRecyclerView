package Repo.remote;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ShibeService {

    @GET("/api/shibes")
    Call<List<String>> getShibes(@Query("count") int count);

}
