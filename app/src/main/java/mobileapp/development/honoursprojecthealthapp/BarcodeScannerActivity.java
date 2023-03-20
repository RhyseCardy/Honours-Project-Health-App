package mobileapp.development.honoursprojecthealthapp;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultCaller;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Camera;
import android.graphics.Point;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.PackageManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.barcode.BarcodeDetector;
import com.google.android.material.button.MaterialButton;
import com.google.mlkit.vision.barcode.BarcodeScanner;
import com.google.mlkit.vision.barcode.BarcodeScannerOptions;
import com.google.mlkit.vision.barcode.BarcodeScanning;
import com.google.mlkit.vision.barcode.common.Barcode;
import com.google.mlkit.vision.common.InputImage;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import mobileapp.development.honoursprojecthealthapp.data.FoodItemsDatabase;
import mobileapp.development.honoursprojecthealthapp.data.UserFoodList;
import mobileapp.development.honoursprojecthealthapp.data.UserFoodListDAO;
import mobileapp.development.honoursprojecthealthapp.data.Utils;

public class BarcodeScannerActivity extends AppCompatActivity {

    private Button btnCamera;
    private Button btnGallery;
    private ImageView ivScannerImage;
    private Button btnScan2;
    private TextView tvBarcodeFoodName;
    private TextView tvBarcodeFoodAllergens;
    private TextView tvBarcodeFoodNUTRIScore;
    private TextView tvBarcodeFoodNOVAScore;
    private TextView tvBarcodeIngredientsAnalysis;

    private static final String ARG_BARCODE_FOOD_ITEM_NAME = "barcodeFoodItemName";

    //Created to handle the result of the camera and gallery permissions in the onRequestPermissionResults function
    private static final int CAMERA_REQUEST_CODE = 100;
    private static final int STORAGE_REQUEST_CODE = 101;

    //Arrays of permissions required to pick an image from the gallery or camera use
    private String[] cameraPermissions;
    private String[] storagePermissions;


    //Uri of the image received from the camera or gallery
    private Uri imageUri = null;

    private BarcodeScannerOptions barcodeScannerOptions;
    private BarcodeScanner barcodeScanner;

    private static final String TAG = "MAIN_TAG";

//    private static BarcodeScannerActivity newInstance(String barcodeFoodItemName) {
//        BarcodeScannerActivity activity = new BarcodeScannerActivity();
//        Bundle args = new Bundle();
//        args.putString(ARG_BARCODE_FOOD_ITEM_NAME, barcodeFoodItemName);
//        activity.setIntent(args);
//
//    }

