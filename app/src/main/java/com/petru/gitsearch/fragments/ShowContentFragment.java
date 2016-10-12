package com.petru.gitsearch.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.petru.gitsearch.R;
import com.petru.gitsearch.adapters.AdapterUsers;
import com.petru.gitsearch.asynctask.UserSearchAsyncTask;
import com.petru.gitsearch.constants.Constants;
import com.petru.gitsearch.entities.User;
import com.petru.gitsearch.interfaces.AdapterClickListener;


/**
 * Created by Petru on 10/11/2016.
 */

public class ShowContentFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private AdapterUsers adapterUsers;

    // IMPLEMENT INTERFACE AS A TYPE - GET CURRENT POSITION ON CLICK
    private AdapterClickListener adapterClickListener = new AdapterClickListener() {
        @Override
        public void onClick(User user) {
            showDialogInfo(user);
        }
    };


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_show, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_user_row);
        // SET CONTENT TO ADAPTER
        adapterUsers = new AdapterUsers(getActivity().getApplicationContext(), adapterClickListener);
        mRecyclerView.setAdapter(adapterUsers);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(linearLayoutManager);

        return view;
    }

    // CHANGE RECYCLER VIEW CONTENT
    public void changeContentText(String user) {
        new UserSearchAsyncTask(adapterUsers).execute(user);
    }

    // OPEN DIALOG INFORMATION FRAGMENT
    private void showDialogInfo(User user) {
        DialogInfoFragment dialogFragment = new DialogInfoFragment();
        // SET POSITION PARAMETER
        Bundle bundle = new Bundle();
        bundle.putString(Constants.LOGIN_KEY, user.getLogin());
        bundle.putString(Constants.AVATAR_KEY, user.getAvatarUrl());
        dialogFragment.setArguments(bundle);
        // OPEN DIALOG FRAGMENT

        dialogFragment.show(getFragmentManager(), "Info");
    }
}
