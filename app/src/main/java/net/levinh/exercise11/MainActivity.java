package net.levinh.exercise11;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import net.levinh.exercise11.Model.Info;

public class MainActivity extends AppCompatActivity {

    EditText txtFirstName,txtLastName,txtEmail,txtPhoneNumber;
    RadioGroup rdoGroup;
    RadioButton rdoMale,rdoFemale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnNext = (Button)findViewById(R.id.btnNext);
        txtFirstName = (EditText)findViewById(R.id.txtFirstName);
        txtLastName = (EditText)findViewById(R.id.txtLastName);
        txtEmail =(EditText)findViewById(R.id.txtEmail);
        txtPhoneNumber =(EditText) findViewById(R.id.txtPhoneNumber);
        rdoGroup = (RadioGroup) findViewById(R.id.rdoGroup);
        rdoFemale = (RadioButton) findViewById(R.id.rdoFemale);
        rdoMale = (RadioButton) findViewById(R.id.rdoMale);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isValid())
                {
                    Intent intent = new Intent(MainActivity.this,SignUpStep2Activity.class);

                    Info i =  new Info();
                    i.setFirstName(txtFirstName.getText().toString());
                    i.setLastName(txtLastName.getText().toString());
                    i.setEmail(txtEmail.getText().toString());
                    i.setPhoneNumber(txtPhoneNumber.getText().toString());
                    i.setGender(rdoMale.isChecked());

                    Bundle b = new Bundle();
                    b.putSerializable("info",i);
                    intent.putExtra("data",b);
                    startActivity(intent);
                }
            }
        });
    }

    public  boolean isValid()
    {
        boolean flag = true;
        if(txtFirstName.getText().length()==0)
        {
            txtFirstName.setError("You must enter FirstName");
            flag = false;
        }
        if(txtLastName.getText().length()==0)
        {
            txtLastName.setError("You must enter LastName");
            flag = false;
        }
        if(txtPhoneNumber.getText().length()== 0 || txtPhoneNumber.getText().length()>11)
        {
            txtPhoneNumber.setError("Phone is invalid");
            flag = false;
        }
        if(!isValidEmail(txtEmail.getText()))
        {
            txtEmail.setError("Email is invalid");
            flag = false;
        }
        return  flag;
    }
    public final static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }
}
