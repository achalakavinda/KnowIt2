package com.edu.knowit.knowit;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;

import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.edu.knowit.knowit.Models.*;
import com.edu.knowit.knowit.Util.DialogBox;
import com.edu.knowit.knowit.Util.FirebaseMethods;
import com.edu.knowit.knowit.Util.StringValidator;
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
    private String error = null;

    private FirebaseAuth mAuth;

    private View view = null;

    private EditText editTextName;
    private EditText editTextSliitID;
    private EditText editTextContact;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private EditText editTextConfirmPassword;
    private Button buttonRegister;
    private ProgressBar registerProgressBar;


    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        view = inflater.inflate(R.layout.fragment_register, container, false);
        mAuth = FirebaseAuth.getInstance();
        editTextName = (EditText) view.findViewById(R.id.rgname);
        editTextSliitID = (EditText) view.findViewById(R.id.rgsliitid);
        editTextContact = (EditText) view.findViewById(R.id.rgmobile);
        editTextEmail = (EditText) view.findViewById(R.id.rgemail);
        editTextPassword = (EditText) view.findViewById(R.id.rgpassword);
        editTextConfirmPassword = (EditText) view.findViewById(R.id.rgcommfirmpassword);
        registerProgressBar = (ProgressBar) view.findViewById(R.id.registerProgressBar);

        registerProgressBar.setVisibility(View.GONE);


        buttonRegister = (Button) view.findViewById(R.id.registerBtn);
        buttonRegister.setOnClickListener(this);


        return  view;
    }

    @Override
    public void onClick(View view)
    {
       switch (view.getId()){

            case R.id.registerBtn:
               if(validator()){
                   register(editTextEmail.getText().toString().trim(),editTextPassword.getText().toString().trim());
               }else{
                   DialogBox dBox =  new DialogBox();
                   dBox.ViewDialogBox(view,"Please Check !",this.error);
               }
                break;
            default:
                break;
        }
    }

    public boolean validator(){
        boolean valiate = true;
        this.error ="";
        String name = this.editTextName.getText().toString().trim();
        String sliitid = this.editTextSliitID.getText().toString().trim();
        String contact = this.editTextContact.getText().toString().trim();
        String email = this.editTextEmail.getText().toString().trim();
        String password = this.editTextPassword.getText().toString().trim();
        String comfirmPassword = this.editTextConfirmPassword.getText().toString().trim();


        StringValidator sValidator = new StringValidator();

        if(sValidator.isEmptyString(name)){
            valiate = false;
            this.error = this.error+"Name,";
        }

        if(sValidator.isEmptyString(sliitid)){
            valiate = false;
            this.error = this.error+" Sliit ID,";
        }

        if(sValidator.isEmptyString(contact)){
            valiate = false;
            this.error = this.error+" Contact,";
        }

        if(sValidator.isEmptyString(email)){
            valiate = false;
            this.error = this.error+" Email,";
        }else {
            if(!sValidator.isValidEmailAddress(email)){
                this.error = this.error +" Invalid Email,";
            }
        }

        if(sValidator.isEmptyString(password)){
            valiate = false;
            this.error = this.error+" Password,";
        }

        if(sValidator.isEmptyString(comfirmPassword)){
            valiate = false;
            this.error = this.error+" Confirm Password,";
        }else{
            if(!password.equals(comfirmPassword)) {
                valiate = false;
                this.error = this.error + " Invalid Confirmation Password";
            }
        }
        return valiate;
    }

    private void register(String email,String password){

        Log.d(TAG,"Register Function call");
        registerProgressBar.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "createUserWithEmail:success");
                            registerProgressBar.setVisibility(View.GONE);
                            FirebaseUser user = mAuth.getCurrentUser();
                            insertUSerData(user.getUid());
                        } else {
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            registerProgressBar.setVisibility(View.GONE);
                            DialogBox dBox = new DialogBox();
                            dBox.ViewDialogBox(view,"Wrong!",task.getException().getMessage());
                        }

                    }
                });
    }

    public void insertUSerData(String uui){
           User us = new User(
                editTextName.getText().toString(),
                editTextContact.getText().toString(),
                editTextEmail.getText().toString(),
                editTextSliitID.getText().toString(),
                "http://themes.gohugo.io/theme/hugo-geo/img/profile.png",
                ""
                );
           FirebaseMethods firebaseMethods = new FirebaseMethods(getActivity().getApplicationContext());
           firebaseMethods.userRegister(us);
    }

}
