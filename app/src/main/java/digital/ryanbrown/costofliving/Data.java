package digital.ryanbrown.costofliving;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


/**
 * Created by Ryan on 9/26/2015.
 */
public class Data {

    public static final String _URL = ""; // URL where API is located
    public static final boolean DO_LOCAL = true; // If set to false, will run the app to connect to the API


    public static boolean NEW_SESSION = true;
    public static JSONObject res = new JSONObject();

    public static HashMap<String, String> data = new HashMap();

    public static void init() {
        data.put("housing", "0.0");
        data.put("electricity", "0.0");
        data.put("housing", "0.0");
        data.put("electricity", "0.0");
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

    public static HashMap<String, Double> calcLocal(){

        HashMap<String, Double> results = new HashMap();
        results.put("totalMonthlyNeed", 0.0);
        results.put("totalMonthlyWant", 0.0);
        results.put("totalMonthly", 0.0);

        HashMap<String, Double> convertedData = new HashMap();

        Iterator it = data.entrySet().iterator();

        while(it.hasNext()){
            Map.Entry pair = (Map.Entry)it.next();
            if(pair.getKey().equals("gender")){
                continue;
            }
            else if(pair.getValue().equals("")) {
                convertedData.put((String) pair.getKey(), 0.0);
            }
            else {
                convertedData.put((String) pair.getKey(), Double.parseDouble((String) pair.getValue()));
            }
        }

        double total;
        total = convertedData.get("housing") + convertedData.get("electricity") + convertedData.get("water") +
                convertedData.get("phone") + convertedData.get("internet") + convertedData.get("cable") + convertedData.get("carInsurance") +
                convertedData.get("medical") + convertedData.get("healthInsurance") + convertedData.get("debts") + convertedData.get("transportation") +
                ((convertedData.get("groceries") + convertedData.get("gas")) * 4.0);

        results.put("totalMonthlyNeed", total);

        total = 0;
        total += results.get("totalMonthlyNeed");
        total += convertedData.get("weeklyFunMoney") * 4.0;

        double savings = total * (convertedData.get("savingsPercent") / 100);
        results.put("totalMonthlyWant", savings + (convertedData.get("weeklyFunMoney") * 4));

        total = results.get("totalMonthlyWant") + results.get("totalMonthlyNeed");
        results.put("totalMonthly", total);
        results.put("income", convertedData.get("income"));

        return results;
    }

}

