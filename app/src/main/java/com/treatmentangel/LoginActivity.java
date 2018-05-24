package com.treatmentangel;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.treatmentangel.utils.Config;
import com.treatmentangel.utils.HTTPUrlConnection;
import com.treatmentangel.utils.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class LoginActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.findViewById(R.id.back).setOnClickListener(this);

        findViewById(R.id.txt_register_now).setOnClickListener(this);
        findViewById(R.id.btn_login).setOnClickListener(this);

        ((EditText) findViewById(R.id.edt_email)).addTextChangedListener(new MyTextWatcher(findViewById(R.id.til_email)));
        ((EditText) findViewById(R.id.edt_pass)).addTextChangedListener(new MyTextWatcher(findViewById(R.id.til_pass)));
    }

    class MyTextWatcher implements TextWatcher {

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

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.txt_register_now:
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                break;
            case R.id.btn_login:
                String email = ((EditText) findViewById(R.id.edt_email)).getText().toString().trim();
                if (email.isEmpty()) {
                    ((TextInputLayout) findViewById(R.id.til_email)).setErrorEnabled(true);
                    ((TextInputLayout) findViewById(R.id.til_email)).setError("Please enter email");
                    break;
                }
                if (!Utils.isEmailValid(email)) {
                    ((TextInputLayout) findViewById(R.id.til_email)).setErrorEnabled(true);
                    ((TextInputLayout) findViewById(R.id.til_email)).setError("Please enter valid email");
                    break;
                }
                String pass = ((EditText) findViewById(R.id.edt_pass)).getText().toString().trim();
                if (pass.isEmpty()) {
                    ((TextInputLayout) findViewById(R.id.til_pass)).setErrorEnabled(true);
                    ((TextInputLayout) findViewById(R.id.til_pass)).setError("Please enter password");
                    break;
                }
                login(email, pass);
                break;
        }
    }


    private void login(String email, String password) {
        new AsyncTask<String, Void, String>() {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                showProgessDialog();
            }

            @Override
            protected String doInBackground(String... params) {
                HashMap<String, String> map = new HashMap<>();
                map.put("email_id", params[0]);
                map.put("password", params[1]);
                return HTTPUrlConnection.getInstance().loadPost(Config.BASE_URL + "/api/user_login", map);
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
                dismissProgressDialog();
                try {
                    JSONObject obj = new JSONObject(result);
                    if (obj.getString("status_id").equalsIgnoreCase("1")) {
                        startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                    } else {
                        Toast.makeText(LoginActivity.this, obj.getString("status_msg"), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }.execute(email, password);
    }
}
