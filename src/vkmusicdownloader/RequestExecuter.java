package vkmusicdownloader;

/**
 *
 * @author mars
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
 
import javax.net.ssl.HttpsURLConnection;

public class RequestExecuter {

    private static final boolean DEBUG = false;
    private final String USER_AGENT = "Mozilla/5.0";
    private LinkedHashMap paramsMap;

        public RequestExecuter(){
            paramsMap = new LinkedHashMap<String, String>();
        }

	// HTTP GET request
	public StringBuffer sendGet(String url) throws Exception {

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		con.setRequestMethod("GET");

		con.setRequestProperty("User-Agent", USER_AGENT);

		//if (con.getResponseCode() != 200)
                   // return null;

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

                
                return response;
	}

	// HTTP POST request
	public StringBuffer sendPost(String url) throws Exception {

                String params = buildParams();
		URL obj = new URL(url);
		HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
                con.setFollowRedirects(true);
                
                if (DEBUG) {
                    System.out.println("URL: " + url);
                    System.out.println("params: " + params);
                }

		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", USER_AGENT);
		//con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
                con.setRequestProperty("Accept-Charset", "UTF-8");
                con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                con.setRequestProperty("charset", "UTF-8");

		con.setDoOutput(true);
                /* // doesnt work with UTF8 requests
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(params);
		wr.flush();
		wr.close();
                */
                con.getOutputStream().write(params.getBytes("UTF-8"));

                /* // catching auth redirect was failed
                URL redirected = con.getURL();
                if (DEBUG) {
                    System.out.println("redirected: " + redirected.toString());
                    System.out.println("response: " + con.getResponseMessage());
                }
                 */
		//if (con.getResponseCode() != 200)
                   // return null;


		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		return response;
	}

        public void addParam(String key, String value) {
            paramsMap.put(key, value);
        }

        public void deleteParam(String key) {
            paramsMap.remove(key);
        }

        public void clearParams() {
            paramsMap.clear();
        }

        private String buildParams() {

            String params = "";
            Iterator<Entry<String, String>> itr = paramsMap.entrySet().iterator();

            while (itr.hasNext()) {
                Entry<String, String> entry = itr.next();                
                params = params + "&" + entry.getKey() + "=" + entry.getValue();
            }
            params = params.substring(1, params.length());
            return params;
        }
}
