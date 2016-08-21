package tw.delta4studio.www.ozccdatabase.tools;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/**
 * Created by kamira on 2016/08/21.
 */
public class GoogleSpreadSheet {
    public String[][] stringsToJSON(String str) {
        String[][] array = null;
        try{
            int start = str.indexOf("{" , str.indexOf("{") + 1 );
            int end   = str.indexOf("}");
            String jsonResponse = str.substring(start, end);
            JSONObject table = new JSONObject(jsonResponse);

            String rows = table.getString("rows");
            JSONArray jArray = new JSONArray(rows);
            int len = new JSONArray(new JSONObject(jArray.getString(0)).getString("c")).length();
            array = new String[jArray.length()][len];

            for (int i = 0; i < jArray.length(); i++){
                JSONArray jArray2 = new JSONArray(new JSONObject(jArray.getString(i)).getString("c"));
                for (int j = 0; j < len; j++){
                    if(!jArray2.isNull(j)){
                        array[i][j] = new JSONObject(jArray2.getString(j)).getString("v");
                    }else{
                        array[i][j] = "";
                    }
                }
            }
        } catch (JSONException e){

        }

        return array;
    }
}
