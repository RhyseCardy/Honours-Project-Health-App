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

    // RECYCLERVIEW WONT BE FULLY FUNCTIONAL UNTIL API IS INTEGRATED AND ROOM DATABASE IS CREATED WITH THE VALUES STORED

    //              TODO

    // UPDATE BOOKMARK ON CLICK LISTENER IN THE RECYCLER VIEW ADAPTER
    // MAKE SURE BUTTON CORRECTLY UPDATES RECYCLER VIEW TO NEW SEARCH RESULTS IN THE API


    @NonNull
    @Override
    public UserFoodListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // inflate the layout for the food item list
        View userFoodListItemView = LayoutInflater.from(this.context).inflate(R.layout.user_food_list_item, parent, false);
        UserFoodListViewHolder viewHolder = new UserFoodListViewHolder(userFoodListItemView, this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UserFoodListViewHolder holder, int position) {
        // get the food items to display
        UserFoodList userFoodList = this.userFoodLists.get(position);

        // update the View being held by holder with details of foodList
        View userFoodListItemView = holder.userFoodListItemView;

        // update the name of food item
        TextView tvUserFoodListFoodName = userFoodListItemView.findViewById(R.id.tvUserFoodListFoodName);
        tvUserFoodListFoodName.setText(userFoodList.getUserFoodItemName());



        // POTENTIALLY INCLUDE IMAGE VIEW IN HERE, IGNORE FOR NOW
        // DO IMAGE VIEW WHEN THE API IS INTEGRATED AND THE IMAGE NEEDS TO UPDATE USING API

    }

    @Override
    public int getItemCount() {
        return this.userFoodLists.size();
    }

    class UserFoodListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private View userFoodListItemView;
        private UserFoodListRecyclerViewAdapter adapter;

        public UserFoodListViewHolder(@NonNull View userFoodListItemView,UserFoodListRecyclerViewAdapter adapter) {
            super(userFoodListItemView);
            this.userFoodListItemView = userFoodListItemView;
            this.adapter = adapter;
            this.userFoodListItemView.setOnClickListener(this);

            userFoodListItemView.findViewById(R.id.btnUserFoodListBookmark).setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            // get the clicked item's position
            int position = getAdapterPosition();

            UserFoodList userFoodList = userFoodLists.get(position);

            Log.d("TASK_RECYCLER", "user clicked on bookmark for " + userFoodList.getUserFoodItemName());
        }
    }
}
