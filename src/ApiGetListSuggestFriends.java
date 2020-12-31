
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.util.HashMap;
import java.util.Map;

public class ApiGetListSuggestFriends {

    public ApiGetListSuggestFriends(String host, String token) throws MalformedURLException, ProtocolException, IOException {

        Map<String, String> params = new HashMap<String, String>();

        String api = host + Constant.Get_List_Suggested_Friends;

        System.out.println("TC1 GetListSuggestFriend - Nhap dung ");
        params.clear();
        params.put("token", token);
        params.put("index", "30");
        params.put("count", "5");
        new HttpRequest(api, params, token);

        System.out.println("TC2.1 - token để trống");
        params.clear();
        params.put("token", "");
        params.put("index", "30");
        params.put("count", "5");
        new HttpRequest(api, params, null);

        System.out.println("TC2.2 - token ngắn");
        params.put("token", "nlnuih7596");
        params.put("index", "30");
        params.put("count", "5");
        new HttpRequest(api, params, null);

//
        System.out.println("TC2.3 - mã phiên cũ");
        params.clear();
        params.put("token", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjVmZTJhMDdkNzNiOWUwMDAxNzNiYTEwNyIsImRhdGVMb2dpbiI6IjIwMjAtMTItMjZUMDc6NTY6MjcuMzY1WiIsImlhdCI6MTYwODk2OTM4NywiZXhwIjoxNjA5MDU1Nzg3fQ.qtEdtZne_eePF9T-ZKjA9gU7GOscMzWnyhf0pN5Z5wE");
        params.put("index", "30");
        params.put("count", "5");
        new HttpRequest(api, params, null);
//
        System.out.println("TC3 - Mất Mạng hoặc lỗi kết nối CSDL");

        System.out.println("TC4- User is blocked");
//
        System.out.println("TC5.1 GetListSuggestFriend - index & count is null");
        params.clear();
        params.put("token", token);
        params.put("index", "");
        params.put("count", "");
        new HttpRequest(api, params, null);

        System.out.println("TC5.2 GetListSuggestFriend - index & count invalid");
        params.clear();
        params.put("token", token);
        params.put("index", "&");
        params.put("count", "-4");
        new HttpRequest(api, params, null);

    }
}
