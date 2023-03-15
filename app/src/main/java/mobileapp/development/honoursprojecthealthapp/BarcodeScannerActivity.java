package mobileapp.development.honoursprojecthealthapp;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;
import com.google.android.material.button.MaterialButton;

public class BarcodeScannerActivity extends AppCompatActivity {

    private Button btnCamera;
    private Button btnGallery;
    private ImageView ivScannerImage;
    private Button btnScan2;
    private TextView tvScanResult;

    //Created to handle the result of the camera and gallery permissions in the onRequestPermissionResults function
    private static final int CAMERA_REQUEST_CODE = 100;
    private static final int STORAGE_REQUEST_CODE = 101;

    //Arrays of permissions required to pick an image from the gallery or camera use
    private String[] cameraPermissions;
    private String[] storagePermissions;


    //Uri of the image received from the camera or gallery
    private Uri imageUri = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode_scanner);

        //Variables for UI elements
        btnCamera = findViewById(R.id.btnCamera);
        btnGallery = findViewById(R.id.btnGallery);
        ivScannerImage = findViewById(R.id.ivScannerImage);
        btnScan2 = findViewById(R.id.btnScan2);
        tvScanResult = findViewById(R.id.tvScanResult);

        //Initialise the arrays of permissions to pick the image from the gallery or camera
        cameraPermissions = new String[]{Manifest.permission.CAMERA , Manifest.permission.WRITE_EXTERNAL_STORAGE};
        storagePermissions = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};




        //Handles the "Camera" button click, checks permissions on device related to camera and takes the image from the camera directly
        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        //Handles the "Gallery" button click, checks permissions on device related to the gallery and takes the image from the phone gallery
        btnGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        //Handles the "Scan" button click, scan the Barcode/QR code from the image taken from the gallery or camera
        btnScan2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }




}