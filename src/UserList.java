import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;


public class UserList {
    private static final UserList userList = new UserList();

    private final List<User> list = new ArrayList<User>();

    public static UserList getInstance() {
        return userList;
    }

    private UserList() {
    }

    public synchronized void add(User user) {
        list.add(user);
    }

    public boolean contains(User newUser) {
        for (User user : list) {
            if (user.equals(newUser))
                return true;
        }
        return false;
    }

    public synchronized String toJSON() {
        if (list.size() > 0) {
            Gson gson = new GsonBuilder().create();
            return gson.toJson(list.toArray());
        } else
            return null;
    }

    public User getByLogin(String login) {
        for (User user : list) {
            if (user.getLogin().equals(login))
                return user;
        }
        return null;
    }

    public List<User> getUserList() {
        return list;
    }
}
