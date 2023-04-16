package com.example.ritwitter;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText etVideoUrl = findViewById(R.id.et_video_url);
        Button btnDownload = findViewById(R.id.btn_download);

        btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String videoUrl = etVideoUrl.getText().toString();
                if (!videoUrl.isEmpty()) {
                    VideoDownloader.downloadVideo(MainActivity.this, videoUrl);
                } else {
                    Toast.makeText(MainActivity.this, "Please enter a valid Twitter video URL", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
