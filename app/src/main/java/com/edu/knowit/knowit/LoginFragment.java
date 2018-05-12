package com.edu.knowit.knowit;


import android.content.DialogInterface;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.edu.knowit.knowit.Util.DialogBox;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;




/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment implements View.OnClickListener {



    private static final String TAG = "LoginFragment";

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    private Button loginButton;
    private View view;
    private ProgressBar progressBar;

    private EditText editTextUsername;
    private EditText editTextPassword;

    public LoginFragment()
    {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart()
    {
        super.onStart();
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_login, container, false);

        editTextUsername = (EditText) view.findViewById(R.id.usernamelogin);
        editTextPassword = (EditText) view.findViewById(R.id.passwordlogin);
        progressBar = (ProgressBar) view.findViewById(R.id.loginProgressBar);
        loginButton = (Button) view.findViewById(R.id.loginBtn);
        loginButton.setOnClickListener(this);
        progressBar.setVisibility(View.GONE);
        mAuth = FirebaseAuth.getInstance();

        return view;

    }


    @Override
    public void onClick(View view)
    {
        Toast.makeText(getActivity(),"Please Check Fields",Toast.LENGTH_SHORT);

        switch (view.getId()){

            case R.id.loginBtn:
                Log.d(TAG,"Onclick Login Button");
                init();
                break;
            default:
                break;
        }
    }

    private void setUpFirebaseAuth()
    {
        Log.d(TAG,"setUpFirebaseAuth: setting up fireabase auth");
        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user != null){
                    Log.d(TAG,"onAuthStateChanged: signed_In"+user.getUid());
                }else{
                    Log.d(TAG,"onAuthStateChanged: signed_Out");
                }
            }
        };
    }

    private void init(){
        String email = editTextUsername.getText().toString();
        String password =  editTextPassword.getText().toString();
        if(!isStringNull(email) && !isStringNull(password)){
            Log.d(TAG,"yes this is call");
        }else {
            progressBar.setVisibility(View.VISIBLE);
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Log.d(TAG, "signInWithEmail:success");
                            } else {
                                Log.w(TAG, "signInWithEmail:failure", task.getException());
                                alert("signInWithEmail:failure");
                                DialogBox Dbox = new DialogBox();
                                Dbox.ViewDialogBox(view,"Login Fail",task.getException().getMessage().toString());
                            }

                           progressBar.setVisibility(View.GONE);
                        }
                    });
        }
    }

    private boolean isStringNull(String string){
//        DialogBox db = new DialogBox();
//        db.ViewDialogBox(view);
        return  true;
    }

    private void alert(String str){
        Toast toast = Toast.makeText(
                getActivity().getApplicationContext(), str, Toast.LENGTH_LONG
        );
        toast.show();
    }


}
