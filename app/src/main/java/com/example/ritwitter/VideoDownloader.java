package com.example.ritwitter;
import android.content.Context;
import android.os.Environment;
import android.widget.Toast;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
public class VideoDownloader {
    public static void downloadVideo(Context context, String videoUrl) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.twitter.com/")
                .build();
        TwitterApiService service = retrofit.create(TwitterApiService.class);
        Call<ResponseBody> call = service.downloadVideo(videoUrl);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    saveVideoToDisk(context, response.body());
                } else {
                    Toast.makeText(context, "Failed to download video", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(context, "Failed to download video", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private static void saveVideoToDisk(Context context, ResponseBody responseBody) {
        try {
            File downloadsDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            File videoFile = new File(downloadsDir, "twitter_video.mp4");
            InputStream inputStream = responseBody.byteStream();
            FileOutputStream outputStream = new FileOutputStream(videoFile);
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            outputStream.flush();
            inputStream.close();
            outputStream.close();
            Toast.makeText(context, "Video saved to Downloads folder", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(context, "Failed to save video", Toast.LENGTH_SHORT).show();
        }
    }
}