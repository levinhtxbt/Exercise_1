package net.levinh.exercise11.fragment;

import android.app.FragmentManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import net.levinh.exercise11.Model.Info;
import net.levinh.exercise11.R;
import net.levinh.exercise11.Activity.UserSignUpFragmentActivity;

import java.io.File;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Levin on 01/05/2016.
 */
public class Step3Fragment extends BaseFragment {

    Info mInfo;
    @Bind(R.id.btnSendMail)
    Button btnSendMail;
    @Bind(R.id.btnRestart)
    Button btnRestart;

    @Override
    protected int getFragmentID() {
        return R.layout.activity_sign_up_step3;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle bundle = getArguments();
        mInfo = (Info) bundle.getSerializable(UserSignUpFragmentActivity.INFO_DATA);
        ButterKnife.bind(this,view);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.btnSendMail, R.id.btnRestart})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSendMail:
                onBtnSendMailClicked();
                break;
            case R.id.btnRestart:
                onBtnRestartClicked();
                break;
        }
    }

    public void onBtnSendMailClicked() {
        Intent email = new Intent(Intent.ACTION_SEND);
        email.putExtra(Intent.EXTRA_EMAIL, new String[]{mInfo.getEmail().toString()});
        email.putExtra(Intent.EXTRA_SUBJECT, "User's registration info");
        String message = mInfo.getFirstName().toString() + "_" + mInfo.getLastName().toString()
                + "\n" + mInfo.getPhoneNumber().toString() + "\n" + mInfo.getSalary() + " dollars";
        email.putExtra(Intent.EXTRA_TEXT, message);
        email.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(new File(mInfo.getImageProfile())));
        email.setType("message/rfc822");
        startActivity(Intent.createChooser(email, "Choose an Email app :"));
    }

    public void onBtnRestartClicked() {
        FragmentManager fragmentManager = getFragmentManager();
        for (int i = 0; i <= fragmentManager.getBackStackEntryCount(); i++) {
            fragmentManager.popBackStack();
            Log.d("Popback", String.valueOf(i));
        }
        fragmentManager.beginTransaction().replace(R.id.container_fragment, new Step1Fragment()).commit();

    }
}
