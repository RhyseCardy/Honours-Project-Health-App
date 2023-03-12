package mobileapp.development.honoursprojecthealthapp;

import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UserFoodListItemFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

public class UserFoodListItemFragment extends Fragment implements View.OnClickListener{

    public UserFoodListItemFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment FavGamesListItemFragment.
     */

    public static UserFoodListItemFragment newInstance() {
        UserFoodListItemFragment fragment = new UserFoodListItemFragment();
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


        return view;
    }

    @Override
    public void onClick(View v) {

    }
}
