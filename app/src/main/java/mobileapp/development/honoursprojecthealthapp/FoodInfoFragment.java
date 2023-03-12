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



    // member variables for setting up the display
    private String mFoodItemName;

    public FoodInfoFragment() {
        // Required empty public constructor
    }
    List<UserFoodList> userFoodLists = new ArrayList<>();

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * //@param foodItemName the name of the food item to display that food items information.
     * @return A new instance of fragment FoodInfoFragment.
     */
    public static FoodInfoFragment newInstance(String foodItemName) {
        FoodInfoFragment fragment = new FoodInfoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_FOOD_ITEM_NAME, foodItemName);
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



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //inflate the layout for this fragment

        Button btnAddToUserList = view.findViewById(R.id.btnAddToUserList);
        btnAddToUserList.setOnClickListener(this);

        Uri uri = Utils.buildUri("https://world.openfoodfacts.org/cgi/search.pl?","search_terms", mFoodItemName, "json", "1");

        Log.d(TAG, "onViewCreated: " + uri.toString());

        // get the database for storing and loading the food item information to be displayed in user food list
        FoodItemsDatabase foodItemsDatabase = FoodItemsDatabase.getDatabase(getContext());
        //get the DAO for the food items
        UserFoodListDAO userFoodListDAO = foodItemsDatabase.userFoodListDAO();

        //request a string response from the provided url using Volley
        StringRequest request = new StringRequest(Request.Method.GET, uri.toString(),
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, response);
                        List<String> ingredientsVeganArray = new ArrayList<String>();
                        List<String> ingredientsVegetarianArray = new ArrayList<String>();

                        try {
                            //convert response to JSON Object
                            JSONObject rootObject = new JSONObject(response);
                            JSONArray resultsObj = rootObject.getJSONArray("products");
                            JSONObject foodItemObj = resultsObj.getJSONObject(0);


                            //
                            //REMEMBER TO UPDATE THE IMAGE, DO THIS LATER!!!!
                            //


                            //add the food item information to foodInfo using API variable names
                            String foodName = foodItemObj.getString("product_name");

                            String foodAllergens = foodItemObj.getString("allergens_from_ingredients");


                            JSONArray ingredientsVeganArrayObj = foodItemObj.getJSONArray("ingredients");


                            JSONArray ingredientsVegetarianArrayObj = foodItemObj.getJSONArray("ingredients");


                            String foodNUTRIScore = foodItemObj.getString("nutriscore_grade");

                            int foodNOVAScore = foodItemObj.getInt("nova_group");

                            // Ingredients are put into an array to find how many of them are vegan friendly
                            for (int i = 0, j = ingredientsVeganArrayObj.length(); i < j; i++) {
                                JSONObject veganObj = ingredientsVeganArrayObj.getJSONObject(i);
                                String vegan = veganObj.getString("vegan");
                                ingredientsVeganArray.add(vegan);
                            }

                            // Ingredients are put into an array to find how many of them are vegetarian friendly
                            for (int i = 0, j = ingredientsVegetarianArrayObj.length(); i < j; i++) {
                                JSONObject vegetarianObj = ingredientsVegetarianArrayObj.getJSONObject(i);
                                String vegetarian = vegetarianObj.getString("vegetarian");
                                ingredientsVeganArray.add(vegetarian);
                            }

                            // update text in the food item name text view
                            TextView tvFoodInfoName = view.findViewById(R.id.tvFoodInfoName);
                            tvFoodInfoName.setText(foodName);

                            // update text in the ingredient vegan information text view
                            TextView tvFoodInfoVegan = view.findViewById(R.id.tvFoodInfoVegan);
                            tvFoodInfoVegan.setText(ingredientsVeganArrayObj.toString());

                            // update text in the ingredient vegetarian text view
                            TextView tvFoodInfoVegetarian = view.findViewById(R.id.tvFoodInfoVegetarian);
                            tvFoodInfoVegetarian.setText(ingredientsVegetarianArray.toString());

                            // update text in the food item's allergens information text view
                            TextView tvFoodInfoAllergens = view.findViewById(R.id.tvFoodInfoAllergens);
                            tvFoodInfoAllergens.setText(foodAllergens);

                            // update text in the food item NUTRI Score text view
                            TextView tvFoodInfoNUTRIScore = view.findViewById(R.id.tvFoodInfoNUTRIScore);
                            tvFoodInfoNUTRIScore.setText(foodNUTRIScore);

                            // update text in the food item NOVA Score text view
                            TextView tvFoodInfoNOVAScore = view.findViewById(R.id.tvFoodInfoNOVAScore);
                            tvFoodInfoNOVAScore.setText(String.valueOf(foodNOVAScore));

                            // log to make the sure the API data is successfully added and read
                            Log.d(TAG, foodName);
                            Log.d(TAG, foodAllergens);
                            Log.d(TAG, String.valueOf(ingredientsVeganArrayObj));
                            Log.d(TAG, String.valueOf(ingredientsVegetarianArray));
                            Log.d(TAG, String.valueOf(foodNOVAScore));
                            Log.d(TAG, foodNUTRIScore);

                            // on click listener for button that sends the data to be read and displayed in the fav games fragment
                            btnAddToUserList.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    UserFoodList ufi = new UserFoodList();
                                    ufi.setUserFoodItemName(foodName);
                                    ufi.setUserFoodItemAllergens(foodAllergens);
                                    ufi.setUserFoodItemNOVAScore(String.valueOf(foodNOVAScore));
                                    ufi.setUserFoodItemVegan(String.valueOf(ingredientsVeganArrayObj));
                                    ufi.setUserFoodItemVegetarian(String.valueOf(ingredientsVegetarianArrayObj));
                                    ufi.setUserFoodItemNUTRIScore(foodNUTRIScore);
                                    userFoodLists.add(ufi);

                                   userFoodListDAO.insert(userFoodLists);
                                   int i = 0 ;
                                   Navigation.findNavController(view).navigate(R.id.action_foodInfoFragment_to_userFoodListFragment);
                                   Log.d(TAG, "onClick: "+ userFoodLists);
                                }
                            });

                        } catch (JSONException e) {
                            Toast.makeText(getActivity(), getString(R.string.error_downloading_food_information), Toast.LENGTH_LONG);

                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), getString(R.string.error_downloading_food_information), Toast.LENGTH_LONG);
        }
    });
        // now the request is made
        RequestQueue queue = Volley.newRequestQueue(getContext());
        queue.add(request);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnGoToAbout) {
            Navigation.findNavController(v).navigate(R.id.action_foodInfoFragment_to_aboutFragment);
        }

        if (v.getId() == R.id.btnAddToUserList) {
            Navigation.findNavController(v).navigate(R.id.action_foodInfoFragment_to_userFoodListFragment);
        }
    }
}