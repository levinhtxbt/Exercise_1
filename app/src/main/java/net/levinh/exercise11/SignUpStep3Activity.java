package net.levinh.exercise11;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import net.levinh.exercise11.Model.Info;

public class SignUpStep3Activity extends AppCompatActivity {

    Button btnSendMail,btnRestart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_step3);
        btnSendMail = (Button)findViewById(R.id.btnSendMail);
        btnRestart = (Button)findViewById(R.id.btnRestart);

        Bundle b = getIntent().getBundleExtra("data");
        final Info info = (Info)b.getSerializable("info");

        btnSendMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent email = new Intent(Intent.ACTION_SEND);
                email.putExtra(Intent.EXTRA_EMAIL, new String[]  { info.getEmail().toString() } );
                email.putExtra(Intent.EXTRA_SUBJECT, "User's registration info");
                String message = info.getFirstName().toString()+"_"+info.getLastName().toString()
                        +"\n"+info.getPhoneNumber().toString()+"\n"+info.getSalary()+" dollars";
                email.putExtra(Intent.EXTRA_TEXT, message);
                email.setType("message/rfc822");
                startActivity(Intent.createChooser(email, "Choose an Email app :"));
            }
        });

        btnRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( SignUpStep3Activity.this, MainActivity.class );
                intent.setFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP );
                startActivity(intent);
            }
        });
    }
}
