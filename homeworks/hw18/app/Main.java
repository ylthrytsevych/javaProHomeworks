package hw18.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        List<User> userList = new ArrayList<>();
        userList.add(new User(1, "John", "jj@mail.com"));
        userList.add(new User(2, "Maria", "maria@mail.com"));
        userList.add(new User(3, "Mikhael", "mik@mail.com"));

        //використання optional + get без перевірок

        UserRepository repository = new UserRepository(userList);

        Optional<List<User>> allUsers = repository.findAllUsers();
        allUsers.ifPresent(list ->
                System.out.println("Список присутній. Кількість користувачів: " + list.size()+"\nКористувачі:\n"+allUsers.get())
        );



        System.out.println("--------");
        //використання if present

        System.out.println("Пошук користувача по ід 2");
        Optional<User> foundUser = repository.findUserById(2);
        foundUser.ifPresent(user -> System.out.println("Знайдено: " + user));
        System.out.println("Пошук користувача по ід 99");
        Optional<User> notFoundUser = repository.findUserById(99);
        notFoundUser.ifPresentOrElse(
                user -> System.out.println("Знайдено: " + user),
                () -> System.out.println("Юзера з таким ID не знайдено!")
        );

        //використання isPresent
        System.out.println("--------");

        System.out.println("Пошук по імейлу mik@mail.com");

        foundUser  = repository.findUserByEmail("mik@mail.com");
        notFoundUser = repository.findUserByEmail("aaa@aa.aa");
        if (foundUser.isPresent()) {
            System.out.println("Юзер з таким мейлом є: " + foundUser.get());
        } else {
            System.out.println("Пошук по мейлу не успішний");
        }

        System.out.println("пошук по імейлу aaa@aa.aa" );

        if (notFoundUser.isPresent()) {
            System.out.println("Юзер з таким мейлом є: " + notFoundUser.get());
        } else {
            System.out.println("Пошук по мейлу не успішний");
        }


    }
}