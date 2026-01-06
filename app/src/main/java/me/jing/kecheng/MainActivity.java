package me.jing.kecheng;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import me.jing.kecheng.fra.gupaiFragment;
import me.jing.kecheng.fra.neixunFragment;
import me.jing.kecheng.fra.settingFragment;

public class MainActivity extends AppCompatActivity {
    private Fragment currentFragment;

    private final neixunFragment neixunFra = new neixunFragment();
    private final gupaiFragment gupaiFra = new gupaiFragment();
    private final settingFragment settingFra = new settingFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        //  如果你想默认显示“我的”，改成：
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container, neixunFra, "neixun")
                .add(R.id.fragment_container, gupaiFra, "gupai")
                .add(R.id.fragment_container, settingFra, "setting")
                .hide(neixunFra)
                .hide(gupaiFra)
                .commit();
        currentFragment = gupaiFra;
        bottomNav.setSelectedItemId(R.id.nav_setting);
        bottomNav.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;
            switch (item.getItemId()) {
                case R.id.nav_neixun-> selectedFragment = neixunFra;
                case R.id.nav_gupai-> selectedFragment = gupaiFra;
                case R.id.nav_setting-> selectedFragment = settingFra;
            }

            if (selectedFragment != null && selectedFragment != currentFragment) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .hide(currentFragment)
                        .show(selectedFragment)
                        .commit();
                currentFragment = selectedFragment;
            }
            return true;
        });
    }


}