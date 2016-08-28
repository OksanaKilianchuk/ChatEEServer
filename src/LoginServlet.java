import javax.servlet.http.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class LoginServlet extends javax.servlet.http.HttpServlet {

    static Map<String, String> users = new HashMap<String, String>();
    private UserList userList = UserList.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        users.put("admin", "admin");
        users.put("user1", "123");
        users.put("oks", "oks");

        try (BufferedReader bf = new BufferedReader(new InputStreamReader(request.getInputStream()))) {
            User user = User.fromJSON(bf.readLine());

            for (Map.Entry<String, String> entry : users.entrySet())
                if (entry.getKey().equals(user.getLogin()) && entry.getValue().equals(user.getPassword())) {
                    user.setStatus("online");
                    response.setStatus(200);
                    if (!userList.getUserList().contains(user))
                        userList.add(user);
                    break;
                } else
                    response.setStatus(400);
            //  response.setStatus(200);
        }
    }

}
