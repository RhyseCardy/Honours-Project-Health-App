package mobileapp.development.honoursprojecthealthapp.data;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "UserFoodList")
public class UserFoodList {

    private String userFoodItemName;

    private int userFoodItemImage;

    public int getUid() {return uid;}

    public void setUid(int uid) {this.uid = uid;}


    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int uid;



    public UserFoodList() {
        super();
    }

    public String getUserFoodItemName() {
        return userFoodItemName;
    }

    public void setUserFoodItemName(String userFoodItemName) {
        this.userFoodItemName = userFoodItemName;
    }

    public int getUserFoodItemImage() {
        return userFoodItemImage;
    }

    public void setUserFoodItemImage(int userFoodItemImage) {
        this.userFoodItemImage = userFoodItemImage;
    }

    @Override
    public String toString() {
        return "UserFoodList{" +
                "userFoodListFoodName=" + userFoodItemName +
                ", userFoodItemImage=" + userFoodItemImage +
                "}";
    }

}
