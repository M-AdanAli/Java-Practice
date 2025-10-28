package collections.project;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        // Mock the data fetched from the DataBase
        List<User> users = new java.util.ArrayList<>(List.of(
                new User("Alice", true, Role.USER, Role.ADMIN),
                new User("Bob", false, Role.USER),
                new User("Charlie", true, Role.Manager)
        ));

        // Removing Inactive Users
//        users.removeIf(user -> !user.isActive());

        // Printing Active Users
        System.out.println(users);

        // Count Users per Role
        Map<Role,Integer> roleCount = new HashMap<>();
        for (User user: users){
            for (Role role:user.getRoles()){
                roleCount.put(role,roleCount.getOrDefault(role,0)+1);
            }
        }
        System.out.println(roleCount);
    }
}
