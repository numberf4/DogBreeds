package com.number4.dogbreeds.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ImageView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import com.number4.dogbreeds.R;

public class ThreeDimensionModelActivity extends AppCompatActivity {
    public  static  final  String FILENAME3D ="com.number4.dogbreeds.Activity.FILENAME3D";
    private int imageIndex = 1;
    private int currentX = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.three_dimension);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        Intent data = getIntent();
        String fileName3D = data.getStringExtra(FILENAME3D);
        setupEvent(fileName3D);
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setupEvent(String filename){

        String finalName =filename;
        ImageView imageView = findViewById(R.id.dogDimension);
        imageView.setOnTouchListener((view, event) -> {
            final int X = (int) event.getRawX();
            switch (event.getAction() & MotionEvent.ACTION_MASK) {
                case MotionEvent.ACTION_MOVE:
                    Log.e("X", String.valueOf(X));
                    Log.e("CurrentX", String.valueOf(currentX));
                    Log.e("Index", String.valueOf(imageIndex));
                    if (currentX > X && imageIndex > 2) {
                        imageIndex--;
                    } else if(imageIndex <= 49) {
                        imageIndex++;
                    }
                    currentX = X;

                    Integer source = getResources().getIdentifier(finalName + imageIndex, "drawable", getPackageName());
                    imageView.setImageResource(source);
                    break;
            }

            return true;
        });
    }
}
