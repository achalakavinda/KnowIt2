package com.edu.knowit.knowit;


import android.net.Uri;
import android.nfc.Tag;
import android.os.Bundle;
import android.app.Fragment;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.edu.knowit.knowit.Models.Post;
import com.edu.knowit.knowit.Models.User;
import com.edu.knowit.knowit.Util.DialogBox;
import com.edu.knowit.knowit.Util.FilePaths;
import com.edu.knowit.knowit.Util.FileSearch;
import com.edu.knowit.knowit.Util.FirebaseMethods;
import com.edu.knowit.knowit.Util.GridImageAdapter;
import com.edu.knowit.knowit.Util.StringValidator;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.io.File;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class PostFragment extends android.support.v4.app.Fragment implements View.OnClickListener {
    private static final String TAG = "PostFragment";
    private String error = "";

    //constants
    private static final int NUM_GRID_COLUMNS = 4;

    //widgets
    private View view;
    private GridView gridView;
    private ImageView galleryImage;
    private ProgressBar mProgressBar;
    private Spinner directorySpinner;
    private Button postBtn;
    private Button attachBtn;

    //view variables
    private Spinner spinner;
    private EditText editTextTitle;
    private EditText editTextDesc;
    private EditText editTextPath;


    // firebase image url path
    private String url;
    private String postID;
    private Boolean uploadImage;

    //data models
    public User user = null;


    //variables
    private ArrayList<String>  directories;
    private static final String[] paths = {"DCCN", "PS", "MIT"};
    private String mAppend = "file:/";
    private String mSelectedImage;

    //firebase
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference myRef;
    private FirebaseMethods mFirebaseMethods;

    public PostFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFirebaseMethods = new FirebaseMethods(getActivity().getApplicationContext());
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getActivity().getApplicationContext()).build();
        ImageLoader.getInstance().init(config);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_post, container, false);
        gridView = (GridView) view.findViewById(R.id.gridView);
        directorySpinner = (Spinner) view.findViewById(R.id.spinnerDirectory);

        postBtn = (Button) view.findViewById(R.id.post);
        attachBtn = (Button) view.findViewById(R.id.attach);

        postBtn.setOnClickListener(this);
        attachBtn.setOnClickListener(this);


        spinner = (Spinner) view.findViewById(R.id.spinner);
        editTextTitle = (EditText) view.findViewById(R.id.title);
        editTextDesc = (EditText) view.findViewById(R.id.description);
        editTextPath = (EditText) view.findViewById(R.id.path);

        ArrayAdapter<String>adapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item,paths);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
