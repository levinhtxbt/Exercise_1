package net.levinh.exercise11.Activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import net.levinh.exercise11.MainApplication;
import net.levinh.exercise11.Model.Info;
import net.levinh.exercise11.R;
import net.levinh.exercise11.Utils.BitmapUtils;
import net.levinh.exercise11.Utils.TakingPhotoUtils;
import net.levinh.exercise11.Utils.Utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignUpStep1Activity extends AppCompatActivity {
    private static final String TAG = "SignUpStep1Activity";
    public static final String DATA = "data";
    public static final String INFO = "info";
    private static final int REQUEST_TAKE_PHOTO = 1;
    private static final String FILE_NAME = "test";


    @Bind(R.id.txtFirstName)
    EditText txtFirstName;
    @Bind(R.id.txtLastName)
    EditText txtLastName;
    @Bind(R.id.txtEmail)
    EditText txtEmail;
    @Bind(R.id.txtPhoneNumber)
    EditText txtPhoneNumber;
    @Bind(R.id.rdoGroup)
    RadioGroup rdoGroup;
    @Bind(R.id.rdoMale)
    RadioButton rdoMale;
    @Bind(R.id.rdoFemale)
    RadioButton rdoFemale;
    @Bind(R.id.imgViewProfile)
    ImageView imgViewProfile;
    @Bind(R.id.btnNext)
    Button btnNext;
    String mCurrentPhotoPath = null;
    //private AlbumStorageDirFactory mAlbumStorageDirFactory = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_step1);
        ButterKnife.bind(this);
        isStoragePermissionGranted();
    }

    @OnClick(R.id.btnNext)
    public void onBtnNextClicked(View v) {
        if (isValid()) {
            Intent intent = new Intent(SignUpStep1Activity.this, SignUpStep2Activity.class);

            Info i = new Info();
            i.setFirstName(txtFirstName.getText().toString());
            i.setLastName(txtLastName.getText().toString());
            i.setEmail(txtEmail.getText().toString());
            i.setPhoneNumber(txtPhoneNumber.getText().toString());
            i.setGender(rdoMale.isChecked());
            i.setImageProfile(mCurrentPhotoPath);

            Bundle b = new Bundle();
            b.putSerializable(INFO, i);
            intent.putExtra(DATA, b);
            startActivity(intent);
        }
    }

    @OnClick(R.id.imgViewProfile)
    public void onImageViewProfileClicked(View v) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File f = null;
        try {
            f = setUpPhotoFile();
            mCurrentPhotoPath = f.getAbsolutePath();
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
            startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
        } catch (IOException e) {
            f = null;
            mCurrentPhotoPath = null;
            e.printStackTrace();
            Toast.makeText(this,"Cannot open camera!",Toast.LENGTH_LONG).show();
        }

    }

    public boolean isValid() {
        boolean flag = true;
        if (txtFirstName.getText().length() == 0) {
            txtFirstName.setError("You must enter FirstName");
            flag = false;
        }
        if (txtLastName.getText().length() == 0) {
            txtLastName.setError("You must enter LastName");
            flag = false;
        }
        if (txtPhoneNumber.getText().length() == 0 || txtPhoneNumber.getText().length() > 11) {
            txtPhoneNumber.setError("Phone is invalid");
            flag = false;
        }
        if (!Utils.isValidEmail(txtEmail.getText())) {
            txtEmail.setError("Email is invalid");
            flag = false;
        }
        if (TextUtils.isEmpty(mCurrentPhotoPath)) {
            Toast.makeText(this, "You must choose an avatar", Toast.LENGTH_LONG).show();
            flag = false;
        }
        return flag;
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.mnuUserFragment) {
            Intent i = new Intent(this, UserSignUpFragmentActivity.class);
            startActivity(i);
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK ) {
            setPic();
        }
    }

    private File setUpPhotoFile() throws IOException {
        File f = new TakingPhotoUtils(this).createImageFile();
        mCurrentPhotoPath = f.getAbsolutePath();
        return f;
    }

    private void setPic() {

		/* There isn't enough memory to open up more than a couple camera photos */
        /* So pre-scale the target bitmap into which the file is decoded */

		/* Get the size of the ImageView */
        int targetW = imgViewProfile.getWidth();
//        int targetH = imgViewProfile.getHeight();

//		/* Get the size of the image */
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
//        int photoW = bmOptions.outWidth;
//        int photoH = bmOptions.outHeight;
//
//		/* Figure out which way needs to be reduced less */
//        int scaleFactor = 1;
//
//
//
//        if ((targetW > 0) || (targetH > 0)) {
//            scaleFactor = Math.min(photoW / targetW, photoH / targetH);
//        }
//
		/* Set bitmap options to scale the image decode target */
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize =1;
        bmOptions.inPurgeable = true;
		/* Decode the JPEG file into a Bitmap */
        Bitmap bitmap = BitmapFactory.decodeFile(mCurrentPhotoPath,bmOptions);
        bitmap = BitmapUtils.getResizedBitmapWithRatioNewWidth(bitmap, targetW);
        //Log.d(TAG, "setPic: " + mCurrentPhotoPath);
        /* Associate the Bitmap to the ImageView */
        imgViewProfile.setImageBitmap(bitmap);
        mCurrentPhotoPath = BitmapUtils.saveBitmap(bitmap,FILE_NAME).getAbsolutePath();
//        Log.d(TAG, "setPic: " + f.getAbsolutePath());
//        new TakingPhotoUtils(this).galleryAddPic(mCurrentPhotoPath);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Log.v(TAG, "Permission: " + permissions[0] + "was " + grantResults[0]);
            //nothing
        }else {
            this.finish();
        }
    }

    public boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v(TAG, "Permission is granted");
                return true;
            } else {
                Log.v(TAG, "Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        } else {
            //permission is automatically granted on sdk<23 upon installation
            Log.v(TAG, "Permission is granted");
            return true;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
