package grant.com.apiguy.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import grant.com.apiguy.R;
import grant.com.apiguy.databinding.ItemImageBinding;

public class ShibeAdapter extends RecyclerView.Adapter<ShibeAdapter.ShibeViewHolder> {


    private List<String> urls;

    static class ShibeViewHolder extends RecyclerView.ViewHolder{

        private final ItemImageBinding binding;
        public ShibeViewHolder(@NonNull ItemImageBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void loadUrl(String url) {
            binding.tvUrl.setText(url);
        }
    }

    public ShibeAdapter(List<String> urls){
        this.urls = urls;
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
    }

    @Override
    public int getItemCount() {
        return urls.size();
    }



}
