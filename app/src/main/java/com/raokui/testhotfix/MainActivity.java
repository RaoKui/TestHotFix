package com.raokui.testhotfix;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.io.File;

public class MainActivity extends AppCompatActivity {



    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.tv_result);

        findViewById(R.id.btn_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Caculater caculater = new Caculater();
                textView.setText("结果：" + caculater.add());
            }
        });
        findViewById(R.id.btn_fix).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fix();
            }
        });
    }


    private void fix() {
        File file = new File(Environment.getExternalStorageDirectory(), "out.dex");
        DexManager dexManager = new DexManager(this);
        dexManager.load(file);
    }

}
