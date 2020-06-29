package pl.zalwi.data;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class UserRepository {

    private List<User> userList;

    public UserRepository(){
        userList = new ArrayList<User>();
        userList.add(new User("Kamil", "Zalwert", 31));
        userList.add(new User("Kamil", "Drugi", 32));
        userList.add(new User("Jan", "Kowalski", 21));
        userList.add(new User("John", "Kowalski", 45));
        userList.add(new User("Piotr", "Pierwszy", 11));
        userList.add(new User("Mietek", "Jedenastolate", 11));
    }

    public List<User> getAll(){
        return userList;
    }

    public void addUser(User user){
        userList.add(user);
    }
    public void delUsers(List<User> userToDelete){
        userList.removeAll(userToDelete);
    }

    public List<User> findUsers(String firstName, String lastName, Integer age){
        List<User> resultList = new ArrayList<User>();
        int inputsBitFlag=0;

        if(!firstName.isEmpty()) inputsBitFlag+=1;
        if(!lastName.isEmpty())  inputsBitFlag+=2;
        if(age != null)          inputsBitFlag+=4;

        switch(inputsBitFlag){
            case 1 -> {resultList = userList.stream() //Only firstName
                                            .filter(user -> user.getFirstName().equals(firstName))
                                            .collect(Collectors.toList());}
            case 2 -> {resultList = userList.stream() //Only lastName
                                            .filter(user -> user.getLastName().equals(lastName))
                                            .collect(Collectors.toList());}
            case 3 -> {resultList = userList.stream() // firstName+lastName
                                            .filter(user -> user.getFirstName().equals(firstName))
                                            .filter(user -> user.getLastName().equals(lastName))
                                            .collect(Collectors.toList());}
            case 4 -> {resultList = userList.stream() //Only Age
                                            .filter(user -> user.getAge().equals(age))
                                            .collect(Collectors.toList());}
            case 5 -> {resultList = userList.stream() //firstName+age
                                            .filter(user -> user.getFirstName().equals(firstName))
                                            .filter(user -> user.getAge().equals(age))
                                            .collect(Collectors.toList());}
            case 6 -> {resultList = userList.stream() //lastName+age
                                            .filter(user -> user.getLastName().equals(lastName))
                                            .filter(user -> user.getAge().equals(age))
                                            .collect(Collectors.toList());}
            case 7 -> {resultList = userList.stream() //firstName+lastName+age
                                            .filter(user -> user.getFirstName().equals(firstName))
                                            .filter(user -> user.getLastName().equals(lastName))
                                            .filter(user -> user.getAge().equals(age))
                                            .collect(Collectors.toList());}
        }
        return resultList;
    }
}
