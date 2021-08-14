package com.number4.dogbreeds.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
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

public class MainFragment extends Fragment implements DogAdapter.Callback {
    private List<DogModel> dogModels = new ArrayList<>();
    ArrayList<DogModel> filterDog = new ArrayList<>();
    static DogAdapter dogAdapter;
    private Callback callback;
    private String dataFromActivity;
    private String searchText;
    static final String ROW_INDEX = "row_index";
    private static final String DATA_FROM_ACTIVITY = "data_from_activity";
    public static final int VIEW_DETAIL_REQUEST = 1;
    private static final String DATA_FROM_SEARCH = "data_form_search";
    RecyclerView recyclerView;


    public MainFragment() {
    }

    public static MainFragment newInstance(String dataFromActivity) {
        Fragment fm = new Fragment();
        Bundle argument = new Bundle();
        argument.putString(DATA_FROM_ACTIVITY, dataFromActivity);
        fm.setArguments(argument);
        return new MainFragment();
    }
    public  static  MainFragment instanceForSearch(String searchText){
        MainFragment mainFragment = new MainFragment();
        Bundle arg = new Bundle();
        arg.putString(DATA_FROM_SEARCH, searchText);
        mainFragment.setArguments(arg);

        return mainFragment;
    }

    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        generateData();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (getArguments() != null) {
            this.dataFromActivity = getArguments().getString(DATA_FROM_ACTIVITY, null);
            this.searchText = getArguments().getString(DATA_FROM_SEARCH,null);

        }
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupView(view);
        MainActivity.dogViewModel.getAllDogs().observe(getViewLifecycleOwner(), new Observer<List<DogModel>>() {
            @Override
            public void onChanged(@Nullable List<DogModel> dogs) {
                if (dogModels != dogs)
                    dogModels = dogs;
                if (MainActivity.dogModels != dogs)
                    MainActivity.dogModels = dogs;
                if (searchText == null || searchText.equals(""))
                    dogAdapter.setDog(dogs);
            }
        });
    }

    private List<DogModel> generateData() {
        //dogModels = MainActivity.dogModels;
//        ArrayList<DogModel> newDogModel = new ArrayList<>();
//
//        dogModels.clear();
//        for (DogModel dog:MainActivity.dogModels ) {
//            if (dog.getName().toLowerCase().contains(this.searchText) || dog.getPlace().toLowerCase().contains(this.searchText)) {
//                newDogModel.add(dog);
//            }
//        }
        return dogModels;
    }

    private void setupView(View view) {
        recyclerView = view.findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        Search();
    }

    private  void Search(){
        if (this.searchText != null && !this.searchText.equals("") ) {

            filterDog.clear();
            for (DogModel dog : MainActivity.dogModels) {

                if (dog.getName().toLowerCase().contains(this.searchText) || dog.getPlace().toLowerCase().contains(this.searchText)) {
                    filterDog.add(dog);
                }
            }
            dogAdapter = new DogAdapter(filterDog, this);
            recyclerView.setAdapter(dogAdapter);
        }

        else {
            filterDog.clear();
            dogAdapter = new DogAdapter(MainActivity.dogModels, this);
            recyclerView.setAdapter(dogAdapter);
        }

    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }


    @Override
    public void onClickItem(int position) {
        List<DogModel> tempDogList = filterDog.size() >0 ? filterDog: dogModels;
        Intent image3dintent= new Intent(getActivity(), ThreeDimensionModelActivity.class);
        image3dintent.putExtra(ROW_INDEX,position);
        DogModel currentDog2 = tempDogList.get(position);
        image3dintent.putExtra(DetailActivity.NAME,currentDog2.getName());

        Intent intent = new Intent(getActivity(), DetailActivity.class);
        intent.putExtra(ROW_INDEX, position);
        DogModel currentDog = tempDogList.get(position);
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
        DetailActivity.dogModels = tempDogList;

        startActivityForResult(intent, VIEW_DETAIL_REQUEST);
        if (callback != null) {
            callback.doSomeThing();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        generateData();
        dogAdapter.notifyDataSetChanged();
        Search();
    }

    public interface Callback {
        void doSomeThing();
    }


}