//        Init();
    }

    private void Init(){
        FilePaths  filePaths = new FilePaths();

        if(FileSearch.getDirectoryPaths(filePaths.PICTURES)!= null){
            directories = FileSearch.getDirectoryPaths(filePaths.PICTURES);
        }

        directories.add(filePaths.CAMERA);

        ArrayList<String> directoryNames = new ArrayList<>();
        for (int i = 0; i < directories.size(); i++) {
            Log.d(TAG, "init: directory: " + directories.get(i));
            int index = directories.get(i).lastIndexOf("/");
            String string = directories.get(i).substring(index);
            directoryNames.add(string);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext().getApplicationContext(),
                android.R.layout.simple_spinner_item, directoryNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        directorySpinner.setAdapter(adapter);

        directorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG, "onItemClick: selected: " + directories.get(position));
                System.out.println( directories.get(position));

                //setup our image grid for the directory chosen
                setupGridView(directories.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void setupGridView(String selectedDirectory){
        Log.d(TAG, "setupGridView: directory chosen: " + selectedDirectory);
        final ArrayList<String> imgURLs = FileSearch.getFilePaths(selectedDirectory);

        //set the grid column width
        int gridWidth = getResources().getDisplayMetrics().widthPixels;
        int imageWidth = gridWidth/NUM_GRID_COLUMNS;
        gridView.setColumnWidth(imageWidth);

        //use the grid adapter to adapter the images to gridview
        GridImageAdapter adapter = new GridImageAdapter(getActivity(), R.layout.layout_grid_imageview, mAppend, imgURLs);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG, "onItemClick: selected an image: " + imgURLs.get(position));
                mSelectedImage = imgURLs.get(position);
                editTextPath.setText(mSelectedImage);
                System.out.println(mSelectedImage);
            }
        });

    }

    @Override
    public void onClick(View view)
    {
        Toast.makeText(getActivity(),"Please Check Fields",Toast.LENGTH_SHORT);

        switch (view.getId()){

            case R.id.post:
                Log.d(TAG,"Post Click");
                if (sValidator()){
                    getUserInfo();
                }else{
                    new DialogBox().ViewDialogBox(view,"Please Check!",this.error);
                }
                break;
            case R.id.attach:
                Init();
                break;
            default:
                break;
        }
    }


    private Boolean sValidator(){
        boolean validate = true;
        this.error = "";
        String category = spinner.getSelectedItem().toString();
        String title = editTextTitle.getText().toString();
        String desc = editTextDesc.getText().toString();
        StringValidator sValdator = new StringValidator();



        if(sValdator.isEmptyString(title)){
            validate = false;
            this.error = this.error +"title, ";
        }

        if(sValdator.isEmptyString(desc)){
            validate = false;
            this.error = this.error +"description.";
        }

        this.uploadImage = false;
        if (!sValdator.isEmptyString(editTextPath.getText().toString())){
            //call file upload
            uploadImage = true;
        }

        return validate;
    }

    private void uploadImage(String Key){
        FirebaseMethods fm = new FirebaseMethods(getActivity().getApplicationContext());
        StorageReference ref = fm.getmStorageReference().child("images/"+fm.getUserID()+"/"+Key);
        Uri file = Uri.fromFile(new File(editTextPath.getText().toString().trim()));
        ref.putFile(file)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Log.d(TAG,"upload success");
                        String url =taskSnapshot.getDownloadUrl().toString();
                        setUrl(url);
                        pushData(2);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d(TAG,"upload fail");
                    }
                })
                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                        double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot
                                .getTotalByteCount());
                        Log.d(TAG,String.valueOf(progress));
                    }
                });
    }

    public void setUrl(String url){
        this.url = url;
    }

    public String getUrl(){
        return url;
    }

    private void getUserInfo(){
        FirebaseMethods fm = new FirebaseMethods(getActivity().getApplicationContext());
        DatabaseReference ref = fm.getUserInfoRef();
        ValueEventListener listener = new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                com.edu.knowit.knowit.Models.User user = dataSnapshot.getValue(com.edu.knowit.knowit.Models.User.class);
                PostFragment.this.user = user;
                System.out.println(PostFragment.this.user);
                pushData(1);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG,databaseError.getDetails());
                new DialogBox().ViewDialogBox(view,"Error",databaseError.getMessage());
            }
        };
        ref.addListenerForSingleValueEvent(listener);
        ref.removeEventListener(listener);

    }

    public void pushData(int key){
        FirebaseMethods fm = new FirebaseMethods(getActivity().getApplicationContext());
        DatabaseReference ref = fm.createPost();
        switch (key){

            case 1:
                Post post = new Post(fm.getUserID(),user.getName(),fm.getTimestamp(),spinner.getSelectedItem().toString(),editTextTitle.getText().toString(),"",editTextDesc.getText().toString(),"0","0","0");
                DatabaseReference set = ref.push();
                postID = set.getKey();
                set.setValue(post).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                        Log.d(TAG,"Post Success");
                        editTextTitle.setText("");
                        editTextDesc.setText("");
                        url ="";
                        new DialogBox().ViewDialogBox(view,"Success","successfully post to community");
                    }
                })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.d(TAG,e.getMessage());
                                new DialogBox().ViewDialogBox(view,"Error",e.getMessage());
                            }
                        });

                if(uploadImage){
                    uploadImage(postID);
                }

                break;
            case 2:

                ref = fm.createPost();
                ref.child(postID).child("image").setValue(url);

                break;

        }

    }
}
