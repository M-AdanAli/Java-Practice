package collections.project;

import java.util.Arrays;
import java.util.EnumSet;

public class User {
    private String name;
    private boolean isActive;
    private EnumSet<Role> roles;

    public User(String name, boolean isActive, Role... roles) {
        this.name = name;
        this.isActive = isActive;
        this.roles = EnumSet.noneOf(Role.class);
        this.roles.addAll(Arrays.asList(roles));
    }

    public String getName() {
        return name;
    }

    public boolean isActive() {
        return isActive;
    }

    public EnumSet<Role> getRoles() {
        return roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", isActive=" + isActive +
                ", roles=" + roles +
                '}';
    }
}
