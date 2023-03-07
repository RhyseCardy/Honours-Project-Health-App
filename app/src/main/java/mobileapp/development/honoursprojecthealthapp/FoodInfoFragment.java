package mobileapp.development.honoursprojecthealthapp;

import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import mobileapp.development.honoursprojecthealthapp.data.FoodItemsDatabase;
import mobileapp.development.honoursprojecthealthapp.data.UserFoodList;
import mobileapp.development.honoursprojecthealthapp.data.UserFoodListDAO;
import mobileapp.development.honoursprojecthealthapp.data.Utils;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FoodInfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FoodInfoFragment extends Fragment implements View.OnClickListener {


    private static final String TAG = "FoodItemFrag";
    private static final String ARG_FOOD_ITEM_NAME = "foodItemName";



    // TODO: Rename and change types of parameters
    private String mFoodItemName;

    public FoodInfoFragment() {
        // Required empty public constructor
    }
    List<UserFoodList> foodLists = new ArrayList<>();

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * //@param foodItemName the name of the food item to display that food items information.
     * @return A new instance of fragment FoodInfoFragment.
     */
    public static FoodInfoFragment newInstance(String mFoodItemName) {
        FoodInfoFragment fragment = new FoodInfoFragment();
        Bundle args = new Bundle();

        //REMEMBER TO UN-COMMENT THIS

        //args.putString(ARG_FOOD_ITEM_NAME, foodItemName);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mFoodItemName = getArguments().getString(ARG_FOOD_ITEM_NAME);
        }
    }

    //foodItemName IS TO BE USED WHEN API LINK IS ESTABLISHED AND DATA IS ADDED TO DATABASE AND RETRIEVED

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_food_info, container, false);
        Button btnGoToAbout = view.findViewById(R.id.btnGoToAbout);
        btnGoToAbout.setOnClickListener(this);

        return view;
    }

    // COMMENTED SO WORK CAN BE FINISHED LATER

//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        //inflate the layout for this fragment
//
//        Button btnAddToUserList = view.findViewById(R.id.btnAddToUserList);
//        btnAddToUserList.setOnClickListener(this);
//
//        Uri uri = Utils.buildUri("https://world.openfoodfacts.org/cgi/search.pl?","search_terms", mFoodItemName, "json", "1");
//
//        Log.d(TAG, "onViewCreated: " + uri.toString());
//
//        // get the database for storing and loading the food item information to be displayed in user food list
//        FoodItemsDatabase foodItemsDatabase = FoodItemsDatabase.getDatabase(getContext());
//        //get the DAO for the food items
//        UserFoodListDAO userFoodListDAO = foodItemsDatabase.userFoodListDAO();
//
//        //request a string response from the provided url using Volley
//        StringRequest request = new StringRequest(Request.Method.GET, uri.toString(),
//                //new Response.Listener<String>() {

//                    @Override
//                    public void onResponse(String response) {
//                        Log.d(TAG, response);
//                        List<String>
//
//                    }
                //}
//    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnGoToAbout) {
            Navigation.findNavController(v).navigate(R.id.action_foodInfoFragment_to_aboutFragment);
        }
    }
}