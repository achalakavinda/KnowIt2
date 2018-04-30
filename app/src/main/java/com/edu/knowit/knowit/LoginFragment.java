package com.edu.knowit.knowit;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment implements View.OnClickListener {
    private Button loginButton;
    private View view;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_login, container, false);
        loginButton = (Button) view.findViewById(R.id.loginBtn);
        loginButton.setOnClickListener(this);

        return view;

    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(getActivity(),BaseActivity.class);
        startActivity(intent);
        Toast.makeText(getActivity(),"Please Check Fields",Toast.LENGTH_SHORT);
        switch (view.getId()){
            case R.id.loginBtn:
                    Toast.makeText(getActivity(),"Please Check Fields",Toast.LENGTH_SHORT);
                break;
            default:
                break;
        }
    }

}
