package com.example.livedataviewmodelexample.viewmodel;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class MyViewModel extends ViewModel {

    private static MyViewModel instances;
    private static String TAG = MyViewModel.class.getSimpleName();
    private static List<String> nameStringList = new ArrayList<String>();
    ;
    private MutableLiveData<List<String>> nameList;
    private MutableLiveData<Boolean> showProgressBar;


    public MyViewModel() {

    }

    public LiveData<Boolean> getShowProgressBar() {
        return showProgressBar;
    }

    public LiveData<List<String>> getNameList() {

        if (nameList == null) {
            nameList = new MutableLiveData<>();
            if (showProgressBar == null) {
                showProgressBar = new MutableLiveData<>();
            }
            loadList();
        }
        return nameList;
    }

    //Get the nameList through Asynch task
    public void loadList() {

        GetNames myTask = new GetNames();
        String sleepTime = "5";
        myTask.execute(sleepTime);


    }


    /// Quick and dirty solution to read the nameList and progressbar flag

    public class GetNames extends AsyncTask<String, String, String> {

        private String resp;


        @Override
        protected String doInBackground(String... params) {
            publishProgress("Sleeping..."); // Calls onProgressUpdate()
            try {
                if (nameStringList != null) {
                    nameStringList.add("Rock");
                    nameStringList.add("Alladin");
                    nameStringList.add("Broke");
                    nameStringList.add("Steve");
                    nameStringList.add("Mike");
                    nameStringList.add("Sam");
                }
                int time = Integer.parseInt(params[0]) * 1000;

                Thread.sleep(time);
                resp = "Slept for " + params[0] + " seconds";
            } catch (InterruptedException e) {
                e.printStackTrace();
                resp = e.getMessage();
            } catch (Exception e) {
                e.printStackTrace();
                resp = e.getMessage();
            }
            return resp;
        }

        @Override
        protected void onPostExecute(String result) {
            // execution of result of Long time consuming operation
            nameList.setValue(nameStringList);
            showProgressBar.setValue(false);
        }

        @Override
        protected void onPreExecute() {
            showProgressBar.setValue(true);
        }

        @Override
        protected void onProgressUpdate(String... text) {
        }
    }
}





