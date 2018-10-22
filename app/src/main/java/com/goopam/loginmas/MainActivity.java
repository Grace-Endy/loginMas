package com.goopam.loginmas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    TextView signup;
    EditText username, userpassword;
    Button btnLogin;

    private static final String loginUrl="https://goopam.com/dbwork/login.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        username=findViewById(R.id.usename);
        userpassword=findViewById(R.id.userpassword);
        signup=findViewById(R.id.userSignup);

        btnLogin=findViewById(R.id.btnUserLogin);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);

            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogin();
            }
        });
    }

     public  void userLogin(){
         StringRequest request = new StringRequest(StringRequest.Method.POST, loginUrl, new Response.Listener<String>() {
             @Override
             public void onResponse(String response) {
                        if (response.contains("Success")){
                            Intent intent =new Intent(getApplicationContext(), UserActivity.class);
                            intent.putExtra("username", username.getText().toString());
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "Invalid username or password", Toast.LENGTH_LONG).show();
                        }
             }
         }, new Response.ErrorListener() {
             @Override
             public void onErrorResponse(VolleyError error) {

             }
         }) {
             @Override
             protected Map<String, String> getParams() throws AuthFailureError {
           Map<String, String> params = new HashMap<>();
           params.put("loginuser", "true");
           params.put("usermame", username.getText().toString().trim());
             params.put("userpassword", userpassword.getText().toString().trim());
             return params;
             }
         };

         Volley.newRequestQueue(this).add(request);
     }
}
