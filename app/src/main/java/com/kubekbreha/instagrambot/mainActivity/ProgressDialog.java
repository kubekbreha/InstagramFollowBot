package com.kubekbreha.instagrambot.mainActivity;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.kubekbreha.instagrambot.R;

import androidx.appcompat.app.AlertDialog;

public class ProgressDialog {

    private View view;
    private TextView msg;
    private ProgressBar progressBar;
    private LinearLayout ll;
    private AlertDialog.Builder builder;
    private Dialog dialog;

    public ProgressDialog(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.progress, null);
        init();
    }

    void init() {
        msg =  view.findViewById(R.id.msg);
        progressBar = ( view.findViewById(R.id.loader));
        ll = ( view.findViewById(R.id.ll));
        builder = new AlertDialog.Builder(view.getContext());
    }


    public ProgressDialog setMessage(String message) {
        msg.setText(message);
        return this;
    }


    public void show() {
        builder.setView(view);
        dialog = builder.create();
        dialog.show();
    }

    public void dismiss() {
        dialog.dismiss();
    }
}