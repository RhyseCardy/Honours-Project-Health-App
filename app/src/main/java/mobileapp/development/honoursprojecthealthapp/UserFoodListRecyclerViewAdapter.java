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

import mobileapp.development.honoursprojecthealthapp.data.UserFoodList;

public class UserFoodListRecyclerViewAdapter  extends RecyclerView.Adapter<UserFoodListRecyclerViewAdapter.UserFoodListViewHolder>{

    private final Context context;
    private final List<UserFoodList> userFoodLists;

    UserFoodListRecyclerViewAdapter(Context context, List<UserFoodList> userFoodLists){
        super();
        this.context = context;
        this.userFoodLists = userFoodLists;
    }


    @NonNull
    @Override
    public UserFoodListRecyclerViewAdapter.UserFoodListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // inflate the layout for the food item list
        View view = LayoutInflater.from(this.context).inflate(R.layout.user_food_list_item, parent, false);
        return new UserFoodListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserFoodListRecyclerViewAdapter.UserFoodListViewHolder holder, int position) {
        // get the food items to display
        UserFoodList userFoodList = this.userFoodLists.get(position);

        // update the View being held by holder with details of foodList
        View itemView = holder.itemView;

        // update the name of food item
        TextView tvUserFoodListFoodNameLabel = itemView.findViewById(R.id.tvUserFoodListNameLabel);
        tvUserFoodListFoodNameLabel.setText(userFoodList.getUserFoodItemName());

        // update the allergens of food item
        TextView tvUserFoodListAllergensLabel = itemView.findViewById(R.id.tvUserFoodListAllergensLabel);
        tvUserFoodListAllergensLabel.setText(userFoodList.getUserFoodItemAllergens());

        // update the vegan info of food item
        TextView tvUserFoodListVeganLabel = itemView.findViewById(R.id.tvUserFoodListVeganLabel);
        tvUserFoodListVeganLabel.setText(userFoodList.getUserFoodItemVegan());

        // update the vegetarian info of food item
        TextView tvUserFoodListVegetarianLabel = itemView.findViewById(R.id.tvUserFoodListVegetarianLabel);
        tvUserFoodListVegetarianLabel.setText(userFoodList.getUserFoodItemVegetarian());

        // update the NUTRI score of food item
        TextView tvUserFoodListFoodNUTRIScoreLabel = itemView.findViewById(R.id.tvUserFoodListNUTRIScoreLabel);
        tvUserFoodListFoodNUTRIScoreLabel.setText(userFoodList.getUserFoodItemNUTRIScore());

        // update the NOVA score of food item
        TextView tvUserFoodListFoodNOVAScoreLabel = itemView.findViewById(R.id.tvUserFoodListNOVAScoreLabel);
        tvUserFoodListFoodNOVAScoreLabel.setText(userFoodList.getUserFoodItemNOVAScore());

        //
        //ADD IMAGES UPDATE HERE
        //


        // POTENTIALLY INCLUDE IMAGE VIEW IN HERE, IGNORE FOR NOW
        // DO IMAGE VIEW WHEN THE API IS INTEGRATED AND THE IMAGE NEEDS TO UPDATE USING API

    }

    @Override
    public int getItemCount() {
        return this.userFoodLists.size();
    }

    class UserFoodListViewHolder extends RecyclerView.ViewHolder {

        public UserFoodListViewHolder(@NonNull View itemView) {
            super(itemView);

        }

    }
}
