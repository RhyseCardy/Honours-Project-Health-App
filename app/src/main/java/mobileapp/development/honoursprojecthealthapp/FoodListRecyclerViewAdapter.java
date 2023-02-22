package mobileapp.development.honoursprojecthealthapp;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import mobileapp.development.honoursprojecthealthapp.data.FoodList;

public class FoodListRecyclerViewAdapter  extends RecyclerView.Adapter<FoodListRecyclerViewAdapter.FoodListViewHolder>{

    private final Context context;
    private final List<FoodList> foodLists;

    FoodListRecyclerViewAdapter(Context context, List<FoodList> foodLists){
        super();
        this.context = context;
        this.foodLists = foodLists;
    }

    // RECYCLERVIEW WONT BE FULLY FUNCTIONAL UNTIL API IS INTEGRATED AND ROOM DATABASE IS CREATED WITH THE VALUES STORED

    //              TODO

    // UPDATE BOOKMARK ON CLICK LISTENER IN THE RECYCLER VIEW ADAPTER
    // MAKE SURE BUTTON CORRECTLY UPDATES RECYCLER VIEW TO NEW SEARCH RESULTS IN THE API


    @NonNull
    @Override
    public FoodListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // inflate the layout for the food item list
        View foodListItemView = LayoutInflater.from(this.context).inflate(R.layout.food_list_item, parent, false);
        FoodListViewHolder viewHolder = new FoodListViewHolder(foodListItemView, this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FoodListViewHolder holder, int position) {
        // get the food items to display
        FoodList foodList = this.foodLists.get(position);

        // update the View being held by holder with details of foodList
        View foodListItemView = holder.foodListItemView;

        // update the name of food item
        TextView tvFoodListFoodName = foodListItemView.findViewById(R.id.tvFoodListFoodName);
        tvFoodListFoodName.setText(foodList.getFoodItemName());



        // POTENTIALLY INCLUDE IMAGE VIEW IN HERE, IGNORE FOR NOW
        // DO IMAGE VIEW WHEN THE API IS INTEGRATED AND THE IMAGE NEEDS TO UPDATE USING API

    }

    @Override
    public int getItemCount() {
        return this.foodLists.size();
    }

    class FoodListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private View foodListItemView;
        private FoodListRecyclerViewAdapter adapter;

        public FoodListViewHolder(@NonNull View foodListItemView,FoodListRecyclerViewAdapter adapter) {
            super(foodListItemView);
            this.foodListItemView = foodListItemView;
            this.adapter = adapter;
            this.foodListItemView.setOnClickListener(this);

            foodListItemView.findViewById(R.id.btnFoodListBookmark).setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            // get the clicked item's position
            int position = getAdapterPosition();

            FoodList foodList = foodLists.get(position);

            Log.d("TASK_RECYCLER", "user clicked on bookmark for " + foodList.getFoodItemName());
        }
    }
}
