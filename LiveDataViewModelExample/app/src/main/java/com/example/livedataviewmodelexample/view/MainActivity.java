/*
 * By Chandra Dhakite
 */

package com.example.livedataviewmodelexample.view;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.livedataviewmodelexample.R;
import com.example.livedataviewmodelexample.viewmodel.MyViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ProgressBar progressBar;
    MyViewModel myViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        listView = findViewById(R.id.listview);
        myViewModel = ViewModelProviders.of(this).get(MyViewModel.class);
        myViewModel.getNameList().observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> nameList) {

                //UI update need to go here
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, android.R.id.text1, nameList);
                listView.setAdapter(adapter);

            }
        });

        progressBar = findViewById(R.id.progressBar);
        myViewModel.getShowProgressBar().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                //UI update need to go here
                if (aBoolean == true) {
                    progressBar.setVisibility(View.VISIBLE);
                } else
                    progressBar.setVisibility(View.GONE);
            }
        });


    }


}
