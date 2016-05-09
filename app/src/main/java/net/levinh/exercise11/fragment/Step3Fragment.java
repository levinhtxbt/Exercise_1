package net.levinh.exercise11.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import net.levinh.exercise11.Model.Info;
import net.levinh.exercise11.R;
import net.levinh.exercise11.UserSignUpFragmentActivity;

/**
 * Created by Levin on 01/05/2016.
 */
public class Step3Fragment extends Step1Fragment {

    Button btnSendMail, btnRestart;

    @Override
    public int getIDFragment() {
        return R.layout.activity_sign_up_step3;
    }

    @Override
    public void initView(View view) {
        btnSendMail = (Button) view.findViewById(R.id.btnSendMail);
        btnSendMail.setOnClickListener(this);
        btnRestart = (Button) view.findViewById(R.id.btnRestart);
        btnRestart.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSendMail:
                sendMail(mInfo);
                break;
            case R.id.btnRestart:
                popBackLastStack();
                break;
        }
    }

    public void sendMail(Info info) {
        Intent email = new Intent(Intent.ACTION_SEND);
        email.putExtra(Intent.EXTRA_EMAIL, new String[]{info.getEmail().toString()});
        email.putExtra(Intent.EXTRA_SUBJECT, "User's registration info");
        String message = info.getFirstName().toString() + "_" + info.getLastName().toString()
                + "\n" + info.getPhoneNumber().toString() + "\n" + info.getSalary() + " dollars";
        email.putExtra(Intent.EXTRA_TEXT, message);
        email.setType("message/rfc822");
        startActivity(Intent.createChooser(email, "Choose an Email app :"));
    }

    public void popBackLastStack() {
        FragmentManager fragmentManager = getFragmentManager();
        for (int i = 0; i <= fragmentManager.getBackStackEntryCount(); i++) {
            fragmentManager.popBackStack();
            Log.d("Popback", String.valueOf(i));
        }
        fragmentManager.beginTransaction().replace(R.id.container_fragment,new Step1Fragment()).commit();

    }


}
