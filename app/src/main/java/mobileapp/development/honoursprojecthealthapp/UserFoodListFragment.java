package mobileapp.development.honoursprojecthealthapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.List;

import mobileapp.development.honoursprojecthealthapp.data.UserFoodList;
import mobileapp.development.honoursprojecthealthapp.data.UserFoodListDAO;
import mobileapp.development.honoursprojecthealthapp.data.FoodItemsDatabase;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UserFoodListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UserFoodListFragment extends Fragment implements View.OnClickListener {

    private UserFoodListRecyclerViewAdapter adapter;

    private FoodItemsDatabase foodItemsDatabase = FoodItemsDatabase.getDatabase(getContext());
    private UserFoodListDAO userFoodListDAO = foodItemsDatabase.userFoodListDAO();

    private List<UserFoodList> userFoodLists = userFoodListDAO.getAllFoodItems();


    public UserFoodListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.

     * @return A new instance of fragment UserFoodListFragment.
     */

    public static UserFoodListFragment newInstance() {
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

            userFoodListDAO.deleteAllItems();
            userFoodLists.clear();
            adapter.notifyDataSetChanged();


        }
        //FILL IN WHEN RECYCLER VIEW IS WORKING AND CONNECTED TO CREATED DATABASE
        //THIS IS BECAUSE THIS BUTTON SUPPOSED TO RECEIVE SPECIFIC DATA FROM DATABASE
    }


        @Override
        public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
            //get the data to display


            // create the adapter for the RecyclerView
            adapter = new UserFoodListRecyclerViewAdapter(getContext(), userFoodLists);

            // get the RecyclerView
            RecyclerView rv_userFoodList = view.findViewById(R.id.rv_userFoodList);

            //wire-up the RecyclerView with the adapter
            rv_userFoodList.setLayoutManager(new LinearLayoutManager(getContext()));
            rv_userFoodList.setAdapter(adapter);

        }

}