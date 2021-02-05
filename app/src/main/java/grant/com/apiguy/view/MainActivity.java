package grant.com.apiguy.view;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.List;

import grant.com.apiguy.adapter.ShibeAdapter;
import grant.com.apiguy.databinding.ActivityMainBinding;
import grant.com.apiguy.utils.Constants;
import grant.com.apiguy.viewModel.MainViewModel;

public class MainActivity extends AppCompatActivity {
    private MainViewModel viewModel;
    private ActivityMainBinding binding;
    AppCompatEditText countText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());
        LinearLayoutManager linearLayout = new LinearLayoutManager(this);
        binding.imageList.setLayoutManager(linearLayout);

        int intData = getIntent().getIntExtra(Constants.SHIBE_ACTIVITY_PARAM_INT, 1);
        boolean url = getIntent().getBooleanExtra(Constants.SHIBE_ACTIVITY_PARAM_URL, true);
        boolean https = getIntent().getBooleanExtra(Constants.SHIBE_ACTIVITY_PARAM_SECURITY_TYPE, true);
        String imageType = getIntent().getStringExtra(Constants.SHIBE_ACTIVITY_PARAM_IMAGE_TYPE);


        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        viewModel.fetchShibes(intData,imageType,url,https);
        setupViews();
        viewModel.isSuccessful().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isSuccessful) {
                Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
            }
        });
        viewModel.getShibes().observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> urls) {
                ShibeAdapter shibeAdapter = new ShibeAdapter(urls, imageType);
                binding.imageList.setAdapter(shibeAdapter);
            }
        });


    }

    private void setupViews() {
        binding.layoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(binding.imageList.getLayoutManager() instanceof LinearLayoutManager){
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(binding.getRoot().getContext(), 3);
                    binding.imageList.setLayoutManager(gridLayoutManager);
                }else{
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager((binding.getRoot().getContext()));
                    binding.imageList.setLayoutManager(linearLayoutManager);
                }
            }
        });
    }

}