package com.freddy.kulachat.media;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.util.Log;

import com.freddy.kulachat.utils.StringUtil;

import java.io.File;

public class MediaScanner {

    private Context context;
    private String[] imageProjection;

    public MediaScanner(Context context) {
        this.context = context;
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            imageProjection = new String[]{
                    MediaStore.Images.Media._ID,
                    MediaStore.Images.Media.DISPLAY_NAME,
                    MediaStore.Images.Media.BUCKET_DISPLAY_NAME,
                    MediaStore.Images.Media.SIZE,
                    MediaStore.Images.Media.WIDTH,
                    MediaStore.Images.Media.HEIGHT,
                    MediaStore.Images.Media.MIME_TYPE,
                    MediaStore.Images.Media.DATE_ADDED,
            };
        }else {
            imageProjection = new String[]{
                    MediaStore.Images.Media._ID,
                    MediaStore.Images.Media.DISPLAY_NAME,
                    MediaStore.Images.Media.SIZE,
                    MediaStore.Images.Media.WIDTH,
                    MediaStore.Images.Media.HEIGHT,
                    MediaStore.Images.Media.MIME_TYPE,
                    MediaStore.Images.Media.DATE_ADDED,
                    MediaStore.Images.Media.DATA,
            };
        }
    }

    public void loadMedias(MediaType mediaType) {
        switch (mediaType) {
            case Image:
                String selection = MediaStore.Images.Media.MIME_TYPE + "=? or "
                        + MediaStore.Images.Media.MIME_TYPE + "=? or "
                        + MediaStore.Images.Media.MIME_TYPE + "=?";
                String[] selectionArgs = new String[]{"image/jpeg", "image/png", "image/webp"};
                Cursor cursor = context.getContentResolver().query(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        imageProjection,
                        selection,
                        selectionArgs,
                        MediaStore.Images.Media.DATE_ADDED + " DESC"
                );
                while(cursor != null && cursor.moveToNext()) {
                    int id = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID));
                    String name = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME));
                    long size = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.SIZE));
                    int width = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.WIDTH));
                    int height = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.HEIGHT));
                    String mimeType = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.MIME_TYPE));
                    long time = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATE_ADDED));
                    String folderName = null;
                    String path;
                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                        path = MediaStore.Images.Media.EXTERNAL_CONTENT_URI.buildUpon().appendPath(String.valueOf(id)).build().toString();
                        folderName = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME));
                    }else {
                        path = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA));
                        if(StringUtil.isNotEmpty(path)) {
                            File file = new File(path);
                            folderName = file.getParentFile().getPath();
                        }
                    }
                    Media media = new Media(id, name, size, width, height, mimeType, time, folderName, Uri.parse(path));
                    Log.d("MediaScanner", "media = " + media);
                }
                if(cursor != null) {
                    cursor.close();
                }
                break;
        }
    }
}
