package com.edu.knowit.knowit.Util;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.View;

public class DialogBox {

    public void ViewDialogBox(View view){
        AlertDialog alertDialog = new AlertDialog.Builder(view.getContext()).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage("Alert message to be shown");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

}
