package com.number4.dogbreeds.Activity.MainActivityEvent;

import android.os.Debug;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;

import com.number4.dogbreeds.Activity.MainActivity;
import com.number4.dogbreeds.Fragment.MainFragment;
import com.number4.dogbreeds.R;

public class TextSearchWatcherEvent implements TextWatcher {
    private MainActivity mainActivity;

    public TextSearchWatcherEvent(MainActivity activity) {
        mainActivity = activity;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        Log.d("TAG", "test");
        MainFragment mainFragment = MainFragment.instanceForSearch(charSequence.toString());
        this.mainActivity.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, mainFragment, "TAG")
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}
