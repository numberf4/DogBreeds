package com.number4.dogbreeds.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.number4.dogbreeds.Models.DogModel;
import com.number4.dogbreeds.R;

import java.util.ArrayList;
import java.util.List;


public class DetailActivity extends AppCompatActivity {

    private static String TAG = "TAG";
    public static final String NAME = "com.number4.dogbreeds.NAME";
    public static final String IMAGE = "com.number4.dogbreeds.IMAGE";
    public static final String HEIGHT = "com.number4.dogbreeds.HEIGHT";
    public static final String WEIGHT = "com.number4.dogbreeds.WEIGHT";
    public static final String ORIGIN = "com.number4.dogbreeds.ORIGIN";
    public static final String LIFESPAN = "com.number4.dogbreeds.LIFESPAN";
    public static final String PLACE = "com.number4.dogbreeds.PLACE";
    public static final String DESCRIPTION = "com.number4.dogbreeds.DESCRIPTION";
    public static final String IS_FAVORITE = "com.number4.dogbreeds.IS_FAVORITE";
    public static final String POSITION = "com.number4.dogbreeds.POSITION";

    TextView txtName,txtHeight,txtWeight,txtOrigin,txtLifeSpan,txtPlace, txtDescription;
    ImageView imageView;
    ImageButton imageFavorite;
    boolean isFavorite;
    int position;
    public  static List<DogModel> dogModels;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_database);
        ActionBar actionBar =getSupportActionBar();
        actionBar.hide();
        setupView();
    }

    private void setupView() {

        // anh xa
        txtName = findViewById(R.id.txtNameDetails);
        imageView = findViewById(R.id.imgViewDetails);
        txtHeight = findViewById(R.id.txtHeightOfDog);
        txtWeight =findViewById(R.id.txtWeightOfDog);
        txtOrigin = findViewById(R.id.txtOrigin);
        txtLifeSpan = findViewById(R.id.txtLifeSpan);
        txtPlace = findViewById(R.id.txtMostPopular);
        txtDescription = findViewById(R.id.txtDescription);
        imageFavorite = findViewById(R.id.imgFavorite);

        Intent intent = getIntent();

        String name = intent.getStringExtra(NAME);
        txtName.setText(name);
        int imageId = intent.getIntExtra(IMAGE, R.drawable.affenpinscher);
        imageView.setImageResource(imageId);
        String height = intent.getStringExtra(HEIGHT);
        txtHeight.setText(height);
        String weight = intent.getStringExtra(WEIGHT);
        txtWeight.setText(weight);
        String origin = intent.getStringExtra(ORIGIN);
        txtOrigin.setText(origin);
        String lifespan = intent.getStringExtra(LIFESPAN);
        txtLifeSpan.setText(lifespan);
        String place = intent.getStringExtra(PLACE);
        txtPlace.setText(place);
        String description = intent.getStringExtra(DESCRIPTION);
        txtDescription.setText(description);

        position = intent.getIntExtra(POSITION, -1);

        isFavorite = intent.getBooleanExtra(IS_FAVORITE, false);
        setImageFavorite(isFavorite);
        imageFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isFavorite = !isFavorite;
                setImageFavorite(isFavorite);
            }
        });
        findViewById(R.id.btn3DModel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String file3d =  dogModels.get(position).getFileName3D();
                if (!file3d.equals("")) {
                    Intent myIntent = new Intent(DetailActivity.this, ThreeDimensionModelActivity.class);
                    myIntent.putExtra(ThreeDimensionModelActivity.FILENAME3D,file3d);
                    startActivity(myIntent);
                }else {
                    Toast.makeText(DetailActivity.this, "Chưa có dữ liệu",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void setImageFavorite(boolean isFavorite){
        imageFavorite.setImageResource(isFavorite ? R.drawable.favorite_on : R.drawable.favorite_off);
        for (int i = 0; i < MainActivity.dogModels.size(); i++) {
            DogModel dog = MainActivity.dogModels.get(i);
            if (dog.getName().equals(dogModels.get(position).getName())){
                MainActivity.dogModels.get(i).setFavorite(isFavorite);
                MainActivity.UpdateData(MainActivity.dogModels.get(i));
            }
        }
//        imageFavorite.setImageResource(isFavorite ? R.drawable.favorite_on : R.drawable.favorite_off);
//        MainActivity.dogModels.get(position).setFavorite(isFavorite);
//        MainActivity.UpdateData(MainActivity.dogModels.get(position));
    }


    public void doSomethingInActivity(){
        Log.e(TAG, "this method call from activity");
    }


}
