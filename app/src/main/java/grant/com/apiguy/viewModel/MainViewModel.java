package grant.com.apiguy.viewModel;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import Repo.ShibeRepository;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModel extends ViewModel {
    private MutableLiveData<List<String>> shibeData = new MutableLiveData<>();
    private MutableLiveData<Boolean> _isSuccessful = new MutableLiveData<>();
    private List<String> res = new ArrayList<>();

    public LiveData<Boolean> isSuccessful(){
        return _isSuccessful;
    }

    public LiveData<List<String>> getShibes(){
        return shibeData;
    }

    public void fetchShibes(int count){
        ShibeRepository.getInstance().getShibes(count).enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(@NotNull Call<List<String>> call,@NotNull Response<List<String>> response) {
                _isSuccessful.setValue(response.body().size() == count);
                res= response.body();
                shibeData.setValue(res);
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                _isSuccessful.setValue(false);
                res.add("Call failed");
                shibeData.setValue(res);
            }
        });
    }
}
