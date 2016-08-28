import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StatusServlet extends HttpServlet {
    private UserList userList = UserList.getInstance();
    private MessageList msgList = MessageList.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(request.getInputStream()));
        Message msg = Message.fromJSON(bf.readLine());
        if (msg != null) {
            User user = userList.getByLogin(msg.getText());
            if (user != null)
                response.setStatus(200);
            else
                response.setStatus(202);
        } else response.setStatus(400);
    }
}