package com.example.example_day10;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.Random;

public class DemoAsyncTask extends AsyncTask<Void, Integer, Void> //Tham số đầu tiên là tham số đầu vào, tham số thứ hai là thma số cập nhật tiến độ, tham số thứ 3 là kiểu data trả về
{
    TextView textView;

    int id;
    public DemoAsyncTask(TextView textView) {
        this.textView = textView;
        Random r = new Random();
        id =r.nextInt();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        int i = 0;
        while (i < 10)
        {
            Log.e("DemoAsyncTask: "+String.valueOf(id), "Gửi dữ liệu cập nhật: "+String.valueOf(i));

            publishProgress(i);
            i++;
            try {
                Thread.sleep(2000);
            }
            catch (InterruptedException e)
            {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        //Chạy trước khi vào doInBackground
        Log.e("DemoAsyncTask", "Bắt đầu vào doInBackground");
        textView.setText("START");
    }

    @NonNull
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        //Cập nhật liên tục từ doInBackground
        Log.e("DemoAsyncTask: "+String.valueOf(id), "Dữ liệu cập nhật: "+String.valueOf(values[0]));
        textView.setText(String.valueOf(values[0]));
    }

    @Override
    protected void onPostExecute(Void unused) {
        super.onPostExecute(unused);
        //Sau khi doInBackground hoàn thành
        Log.e("DemoAsyncTask: "+String.valueOf(id), "Hoàn thành");
        textView.setText("DONE");
    }
}
