package net.levinh.exercise11.fragment;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import net.levinh.exercise11.Model.Info;
import net.levinh.exercise11.R;
import net.levinh.exercise11.SignUpStep2Activity;
import net.levinh.exercise11.UserSignUpFragmentActivity;

/**
 * Created by Levin on 01/05/2016.
 */
public class Step1Fragment extends Fragment implements View.OnClickListener{
    static Info mInfo;
    EditText txtFirstName, txtLastName, txtEmail, txtPhoneNumber;
    RadioGroup rdoGroup;
    RadioButton rdoMale, rdoFemale;
    Button btnNext;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(getIDFragment(), container, false);
        initView(fragmentView);
        return fragmentView;
    }

    public int getIDFragment() {
        return R.layout.activity_main;
    }

    public void initView(View view) {
        txtFirstName = (EditText) view.findViewById(R.id.txtFirstName);
        txtLastName = (EditText) view.findViewById(R.id.txtLastName);
        txtEmail = (EditText) view.findViewById(R.id.txtEmail);
        txtPhoneNumber = (EditText) view.findViewById(R.id.txtPhoneNumber);
        rdoGroup = (RadioGroup) view.findViewById(R.id.rdoGroup);
        rdoFemale = (RadioButton) view.findViewById(R.id.rdoFemale);
        rdoMale = (RadioButton) view.findViewById(R.id.rdoMale);
        btnNext = (Button) view.findViewById(R.id.btnNext);
        btnNext.setOnClickListener(this);
    }

    public boolean putInfor() {
        if (isValid()) {
            mInfo = new Info();
            mInfo.setFirstName(txtFirstName.getText().toString());
            mInfo.setLastName(txtLastName.getText().toString());
            mInfo.setEmail(txtEmail.getText().toString());
            mInfo.setPhoneNumber(txtPhoneNumber.getText().toString());
            mInfo.setGender(rdoMale.isChecked());
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
        if (!isValidEmail(txtEmail.getText())) {
            txtEmail.setError("Email is invalid");
            flag = false;
        }
        return flag;
    }

    public final static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    @Override
    public void onClick(View v) {
        if(v.getId()== R.id.btnNext){
//            Log.d("Step 1","Click on button Next");
            if(putInfor())
            {
                ((UserSignUpFragmentActivity)getActivity()).addFragment(new Step2Fragment());
            }
        }

    }
}
