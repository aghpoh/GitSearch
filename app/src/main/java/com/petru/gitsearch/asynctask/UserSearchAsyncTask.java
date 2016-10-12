package com.petru.gitsearch.asynctask;

import android.os.AsyncTask;

import com.petru.gitsearch.adapters.AdapterUsers;
import com.petru.gitsearch.constants.Constants;
import com.petru.gitsearch.entities.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Petru on 10/11/2016.
 */

public class UserSearchAsyncTask extends AsyncTask<String, Void, ArrayList<User>> {

    private HttpURLConnection connection;
    private ArrayList<User> users;
    private BufferedReader reader;
    private AdapterUsers adapter;

    public UserSearchAsyncTask() {
    }

    public UserSearchAsyncTask(AdapterUsers adapter) {
        this.adapter = adapter;
    }

    @Override
    protected ArrayList<User> doInBackground(String... params) {
        if (params != null) {
            try {
                String urlString = Constants.URL_LINK + params[0];
                URL url = new URL(urlString);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                InputStream inputStream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuffer = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    stringBuffer.append(line);
                }
                users = new ArrayList<>();
                JSONObject jsonObject = new JSONObject(stringBuffer.toString());
                JSONArray arr = jsonObject.getJSONArray("items");
                for (int i = 0; i < arr.length(); i++) {

                    String id = arr.getJSONObject(i).getString("id");
                    String login = arr.getJSONObject(i).getString("login");
                    String avatarUrl = arr.getJSONObject(i).getString("avatar_url");
                    users.add(new User(Integer.parseInt(id), login, avatarUrl));
                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            users.clear();
        }
        return users;
    }

    @Override
    protected void onPostExecute(ArrayList<User> userList) {
        super.onPostExecute(userList);

        adapter.setmListUsers(userList);
        adapter.notifyDataSetChanged();
    }
}
