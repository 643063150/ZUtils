package com.android.zpp.zutils;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.android.zpp.myapplication.XUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyDialog myDialog=new MyDialog();
        myDialog.show(getSupportFragmentManager(),"sss");
        XUtils.clearOrPaste(this,"");
    }
}