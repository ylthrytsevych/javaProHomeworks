package hw14.app.StreamTask;


import java.util.Objects;

public class User implements Comparable<User> {

    private int id;
    private String firstName;
    private String lastName;
    private Integer age = 0;
    private Sex sex;
    private String fullName;

    // --- Конструктори ---

    public User() {
    }

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // Конструктор копіювання
    public User(User user) {
        this.id = user.id;
        this.firstName = user.firstName;
        this.lastName = user.lastName;
        this.age = user.age;
        this.sex = user.sex;
        this.fullName = user.fullName;
    }

    // Повний конструктор
    public User(int id, String firstName, String lastName, Integer age, Sex sex) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.sex = sex;
    }

    public User(String firstName) {
        this.firstName = firstName;
    }

    public User(String firstName, String lastName, Integer age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    // --- Геттери та Сеттери ---

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    // --- Додаткові методи ---

    // Той самий метод print, який викликався як User::print у стрімах
    public void print() {
        System.out.println(this.toString());
    }

    // Реалізація Comparable (порівняння за ID)
    @Override
    public int compareTo(User o) {
        return Integer.compare(this.id, o.id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(age, user.age) &&
                sex == user.sex;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, age, sex);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                '}';
    }
}