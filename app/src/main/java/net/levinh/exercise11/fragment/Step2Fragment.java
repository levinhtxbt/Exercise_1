package net.levinh.exercise11.fragment;

import android.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.TextView;

import net.levinh.exercise11.R;
import net.levinh.exercise11.UserSignUpFragmentActivity;

/**
 * Created by Levin on 01/05/2016.
 */
public class Step2Fragment extends Step1Fragment {

    public SeekBar seekbar;
    public TextView lblResult;
    Button btnDone;
    public CheckBox chkFootball, chkTennis, chkPingpong, chkSwimming, chkVolleyball, chkBasketball;

    @Override
    public int getIDFragment() {
        return R.layout.activity_sign_up_step2;
    }

    @Override
    public void initView(View view) {
        seekbar = (SeekBar) view.findViewById(R.id.seekbar);
        lblResult = (TextView) view.findViewById(R.id.lblResult);
        btnDone = (Button) view.findViewById(R.id.btnDone);
        chkFootball = (CheckBox) view.findViewById(R.id.chkFootball);
        chkTennis = (CheckBox) view.findViewById(R.id.chkTennis);
        chkPingpong = (CheckBox) view.findViewById(R.id.chkPingPong);
        chkSwimming = (CheckBox) view.findViewById(R.id.chkSwimming);
        chkVolleyball = (CheckBox) view.findViewById(R.id.chkVolleyball);
        chkBasketball = (CheckBox) view.findViewById(R.id.chkBasketball);
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
        btnDone.setOnClickListener(this);
    }

    @Override
    public boolean putInfor() {
        mInfo.setSalary(seekbar.getProgress());
        return true;
    }

    public Boolean isChecked() {
        if (chkFootball.isChecked() || chkTennis.isChecked() ||
                chkPingpong.isChecked() || chkSwimming.isChecked() ||
                chkVolleyball.isChecked() || chkBasketball.isChecked())
            return true;
        return false;

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnDone) {
            if (isChecked()) {
                putInfor();
                ((UserSignUpFragmentActivity) getActivity()).addFragment(new Step3Fragment());
            }
        }


    }
}
