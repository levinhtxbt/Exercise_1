package net.levinh.exercise11.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.TextView;

import net.levinh.exercise11.Model.Info;
import net.levinh.exercise11.R;
import net.levinh.exercise11.Activity.UserSignUpFragmentActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Levin on 01/05/2016.
 */
public class Step2Fragment extends BaseFragment {
    Info mInfo;

    @Bind(R.id.seekbar)
    SeekBar seekbar;
    @Bind(R.id.lblResult)
    TextView lblResult;
    @Bind(R.id.btnDone)
    Button btnDone;
    @Bind(R.id.chkFootball)
    CheckBox chkFootball;
    @Bind(R.id.chkTennis)
    CheckBox chkTennis;
    @Bind(R.id.chkPingPong)
    CheckBox chkPingpong;
    @Bind(R.id.chkSwimming)
    CheckBox chkSwimming;
    @Bind(R.id.chkVolleyball)
    CheckBox chkVolleyball;
    @Bind(R.id.chkBasketball)
    CheckBox chkBasketball;

    @Override
    protected int getFragmentID() {
        return R.layout.activity_sign_up_step2;
    }



    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
        Bundle bundle = getArguments();
        mInfo = (Info) bundle.getSerializable(UserSignUpFragmentActivity.INFO_DATA);

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

    @OnClick(R.id.btnDone)
    public void onClick(View v) {
        if (v.getId() == R.id.btnDone) {
            if (isChecked()) {
                putInfor();
                Bundle bundle = new Bundle();
                bundle.putSerializable(UserSignUpFragmentActivity.INFO_DATA, mInfo);
                Step3Fragment fragment = new Step3Fragment();
                fragment.setArguments(bundle);
                ((UserSignUpFragmentActivity) getActivity()).addFragment(fragment);
            }
        }
    }

    public boolean putInfor() {
        if (mInfo != null) {
            mInfo.setSalary(seekbar.getProgress());
            return true;
        }
        return false;
    }

    public Boolean isChecked() {
        if (chkFootball.isChecked() || chkTennis.isChecked() ||
                chkPingpong.isChecked() || chkSwimming.isChecked() ||
                chkVolleyball.isChecked() || chkBasketball.isChecked())
            return true;
        return false;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
