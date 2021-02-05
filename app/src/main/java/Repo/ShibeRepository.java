package Repo;

import java.util.List;

import Repo.remote.RetrofitInstance;
import Repo.remote.ShibeService;
import retrofit2.Call;
import retrofit2.http.Query;

public class ShibeRepository {

    private static ShibeRepository INSTANCE = null;

    private ShibeRepository(){

    }

    public Call<List<String>> getShibes(int count, boolean urls, boolean https){
        ShibeService shibeService = RetrofitInstance.getInstance();
        return shibeService.getShibes(count, urls, https);
    }
    public Call<List<String>> getBirds(int count, boolean urls, boolean https){
        ShibeService shibeService = RetrofitInstance.getInstance();
        return shibeService.getBirds(count, urls, https);
    }
    public Call<List<String>> getCats(int count, boolean urls, boolean https){
        ShibeService shibeService = RetrofitInstance.getInstance();
        return shibeService.getCats(count, urls, https);
    }

    public static ShibeRepository getInstance(){
        if(INSTANCE == null) INSTANCE = new ShibeRepository();
        return INSTANCE;
    }

}
