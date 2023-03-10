package mobileapp.development.honoursprojecthealthapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import mobileapp.development.honoursprojecthealthapp.data.FoodItemsDatabase;
import mobileapp.development.honoursprojecthealthapp.data.UserFoodListDAO;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.

     * @return A new instance of fragment HomeFragment.
     */
    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        //DATABASE STARTER CODE FOR IMPLEMENTING DATABASE TO APPLICATION ON STARTUP

        FoodItemsDatabase foodItemsDatabase = FoodItemsDatabase.getDatabase(getContext());

        // Get the DAO for the food items
        UserFoodListDAO userFoodListDAO = foodItemsDatabase.userFoodListDAO();

        return view;
    }
}