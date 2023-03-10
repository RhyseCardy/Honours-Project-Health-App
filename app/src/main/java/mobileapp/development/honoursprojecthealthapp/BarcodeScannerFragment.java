package mobileapp.development.honoursprojecthealthapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BarcodeScannerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BarcodeScannerFragment extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public BarcodeScannerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BarcodeScannerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BarcodeScannerFragment newInstance(String param1, String param2) {
        BarcodeScannerFragment fragment = new BarcodeScannerFragment();
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
        View view =  inflater.inflate(R.layout.fragment_barcode_scanner, container, false);

        Button btnCancelScan = view.findViewById(R.id.btnCancelScan);
        btnCancelScan.setOnClickListener(this);

        Button btnScan = view.findViewById(R.id.btnScan);
        btnScan.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnCancelScan) {
            Navigation.findNavController(v).navigate(R.id.action_barcodeScannerFragment_to_foodSelectionFragment);
        }
        else if (v.getId() == R.id.btnScan) {
            Navigation.findNavController(v).navigate(R.id.action_barcodeScannerFragment_to_foodInfoFragment);
        }
    }
}