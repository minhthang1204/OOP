
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.util.HashMap;
import java.util.Map;
//TC719i0Jylg6SQ6KHg062Qxvibb3yTfxtXpvAlpI

public class ApiSetAcceptFriend {

    public ApiSetAcceptFriend(String host, String token) throws MalformedURLException, ProtocolException, IOException {

        Map<String, String> params = new HashMap<String, String>();

        String api = host + Constant.SET_Accept_Friend;

        System.out.println("--------------------Test case voi HOST la: " + host + "---------------------------");
        System.out.println("------------------------ API Set Accept Friend -------------------------------------------");

        System.out.println("TC1 -đúng ");
        params.put("token", token);
        params.put("user_id", "5fe6ee5f9254ac00179af72a");
        params.put("is_accept", "1");
        new HttpRequest(api, params, token);

        System.out.println("TC2.1 - token để trống");
        params.clear();
        params.put("token", "");
        params.put("user_id", "5fe6ee5f9254ac00179af72a");
        params.put("is_accept", "1");
        new HttpRequest(api, params, null);

        System.out.println("TC2.2 - token ngắn");
        params.put("token", "nlnuih7596");
        params.put("user_id", "5fe6ee5f9254ac00179af72a");
        params.put("is_accept", "1");
        new HttpRequest(api, params, null);

//
        System.out.println("TC2.3 - mã phiên cũ");
        params.clear();
        params.put("token", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjVmZTJhMDdkNzNiOWUwMDAxNzNiYTEwNyIsImRhdGVMb2dpbiI6IjIwMjAtMTItMjZUMDc6NTY6MjcuMzY1WiIsImlhdCI6MTYwODk2OTM4NywiZXhwIjoxNjA5MDU1Nzg3fQ.qtEdtZne_eePF9T-ZKjA9gU7GOscMzWnyhf0pN5Z5wE");
        params.put("user_id", "5fe6ee5f9254ac00179af72a");
        params.put("is_accept", "1");
        new HttpRequest(api, params, null);

        System.out.println("TC3 - Mất Mạng hoặc lỗi kết nối CSDL");

        System.out.println("TC4- User is blocked");

        System.out.println("TC5.1 - user_id trống ");
        params.clear();
        params.put("token", token);
        params.put("user_id", "");
        params.put("is_accept", "1");
        new HttpRequest(api, params, null);

        System.out.println("TC5.2 - user_id chứa kí tự ");
        params.clear();
        params.put("token", token);
        params.put("user_id", "hdoajd&*^%&");
        params.put("is_accept", "1");
        new HttpRequest(api, params, null);

        System.out.println("TC6 - user_id không tồn tại ");
        params.clear();
        params.put("token", token);
        params.put("user_id", "hfieuw79304ujsdak");
        params.put("is_accept", "1");
        new HttpRequest(api, params, null);

        System.out.println("TC7 - is_accept ko chuẩn ");
        params.clear();
        params.put("token", token);
        params.put("user_id", "5fe6ee5f9254ac00179af72a");
        params.put("is_accept", "9");
        new HttpRequest(api, params, null);
//
        System.out.println("-----------------------------------------------------------------------");
    }
}
