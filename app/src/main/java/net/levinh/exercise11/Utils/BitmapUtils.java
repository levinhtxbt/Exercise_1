package net.levinh.exercise11.Utils;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.Environment;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by levinh on 05/08/2016.
 */
public class BitmapUtils {

    public static Bitmap getResizedBitmap(Bitmap bm, int newWidth, int newHeight) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // CREATE A MATRIX FOR THE MANIPULATION
        Matrix matrix = new Matrix();
        // RESIZE THE BIT MAP
        matrix.postScale(scaleWidth, scaleHeight);
        // "RECREATE" THE NEW BITMAP
        Bitmap resizedBitmap = Bitmap.createBitmap(
                bm, 0, 0, width, height, matrix, false);
        return resizedBitmap;
    }

    public static Bitmap getResizedBitmapWithRatioNewWidth(Bitmap bm, int newWidth) {
        float aspectRatio = bm.getWidth() / (float) bm.getHeight();
        int width = newWidth;
        int height = Math.round(width / aspectRatio);
        Bitmap resizedBitmap = Bitmap.createScaledBitmap(
                bm, width, height, false);
        return resizedBitmap;
    }

    public static Bitmap getResizedBitmapWithRatioNewHeight(Bitmap bm, int newHeight) {
        float aspectRatio = bm.getWidth() / (float) bm.getHeight();
        int height = newHeight;
        int width = Math.round(height / aspectRatio);
        Bitmap resizedBitmap = Bitmap.createScaledBitmap(
                bm, width, height, false);

        return resizedBitmap;
    }

    public static File saveBitmap(Bitmap bitmap, String fileName) {

//        Bitmap immutableBmp = BitmapFactory.decodeFile(filePath);
//        immutableBmp = immutableBmp.copy(Bitmap.Config., true);
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
//        bitmap = BitmapUtils.getResizedBitmapWithRatioNewWidth(bitmap,400);
//        BitmapFactory.Options options = new BitmapFactory.Options();
//        options.inMutable = true;
//        options.outWidth = 400;
//        options.outHeight=400;
//        myBitmap = BitmapFactory.decodeFile(filePath,options);
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
//        filePath.setWidth(400);
//        filePath.setHeight(400);
//        filePath.compress(Bitmap.CompressFormat.JPEG, 40, bytes);

        //you can create a new file name "test.jpg" in sdcard folder.

        File f = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)
                + File.separator + fileName + TakingPhotoUtils.JPEG_FILE_SUFFIX);
        try {
            f.createNewFile();
            FileOutputStream fo = new FileOutputStream(f);
            fo.write(bytes.toByteArray());
            // remember close de FileOutput
            fo.close();

        } catch (IOException e) {

            e.printStackTrace();
        }
        return f;
    }

}
