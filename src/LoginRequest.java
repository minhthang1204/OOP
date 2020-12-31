import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.util.HashMap;
import java.util.Map;

public class LoginRequest {
	private String token;
	public LoginRequest(String host) throws MalformedURLException, ProtocolException, IOException {

		Map<String, String> params = new HashMap<String, String>();

		String api = host + Constant.LOG_IN;

		System.out.println("TC1 Login - Nhap dung ");
		params.clear();
		params.put("phonenumber", "0212348122");
		params.put("password", "123456s");
				params.put("uuid", "ac389d10-3933-11eb-adc1-0242ac120002");
		HttpRequest login = new HttpRequest(api, params, null);
		this.token = login.getResponse().data.token; 
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
}
