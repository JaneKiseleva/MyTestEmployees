package my.demo.mytestemployees.pojo;

import androidx.room.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "speciality")
public class Speciality {
    @SerializedName("speciality_id")
    @Expose
    private int specialityId;
    @SerializedName("name")
    @Expose
    private String name;

    public int getSpecialityId() {
        return specialityId;
    }

    public void setSpecialtyId(int specialtyId) {
        this.specialityId = specialityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
