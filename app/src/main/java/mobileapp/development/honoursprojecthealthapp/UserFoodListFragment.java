package mobileapp.development.honoursprojecthealthapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import mobileapp.development.honoursprojecthealthapp.data.UserFoodListDAO;

import mobileapp.development.honoursprojecthealthapp.data.FoodList;
//import mobileapp.development.honoursprojecthealthapp.data.FoodItemsDatabase;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UserFoodListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UserFoodListFragment extends Fragment implements View.OnClickListener {

    private UserFoodListRecyclerViewAdapter adapter;




    public UserFoodListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UserFoodListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UserFoodListFragment newInstance(String param1, String param2) {
        UserFoodListFragment fragment = new UserFoodListFragment();
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
       View view = inflater.inflate(R.layout.fragment_user_food_list, container, false);

        Button btnClearItems = view.findViewById(R.id.btnClearItems);
        btnClearItems.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnClearItems) {
            //delete all the items displayed in the user food list
            //UserFoodListDAO.deleteAllItems();


        }
        //FILL IN WHEN RECYCLER VIEW IS WORKING AND CONNECTED TO CREATED DATABASE
        //THIS IS BECAUSE THIS BUTTON SUPPOSED TO RECEIVE SPECIFIC DATA FROM DATABASE

    }
}