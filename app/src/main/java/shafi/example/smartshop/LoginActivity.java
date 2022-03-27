package shafi.example.smartshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rey.material.widget.CheckBox;

import io.paperdb.Paper;
import shafi.example.smartshop.CommonData.ManifestData;
import shafi.example.smartshop.Model.Users;

public class LoginActivity extends AppCompatActivity {
    private EditText InputPhoneNumber, InputPassword;
    private ProgressDialog loadingBar;
    private TextView Admin, NotAdmin;

    private String parentDbName = "Users";
    private CheckBox checkBox;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        InputPhoneNumber = findViewById(R.id.login_phone_number_input);
        InputPassword = findViewById(R.id.login_password_input);
        Button loginButton = findViewById(R.id.login_btn);
        loadingBar = new ProgressDialog(this);

        Admin = findViewById(R.id.admin_panel);
        NotAdmin = findViewById(R.id.not_admin_panel);



        checkBox = findViewById(R.id.remember_me);
        Paper.init(this);

        loginButton.setOnClickListener(v -> LoginUser());

        Admin.setOnClickListener(v -> {
            loginButton.setText("Login Admin");
            Admin.setVisibility(View.INVISIBLE);
            NotAdmin.setVisibility(View.VISIBLE);
            parentDbName = "Admins";

        });

        NotAdmin.setOnClickListener(v -> {
            loginButton.setText("Login");
            Admin.setVisibility(View.VISIBLE);
            NotAdmin.setVisibility(View.INVISIBLE);
            parentDbName = "Users";
        });

    }

    private void LoginUser() {
        String phone = InputPhoneNumber.getText().toString();
        String password = InputPassword.getText().toString();

        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "Please Enter your Phone Number", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please Enter your Password", Toast.LENGTH_SHORT).show();
        }else {
            loadingBar.setTitle("Log In Account");
            loadingBar.setMessage("Please Wait....");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

            AccessToAccount(phone, password);
        }
    }

    private void AccessToAccount(String phone, String password) {

        if (checkBox.isChecked()){
            Paper.book().write(ManifestData.UserPhoneKey, phone);
            Paper.book().write(ManifestData.UserPasswordKey, password);
        }

        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.child(parentDbName).child(phone).exists()){
                    Users users = snapshot.child(parentDbName).child(phone).getValue(Users.class);

                    if (users != null && users.getPhone().equals(phone)) {
                        if (users.getPassword().equals(password)) {
                            if (parentDbName.equals("Admins")){
                                Toast.makeText(LoginActivity.this, "Hey!!! Admin,logged in successful", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();

                                Intent intent = new Intent(LoginActivity.this, AdminCategoryActivity.class);
                                startActivity(intent);
                            }else if (parentDbName.equals("Users")){
                                Toast.makeText(LoginActivity.this, "logged in successful", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();

                                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                                startActivity(intent);
                            }
                        } else {
                            loadingBar.dismiss();
                            Toast.makeText(LoginActivity.this, "Password incorrect", Toast.LENGTH_SHORT).show();

                        }
                    }
                }else {
                    Toast.makeText(LoginActivity.this, "input incorrect", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}