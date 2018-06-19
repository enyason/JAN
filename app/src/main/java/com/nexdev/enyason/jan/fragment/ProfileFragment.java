package com.nexdev.enyason.jan.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.nexdev.enyason.jan.R;
import com.nexdev.enyason.jan.SignInActivity;

/**
 * Created by enyason on 6/8/18.
 */

public class ProfileFragment extends Fragment {


    FirebaseAuth firebaseAuth;

    TextView button;

    ProgressDialog progressDialog;

    LinearLayout layoutLogOut;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, null);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        firebaseAuth =FirebaseAuth.getInstance();

        button = view.findViewById(R.id.btn_sign_out);
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Signing out please wait...");


        layoutLogOut = view.findViewById(R.id.layout5);

        layoutLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                signOut();


            }
        });


    }


    public void signOut(){
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

        if (firebaseUser != null) {
            firebaseAuth.signOut();
            startActivity(new Intent(getContext(), SignInActivity.class));
            getActivity().finish();

        }

    }
}
