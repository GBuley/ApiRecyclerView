package grant.com.apiguy.view;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.imageview.ShapeableImageView;

import java.util.List;

import grant.com.apiguy.adapter.ShibeAdapter;
import grant.com.apiguy.databinding.ActivityMainBinding;
import grant.com.apiguy.viewModel.MainViewModel;

public class MainActivity extends AppCompatActivity {
    private MainViewModel viewModel;
    private ShapeableImageView shibeImage;
    private ActivityMainBinding binding;
    private Bitmap bitmap = null;
    AppCompatEditText countText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        setupViews();
        viewModel.isSuccessful().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isSuccessful) {
                Toast.makeText(MainActivity.this, "Call: " + isSuccessful, Toast.LENGTH_SHORT).show();
            }
        });
        viewModel.getShibes().observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> urls) {
                ShibeAdapter shibeAdapter = new ShibeAdapter(urls);
                binding.imageList.setAdapter(shibeAdapter);
            }
        });


    }

    private void setupViews() {
        binding.shibeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countText = binding.editTextCount;
                if (countText.getText().toString().isEmpty()) {
                    countText.setText("1");
                }
                int count = Integer.parseInt(countText.getText().toString());
                if (count < 1) {
                    count = 1;
                }
                viewModel.fetchShibes(count);
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        binding.imageList.setLayoutManager(linearLayoutManager);
    }

}