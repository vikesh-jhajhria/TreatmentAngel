package com.treatmentangel;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.treatmentangel.utils.AppPreferences;
import com.treatmentangel.utils.Config;
import com.treatmentangel.utils.HTTPUrlConnection;
import com.treatmentangel.utils.MyTextWatcher;
import com.treatmentangel.utils.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class RegisterActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.findViewById(R.id.back).setOnClickListener(this);

        findViewById(R.id.txt_login).setOnClickListener(this);
        findViewById(R.id.btn_reg).setOnClickListener(this);

        ((EditText) findViewById(R.id.edt_email)).addTextChangedListener(new MyTextWatcher(findViewById(R.id.til_email)));
        ((EditText) findViewById(R.id.edt_pass)).addTextChangedListener(new MyTextWatcher(findViewById(R.id.til_pass)));
        ((EditText) findViewById(R.id.edt_phone)).addTextChangedListener(new MyTextWatcher(findViewById(R.id.til_phone)));
        ((EditText) findViewById(R.id.edt_fname)).addTextChangedListener(new MyTextWatcher(findViewById(R.id.til_fname)));
        ((EditText) findViewById(R.id.edt_lname)).addTextChangedListener(new MyTextWatcher(findViewById(R.id.til_lname)));
        ((EditText) findViewById(R.id.edt_conf_pass)).addTextChangedListener(new MyTextWatcher(findViewById(R.id.til_conf_pass)));
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.txt_login:
                startActivity(new Intent(this, LoginActivity.class));
                finish();
                break;
            case R.id.btn_reg:
                String fname = ((EditText) findViewById(R.id.edt_fname)).getText().toString().trim();
                if (fname.isEmpty()) {
                    ((TextInputLayout) findViewById(R.id.til_fname)).setErrorEnabled(true);
                    ((TextInputLayout) findViewById(R.id.til_fname)).setError("Please enter first name");
                    break;
                }
                String lname = ((EditText) findViewById(R.id.edt_lname)).getText().toString().trim();
                if (lname.isEmpty()) {
                    ((TextInputLayout) findViewById(R.id.til_lname)).setErrorEnabled(true);
                    ((TextInputLayout) findViewById(R.id.til_lname)).setError("Please enter last name");
                    break;
                }
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
                String phone = ((EditText) findViewById(R.id.edt_phone)).getText().toString().trim();
                if (phone.isEmpty()) {
                    ((TextInputLayout) findViewById(R.id.til_phone)).setErrorEnabled(true);
                    ((TextInputLayout) findViewById(R.id.til_phone)).setError("Please enter phone number");
                    break;
                }
                if (phone.length() < 10) {
                    ((TextInputLayout) findViewById(R.id.til_phone)).setErrorEnabled(true);
                    ((TextInputLayout) findViewById(R.id.til_phone)).setError("Please enter valid phone number");
                    break;
                }
                String pass = ((EditText) findViewById(R.id.edt_pass)).getText().toString().trim();
                if (pass.isEmpty()) {
                    ((TextInputLayout) findViewById(R.id.til_pass)).setErrorEnabled(true);
                    ((TextInputLayout) findViewById(R.id.til_pass)).setError("Please enter password");
                    break;
                }
                String confPass = ((EditText) findViewById(R.id.edt_conf_pass)).getText().toString().trim();
                if (confPass.isEmpty()) {
                    ((TextInputLayout) findViewById(R.id.til_conf_pass)).setErrorEnabled(true);
                    ((TextInputLayout) findViewById(R.id.til_conf_pass)).setError("Please enter confirm password");
                    break;
                }
                if (!confPass.equals(pass)) {
                    ((TextInputLayout) findViewById(R.id.til_conf_pass)).setErrorEnabled(true);
                    ((TextInputLayout) findViewById(R.id.til_conf_pass)).setError("Confirm password is not matching");
                    break;
                }
                register(fname, lname, email, phone, pass);
                break;
        }
    }


    private void register(String fname, String lname, String email, String phone, String password) {
        new AsyncTask<String, Void, String>() {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                showProgessDialog();
            }

            @Override
            protected String doInBackground(String... params) {
                HashMap<String, String> map = new HashMap<>();
                map.put("first_name", params[0]);
                map.put("last_name", params[1]);
                map.put("email_id", params[2]);
                map.put("phone_number", params[3]);
                map.put("password", params[4]);
                return HTTPUrlConnection.getInstance().loadPost(Config.BASE_URL + "api/signup", map);
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
                dismissProgressDialog();
                try {
                    JSONObject obj = new JSONObject(result);
                    if (obj.getString("status_id").equalsIgnoreCase("1")) {
                        JSONObject data = obj.getJSONObject("user_data");
                        AppPreferences.getAppPreferences(getApplicationContext()).setUserData(data.toString());
                        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                        finish();
                    } else {
                        Toast.makeText(RegisterActivity.this, obj.getString("status_msg"), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }.execute(fname, lname, email, phone, password);
    }
}
