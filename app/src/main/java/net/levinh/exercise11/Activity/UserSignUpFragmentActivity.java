package net.levinh.exercise11.Activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import net.levinh.exercise11.Model.Info;
import net.levinh.exercise11.R;
import net.levinh.exercise11.fragment.Step1Fragment;

/**
 * Created by Levin on 01/05/2016.
 */
public class UserSignUpFragmentActivity extends AppCompatActivity {
    public static final String INFO_DATA = "info_data";
    Info mInfo;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_sign_up_fragment);
        mInfo = new Info();
        fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().add(R.id.container_fragment, new Step1Fragment()).commit();
    }

    public void addFragment(Fragment fragment) {
        fragmentManager.beginTransaction().replace(R.id.container_fragment, fragment).addToBackStack(null).commit();
    }

    @Override
    public void onBackPressed() {
        if (fragmentManager.getBackStackEntryCount() > 0)
            fragmentManager.popBackStack();
        else finish();
    }
}
