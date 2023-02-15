package mobileapp.development.honoursprojecthealthapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FoodSelectionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FoodSelectionFragment extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FoodSelectionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FoodSelectionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FoodSelectionFragment newInstance(String param1, String param2) {
        FoodSelectionFragment fragment = new FoodSelectionFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_food_selection, container, false);

        Button btnGoToBarcodeScanner = view.findViewById(R.id.btnGoToBarcodeScanner);
        btnGoToBarcodeScanner.setOnClickListener(this);

        Button btnGoToFoodList = view.findViewById(R.id.btnGoToFoodList);
        btnGoToFoodList.setOnClickListener(this);

        return view;
    }

//    TO BE UPDATED WHEN API LINK IS MADE!!

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnGoToBarcodeScanner) {
            Navigation.findNavController(v).navigate(R.id.action_foodSelectionFragment_to_barcodeScannerFragment);
        }
        else if (v.getId() == R.id.btnGoToFoodList) {
            Navigation.findNavController(v).navigate(R.id.action_foodSelectionFragment_to_foodListFragment);
        }
    }
}