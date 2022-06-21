package uz.jl.dao;

import uz.jl.domains.User;

import java.util.List;
import java.util.Optional;

public class UserDao extends DAO<User> {

    public Long save(User user) {
        user.setId(System.currentTimeMillis());
        generic_list.add(user);
        return user.getId();
    }

    public List<User> getAll() {
        return generic_list;
    }

    public Optional<User> findByUsername(String username) {
        return generic_list.stream()
                .filter(user -> user.getUsername().equalsIgnoreCase(username))
                .findFirst();
    }


}
