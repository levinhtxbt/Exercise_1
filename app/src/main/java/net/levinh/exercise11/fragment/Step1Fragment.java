package net.levinh.exercise11.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import net.levinh.exercise11.Model.Info;
import net.levinh.exercise11.R;
import net.levinh.exercise11.Activity.UserSignUpFragmentActivity;
import net.levinh.exercise11.Utils.BitmapUtils;
import net.levinh.exercise11.Utils.TakingPhotoUtils;
import net.levinh.exercise11.Utils.Utils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Levin on 01/05/2016.
 */
public class Step1Fragment extends BaseFragment {

    private static final int REQUEST_TAKE_PHOTO = 1;
    private static final String FILE_NAME = "test";
    Info mInfo;

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

    String mCurrentPhotoPath = "";

    @Override
    protected int getFragmentID() {
        return R.layout.activity_sign_up_step1;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, v);
        return v;
    }


    public boolean putInfo() {
        if (isValid()) {
            mInfo = new Info();
            mInfo.setFirstName(txtFirstName.getText().toString());
            mInfo.setLastName(txtLastName.getText().toString());
            mInfo.setEmail(txtEmail.getText().toString());
            mInfo.setPhoneNumber(txtPhoneNumber.getText().toString());
            mInfo.setGender(rdoMale.isChecked());
            mInfo.setImageProfile(mCurrentPhotoPath);
            return true;
        }
        return false;
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
            Toast.makeText(getActivity(), "You must choose an avatar", Toast.LENGTH_LONG).show();
            flag = false;
        }
        return flag;
    }


    @OnClick(R.id.btnNext)
    public void onClick(View v) {
        if (putInfo()) {
            Bundle bundle = new Bundle();
            bundle.putSerializable(UserSignUpFragmentActivity.INFO_DATA, mInfo);

            Step2Fragment fragment = new Step2Fragment();
            fragment.setArguments(bundle);
            ((UserSignUpFragmentActivity) getActivity()).addFragment(fragment);
        }
    }

    @OnClick(R.id.imgViewProfile)
    public void onImageViewClicked(View v) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_TAKE_PHOTO);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == getActivity().RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            if (imageBitmap != null) {
                imgViewProfile.setImageBitmap(imageBitmap);
                mCurrentPhotoPath = BitmapUtils.saveBitmap(imageBitmap, TakingPhotoUtils.FILE_NAME).getAbsolutePath();
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
