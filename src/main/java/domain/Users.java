package domain;
import lombok.Data;

import javax.validation.constraints.*;

@Data
public class Users {
    @Size(max = 8)
    @NotNull
    private String name;
    @Max(100)
    @Min(18)
    @NotNull
    private int age;
    @NotNull
    private String gender;
    @Email
    private String emil;
    @Pattern(regexp = "1\\d{10}")
    private String phone;

    public Users(String name, int age, String gender, String emil, String phone){
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.emil  = emil;
        this.phone  = phone;
    }
    public void   setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void   setAge(int age) {
        this.age = age;
    }
    public int    getAge() {return age; }

    public void   setGender(String gender) {
        this.gender = gender;
    }
    public String getGender() {
        return gender;
    }

    public void   setEmail(String emil) {
        this.emil = emil;
    }
    public String getEmil() {
        return emil;
    }

    public void   setPhone(String phone) {
        this.phone = phone;
    }
    public String getPhone() {
        return phone;
    }
}