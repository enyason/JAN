package com.nexdev.enyason.jan;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.nexdev.enyason.jan.fragment.DashBoardFragment;
import com.nexdev.enyason.jan.fragment.EnrolledCoursesFragment;
import com.nexdev.enyason.jan.fragment.JanCoursesFragment;
import com.nexdev.enyason.jan.fragment.ProfileFragment;

public class HomeActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mAuth = FirebaseAuth.getInstance();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_view_home);
        BottomNavigationViewHelper.removeShiftMode(bottomNavigationView);

        //load default fragment on starting

        loadFragment(new EnrolledCoursesFragment());

        bottomNavigationView.setOnNavigationItemSelectedListener(this);
    }



    @Override
    protected void onStart() {
        super.onStart();

        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser != null) {
            Log.i("Home Activity ", " User not Null");
            Log.i("email ",currentUser.getEmail());

        } else {

            Log.i("Home Activity ", " User is Null");
            startActivity(new Intent(HomeActivity.this,LandingActivity.class));



        }

    }



    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }

        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;

        switch (item.getItemId()) {
            case R.id.menu_enrolled_courses:
                fragment = new EnrolledCoursesFragment();
                break;

            case R.id.menu_jan_courses:
                fragment = new JanCoursesFragment();
                break;

            case R.id.menu_dash_board:
                fragment = new DashBoardFragment();
                break;

            case R.id.menu_profile:
                fragment = new ProfileFragment();
                break;
        }

        return loadFragment(fragment);
    }
}
