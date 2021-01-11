package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class MainApp {
   public static void main(String[] args) {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      userService.cleanUsersTable();
      userService.cleanCarsTable();

      User usr;

      usr = new User("User1", "Lastname1", "user1@mail.ru");
      usr.setCar(new Car(911, "Porshe"));
      userService.add(usr);

      usr = new User("User2", "Lastname2", "user2@mail.ru");
      usr.setCar(new Car(200, "Toyota Land Cruiser"));
      userService.add(usr);

      usr = new User("User3", "Lastname3", "user3@mail.ru");
      usr.setCar(new Car(2020, "Subaru Forester"));
      userService.add(usr);

      usr = new User("User4", "Lastname4", "user4@mail.ru");
      usr.setCar(new Car(500, "Mersedes-Benz"));
      userService.add(usr);


      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+ user.getId());
         System.out.println("First Name = "+ user.getFirstName());
         System.out.println("Last Name = "+ user.getLastName());
         System.out.println("Email = "+ user.getEmail());
         System.out.println(user.getCar());
         System.out.println();
      }
      System.out.println("The User who has Toyota Land Cruiser 200 is: \n"
              + userService.getUserByCarModelAndSeries("Toyota Land Cruiser", 200));

      context.close();
   }
}
