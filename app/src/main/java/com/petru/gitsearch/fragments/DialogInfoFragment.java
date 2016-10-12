package com.petru.gitsearch.fragments;

import android.app.DialogFragment;
import android.graphics.Bitmap;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.petru.gitsearch.R;
import com.petru.gitsearch.constants.Constants;

/**
 * Created by Petru on 10/12/2016.
 */

public class DialogInfoFragment extends DialogFragment {


    private TextView mTextView;
    private ImageView mImageView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dialog_info, container, false);

    }

    // HERE USED TO GET REFERENCES ON ID'S/SET BUTTON CLICK LISTENERS
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        mTextView = (TextView) view.findViewById(R.id.tv_login_show);
        mImageView = (ImageView) view.findViewById(R.id.iv_avatar);

        Bundle bundle = getArguments();
        mTextView.setText(bundle.getString(Constants.LOGIN_KEY));

        // LOAD IMAGE -AVATAR
        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.loadImage(bundle.getString(Constants.AVATAR_KEY), new SimpleImageLoadingListener() {
            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                mImageView.setImageBitmap(loadedImage);
            }
        });
    }
}
