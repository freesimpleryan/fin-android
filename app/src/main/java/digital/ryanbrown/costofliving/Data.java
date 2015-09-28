package digital.ryanbrown.costofliving;

import com.android.volley.Network;

import com.android.volley.Cache;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import android.util.Log;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.io.File;


/**
 * Created by Ryan on 9/26/2015.
 */
public class Data {

    public static final String _URL = "https://finances-testuserpleaseignore.c9.io/api/financials";
    public static boolean NEW_SESSION = true;

    public static HashMap<String, String> data = new HashMap();
    public static void init() {
        data.put("housing", "0.0");
        data.put("electricity", "0.0");
        data.put("housing", "0.0");
        data.put("electricity", "25.0");
        data.put("water", "0.0");
        data.put("phone", "0.0");
        data.put("internet", "0.0");
        data.put("cable", "0.0");
        data.put("carInsurance", "0.0");
        data.put("medical", "0.0");
        data.put("debts", "0.0");
        data.put("groceries", "0.0");
        data.put("healthInsurance", "0.0");
        data.put("gas", "0.0");
        data.put("savingsPercent", "0.0");
        data.put("weeklyFunMoney", "0.0");
        data.put("income", "0.0");
        data.put("gender", "");
        data.put("age", "0.0");
        data.put("transportation", "0.0");
    }




    public static JSONObject res = new JSONObject();

    // Instantiate the cache
    private static Cache cache = new DiskBasedCache(new File("/"), 1024 * 1024); // 1MB cap

    // Set up the network to use HttpURLConnection as the HTTP client.
    private static Network network = new BasicNetwork(new HurlStack());

    private static RequestQueue mRequestQueue = new RequestQueue(cache, network);


    private static JsonObjectRequest postRequest = new JsonObjectRequest(
            Request.Method.POST,
            _URL,
            new JSONObject(data),
            new Response.Listener<JSONObject>(){
                @Override
                public void onResponse(JSONObject response){
                    try{

                        res = response;
                        Log.d("onResponse fired", res.toString());
                    }
                    catch(Exception e){
                        e.printStackTrace();
                    }
                }

            },
            new Response.ErrorListener(){
                @Override
                public void onErrorResponse(VolleyError error){
                    error.printStackTrace();
                }
            }
    )   {
        @Override
        protected Map getParams(){
            HashMap<String, String> params = data;
            Log.d("params from response", String.valueOf(data.size()));
            return params;
        }

        @Override
        public Map getHeaders(){
            Map<String, String> params = new HashMap();
            params.put("Content-Type", "application/json");
            Log.d("setting header", params.get("Content-Type"));
            return params;
        }

    };



    public static JSONObject send(){

        mRequestQueue.add(postRequest);

        Log.d("Returning res from Data", res.toString());
        return res;
    }

    private static JSONObject responseStub(){
        HashMap<String, Object> stub = new HashMap();
        stub.put("id", "5607f3373e8c665a074a4634");
        stub.put("totalMonthlyNeed", 25);
        stub.put("totalMonthlyWant", 30);
        stub.put("income", 100);
        return new JSONObject(stub);
    }

    public static void stubData(){
        data.clear();
        data.put("electricity","25");
    }

}

