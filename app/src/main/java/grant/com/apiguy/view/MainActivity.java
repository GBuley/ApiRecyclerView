package grant.com.apiguy.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Network;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textview.MaterialTextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import Repo.ShibeRepository;
import grant.com.apiguy.R;
import grant.com.apiguy.adapter.ShibeAdapter;
import grant.com.apiguy.databinding.ActivityMainBinding;
import grant.com.apiguy.viewModel.MainViewModel;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;
import retrofit2.http.GET;

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