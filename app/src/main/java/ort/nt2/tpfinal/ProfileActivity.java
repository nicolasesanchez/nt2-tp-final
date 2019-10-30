package ort.nt2.tpfinal;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.IOException;

public class ProfileActivity extends AppCompatActivity {
    EditText etName;
    EditText etZone;
    EditText etEmail;
    EditText etAddress;
    EditText etBirthDate;
    ImageButton etImage;

    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        etName = findViewById(R.id.profile_etname);
        etZone = findViewById(R.id.profile_zone);
        etEmail = findViewById(R.id.profile_mail);
        etAddress = findViewById(R.id.profile_addres);
        etBirthDate = findViewById(R.id.profile_birthDate);
        etImage = findViewById(R.id.profile_imageButton);


        sp = getSharedPreferences("SPFile", MODE_PRIVATE);

        String strname = sp.getString("name", null);
        String strzone = sp.getString("zone", null);
        String stremail = sp.getString("email", null);
        String straddress = sp.getString("address", null);
        String strbirthDate = sp.getString("birthdate", null);
        String strimage = sp.getString("image", null);

        etName.setText(strname);
        etBirthDate.setText(strbirthDate);
        etZone.setText(strzone);
        etEmail.setText(stremail);
        etAddress.setText(straddress);
    }

    public void save(View v) {
        SharedPreferences.Editor ed = sp.edit();
        ed.putString("name", etName.getText().toString());
        ed.putString("zone", etZone.getText().toString());
        ed.putString("email", etEmail.getText().toString());
        ed.putString("address", etAddress.getText().toString());
        ed.putString("birthday", etBirthDate.getText().toString());
//        ed.putString("image", etImage.getImageAlpha());

        ed.apply();

        Intent home = new Intent(ProfileActivity.this, MainActivity.class);
        startActivity(home);
    }

    public void openCamera(View v) {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        startActivity(intent);
    }
}
