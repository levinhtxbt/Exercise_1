package net.levinh.exercise11;

import android.app.Application;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import net.levinh.exercise11.Model.Info;

import java.util.ArrayList;
import java.util.List;

public class SignUpStep2Activity extends AppCompatActivity {

    public SeekBar seekbar;
    public TextView lblResult;
    Button btnDone;
    public CheckBox chkFootball, chkTennis,chkPingpong,chkSwimming,chkVolleyball,chkBasketball;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_step2);
        CreateView();

        Bundle b = getIntent().getBundleExtra("data");
        final Info info = (Info)b.getSerializable("info");

        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int step =100;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progress = ((int)Math.round(progress/step ))*step;
                seekBar.setProgress(progress);
                lblResult.setText(progress + "");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(IsChecked())
               {
                  info.setSalary(seekbar.getProgress());
                   Bundle b = new Bundle();
                   b.putSerializable("info", info);
                   Intent intent3 = new Intent(SignUpStep2Activity.this, SignUpStep3Activity.class);
                   intent3.putExtra("data",b);
                   startActivity(intent3);
               }
            }
        });
    }
    public  void CreateView()
    {
        seekbar = (SeekBar)super.findViewById(R.id.seekbar);
        lblResult = (TextView)super.findViewById(R.id.lblResult);
        btnDone = (Button) super.findViewById(R.id.btnDone);
        chkFootball = (CheckBox)findViewById(R.id.chkFootball);
        chkTennis = (CheckBox)findViewById(R.id.chkTennis);
        chkPingpong = (CheckBox)findViewById(R.id.chkPingPong);
        chkSwimming = (CheckBox)findViewById(R.id.chkSwimming);
        chkVolleyball = (CheckBox)findViewById(R.id.chkVolleyball);
        chkBasketball = (CheckBox)findViewById(R.id.chkBasketball);
    }
   public  Boolean IsChecked()
    {
       if(chkFootball.isChecked() || chkTennis.isChecked()||
                chkPingpong.isChecked()||chkSwimming.isChecked()||
                chkVolleyball.isChecked()||chkBasketball.isChecked())
            return true;
        return false;

   }
}
