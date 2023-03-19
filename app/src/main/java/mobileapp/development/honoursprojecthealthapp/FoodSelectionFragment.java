package mobileapp.development.honoursprojecthealthapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EdgeEffect;
import android.widget.EditText;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FoodSelectionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FoodSelectionFragment extends Fragment implements View.OnClickListener {

    public FoodSelectionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment FoodSelectionFragment.
     */

    public static FoodSelectionFragment newInstance(String param1, String param2) {
        FoodSelectionFragment fragment = new FoodSelectionFragment();
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
        View view =  inflater.inflate(R.layout.fragment_food_selection, container, false);

        Button btnGoToBarcodeScanner = view.findViewById(R.id.btnGoToBarcodeScanner);
        btnGoToBarcodeScanner.setOnClickListener(this);

        Button btnGoToFoodList = view.findViewById(R.id.btnGoToFoodList);
        btnGoToFoodList.setOnClickListener(this);

        return view;
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnGoToBarcodeScanner) {
            Navigation.findNavController(v).navigate(R.id.action_foodSelectionFragment_to_barcodeScannerActivity3);
        }
        else if (v.getId() == R.id.btnGoToFoodList) {

            // Get the food item entered by the user
            EditText etEnterFoodItem = getView().findViewById(R.id.etEnterFoodItem);
            String  FoodItemName = etEnterFoodItem.getText().toString();

            // Create the bundles for the arguments
            Bundle args = new Bundle();
            args.putString("foodItemName", FoodItemName);

            Navigation.findNavController(v).navigate
                    (R.id.action_foodSelectionFragment_to_foodInfoFragment, args);
        }
    }
}