    List<UserFoodList> userFoodLists = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode_scanner);

        //Variables for UI elements
        btnCamera = findViewById(R.id.btnCamera);
        btnGallery = findViewById(R.id.btnGallery);
        ivScannerImage = findViewById(R.id.ivScannerImage);
        btnScan2 = findViewById(R.id.btnScan2);
        tvBarcodeFoodName = findViewById(R.id.tvBarcodeFoodName);
        tvBarcodeFoodAllergens = findViewById(R.id.tvBarcodeFoodAllergens);
        tvBarcodeFoodNUTRIScore = findViewById(R.id.tvBarcodeFoodNUTRIScore);
        tvBarcodeFoodNOVAScore = findViewById(R.id.tvBarcodeFoodNOVAScore);
        tvBarcodeIngredientsAnalysis = findViewById(R.id.tvBarcodeIngredientsAnalysis);

        //Initialise the arrays of permissions to pick the image from the gallery or camera
        cameraPermissions = new String[]{Manifest.permission.CAMERA , Manifest.permission.WRITE_EXTERNAL_STORAGE};
        storagePermissions = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};



        //Initialised setup for the BarcodeScannerOptions
        barcodeScannerOptions = new BarcodeScannerOptions.Builder()
                .setBarcodeFormats(Barcode.FORMAT_ALL_FORMATS)
                    .build();


        barcodeScanner = BarcodeScanning.getClient(barcodeScannerOptions);

        //Handles the "Camera" button click, checks permissions on device related to camera and takes the image from the camera directly
        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkCameraPermission()){

                    // The permission required for the camera function is already granted so the camera intent is launched
                    pickImageCamera();
                }
                else{

                    // The permission required for the camera function is not granted so the permission is requested
                    requestCameraPermission();
                }
            }
        });

        //Handles the "Gallery" button click, checks permissions on device related to the gallery and takes the image from the phone gallery
        btnGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkStoragePermission()){

                    // The permission required for the gallery function is already granted so the gallery intent is launched
                    pickImageGallery();
                }
                else{

                    // The permission required for the gallery function is not granted so the permission is requested
                    requestStoragePermission();
                }
            }
        });

        //Handles the "Scan" button click, scan the Barcode/QR code from the image taken from the gallery or camera
        btnScan2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (imageUri == null){

                    Toast.makeText(BarcodeScannerActivity.this, "Pick Image First...", Toast.LENGTH_SHORT).show();
                }
                else{

                    detectResultFromImage();
                }



            }
        });

    }

    private void detectResultFromImage() {
        try {
            // Prepare image from the image uri
            InputImage inputImage = InputImage.fromFilePath(this, imageUri);

            // Start scanning the Barcode/QR Code data from the image
            Task<List<Barcode>> barcodeResult = barcodeScanner.process(inputImage)
                    .addOnSuccessListener(new OnSuccessListener<List<Barcode>>() {
                        @Override
                        public void onSuccess(List<Barcode> barcodes) {
                            // Task successfully completed and the detailed info can be received
                            extractBarcodeQRCodeInfo(barcodes);
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            // Task failed with an exception and no info can be received
                            Toast.makeText(BarcodeScannerActivity.this, "Failed Scanning Due To "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        }
        catch (Exception e){
            // failed with an exception either due to preparing the InputImage or an issue in the BarcodeScanner initialisation
            Toast.makeText(this, "Failed Due To "+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void extractBarcodeQRCodeInfo(List<Barcode> barcodes) {

        for (Barcode barcode : barcodes){
            Rect bounds = barcode.getBoundingBox();
            Point[] corners = barcode.getCornerPoints();


            String rawValue = barcode.getRawValue();
            Log.d(TAG, "extractBarcodeQRCodeInfo rawValue "+ rawValue);


            int valueType = barcode.getValueType();

            Uri uri = Utils.buildUriBarcode("https://world.openfoodfacts.org/api/v2/search?","code", rawValue);

            Log.d(TAG, "Barcode API connection: " + uri.toString());

            FoodItemsDatabase foodItemsDatabase = FoodItemsDatabase.getDatabase(getApplicationContext());

            UserFoodListDAO userFoodListDAO = foodItemsDatabase.userFoodListDAO();

            StringRequest request = new StringRequest(Request.Method.GET, uri.toString(),
            new Response.Listener<String>() {

                @Override
                public void onResponse(String response) {
                    Log.d(TAG, response);

                    try {
                        //convert response to JSON Object
                        JSONObject rootObject = new JSONObject(response);
                        JSONArray resultsObj = rootObject.getJSONArray("products");
                        JSONObject foodItemObj = resultsObj.getJSONObject(0);


                        //
                        //REMEMBER TO UPDATE THE IMAGE, DO THIS LATER!!!!
                        //
                        Log.d(TAG, "food item info" + foodItemObj);


                        //add the food item information to foodInfo using API variable names
                        String foodName = foodItemObj.getString("product_name");

                        Log.d(TAG, "Food name Info" + foodName);


                        String foodAllergens = foodItemObj.getString("allergens_from_ingredients");

                        Log.d(TAG, "Food Allergens Info" + foodAllergens);


                        String foodIngredientsAnalysis = foodItemObj.getString("ingredients_analysis_tags");

                        Log.d(TAG, "Food Ingredients Tags" + foodIngredientsAnalysis);


                        String foodNUTRIScore = foodItemObj.getString("nutriscore_grade");

                        Log.d(TAG, "Food NUTRIScore Info" + foodNUTRIScore);



                        int foodNOVAScore = foodItemObj.getInt("nova_group");

                        Log.d(TAG, "Food NOVAScore Info" + foodNOVAScore);




                        // update text in the food item name text view
                        tvBarcodeFoodName.setText(foodName);


                        // update text in the food item's allergens information text view
                        tvBarcodeFoodAllergens.setText(foodAllergens);


                        tvBarcodeIngredientsAnalysis.setText(foodIngredientsAnalysis);

                        // update text in the food item NUTRI Score text view
                        tvBarcodeFoodNUTRIScore.setText(foodNUTRIScore);

                        // update text in the food item NOVA Score text view
                        tvBarcodeFoodNOVAScore.setText(String.valueOf(foodNOVAScore));

                        // log to make the sure the API data is successfully added and read
                        Log.d(TAG, foodName);
                        Log.d(TAG, foodAllergens);
                        Log.d(TAG, foodIngredientsAnalysis);
                        Log.d(TAG, String.valueOf(foodNOVAScore));
                        Log.d(TAG, foodNUTRIScore);

                        // on click listener for button that sends the data to be read and displayed in the user food items fragment

                    } catch (JSONException e) {
                        Toast.makeText(getApplicationContext(), getString(R.string.error_downloading_food_information), Toast.LENGTH_LONG);

                    }


                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(), getString(R.string.error_downloading_food_information), Toast.LENGTH_LONG);
                }
            });
            // now the request is made
            RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
            queue.add(request);

        }




    }






//            switch (valueType){
//                case Barcode.TYPE_WIFI:{
//
//                    //
//                    //
//                    //FROM THIS POINT ONWARDS, CONNECT TEXT VIEWS TO API!!!!!!!!
//                    //
//                    //
//
//                    // Used to get wifi related data
//                    Barcode.WiFi typeWiFi = barcode.getWifi();
//
//                    // Used to get all the info about wifi
//                    String ssid = ""+ typeWiFi.getSsid();
//                    String password = ""+ typeWiFi.getPassword();
//                    String encryptionType = ""+ typeWiFi.getEncryptionType();
//
//                    // Log the outcome
//                    Log.d(TAG, "extractBarcodeQRCodeInfo ssid: "+ ssid);
//                    Log.d(TAG, "extractBarcodeQRCodeInfo password: "+ password);
//                    Log.d(TAG, "extractBarcodeQRCodeInfo encryptionType: "+ encryptionType);
//
//                    // Set the results to the textview
//                    //tvScanResult.setText("TYPE: TYPE_WIFI \nssid: "+ ssid +"\npassword: "+password+"\nencryptionType"+encryptionType+"\nraw value: "+rawValue);
//                }
//                break;
//                case Barcode.TYPE_URL:{
//
//                    // Used to get url related data
//                    Barcode.UrlBookmark typeUrl = barcode.getUrl();
//
//                    // Used to get all the info about the url
//                    String title = ""+ typeUrl.getTitle();
//                    String url = ""+ typeUrl.getUrl();
//
//                    // Log the outcome
//                    Log.d(TAG, "extractBarcodeQRCodeInfo: TYPE_URL");
//                    Log.d(TAG, "extractBarcodeQRCodeInfo: title: "+title);
//                    Log.d(TAG, "extractBarcodeQRCodeInfo: url: "+url);
//
//                    // Set the results to the textview
//                    //tvScanResult.setText("TYPE: TYPE_URL \ntitle: " + title + "\nurl: " + url + "\nraw value: " + rawValue);
//                }
//
//                //
//                // EVERYTHING UNDER HERE NEEDS RE-ADJUSTED TO FIT THE PROJECT, POTENTIALLY ABOVE AS WELL!!!!!!!!!!!!!!
//                // REMEMBER THIS!!!!!!!!!!!!
//                //
//
//                break;
//                case Barcode.TYPE_EMAIL:{
//
//                    Barcode.Email typeEmail = barcode.getEmail();
//
//                    String address = ""+ typeEmail.getAddress();
//                    String body = ""+ typeEmail.getBody();
//                    String subject = ""+ typeEmail.getSubject();
//
//                    Log.d(TAG, "extractBarcodeQRCodeInfo: TYPE_EMAIL");
//                    Log.d(TAG, "extractBarcodeQRCodeInfo: address: "+ address);
//                    Log.d(TAG, "extractBarcodeQRCodeInfo: body: "+ body);
//                    Log.d(TAG, "extractBarcodeQRCodeInfo: subject: "+ subject);
//
//                    //tvScanResult.setText("TYPE: TYPE_EMAIL \naddress: " + address + "\nbody: " + body + "\nsubject: "+ subject + "\nraw value: " + rawValue);
//                }
//                break;
//                default:{
//                    //tvScanResult.setText("raw value " + rawValue);
//                }
//            }
//
//
//        }
//    }





    private void pickImageGallery(){

        Intent intent = new Intent(Intent.ACTION_PICK);

        intent.setType("image/*");
        galleryActivityResultLauncher.launch(intent);

    }

    private final ActivityResultLauncher<Intent> galleryActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {

                    if (result.getResultCode() == Activity.RESULT_OK){

                        Intent data = result.getData();
                        imageUri = data.getData();
                        Log.d(TAG, "OnActivityResult: imageUri" + imageUri);

                        ivScannerImage.setImageURI(imageUri);
                    }
                    else{

                        Toast.makeText(BarcodeScannerActivity.this, "Cancelled", Toast.LENGTH_SHORT).show();
                    }
                }
            }

    );

    private void pickImageCamera(){

        ContentValues contentValues = new ContentValues();
        contentValues.put(MediaStore.Images.Media.TITLE, "Sample Title");
        contentValues.put(MediaStore.Images.Media.DESCRIPTION, "Sample Title Description");

        imageUri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        cameraActivityResultLauncher.launch(intent);

    }

    private final ActivityResultLauncher<Intent> cameraActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {

                    if (result.getResultCode() == Activity.RESULT_OK){

                        Intent data = result.getData();

                        Log.d(TAG, "onActivityResult: imageUri: "+ imageUri);
                        ivScannerImage.setImageURI(imageUri);
                    }
                    else{

                        Toast.makeText(BarcodeScannerActivity.this, "Cancelled...", Toast.LENGTH_SHORT).show();
                    }
                }
            }
    );

    private boolean checkStoragePermission(){

        boolean result = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED;

        return result;
    }

    private void requestStoragePermission(){
        ActivityCompat.requestPermissions(this, storagePermissions, STORAGE_REQUEST_CODE);
    }

    private boolean checkCameraPermission(){

        boolean resultCamera = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                ==PackageManager.PERMISSION_GRANTED;

        boolean resultStorage = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                ==PackageManager.PERMISSION_GRANTED;

        return resultCamera && resultStorage;
    }

    private void requestCameraPermission(){

        // Requesting the camera permissions for the launch intent
        ActivityCompat.requestPermissions(this, cameraPermissions, CAMERA_REQUEST_CODE);
    }

    // Handles runtime permission results
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){
            case CAMERA_REQUEST_CODE:{

                // Check if some action from the permission dialog is performed or not and Allow/Deny
                if (grantResults.length > 0){

                    // Check if the Camera and Storage permissions are granted, contains boolean results for either true or false
                    boolean cameraAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean storageAccepted = grantResults[1] == PackageManager.PERMISSION_GRANTED;

                    // Check if both permissions are granted or not
                    if(cameraAccepted && storageAccepted){

                        // Runs if the permissions from the gallery and camera are met and so the camera intent is launched
                        pickImageCamera();
                    }
                    else{
                        // Runs if one or both of the permissions are not met and so the camera intent cant be launched
                        Toast.makeText(this, "Camera and Storage Permissions Are Required...", Toast.LENGTH_SHORT).show();
                    }
                }
            }
            break;
            case STORAGE_REQUEST_CODE:{

                // Check if some action from the permission dialog is performed or not and Allow/Deny
                if (grantResults.length > 0){

                    // Check if the Storage permissions are granted, contains boolean results for either true or false
                    boolean storageAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;

                    // To check if the storage permission is granted or not
                    if (storageAccepted){

                        // Storage permission is granted and so the gallery intent can be launched
                        pickImageGallery();
                    }
                    else{

                        // Storage permission is denied and so the gallery intent cant be launched
                        Toast.makeText(this, "Storage Permission Is Required...", Toast.LENGTH_SHORT).show();
                    }
                }
            }
            break;

        }
    }

}