package grant.com.apiguy.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.imageview.ShapeableImageView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import grant.com.apiguy.R;
import grant.com.apiguy.databinding.ItemImageBinding;

public class ShibeAdapter extends RecyclerView.Adapter<ShibeAdapter.ShibeViewHolder> {


    private List<String> urls;
    private String imageType;

    static class ShibeViewHolder extends RecyclerView.ViewHolder{

        private final ItemImageBinding binding;
        public ShibeViewHolder(@NonNull ItemImageBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void loadUrl(String url) {
            binding.tvUrl.setText(url);
        }
        public void loadImages(String url, String imageType){
            if(!url.contains("http") && imageType.equals("Shibes")) {
                Glide.with(binding.getRoot()).load("https://cdn.shibe.online/shibes/"+url+".jpg").centerCrop().placeholder(binding.shibeImage.getDrawable()).into(binding.shibeImage);
            }
            else if(!url.contains("http") && imageType.equals("Cats")) {
                Glide.with(binding.getRoot()).load("https://cdn.shibe.online/cats/"+url+".jpg").centerCrop().placeholder(binding.shibeImage.getDrawable()).into(binding.shibeImage);
            }
            else if(!url.contains("http") && imageType.equals("Birds")) {
                Glide.with(binding.getRoot()).load("https://cdn.shibe.online/birds/"+url+".jpg").centerCrop().placeholder(binding.shibeImage.getDrawable()).into(binding.shibeImage);
            }else{
                Glide.with(binding.getRoot()).load(url).centerCrop().placeholder(binding.shibeImage.getDrawable()).into(binding.shibeImage);
            }
        }
    }

    public ShibeAdapter(List<String> urls, String imageType){
        this.urls = urls;
        this.imageType = imageType;
    }


    @NonNull
    @Override
    public ShibeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemImageBinding imageBinding= ItemImageBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ShibeViewHolder(imageBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ShibeViewHolder holder, int position) {
        String url = urls.get(position);
        holder.loadUrl(url);
        holder.loadImages(url, imageType);
    }

    @Override
    public int getItemCount() {
        return urls.size();
    }



}
