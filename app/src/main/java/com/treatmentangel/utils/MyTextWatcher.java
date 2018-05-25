package com.treatmentangel.utils;

import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

public class MyTextWatcher implements TextWatcher {
    TextInputLayout til;

    public MyTextWatcher(View view) {
        til = (TextInputLayout) view;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        til.setErrorEnabled(false);
        til.setError("");
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
