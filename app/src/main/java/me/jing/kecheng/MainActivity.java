package me.jing.kecheng;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import me.jing.kecheng.fra.gupaiFragment;
import me.jing.kecheng.fra.neixunFragment;
import me.jing.kecheng.fra.settingFragment;

public class MainActivity extends AppCompatActivity {
    private Fragment currentFragment;

    private neixunFragment neixunFra = new neixunFragment();
    private gupaiFragment gupaiFra = new gupaiFragment();
    private settingFragment settingFra = new settingFragment();

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
        currentFragment = settingFra;
        bottomNav.setSelectedItemId(R.id.nav_setting);
        bottomNav.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;
            int id = item.getItemId();
            if (id == R.id.nav_neixun) {
                selectedFragment = neixunFra;
            } else if (id == R.id.nav_gupai) {
                selectedFragment = gupaiFra;
            } else if (id == R.id.nav_setting) {
                selectedFragment = settingFra;
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