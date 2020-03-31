package com.example.myapplication.DialogBox;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.R;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class LoginFaildDialog extends AppCompatDialogFragment {

    Button ok;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder( getActivity() );

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate( R.layout.login_failed_alart_dialog,null );

        ok = (Button)view.findViewById( R.id.logok );

        ok.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        } );


       return builder.create();
    }
}
