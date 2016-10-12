package com.petru.gitsearch.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.petru.gitsearch.R;
import com.petru.gitsearch.constants.Constants;
import com.petru.gitsearch.entities.User;
import com.petru.gitsearch.interfaces.AdapterClickListener;

import java.util.ArrayList;

/**
 * Created by Petru on 10/11/2016.
 */

public class AdapterUsers extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    // GET CONTENT FROM XML FILE
    private LayoutInflater mInfalter;
    private ArrayList<User> mListUsers;
    private AdapterClickListener mAdapterClickListener;


    public AdapterUsers(Context context, AdapterClickListener mAdapterClickListener) {
        mInfalter = LayoutInflater.from(context);
        this.mAdapterClickListener = mAdapterClickListener;
    }

    public void setmListUsers(ArrayList<User> mListUsers) {
        this.mListUsers = mListUsers;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (mListUsers != null && mListUsers.size() > 0) {
            if (viewType == Constants.ITEM) {
                View view = mInfalter.inflate(R.layout.row_content, parent, false);
                return new UserHolder(view);
            } else {
                View view = mInfalter.inflate(R.layout.no_user, parent, false);
                return new NoItemsHolder(view);
            }
        } else {
            View view = mInfalter.inflate(R.layout.no_user, parent, false);
            return new NoItemsHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof UserHolder) {
            if (mListUsers != null) {
                UserHolder userHolder = (UserHolder) holder;
                userHolder.mTextView.setText("User: " + mListUsers.get(position).getLogin());
            }
        }
    }

    @Override
    public int getItemCount() {

        if (mListUsers != null && mListUsers.size() > 0) {
            return mListUsers.size();
        } else {
            return 1;
        }
    }

    @Override
    public int getItemViewType(int position) {

        if (mListUsers != null && mListUsers.size() > 0) {
            return Constants.ITEM;
        } else {
            return Constants.NO_ITEM;
        }
    }


    // ANONYMOUS CLASSES

    class UserHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mTextView;

        public UserHolder(View itemView) {
            super(itemView);
            // MAKE AVAILABLE ONCLICK METHOD FOR VIEWHOLDER
            itemView.setOnClickListener(this);
            mTextView = (TextView) itemView.findViewById(R.id.tv_user);
        }

        // SET POSITION AT CLICK ON ITEM IN VIEWHOLDER
        @Override
        public void onClick(View v) {
            mAdapterClickListener.onClick(mListUsers.get(getAdapterPosition()));
        }
    }

    class NoItemsHolder extends RecyclerView.ViewHolder {

        public NoItemsHolder(View view) {
            super(view);
        }
    }
}
