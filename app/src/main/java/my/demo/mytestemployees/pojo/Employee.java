package my.demo.mytestemployees.pojo;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import my.demo.mytestemployees.converters.Converter;


@Entity(tableName = "employees")
@TypeConverters (value = Converter.class)
public class Employee {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @SerializedName("f_name")
    @Expose
    private String name;
    @SerializedName("l_name")
    @Expose
    private String lName;
    @SerializedName("birthday")
    @Expose
    private String birthday;

    @Expose(serialize = false)
    @Ignore private LocalDate birthdayDate;
    @SerializedName("avatr_url")
    @Expose
    private String avatrUrl;
    @SerializedName("specialty")
    @Expose
    private List<Speciality> specialities = null;

    public String getFormattedDate() {
        if(this.getBirthdayDate() == null) {
            return "-";
        }
        DateTimeFormatter f = DateTimeFormatter.ofPattern("dd.MM.yyyy" + " г.");
        return this.getBirthdayDate().format(f);
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public LocalDate formatdate (String birthday) {
        if (birthday == null || birthday.isEmpty()) {
            return null;
        }

        final List<String> dateFormats = Arrays.asList("yyyy-MM-dd", "dd-MM-yyyy", "MM-dd-yyyy");
        LocalDate date = null;
        for (String inputPattern: dateFormats) {
            try {
                date = LocalDate.parse(
                        birthday,
                        DateTimeFormatter.ofPattern( inputPattern )
                );
            } catch (DateTimeParseException e) {
                continue;
            }
        }

        return date;
    }

    public java.lang.Integer getAge() {
        if(this.getBirthdayDate() == null) {
            return null;
        }

        return Period.between(
                this.getBirthdayDate(),
                LocalDate.now()
        ).getYears();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String fName) {
        this.name = fName;
    }

    public String getLName() {
        return lName;
    }

    public void setLName(String lName) {
        this.lName = lName;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

//    public void setBirthdayDate() {
//        this.birthdayDate = formatdate(this.birthday);
//    }

    public LocalDate getBirthdayDate() {
        return formatdate(this.birthday);
        //return birthdayDate;
    }

    public String getAvatrUrl() {
        return avatrUrl;
    }

    public void setAvatrUrl(String avatrUrl) {
        this.avatrUrl = avatrUrl;
    }


    //    public void setAge(int age) {
//        this.age = age;
//    }
    public List<Speciality> getSpecialities() {
        return specialities;
    }

    public void setSpecialities(List<Speciality> specialities) {
        this.specialities = specialities;
    }

    public String getSpecialityNames() {
        return specialities.stream().map(s -> s.getName()).collect(Collectors.joining(", "));
    }
}

