package com.nexdev.enyason.jan.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.nexdev.enyason.jan.LearnActivity;
import com.nexdev.enyason.jan.R;

/**
 * Created by enyason on 6/16/18.
 */

public class PreTestFragment extends DialogFragment {


    private Button buttonSubmitPreTest;
    private ProgressDialog progressDialog;

    private RadioGroup radioGroup;
    private RadioButton radioButton;

    FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;

    DocumentReference documentReference;

    DocumentReference reference;

    DocumentReference lessontakenReference;

    public PreTestFragment() {
    }

    public static PreTestFragment newInstance(String title) {
        PreTestFragment frag = new PreTestFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        frag.setArguments(args);
        return frag;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_pre_test, container, false);

        getDialog().setCanceledOnTouchOutside(false);

        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("uploading response...");
        progressDialog.setCanceledOnTouchOutside(false);

        radioGroup = view.findViewById(R.id.radio_group);


        buttonSubmitPreTest = view.findViewById(R.id.btn_submit_pre_survey);

        documentReference = firebaseFirestore.collection("week_list").document("courses").collection(LearnActivity.cid).document(LearnActivity.lessonId);


        reference = firebaseFirestore.collection("students").document(firebaseAuth.getUid())
                .collection("week_list").document("course").collection(LearnActivity.cid).document(LearnActivity.lessonId);


        lessontakenReference = firebaseFirestore.collection("students").
                document(firebaseAuth.getUid()).collection("course_progress")
                .document(LearnActivity.cid);


        buttonSubmitPreTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                // get selected radio button from radioGroup
                int selectedId = radioGroup.getCheckedRadioButtonId();

                // find the radiobutton by returned id
                radioButton = (RadioButton) view.findViewById(selectedId);

                final String option = radioButton.getText().toString();


                progressDialog.show();


                documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {

                        if (documentSnapshot.exists()) {

                            String preTestAnswer = documentSnapshot.getString("preTest");


                            if (option.contains(preTestAnswer)) {


//
//                               documentReference.update("izPreTestTaken",true).addOnSuccessListener(new OnSuccessListener<Void>() {
//                                   @Override
//                                   public void onSuccess(Void aVoid) {
//
//                                       LearnActivity.weekListCheckFlag.setIzPreTesttaken(true);
//                                       LearnActivity.adapter.notifyDataSetChanged();
//
//                                       progressDialog.dismiss();
//
//
//                                       Toast.makeText(getContext(), "Correct", Toast.LENGTH_SHORT).show();
//                                       buttonSubmitPreTest.setText("Done");
//
//                                       buttonSubmitPreTest.setOnClickListener(new View.OnClickListener() {
//                                           @Override
//                                           public void onClick(View v) {
//
//                                               getDialog().dismiss();
//                                           }
//                                       });
//
//                                   }
//                               });


                                reference.update("izPreTestTaken", true).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {

                                        LearnActivity.weekListCheckFlag.setIzPreTesttaken(true);

                                        Log.i("flag2", "" + LearnActivity.weekListCheckFlag.getIzPreTesttaken());



                                        lessontakenReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                            @Override
                                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                                                if (task.isSuccessful()) {

                                                    DocumentSnapshot result = task.getResult();

                                                    double lessonsTaken = result.getDouble("number_of_lessons_taken");

                                                    lessonsTaken++;


                                                    lessontakenReference.update("number_of_lessons_taken",lessonsTaken).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                        @Override
                                                        public void onComplete(@NonNull Task<Void> task) {

                                                            progressDialog.dismiss();

                                                            if (task.isSuccessful()) {


                                                                Toast.makeText(getContext(), "Correct", Toast.LENGTH_SHORT).show();
                                                                buttonSubmitPreTest.setText("Done");

                                                                buttonSubmitPreTest.setOnClickListener(new View.OnClickListener() {
                                                                    @Override
                                                                    public void onClick(View v) {

                                                                        getDialog().dismiss();
                                                                    }
                                                                });

                                                            }

                                                        }
                                                    });


                                                }


                                            }
                                        });


//
//


                                    }
                                });


                            } else {
                                progressDialog.dismiss();
                                Toast.makeText(getContext(), "Incorrect, try again", Toast.LENGTH_SHORT).show();

                            }


                        }

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Toast.makeText(getContext(), "Error! please try again", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                });


            }
        });


    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
