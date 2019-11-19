package ort.nt2.tpfinal;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import ort.nt2.tpfinal.util.DatePickerFragment;

public class ProfileActivity extends AppCompatActivity {
    EditText etName;
    EditText etZone;
    EditText etEmail;
    EditText etAddress;
    EditText etBirthDate;
    ImageButton etImage;

    Bitmap profilePicture;

    SharedPreferences sp;

    String currentPhotoPath;

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
        String strbirthDate = sp.getString("birthday", null);
        String strimage = sp.getString("image", null);

        etName.setText(strname);
        etBirthDate.setText(strbirthDate);
        etZone.setText(strzone);
        etEmail.setText(stremail);
        etAddress.setText(straddress);

        if (strimage != null) etImage.setImageBitmap(decodeBase64(strimage));

        final EditText birthDate = (EditText) findViewById(R.id.profile_birthDate);
        birthDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(birthDate);
            }
        });
    }

    public void showDatePickerDialog(final EditText date) {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                // +1 because January is zero
                final String selectedDate = day + " / " + (month + 1) + " / " + year;
                date.setText(selectedDate);
            }
        });

        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void save(View v) {
        SharedPreferences.Editor ed = sp.edit();
        ed.putString("name", etName.getText().toString());
        ed.putString("zone", etZone.getText().toString());
        ed.putString("email", etEmail.getText().toString());
        ed.putString("address", etAddress.getText().toString());
        ed.putString("birthday", etBirthDate.getText().toString());
        ed.putString("image", encodeTobase64(profilePicture));

        ed.apply();

        Intent home = new Intent(ProfileActivity.this, MainActivity.class);
        startActivity(home);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1 && resultCode == RESULT_OK) {
            etImage = findViewById(R.id.profile_imageButton);

            Bundle extras = data.getExtras();
            profilePicture = (Bitmap) extras.get("data");
            etImage.setImageBitmap(profilePicture);
        }
    }

    public void openCamera(View v) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "ort.nt2.tpfinal.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, 1);
            }
        }
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }

    // method for bitmap to base64
    public static String encodeTobase64(Bitmap image) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();

        return Base64.encodeToString(b, Base64.DEFAULT);
    }

    // method for base64 to bitmap
    public static Bitmap decodeBase64(String input) {
        byte[] decodedByte = Base64.decode(input, 0);
        return BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length);
    }

}
