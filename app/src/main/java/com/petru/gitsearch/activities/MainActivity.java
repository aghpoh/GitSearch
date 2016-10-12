package com.petru.gitsearch.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.petru.gitsearch.R;
import com.petru.gitsearch.fragments.ShowContentFragment;
import com.petru.gitsearch.interfaces.SearchListener;

public class MainActivity extends AppCompatActivity implements SearchListener {

    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).build();
        ImageLoader.getInstance().init(config);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle(null);


    }

    // THIS IS  CALED WHEN CALLED THE BUTTON - METHOD FROM INTERFACE
    @Override
    public void changeContent(String user) {
        ShowContentFragment showContentFragment = (ShowContentFragment) getFragmentManager().findFragmentById(R.id.showFragment);
        showContentFragment.changeContentText(user);
    }
}
