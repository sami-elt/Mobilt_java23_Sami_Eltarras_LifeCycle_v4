package com.gritacademy.lifecycle;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText et, etp;
    Button btn;
    SharedPreferences sharedpref;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        //initiate sharedpref
        sharedpref = this.getSharedPreferences("sami", this.MODE_PRIVATE);
        editor = sharedpref.edit();

        et = findViewById(R.id.editText);
        etp = findViewById(R.id.editTextPassword);
        btn = findViewById(R.id.button);

        //hardcoded input for log in with only one credential. makes toast for success or failed login
        btn.setOnClickListener((e)->{
            if (et.getText().toString().equals("sami") && etp.getText().toString().equals("hej")){
                Toast.makeText(this, "Welcome", Toast.LENGTH_SHORT).show();
                Intent a2 = new Intent(MainActivity.this, MainActivity2.class);
                startActivity( a2 );
            } else {
                Toast.makeText(this, "Try again", Toast.LENGTH_SHORT).show();
                Log.d("sami", "log in failed");
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    //get the last input when u start
    @Override
    protected void onStart() {
        super.onStart();

        String logIn = sharedpref.getString("sams", null);
        String pass = sharedpref.getString("hejsan", null);

        et.setText(logIn);
        etp.setText(pass);
    }

    //saves the input when u pause
    @Override
    protected void onPause() {
        super.onPause();
        String logInName = et.getText().toString();
        String logInPassword = etp.getText().toString();

        editor.putString("sams", logInName);
        editor.putString("hejsan", logInPassword);
        editor.apply();
    }


}