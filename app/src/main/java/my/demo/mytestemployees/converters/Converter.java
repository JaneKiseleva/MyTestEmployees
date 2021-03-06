package my.demo.mytestemployees.converters;

import android.util.Log;

import androidx.room.TypeConverter;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;

import my.demo.mytestemployees.pojo.Speciality;

public class Converter {

    @TypeConverter
    public String listSpecialityToString(List<Speciality> specialities) {
        return new Gson().toJson(specialities);
    }

    @TypeConverter
    public List<Speciality> stringToListSpecialty(String specialitiesAsString) {
        Gson gson = new Gson();
        ArrayList objects = gson.fromJson(specialitiesAsString, ArrayList.class);
        ArrayList<Speciality> specialties = new ArrayList<>();
        for (Object o : objects) {
            specialties.add(gson.fromJson(o.toString(), Speciality.class));
        }
        return specialties;
    }
}

