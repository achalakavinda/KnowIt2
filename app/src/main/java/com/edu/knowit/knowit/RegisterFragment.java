package com.edu.knowit.knowit;


import android.nfc.Tag;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import android.widget.EditText;
import android.widget.Toast;

import com.edu.knowit.knowit.Models.*;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends android.support.v4.app.Fragment implements View.OnClickListener {

    private static final String TAG = "LoginFragment";

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    private View view = null;

    private EditText editTextName;
    private EditText editTextContact;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private EditText editTextConfirmPassword;
    private Button buttonRegister;


    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_register, container, false);
        mAuth = FirebaseAuth.getInstance();
        editTextName = (EditText) view.findViewById(R.id.rgname);
        editTextContact = (EditText) view.findViewById(R.id.rgmobile);
        editTextEmail = (EditText) view.findViewById(R.id.rgemail);
        editTextPassword = (EditText) view.findViewById(R.id.rgpassword);
        editTextConfirmPassword = (EditText) view.findViewById(R.id.rgcommfirmpassword);

        buttonRegister = (Button) view.findViewById(R.id.registerBtn);
        buttonRegister.setOnClickListener(this);


        return  view;
    }

    @Override
    public void onClick(View view)
    {
       switch (view.getId()){

            case R.id.registerBtn:
                register(editTextEmail.getText().toString(),editTextPassword.getText().toString());
                break;
            default:
                break;
        }
    }
    private void register(String email,String password){


        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            insertDataTest(user.getUid());
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(getActivity().getApplicationContext(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }


                    }
                });
    }

    public void insertDataTest(String uui){
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference();

        UserDetailModel us = new UserDetailModel("achala","kavinda","",uui);
//        Log.w(TAG,us.getString());
//        databaseReference.child(getContext().getString(R.string.DB_UserDetails)).child(uui).setValue(us.getString());
    }

}
