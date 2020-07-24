package com.example.intern;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {
  private Button registerBtn;
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase db;
    private EditText regName,password,regEmail,cnfPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        openHelper = new DatabaseHelper(this);

        registerBtn = findViewById(R.id.btnsignup);
        regName = findViewById(R.id.editTextTextPersonName);
        password = findViewById(R.id.editTextTextPassword);
        regEmail = findViewById(R.id.editTextTextEmailAddress);
        cnfPassword = findViewById(R.id.editcnfTextPassword);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db = openHelper.getWritableDatabase();
                String fname = regName.getText().toString().trim();
                String fpassword = password.getText().toString().trim();
                String fGmail = regEmail.getText().toString().trim();
                String cPassword = cnfPassword.getText().toString().trim();
                if (fname.isEmpty() || cPassword.isEmpty() || fGmail.isEmpty() || fpassword.isEmpty()) {
                    Toast.makeText(Register.this, "Please fill all the details", Toast.LENGTH_SHORT).show();
                }
                else if(fpassword!= cPassword)
                {
                    Toast.makeText(Register.this, "password Missmatch", Toast.LENGTH_SHORT).show();
                }
                else {
                    insertData(fname, fpassword, fGmail, cPassword);
                    Toast.makeText(Register.this, "Registration Successful", Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(Register.this, MainActivity.class));
                    finish();
                }


            }
        });


    }
    public void insertData(String fname,String fPhone,String fGmail,String fPassword){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.COL_2,fname);
        contentValues.put(DatabaseHelper.COL_3,fPhone);
        contentValues.put(DatabaseHelper.COL_4,fGmail);
        contentValues.put(DatabaseHelper.COL_5,fPassword);

        long id = db.insert(DatabaseHelper.TABLE_NAME,null,contentValues);
    }
}

