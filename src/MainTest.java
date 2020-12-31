import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;

import com.google.gson.Gson;

public class MainTest {
	public static void main(String[] args) throws MalformedURLException, IOException {
		String[] hosts = {Constant.HOST};
		for (int i = 0; i < 1; i++) {
//			String host = Constant.HOST_3;
			String host = hosts[i];
			String token = null;


			System.out.println(token);
			System.out.println("-----------------------------------------------------------------------");
			System.out.println("-----------------------------------------------------------------------");
			LoginRequest login = new LoginRequest(host);
			token = login.getToken();
			//ApiSetAcceptFriend test3 = new ApiSetAcceptFriend(host, token);
			ApiGetListSuggestFriends test1 = new ApiGetListSuggestFriends(host, token);
			//ApiSetRequestFriends test2 = new ApiSetRequestFriends(host, token);

		}
	}
}
