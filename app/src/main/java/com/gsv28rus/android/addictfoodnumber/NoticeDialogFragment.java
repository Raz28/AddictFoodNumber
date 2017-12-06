package com.gsv28rus.android.addictfoodnumber;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import com.gsv28rus.android.addictfoodnumber.R;

/**
 * Created by Stepan on 06.12.2017.
 */

public class NoticeDialogFragment extends android.support.v4.app.DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.dialog_about_title).setMessage(R.string.dialog_about_message).setPositiveButton(R.string.dialog_positive_buuton, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getActivity(), "Ok selected", Toast.LENGTH_SHORT).show();
            }
        });
        return builder.create();
    }
}
