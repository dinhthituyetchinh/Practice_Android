package com.example.example;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Random;

public class DemoContentProvider extends ContentProvider {

    //Trong Mainfest cũng phải khai báo
    private static  final  String AUTHORITY = DemoContentProvider.class.getCanonicalName();
    private  static  final UriMatcher URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);

    static{
    URI_MATCHER.addURI(AUTHORITY,"random-number" , 123);
        URI_MATCHER.addURI(AUTHORITY,"read-contact" , 124);
    }
    @Override
    public boolean onCreate() {
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
       // Cung cấp dữ liệu cho bên ứng dụng muốn đọc
        int type = URI_MATCHER.match(uri);
        if(type == 123)
        {
            //
            Random random = new Random();
            int number = random.nextInt();
            MatrixCursor matrixCursor = new MatrixCursor(
                    new String[]{"data"}, 1 //Số cột, số dòng
            );

            matrixCursor.addRow(new Integer[] {number});
            Log.e("DemoContentProvider", "Gọi vào query random");
            return matrixCursor;
        }
        else if(type == 124)
        {
            //Read...
        }



        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
