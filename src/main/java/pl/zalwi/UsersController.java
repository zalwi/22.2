package pl.zalwi;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.zalwi.data.User;
import pl.zalwi.data.UserRepository;

import java.util.List;

@Controller
public class UsersController {

    UserRepository userRepository;

    public UsersController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping("/")
    public String addUserForm(){
        return "redirect:/index.html";
    }

    @ResponseBody
    @RequestMapping("/users")
    public String listOfUsers(){
        String result = "<b>Lista użytkowników</b><br>";
        for(User user: userRepository.getAll()){
            result += user + "<br>";
        }
        result += "Powrót do <a href='/'>strony głównej</a>";
        return result;
    }

    @RequestMapping("/add")
    public String addUser(@RequestParam String firstName, @RequestParam String lastName, @RequestParam int age){
        if(firstName.length()<1){
            return "redirect:/err.html";
        }
        userRepository.addUser(new User(firstName, lastName, age));
        return "redirect:/success.html";
    }

    @ResponseBody
    @RequestMapping("/find")
    public String findUser(@RequestParam String firstName, @RequestParam String lastName, @RequestParam Integer age){
        List<User> findResult = userRepository.findUsers(firstName, lastName, age);
        if(findResult.size() == 0){
            return "<h1>Brak użytkowników pasujących do zadanych kryteriów</h1>" +
                    "<h3>Powrót do <a href='/'>strony głównej</a></h3>";
        }
        String result = "<b>Lista znalezionych użytkowników</b><br>";
        for(User user: findResult){
            result += user + "<br>";
        }
        result += "Powrót do <a href='/'>strony głównej</a>";
        return result;
    }

    @ResponseBody
    @RequestMapping("/delete")
    public String deleteUser(@RequestParam String firstName, @RequestParam String lastName, @RequestParam Integer age){
        List<User> findResult = userRepository.findUsers(firstName, lastName, age);
        if(findResult.size() == 0){
            return "<h1>Brak użytkowników pasujących do zadanych kryteriów</h1>" +
                    "<h3>Powrót do <a href='/'>strony głównej</a></h3>";
        }
        userRepository.delUsers(findResult);
        String result = "<b>Lista usuniętych użytkowników</b><br>";
        for(User user: findResult){
            result += user + "<br>";
        }
        result += "Powrót do <a href='/'>strony głównej</a>";
        return result;
    }
}
