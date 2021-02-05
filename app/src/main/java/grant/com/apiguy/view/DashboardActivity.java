package grant.com.apiguy.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import grant.com.apiguy.R;
import grant.com.apiguy.databinding.ActivityDashboardBinding;
import grant.com.apiguy.utils.Constants;

public class DashboardActivity extends AppCompatActivity {

    private ActivityDashboardBinding binding;
    private String imageType;
    private boolean urls;
    private boolean https;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        binding = ActivityDashboardBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());

        initViews();
    }

    private void initViews() {
        binding.shibeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToShibeActivity();
            }
        });
    }

    public void onImageTypeRadioButtonClick(View view){
        boolean check = ((RadioButton ) view).isChecked();
        imageType = "Shibes";

        switch(view.getId()){
            case R.id.rb_shibes:
                if(check)
                    imageType = "Shibes";
                break;
            case R.id.birds:
                if(check)
                    imageType = "Birds";
                break;
            case R.id.cats:
                if(check)
                    imageType = "Cats";
                break;
        }
    }
    public void onUrlsRadioButtonClick(View view){
        boolean check = ((RadioButton ) view).isChecked();
        urls = true;

        switch(view.getId()){
            case R.id.url_true:
                if(check)
                    urls = true;
                break;
            case R.id.url_false:
                if(check)
                    urls = false;
                break;
        }
    }

    public void onHttpsRadioButtonClick(View view){
        boolean check = ((RadioButton ) view).isChecked();
        https = true;

        switch(view.getId()){
            case R.id.https:
                if(check)
                    https = true;
                break;
            case R.id.http:
                if(check)
                    https = false;
                break;
        }
    }


    private void goToShibeActivity() {
        int intData=1;
        boolean urlType=true;
        boolean urlSecurty=true;
        String typeOfImage = "Shibes";

        if(binding.etIntCount.getText() != null){
            String intDataString = binding.etIntCount.getText().toString();
            intData = Integer.parseInt(intDataString);
        }
        if(!https){
            urlSecurty = false;
        }

        if(!urls){
            urlType = false;
        }

        if(imageType.equals("Birds")){
            typeOfImage = "Birds";
        }else if(imageType.equals("Cats")){
            typeOfImage = "Cats";
        }else{
            typeOfImage = "Shibes";
        }



        Intent intent = new Intent(DashboardActivity.this, MainActivity.class);

        intent.putExtra(Constants.SHIBE_ACTIVITY_PARAM_INT, intData);
        intent.putExtra(Constants.SHIBE_ACTIVITY_PARAM_IMAGE_TYPE, typeOfImage);
        intent.putExtra(Constants.SHIBE_ACTIVITY_PARAM_SECURITY_TYPE, urlSecurty);
        intent.putExtra(Constants.SHIBE_ACTIVITY_PARAM_URL, urlType);
        startActivity(intent);


    }
}
