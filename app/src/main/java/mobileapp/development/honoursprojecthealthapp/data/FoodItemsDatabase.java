package mobileapp.development.honoursprojecthealthapp.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {UserFoodList.class}, version = 3)
public abstract class FoodItemsDatabase extends RoomDatabase {

    public abstract UserFoodListDAO userFoodListDAO();

    private static FoodItemsDatabase INSTANCE;

    public static FoodItemsDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            synchronized (FoodItemsDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(), FoodItemsDatabase.class, "food_items_database").fallbackToDestructiveMigration().allowMainThreadQueries().build();
                }
            }
        }
        return INSTANCE;
    }
}
