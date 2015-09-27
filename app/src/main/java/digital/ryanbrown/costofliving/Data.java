package digital.ryanbrown.costofliving;

import org.json.JSONObject;

import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;

/**
 * Created by Ryan on 9/26/2015.
 */
public class Data {

    public static HashMap<String, Double> data = new HashMap();
    public static void init() {
        data.put("housing", 0.0);
        data.put("electricty", 0.0);
        data.put("housing", 0.0);
        data.put("electricity", 0.0);
        data.put("water", 0.0);
        data.put("phone", 0.0);
        data.put("internet", 0.0);
        data.put("cable", 0.0);
        data.put("carInsurance", 0.0);
        data.put("medical", 0.0);
        data.put("debts", 0.0);
        data.put("groceries", 0.0);
        data.put("healthInsurance", 0.0);
        data.put("gas", 0.0);
        data.put("savingsPercent", 0.0);
        data.put("weeklyFunMoney", 0.0);
        data.put("income", 0.0);
        data.put("gender", 0.0);
    }

    public static void send(){
        JSONObject json = new JSONObject(data);
    }
}
