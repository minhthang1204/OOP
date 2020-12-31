
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.util.HashMap;
import java.util.Map;

public class ApiSetRequestFriends {

    public ApiSetRequestFriends(String host, String token) throws MalformedURLException, ProtocolException, IOException {

        Map<String, String> params = new HashMap<String, String>();

        String api = host + Constant.Set_Request_Friends;

        System.out.println("--------------------Test case voi HOST la: " + host + "---------------------------");
        System.out.println("------------------------ API Request Friend -----------------------------------------------");

        System.out.println("TC1 - đúng");
        params.put("token", token);
        params.put("user_id", "5fe6ee5f9254ac00179af72a");
        new HttpRequest(api, params, token);

        System.out.println("TC2.1 - token để trống");
        params.clear();
        params.put("token", "");
        params.put("user_id", "5fe6ee5f9254ac00179af72a");
        new HttpRequest(api, params, null);

        System.out.println("TC2.2 - token ngắn");
        params.put("token", "nlnuih7596");
        params.put("user_id", "5fe6ee5f9254ac00179af72a");
        new HttpRequest(api, params, null);

//
        System.out.println("TC2.3 - mã phiên cũ");
        params.clear();
        params.put("token", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjVmZTJhMDdkNzNiOWUwMDAxNzNiYTEwNyIsImRhdGVMb2dpbiI6IjIwMjAtMTItMjZUMDc6NTY6MjcuMzY1WiIsImlhdCI6MTYwODk2OTM4NywiZXhwIjoxNjA5MDU1Nzg3fQ.qtEdtZne_eePF9T-ZKjA9gU7GOscMzWnyhf0pN5Z5wE");
        params.put("user_id", "5fe6ee5f9254ac00179af72a");
        new HttpRequest(api, params, null);

        System.out.println("TC3 - Mất Mạng hoặc lỗi kết nối CSDL");

        System.out.println("TC4- User is blocked");

        System.out.println("TC5 - can not find id");
        params.put("token", token);
        params.put("user_id", "987hjkeiuh");
        new HttpRequest(api, params, null);
//
        System.out.println("TC 6 - user_id là chính mình");
        params.put("token", token);
        params.put("user_id", "5fe2a07d73b9e000173ba107");
        new HttpRequest(api, params, null);

        System.out.println("TC7 - hệ thống trả về request ko đúng định dạng");

        System.out.println("TC8 - Đã gửi kết bạn rồi");
        params.put("token", token);
        params.put("user_id", "5fe6ee5f9254ac00179af72a");

        new HttpRequest(api, params, token);

        System.out.println("-----------------------------------------------------------------------");
    }
}
