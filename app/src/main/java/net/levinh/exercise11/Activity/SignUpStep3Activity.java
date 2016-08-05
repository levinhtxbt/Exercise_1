package net.levinh.exercise11.Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import net.levinh.exercise11.Model.Info;
import net.levinh.exercise11.R;

import java.io.File;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignUpStep3Activity extends AppCompatActivity {
    Info mInfo;
    @Bind(R.id.btnSendMail)
    Button btnSendMail;
    @Bind(R.id.btnRestart)
    Button btnRestart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_step3);
        ButterKnife.bind(this);
        Bundle b = getIntent().getBundleExtra(SignUpStep1Activity.DATA);
        mInfo = (Info) b.getSerializable(SignUpStep1Activity.INFO);
    }

    @OnClick({R.id.btnSendMail, R.id.btnRestart})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSendMail:
                sendMail();
                break;
            case R.id.btnRestart:
                restart();
                break;
        }
    }

    public void sendMail(){
        Intent email = new Intent(Intent.ACTION_SEND);
        email.putExtra(Intent.EXTRA_EMAIL, new String[]{mInfo.getEmail().toString()});
        email.putExtra(Intent.EXTRA_SUBJECT, "User's registration info");
        String message = mInfo.getFirstName().toString() + "_" + mInfo.getLastName().toString()
                + "\n" + mInfo.getPhoneNumber().toString() + "\n" + mInfo.getSalary() + " dollars";
        email.putExtra(Intent.EXTRA_TEXT, message);
        email.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(new File(mInfo.getImageProfile())));
        //Log.d(TAG, "sendMail: ");
        email.setType("message/rfc822");
        startActivity(Intent.createChooser(email, "Choose an Email app :"));
    }

    public void restart(){
        Intent intent = new Intent(SignUpStep3Activity.this, SignUpStep1Activity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
