package mobileapp.development.honoursprojecthealthapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import mobileapp.development.honoursprojecthealthapp.data.FoodList;

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
    List<FoodList> foodLists = new ArrayList<>();

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

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnGoToAbout) {
            Navigation.findNavController(v).navigate(R.id.action_foodInfoFragment_to_aboutFragment);
        }
    }
}