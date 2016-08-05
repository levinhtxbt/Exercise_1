package net.levinh.exercise11.Utils;

import android.os.Environment;

import java.io.File;

/**
 * Created by levinh on 05/08/2016.
 */
public class BaseAlbumDirFactory extends AlbumStorageDirFactory {

//    private static final String CAMERA_DIR = "/DCIM/";

    @Override
    public File getAlbumStorageDir(String albumName) {
        return new File(
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)
                        + File.separator
                        + albumName
        );
    }
}
