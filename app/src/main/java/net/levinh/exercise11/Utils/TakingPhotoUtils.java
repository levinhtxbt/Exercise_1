package net.levinh.exercise11.Utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by levinh on 05/08/2016.
 */
public class TakingPhotoUtils {
    private static final String TAG = "AlbumUtils";
    private static final String ALBUM_NAME = "CameraSample";
    private static final String JPEG_FILE_PREFIX = "IMG_";
    public static final String JPEG_FILE_SUFFIX = ".jpg";
    public static final String FILE_NAME = "TEST";

    BaseAlbumDirFactory baseAlbumDirFactory;

    Context mContext;

    public TakingPhotoUtils(Context context) {
        this.mContext = context;
        baseAlbumDirFactory = new BaseAlbumDirFactory();
    }

    private File getAlbumDir() {
        File storageDir = null;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            storageDir = baseAlbumDirFactory.getAlbumStorageDir(ALBUM_NAME);
            Log.d(TAG, "getAlbumDir: "+storageDir.getAbsolutePath());
            if (storageDir != null) {
                if (!storageDir.mkdirs()) {
                    if (!storageDir.exists()) {
                        Log.d("CameraSample", "failed to create directory");
                        return null;
                    }
                }
            }
        } else {
            Log.v(TAG, "External storage is not mounted READ/WRITE.");
        }
        return storageDir;
    }



    public File createImageFile() throws IOException {
        // Create an image file name
//        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
//         imageFileName = JPEG_FILE_PREFIX + timeStamp + "_";
        File albumF = getAlbumDir();
        File imageF = File.createTempFile(FILE_NAME, JPEG_FILE_SUFFIX, albumF);
        return imageF;
    }

    public void galleryAddPic(String filePatch) {
        Intent mediaScanIntent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
        File f = new File(filePatch);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        mContext.sendBroadcast(mediaScanIntent);
    }
}
