package mobileapp.development.honoursprojecthealthapp.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

/**
 Data Access Object for {@link UserFoodList} entities.
 */


@Dao
public interface UserFoodListDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(UserFoodList userFoodList);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(List<UserFoodList> userFoodList);

    @Delete
    public void delete(UserFoodList userFoodList);

    @Delete
    public void delete(List<UserFoodList> userFoodList);

    @Query("SELECT * FROM UserFoodlist WHERE userFoodItemName = :userFoodItemName")
    public List<UserFoodList> findByFoodItemName(String userFoodItemName);

    @Query("SELECT * FROM userFoodlist")
    public List<UserFoodList> getAllFoodItems();

    @Query("DELETE FROM userFoodlist")
    public void deleteAllItems();
}
