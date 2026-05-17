package hw18.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepository {

    List<User> users;

    public UserRepository(List<User> users){
        this.users = users;
    }

    public Optional<User> findUserById(int id){
        Optional<User> result = users.stream().filter(i->i.getId()==id).findFirst();
        return result;
    }

    public Optional<User> findUserByEmail(String email){
        Optional<User> result = users.stream().filter(i->i.getEmail().equals(email)).findAny();
        return result;
    }

    public Optional<List<User>> findAllUsers(){
        return Optional.ofNullable(users);
    }

}
