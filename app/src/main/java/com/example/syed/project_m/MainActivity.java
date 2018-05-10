package com.example.syed.project_m;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    TextView textFile;
    EditText editText;
    String FilePath;
    private static final int PICKFILE_RESULT_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button buttonPick = findViewById(R.id.buttonpick);
        textFile = findViewById(R.id.textfile);

        buttonPick.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("file/*");
                startActivityForResult(intent, PICKFILE_RESULT_CODE);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case PICKFILE_RESULT_CODE:
                if (resultCode == RESULT_OK) {
                    FilePath = data.getData().getPath();
                    textFile.setText(FilePath);
                }
                break;

        }
    }
    public void process(View view) {
        Intent intentShareFile = new Intent(Intent.ACTION_SEND);
        File fileWithinMyDir = new File(FilePath);

        if (fileWithinMyDir.exists()) {
            intentShareFile.setType("*/*");
            intentShareFile.putExtra(Intent.EXTRA_STREAM, Uri.parse("file://" + FilePath));
            intentShareFile.setPackage("com.android.bluetooth");
            intentShareFile.putExtra(Intent.EXTRA_SUBJECT,"Sharing File...");
            intentShareFile.putExtra(Intent.EXTRA_TEXT, "Sharing File...");
            startActivity(Intent.createChooser(intentShareFile, "Share file with Bluetooth"));
        }
    }
    public void process2(View view) {
        editText = findViewById(R.id.editText2);
        String result = editText.getText().toString();
        Intent a = new Intent();
        a.setAction(Intent.ACTION_SEND);
        a.putExtra(Intent.EXTRA_TEXT, result);
        a.setType("text/plain");
        startActivity(a);
    }

    public void process3(View view) {
        Intent a = new Intent(Intent.ACTION_SEND);
        File f = new File(FilePath);
        a.putExtra(Intent.EXTRA_STREAM, Uri.parse("file://" + FilePath));
        a.setPackage("com.hardev.hifi");
        a.setType("*/*");
        startActivity(Intent.createChooser(a, "Share file with Wifi"));
    }

    public void process4(View view) {
        PackageManager pm = getPackageManager();
        Intent intent = pm.getLaunchIntentForPackage("com.hardev.hifi");
        startActivity(intent);
    }
}