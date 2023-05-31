package dat.backend.model.entities;

import java.util.Objects;

/**
 * The type User.
 */
public class User {
    private int id;
    private String email;
    private String password;
    private String name;
    private int zip;
    private String city;
    private String address;
    private String role;

    /**
     * Instantiates a new User.
     *
     * @param id       the id
     * @param email    the email
     * @param password the password
     * @param role     the role
     */
    public User(int id, String email, String password, String role) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    /**
     * Instantiates a new User.
     * Mainly used for login
     * @param email    the email
     * @param password the password
     * @param role     the role
     */
    public User(String email, String password, String role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    /**
     * Instantiates a new User.
     *
     * @param email    the email
     * @param password the password
     * @param name     the name
     * @param zip      the zip
     * @param city     the city
     * @param address  the address
     * @param role     the role
     */
    public User(String email, String password, String name, int zip, String city, String address, String role) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.zip = zip;
        this.city = city;
        this.address = address;
        this.role = role;
    }

    /**
     * Instantiates a new User.
     * Mainly used for user creation
     * @param id       the id
     * @param email    the email
     * @param password the password
     * @param name     the name
     * @param zip      the zip
     * @param city     the city
     * @param address  the address
     * @param role     the role
     */
    public User(int id, String email, String password, String name, int zip, String city, String address, String role) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.zip = zip;
        this.city = city;
        this.address = address;
        this.role = role;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets zip.
     *
     * @return the zip
     */
    public int getZip() {
        return zip;
    }

    /**
     * Sets zip.
     *
     * @param zip the zip
     */
    public void setZip(int zip) {
        this.zip = zip;
    }

    /**
     * Gets city.
     *
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets city.
     *
     * @param city the city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Gets address.
     *
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets address.
     *
     * @param address the address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Gets role.
     *
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * Sets role.
     *
     * @param role the role
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getEmail().equals(user.getEmail()) && getPassword().equals(user.getPassword()) &&
                getRole().equals(user.getRole());
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(getEmail(), getPassword(), getRole());
    }

    @Override
    public String toString()
    {
        return "User{" +
                "brugerNavn='" + email + '\'' +
                ", kodeord='" + password + '\'' +
                ", rolle='" + role + '\'' +
                '}';
    }
}
