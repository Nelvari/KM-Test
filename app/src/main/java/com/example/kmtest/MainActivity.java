package com.example.kmtest;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button btnCheck, btnNext;
    EditText etName, etPalindrome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        etPalindrome = findViewById(R.id.etPalindrome);
        btnCheck = findViewById(R.id.btnCheck);
        btnNext = findViewById(R.id.btnNext);

        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String str = etPalindrome.getText().toString();
                String newStr=new StringBuilder(str).reverse().toString();

                if(str.equals(newStr)){

                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setMessage("isPalindrome")
                            .setTitle("Check Palindrome")
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            });

                    AlertDialog dialog = builder.create();
                    dialog.show();

                } else{

                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setMessage("not palindrome")
                            .setTitle("Check Palindrome")
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            });

                    AlertDialog dialog = builder.create();
                    dialog.show();

                }

            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("name", etName.getText().toString());
                startActivity(intent);

            }
        });

    }

}