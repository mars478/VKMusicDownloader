package vkmusicdownloader;

import java.util.LinkedList;
import javax.swing.JOptionPane;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


/**
 *
 * @author mars
 */
public class VKApi {

    private static final boolean DEBUG = true;

    private String access_token;
    private RequestExecuter rqstExec;
    private boolean authorized;

    public VKApi() {
        rqstExec = new RequestExecuter();
        authorized = false;
    }

    
    public boolean setATokenRequest(String token){
        try{
            token = token.replace("https://oauth.vk.com/blank.html#access_token=", "");
            int lastChar = token.indexOf("&");
            token = token.substring(0, lastChar);
            if (DEBUG) System.out.println("token: "+token);
            this.access_token = token;
            return true;
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage() , "Error" ,JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
        
    public boolean auth2(){
        if (access_token == null){
            authorized = false;
            return false;
        }
        authorized = true;
        return true;
    }
    
    public StringBuffer searchAudio(String query, int count) {

        StringBuffer response = null;
        rqstExec.clearParams();
        rqstExec.addParam("q", query);
        rqstExec.addParam("count", "" + count);
        rqstExec.addParam("auto_complete", "1");
        rqstExec.addParam("access_token", access_token);

        try {
            response = rqstExec.sendPost("https://api.vk.com/method/audio.search?");
            if (DEBUG) System.out.println("\n Response: " + response);
        }
        catch (Exception e) {
            e.printStackTrace();
            response = null;
        }
        finally {
            return response;
        }
    }

    public LinkedList makeTrackList(StringBuffer str) throws Exception{

        LinkedList<Track> trckList = new LinkedList();

        JSONParser parser = new JSONParser();
        JSONObject jsonResponse = (JSONObject) parser.parse(str.toString());
        JSONArray trackListJSON = (JSONArray) jsonResponse.get("response");
        for (int i=1; i<trackListJSON.size(); i++) {
            JSONObject trckJSON = (JSONObject) trackListJSON.get(i);

            Track trck = new Track();
            trck.setAid((Long)trckJSON.get("aid"));
            trck.setArtist((String)trckJSON.get("artist"));
            trck.setDuration((Long)trckJSON.get("duration"));
            trck.setOwner_id((Long)trckJSON.get("owner_id"));
            trck.setTitle((String)trckJSON.get("title"));
            trck.setUrl((String)trckJSON.get("url"));

            trckList.add(trck);
        }

        return trckList;
    }
}


    /*
    public boolean auth() {
        rqstExec.clearParams();
        rqstExec.addParam("client_id","4156308");
        rqstExec.addParam("scope", "audio");
        rqstExec.addParam("redirect_uri", "https://oauth.vk.com/blank.html");
        rqstExec.addParam("display", "popup");
        rqstExec.addParam("v", "5.7");
        rqstExec.addParam("response_type","token");

        try {
            authorized = false;
            StringBuffer response = rqstExec.sendPost("https://oauth.vk.com/authorize?");

            if (DEBUG) System.out.println("\n Response: " + response);

            if (response != null)
                authorized = true;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            return authorized;
        }
        
    }
*/
    