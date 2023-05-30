package dat.backend.model.entities;

/**
 * The type Role.
 */
public class Role {

    private String role;

    /**
     * Instantiates a new Role.
     *
     * @param role the role
     */
    public Role(String role){
        this.role = role;
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

    @Override
    public String toString() {
        return "Role{" +
                "role='" + role + '\'' +
                '}';
    }
}
