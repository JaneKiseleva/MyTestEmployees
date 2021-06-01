package my.demo.mytestemployees.pojo;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "specialty")
public class Speciality {
    @PrimaryKey
    @SerializedName("specialty_id")
    @Expose
    private int specialtyId;
    @SerializedName("name")
    @Expose
    private String name;

    public int getSpecialtyId() {
        return specialtyId;
    }

    public void setSpecialtyId(int specialtyId) {
        this.specialtyId = specialtyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
