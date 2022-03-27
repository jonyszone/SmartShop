package shafi.example.smartshop;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    private EditText InputName, InputPhoneNumber, InputPassword;
    private ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button createAccountButton = findViewById(R.id.register_btn);
        InputName = findViewById(R.id.register_username_input);
        InputPhoneNumber = findViewById(R.id.register_phone_number_input);
        InputPassword = findViewById(R.id.register_password_input);
        loadingBar = new ProgressDialog(this);
        
        createAccountButton.setOnClickListener(v -> CreateAccount());
    }

    private void CreateAccount() {

        String name = InputName.getText().toString();
        String phone = InputPhoneNumber.getText().toString();
        String password = InputPassword.getText().toString();

        if (TextUtils.isEmpty(name)){
            Toast.makeText(this,"Please Enter your name", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(phone)){
            Toast.makeText(this,"Please Enter your Phone Number", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(password)){
            Toast.makeText(this,"Please Enter your Password", Toast.LENGTH_SHORT).show();
        }

        else{
            loadingBar.setTitle("Create Account");
            loadingBar.setMessage("Please Wait....");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

            ValidatePhoneNumber(name,phone,password);
        }
    }

    private void ValidatePhoneNumber(String name, String phone, String password) {

        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (!(snapshot.child("Users").child(phone).exists())){

                    HashMap<String, Object> userdataMap = new HashMap<>();
                    userdataMap.put("phone",phone);
                    userdataMap.put("password",password);
                    userdataMap.put("name",name);

                    RootRef.child("Users").child(phone).updateChildren(userdataMap)
                            .addOnCompleteListener(task -> {

                                if (task.isSuccessful()){
                                    Toast.makeText(RegisterActivity.this, "WELCOME, Account Created Successfully",Toast.LENGTH_SHORT).show();
                                    loadingBar.dismiss();

                                    Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                                    startActivity(intent);
                                }
                                else{
                                    loadingBar.dismiss();
                                    Toast.makeText(RegisterActivity.this,"Network Problem: Try again",Toast.LENGTH_SHORT).show();

                                }

                            });
                }
                else{
                    Toast.makeText(RegisterActivity.this, "This "+ phone+" exists.", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                    Toast.makeText(RegisterActivity.this,"Try again with another phone Number", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}