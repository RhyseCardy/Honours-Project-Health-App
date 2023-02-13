package mobileapp.development.honoursprojecthealthapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bnv = findViewById(R.id.bottomNavigationView);
        bnv.setOnItemSelectedListener(this);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        NavController navController = Navigation.findNavController(findViewById(R.id.fragmentContainerView));
        int currentFragmentId = navController.getCurrentDestination().getId();

        if (item.getItemId() == R.id.miBottomNavHome) {
            if (currentFragmentId != R.id.homeFragment) {
                navController.navigate(R.id.homeFragment);
            }
            return true;
        } else if (item.getItemId() == R.id.miBottomNavSearch) {
            if (currentFragmentId != R.id.foodSelectionFragment) {
                navController.navigate(R.id.foodSelectionFragment);
            }
            return true;
        } else if (item.getItemId() == R.id.miBottomNavUserList) {
            if (currentFragmentId != R.id.userFoodListFragment) {
                navController.navigate(R.id.userFoodListFragment);
            }
            return true;
        }

        return false;
    }
}