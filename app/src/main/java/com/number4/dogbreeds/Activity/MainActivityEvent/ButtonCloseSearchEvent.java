package com.number4.dogbreeds.Activity.MainActivityEvent;

import android.view.View;
import android.widget.LinearLayout;

import com.number4.dogbreeds.Activity.MainActivity;
import com.number4.dogbreeds.Fragment.MainFragment;
import com.number4.dogbreeds.R;

public class ButtonCloseSearchEvent implements View.OnClickListener {
    private MainActivity mainActivity;
    public ButtonCloseSearchEvent(MainActivity activity) {
        mainActivity = activity;
    }
    @Override
    public void onClick(View view) {
        LinearLayout logoAndTitle = view.getRootView().findViewById(R.id.logoAndTitle);
        LinearLayout searchBar = view.getRootView().findViewById(R.id.searchBar);

        logoAndTitle.setVisibility(View.VISIBLE);
        searchBar.setVisibility(View.GONE);
        MainFragment mainFragment = MainFragment.newInstance("");
        this.mainActivity.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, mainFragment, "TAG")
                .addToBackStack(null)
                .commit();
    }
}
