package com.number4.dogbreeds.Activity.MainActivityEvent;

import android.view.View;
import android.widget.LinearLayout;

import com.number4.dogbreeds.R;

public class ButtonSearchEvent implements View.OnClickListener {
    @Override
    public void onClick(View view) {
        LinearLayout logoAndTitle = view.getRootView().findViewById(R.id.logoAndTitle);
        LinearLayout searchBar = view.getRootView().findViewById(R.id.searchBar);

        logoAndTitle.setVisibility(View.GONE);
        searchBar.setVisibility(View.VISIBLE);
    }
}
