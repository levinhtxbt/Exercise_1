package net.levinh.exercise11.Utils;

import java.io.File;

/**
 * Created by levinh on 05/08/2016.
 */
public abstract class AlbumStorageDirFactory {
    public abstract File getAlbumStorageDir(String albumName);
}