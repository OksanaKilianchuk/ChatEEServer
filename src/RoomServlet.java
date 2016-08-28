import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class RoomServlet extends HttpServlet {

    private static List<Room> roomList = new ArrayList<Room>();
    private UserList userList = UserList.getInstance();
    private MessageList msgList = MessageList.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(request.getInputStream()));
        Message msg = Message.fromJSON(bf.readLine());

        if (msg != null) {

            String tostr = msg.getTo().substring(0, 3);
            if (tostr.equals("new")) {
                Room room = new Room();
                room.setName(msg.getTo().substring(4));
                String[] listUserLogins = msg.getText().split(" ");
                for (int i = 0; i < listUserLogins.length; i++) {
                    User user = userList.getByLogin(listUserLogins[i]);
                    if (user != null)
                        room.usersList.add(user);

                }
                //room.usersList.add(userList.getByLogin(msg.getFrom()));
                roomList.add(room);
            } else {
                for (Room room : roomList) {
                    if (room.getName().equals(msg.getTo())) {
                        if (room.findUser(msg.getFrom())) {
                            for (User user : room.usersList) {
                                Message newMsg = new Message();
                                newMsg.setPrivate(true);
                                newMsg.setTo(user.getLogin());
                                newMsg.setFrom(msg.getFrom());
                                newMsg.setText(msg.getText());
                                msgList.add(newMsg);
                            }
                        }
                    }
                }
            }

        } else
            response.setStatus(400); // Bad request
    }
}
