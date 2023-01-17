package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);


//-------------------СОЗДАЕМ ВЛАДЕЛЬЦЕВ И ИХ АВТО, добавляем в таблицы--------------------
        User user1 = new User("User1", "Lastname1", "user1@mail.ru");
        user1.setUserCar(new Car("car1", 111));
        userService.add(user1);

        User user2 = new User("User2", "Lastname2", "user2@mail.ru");
        user2.setUserCar(new Car("car2", 222));
        userService.add(user2);

        User user3 = new User("User3", "Lastname3", "user3@mail.ru");
        user3.setUserCar(new Car("car3", 333));
        userService.add(user3);

        User user4 = new User("User4", "Lastname4", "user4@mail.ru");
        user4.setUserCar(new Car("car4", 444));
        userService.add(user4);

//--------------Получаем владельцев и их авто-------------------
        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println("Car = " + user.getUserCar().getModel());
            System.out.println("series = " + user.getUserCar().getSeries());
            System.out.println();
        }
//-----------Ищем владельца по модели авто--------------------
        System.out.println(userService.findUserByCar("car3", 333));

        context.close();
    }
}
