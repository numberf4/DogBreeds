package com.number4.dogbreeds.Adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.number4.dogbreeds.Activity.MainActivity;
import com.number4.dogbreeds.Models.DogModel;
import com.number4.dogbreeds.R;
import java.util.List;


public class DogAdapter extends RecyclerView.Adapter<DogAdapter.ViewHolder> {
    private List<DogModel> dogModels;
    private Callback callback;

    public DogAdapter(List<DogModel> dogModels, Callback callback) {
        this.dogModels = dogModels;
        this.callback = callback;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_favorite_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DogAdapter.ViewHolder holder, final int position) {

        View view = holder.getView();
        final ImageButton ibHeart = view.findViewById(R.id.imgHeart);
        boolean isFavorite = dogModels.get(position).isFavorite();
        ibHeart.setImageResource(isFavorite ? R.drawable.favorite_on : R.drawable.favorite_off);

        ibHeart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isFavorite = dogModels.get(position).isFavorite();
                dogModels.get(position).setFavorite(!isFavorite);
                ibHeart.setImageResource(!isFavorite ? R.drawable.favorite_on : R.drawable.favorite_off);
                MainActivity.UpdateData(dogModels.get(position));
                notifyDataSetChanged();
            }
        });
        setupView(view, position);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (callback == null) {
                    return;
                }
                callback.onClickItem(position);
            }
        });
    }
    private void setupView(View view, final int position) {
        final DogModel item = dogModels.get(position);
        TextView txtName = view.findViewById(R.id.txtName);
        txtName.setText(item.getName());

        ImageView imgDog = view.findViewById(R.id.imgView);
        imgDog.setImageResource(item.getThump());
        TextView txtDetails = view.findViewById(R.id.txtDetails);
        txtDetails.setText(item.getDescription());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.onClickItem(position);
            }
        });
    }

    public void setDog(List<DogModel> listDog) {
        this.dogModels = listDog;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return dogModels.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        View view;

        ViewHolder(@NonNull View view) {
            super(view);
            this.view = view;
        }

        View getView() {
            return view;
        }
    }

    public interface Callback {
        void onClickItem(int position);
    }
}
