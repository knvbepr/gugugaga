package com.an.gugugaga;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private int[] audioFiles = {R.raw.gg1, R.raw.gg2, R.raw.gg3, R.raw.gg4, R.raw.gg5, R.raw.lg1, R.raw.lg2};
    private List<Integer> audioFilesList;
    private Random random = new Random();
    private int currentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 初始化音频文件列表并打乱顺序
        audioFilesList = new ArrayList<>();
        for (int audioFile : audioFiles) {
            audioFilesList.add(audioFile);
        }
        Collections.shuffle(audioFilesList);

        // 点击图片播放音频
        ImageView imageView = findViewById(R.id.imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "竟敢误拭灯", Toast.LENGTH_SHORT).show();
                playAudio();
            }
        });
    }

    private void playAudio() {
        if (currentIndex >= audioFilesList.size()) {
            // 如果所有音频文件都已播放，重新打乱顺序
            Collections.shuffle(audioFilesList);
            currentIndex = 0;
        }

        if (mediaPlayer != null) {
            mediaPlayer.release();
        }

        mediaPlayer = MediaPlayer.create(this, audioFilesList.get(currentIndex));
        mediaPlayer.start();
        currentIndex++;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
