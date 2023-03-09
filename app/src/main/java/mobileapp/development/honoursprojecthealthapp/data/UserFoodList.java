package mobileapp.development.honoursprojecthealthapp.data;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "UserFoodList")
public class UserFoodList {

    private String userFoodItemName;

    private int userFoodItemImage;

    private String userFoodItemAllergens;

    private String userFoodItemVegan;

    private String userFoodItemVegetarian;

    private String userFoodItemNUTRIScore;

    private String userFoodItemNOVAScore;


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


    public String getUserFoodItemAllergens() {
        return userFoodItemAllergens;
    }

    public void setUserFoodItemAllergens(String userFoodItemAllergens) {
        this.userFoodItemAllergens = userFoodItemAllergens;

    }

    public String getUserFoodItemVegan() {
        return userFoodItemVegan;
    }

    public void setUserFoodItemVegan(String userFoodItemVegan) {
        this.userFoodItemVegan = userFoodItemVegan;
    }

    public String getUserFoodItemVegetarian() {
        return userFoodItemVegetarian;
    }

    public void setUserFoodItemVegetarian(String userFoodItemVegetarian) {
        this.userFoodItemVegetarian = userFoodItemVegetarian;
    }

    public String getUserFoodItemNUTRIScore() {
        return userFoodItemNUTRIScore;
    }

    public void setUserFoodItemNUTRIScore(String userFoodItemNUTRIScore) {
        this.userFoodItemNUTRIScore = userFoodItemNUTRIScore;
    }

    public String getUserFoodItemNOVAScore() {
        return userFoodItemNOVAScore;
    }

    public void setUserFoodItemNOVAScore(String userFoodItemNOVAScore) {
        this.userFoodItemNOVAScore = userFoodItemNOVAScore;
    }


    @Override
    public String toString() {
        return "UserFoodList{" +
                "userFoodListFoodName=" + userFoodItemName +
                ", userFoodItemImage=" + userFoodItemImage +
                ", userFoodItemAllergens=" + userFoodItemAllergens +
                ", userFoodItemVegan=" + userFoodItemVegan +
                ", userFoodItemVegetarian=" + userFoodItemVegetarian +
                ", userFoodItemNUTRIScore=" + userFoodItemNUTRIScore +
                ", userFoodItemNOVAScore=" + userFoodItemNOVAScore +
                "}";
    }

}
