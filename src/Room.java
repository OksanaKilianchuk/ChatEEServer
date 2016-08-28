import java.util.ArrayList;
import java.util.List;

class Room {

    private String name;
    List<User> usersList = new ArrayList<User>();

    public Room() {

    }

    public Room(String name, List<User> usersList) {
        this.name = name;
        this.usersList = usersList;
    }

    public boolean findUser(String login) {
        for (User user : usersList) {
            if (user.getLogin().equals(login))
                return true;
        }
        return false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsersList() {
        return usersList;
    }

    public void setUsersList(List<User> usersList) {
        this.usersList = usersList;
    }
}
