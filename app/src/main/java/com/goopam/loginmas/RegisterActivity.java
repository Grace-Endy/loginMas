package com.goopam.loginmas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    EditText username, userpassword, useremail, usercontact;
    Button btnRegister;

    private static final String registerURL ="https://www.goopam.com/dbwork/registeruser.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        username= findViewById(R.id.rgUserName);
        userpassword=findViewById(R.id.rgUserPassword);
        usercontact=findViewById(R.id.rgUserContactNo);
        useremail=findViewById(R.id.rgUserEmail);

        btnRegister=findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registernewuser();
            }
        });

    }

    private void registernewuser() {
        StringRequest request = new StringRequest(Request.Method.POST, registerURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.contains("success")){
                    Toast.makeText( getApplicationContext(), "Successfully Registered User", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                    startActivity(intent);

                }
                else {
                    Toast.makeText(getApplicationContext(), "Error Registering New User", Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }

        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params=new HashMap<>();
                params.put("registeruser", "true");

                params.put("username", username.getText().toString().trim());
                params.put("userpassword", userpassword.getText().toString().trim());
                params.put("useremail", useremail.getText().toString());
                params.put("usercontact", usercontact.getText().toString());
                return params;
            }
        };

        Volley.newRequestQueue(this).add(request);
    }


}
