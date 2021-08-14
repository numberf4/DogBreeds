package com.number4.dogbreeds.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.number4.dogbreeds.Activity.DetailActivity;
import com.number4.dogbreeds.Activity.MainActivity;
import com.number4.dogbreeds.Activity.ThreeDimensionModelActivity;
import com.number4.dogbreeds.Adapter.DogAdapter;
import com.number4.dogbreeds.Models.DogModel;
import com.number4.dogbreeds.R;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;


public class FavoriteFragment extends Fragment implements DogAdapter.Callback {
    private List<DogModel> dogModels= new ArrayList<>();
    DogAdapter dogAdapter;
    private Callback callback;
    private String dataFromActivity;
    static final String ROW_INDEX = "row_index";
    private static final String DATA_FROM_ACTIVITY = "data_from_activity";
    public static final int VIEW_DETAIL_REQUEST = 1;

    public FavoriteFragment() {
    }
    public  static  MainFragment newInstance(String dataFromActivity){
        Fragment fm= new Fragment();
        Bundle argument= new Bundle();
        argument.putString(DATA_FROM_ACTIVITY, dataFromActivity);
        fm.setArguments(argument);
        return  new MainFragment();
    }
    public void onAttach(@NonNull Context context){
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        if (getArguments()!= null){
            this.dataFromActivity= getArguments().getString(DATA_FROM_ACTIVITY,null);
        }
        return inflater.inflate(R.layout.fragment_favorite, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        generateData();
        setupView(view);
    }

    private List<DogModel> generateData(){
        dogModels.clear();
        for (DogModel dog:MainActivity.dogModels ) {
            if(dog.isFavorite())
                dogModels.add(dog);
        }
        return dogModels;
    }
    private void setupView(View view){
        RecyclerView recyclerView= view.findViewById(R.id.recyclerViewFavorite);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        dogAdapter= new DogAdapter(dogModels, this);
        recyclerView.setAdapter(dogAdapter);
    }


    @Override
    public void onClickItem(int position) {
//        Intent image3dintent= new Intent(getActivity(), ThreeDimensionModelActivity.class);
//        image3dintent.putExtra(ROW_INDEX,position);
//        DogModel currentDog2 = dogModels.get(position);
//        image3dintent.putExtra(DetailActivity.NAME,currentDog2.getName());

        Intent intent = new Intent(getActivity(), DetailActivity.class);
        intent.putExtra(ROW_INDEX, position);
        DogModel currentDog = dogModels.get(position);
        intent.putExtra(DetailActivity.NAME, currentDog.getName());
        intent.putExtra(DetailActivity.IMAGE, currentDog.getThump());
        intent.putExtra(DetailActivity.HEIGHT, currentDog.getHeight());
        intent.putExtra(DetailActivity.WEIGHT, currentDog.getWeight());
        intent.putExtra(DetailActivity.ORIGIN, currentDog.getOrigin());
        intent.putExtra(DetailActivity.LIFESPAN, currentDog.getLifeSpan());
        intent.putExtra(DetailActivity.PLACE, currentDog.getPlace());
        intent.putExtra(DetailActivity.DESCRIPTION, currentDog.getDescription());
        intent.putExtra(DetailActivity.IS_FAVORITE, currentDog.isFavorite());
        intent.putExtra(DetailActivity.POSITION, position);
        DetailActivity.dogModels = dogModels;

        startActivityForResult(intent, VIEW_DETAIL_REQUEST);
        if (callback!=null){
            callback.doSomeThing();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        generateData();
        dogAdapter.notifyDataSetChanged();
    }

    public interface Callback{
        void doSomeThing();
    }



}
