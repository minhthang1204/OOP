import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;
import java.util.Map.Entry;

import com.google.gson.Gson;


public class HttpRequest {
	private final String boundary;
	private static final String LINE_FEED = "\r\n";
	private OutputStream outputStream;
	private PrintWriter writer;
	HttpURLConnection connection;
	public Response response;

	StringBuilder content;
	private String newtoken;
	public HttpRequest(String api, Map<String, String> params, String token)
			throws MalformedURLException, ProtocolException, IOException {
		boundary = "===" + System.currentTimeMillis() + "===";
		String urlRequest = api;
		boolean isFirstParam = true;
		for (Entry<String, String> entry : params.entrySet()) {
			if (isFirstParam) {
				urlRequest += "?";
				isFirstParam = false;
			} else {
				urlRequest += "&";
			}
			urlRequest += entry.getKey() + "=" + entry.getValue();
		}
		System.out.println(urlRequest);
		URL url = new URL(urlRequest);
		connection = (HttpURLConnection) url.openConnection();
		if (token != null)
			connection.setRequestProperty("Authorization", "Bearer " + token);

		connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
		connection.setUseCaches(false);
		connection.setRequestMethod("POST");
		connection.setDoOutput(true);



		try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
			String line;
			content = new StringBuilder();
			while ((line = in.readLine()) != null) {
				content.append(line);
				content.append(System.lineSeparator());
			}
			System.out.println("JSON response: " + content.toString());

//			assert (rp.code != null && !"".equals(rp.code));
//			assert (rp.message != null && !"".equals(rp.message));
		} catch (Exception e) {
			if(connection.getErrorStream() != null){
				BufferedReader in = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
				String line;
				content = new StringBuilder();
				while ((line = in.readLine()) != null) {
					content.append(line);
					content.append(System.lineSeparator());
				}
				if(content.toString().length() <= 100) {
					System.out.println("JSON response: " + content.toString() );
				}
				else {
					System.out.println(e.getMessage());
				}
			}
			else {
				System.out.println(e.getMessage());
			}

		}
		connection.disconnect();
	}

	public void addFilePart(String fieldName, String filePath) throws IOException {
		File uploadFile = new File(filePath);
		outputStream = connection.getOutputStream();
		writer = new PrintWriter(new OutputStreamWriter(outputStream, "UTF-8"), true);
		if (uploadFile.exists()) {

			String fileName = uploadFile.getName();
			writer.append("--" + boundary).append(LINE_FEED);
			writer.append("Content-Disposition: form-data; name=\"" + fieldName + "\"; filename=\"" + fileName + "\"")
					.append(LINE_FEED);
			writer.append("Content-Type: " + URLConnection.guessContentTypeFromName(fileName)).append(LINE_FEED);
			writer.append("Content-Transfer-Encoding: binary").append(LINE_FEED);
			writer.append(LINE_FEED);
			writer.print(true);
			writer.flush();

			FileInputStream inputStream = new FileInputStream(uploadFile);
			byte[] buffer = new byte[4096];
			int bytesRead = -1;
			while ((bytesRead = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, bytesRead);
			}
			outputStream.flush();
			inputStream.close();
			writer.append(LINE_FEED);
			writer.flush();

		} else {
			writer.append("--" + boundary).append(LINE_FEED);
			writer.append("Content-Disposition: form-data; name=\"" + fieldName + "\"").append(LINE_FEED);
			writer.append("Content-Type: text/plain; charset=" + "UTF-8").append(LINE_FEED);
			writer.append(LINE_FEED);
			writer.append(filePath).append(LINE_FEED);
			writer.flush();
		}
		writer.append(LINE_FEED).flush();
		writer.append("--" + boundary + "--").append(LINE_FEED);
		writer.close();

	}
	public Response getResponse() {
		Gson g = new Gson();
		this.response = g.fromJson(content.toString(), Response.class);
		return this.response;
	
	}
	public String getNewtoken() {
		return newtoken;
	}

	public void setNewtoken(String newtoken) {
		this.newtoken = newtoken;
	}
}

