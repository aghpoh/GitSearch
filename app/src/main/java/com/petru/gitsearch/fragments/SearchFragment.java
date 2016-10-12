package com.petru.gitsearch.fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.petru.gitsearch.R;
import com.petru.gitsearch.interfaces.SearchListener;


/**
 * Created by Petru on 10/11/2016.
 */

public class SearchFragment extends Fragment {

    private Button mSearchBtn;
    private EditText mUserText;
    private SearchListener searchListener;


    private View.OnClickListener searchBtnListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (mUserText.getText().length() > 0) {
                searchListener.changeContent(mUserText.getText().toString());
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_search, container, false);
        mUserText = (EditText) view.findViewById(R.id.user_name);
        mSearchBtn = (Button) view.findViewById(R.id.search_button);
        mSearchBtn.setOnClickListener(searchBtnListener);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        searchListener = (SearchListener) getActivity();
    }
}
