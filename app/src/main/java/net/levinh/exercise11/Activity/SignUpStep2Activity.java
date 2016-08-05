package net.levinh.exercise11.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.TextView;

import net.levinh.exercise11.Model.Info;
import net.levinh.exercise11.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignUpStep2Activity extends AppCompatActivity {

    Info mInfo;
    @Bind(R.id.lblResult)
    TextView lblResult;
    @Bind(R.id.seekbar)
    SeekBar seekbar;
    @Bind(R.id.chkFootball)
    CheckBox chkFootball;
    @Bind(R.id.chkTennis)
    CheckBox chkTennis;
    @Bind(R.id.chkPingPong)
    CheckBox chkPingPong;
    @Bind(R.id.chkSwimming)
    CheckBox chkSwimming;
    @Bind(R.id.chkVolleyball)
    CheckBox chkVolleyball;
    @Bind(R.id.chkBasketball)
    CheckBox chkBasketball;
    @Bind(R.id.btnDone)
    Button btnDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_step2);
        ButterKnife.bind(this);

        Bundle b = getIntent().getBundleExtra(SignUpStep1Activity.DATA);
        mInfo = (Info) b.getSerializable(SignUpStep1Activity.INFO);

        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int step = 100;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progress = ((int) Math.round(progress / step)) * step;
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
    }

    public Boolean IsChecked() {
        if (chkFootball.isChecked() || chkTennis.isChecked() ||
                chkPingPong.isChecked() || chkSwimming.isChecked() ||
                chkVolleyball.isChecked() || chkBasketball.isChecked())
            return true;
        return false;

    }

    @OnClick(R.id.btnDone)
    public void onClick() {
        if (IsChecked()) {
            mInfo.setSalary(seekbar.getProgress());
            Bundle b = new Bundle();
            b.putSerializable(SignUpStep1Activity.INFO, mInfo);
            Intent intent3 = new Intent(SignUpStep2Activity.this, SignUpStep3Activity.class);
            intent3.putExtra(SignUpStep1Activity.DATA, b);
            startActivity(intent3);
        }
    }
}
