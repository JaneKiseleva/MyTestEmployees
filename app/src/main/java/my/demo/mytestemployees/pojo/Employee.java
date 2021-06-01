package my.demo.mytestemployees.pojo;

import android.annotation.SuppressLint;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Insert;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
    //    @SerializedName ("age")
//    @Expose
//    private int age;
    @SerializedName("speciality")
    @Expose
    private List<Speciality> speciality = null;

    public String getFormattedDate() {
        if(this.getBirthdayDate() == null) {
            return "-";
        }
        DateTimeFormatter f = DateTimeFormatter.ofPattern("dd.MM.yyyy" + " г.");
        return this.getBirthdayDate().format(f);
    }


//    public int getAge() {
//        return calculateAge(birthday);
//    }

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


//    public static int calculateAge (String birthday) {
//        int age = 0;
//        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
//        Date date = null;
//        try {
//            date = sdf.parse(birthday);
//        } catch (ParseException e) {
//        }
//        if (date == null) return 0;
//
//        Calendar birthdayDate = Calendar.getInstance();
//        Calendar today = Calendar.getInstance();
//
//        birthdayDate.setTime(date);
//
//        int year = birthdayDate.get(Calendar.YEAR);
//        int month = birthdayDate.get(Calendar.MONTH);
//        int day = birthdayDate.get(Calendar.DAY_OF_MONTH);
//
//        birthdayDate.set(year, month + 1, day);
//
//        age = today.get(Calendar.YEAR) - birthdayDate.get(Calendar.YEAR);
//
//        if (today.get(Calendar.DAY_OF_YEAR) < birthdayDate.get(Calendar.DAY_OF_YEAR)) {
//            age--;
//        }
//
//        return age;
//    }


    public java.lang.Integer getAge() {
        if(this.getBirthdayDate() == null) {
            return null;
        }

        return Period.between(
                this.getBirthdayDate(),
                LocalDate.now()
        ).getYears();
    }

//    public int getAge (Date birthday) {
//        GregorianCalendar today = new GregorianCalendar();
//        GregorianCalendar bday = new GregorianCalendar();
//        GregorianCalendar bdayThisYear = new GregorianCalendar();
//
//        bday.setTime(birthday);
//        bdayThisYear.setTime(birthday);
//        bdayThisYear.set(Calendar.YEAR, today.get(Calendar.YEAR));
//
//        int age = today.get(Calendar.YEAR) - bday.get(Calendar.YEAR);
//
//        if(today.getTimeInMillis() < bdayThisYear.getTimeInMillis())
//            age--;
//
//        return age;
//    }


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
    public List<Speciality> getSpeciality() {
        return speciality;
    }

    public void setSpeciality(List<Speciality> speciality) {
        this.speciality = speciality;
    }


}

