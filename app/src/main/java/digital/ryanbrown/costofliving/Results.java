package digital.ryanbrown.costofliving;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;


/**
 * Created by Ryan on 9/26/2015.
 */
public class Results extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static Results newInstance(int sectionNumber) {
        Results fragment = new Results();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public Results() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_results, container, false);
        return rootView;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser){
        super.setUserVisibleHint(isVisibleToUser);

        if(isVisibleToUser && Data.NEW_SESSION){
            final View rootView = this.getView();

            RequestQueue rq = Volley.newRequestQueue(getActivity());
            CustomRequest jsReq = new CustomRequest(
                    Request.Method.POST,
                    Data._URL,
                    Data.data,
                    new Response.Listener<JSONObject>(){
                        @Override
                        public void onResponse(JSONObject response){
                            try{
                                String sTotalMonthlyNeed = "ERR";
                                String sTotalMonthlyWant = "ERR";
                                String sIncome = "ERR";
                                String sDifference = "ERR";

                                double dTotalMonthlyNeed = 0;
                                double dTotalMonthlyWant = 0;
                                double dIncome = 0;
                                double dDifference = 0;

                                try{
                                    Data.res = response;
                                    Log.d("onResponse fired", Data.res.toString());

                                    dTotalMonthlyNeed = Data.res.getDouble("totalMonthlyNeed");
                                    dTotalMonthlyWant = Data.res.getDouble("totalMonthlyWant");
                                    dIncome = Data.res.getDouble("income");

                                    sTotalMonthlyNeed= Double.toString(dTotalMonthlyNeed);
                                    sTotalMonthlyWant = Double.toString(dTotalMonthlyWant);
                                    sIncome = Double.toString(dIncome);

                                }
                                catch(Exception e){
                                    Log.d("error with JSON", e.toString());
                                    e.printStackTrace();
                                }

                                dDifference = dIncome - (dTotalMonthlyNeed + dTotalMonthlyWant);
                                sDifference = Double.toString(dDifference);


                                TextView totalMonthlyNeed = (TextView) rootView.findViewById(R.id.results_totalMonthlyNeed);
                                totalMonthlyNeed.setText(sTotalMonthlyNeed);

                                TextView totalMonthlyWant = (TextView) rootView.findViewById(R.id.results_totalMonthlyWant);
                                totalMonthlyWant.setText(sTotalMonthlyWant);

                                TextView income = (TextView) rootView.findViewById(R.id.results_income);
                                income.setText(sIncome);

                                TextView difference = (TextView) rootView.findViewById(R.id.results_difference);
                                difference.setText(sDifference);
                                if(dDifference < 0){
                                    difference.setTextColor(Color.parseColor("#CC0000"));
                                }
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
                    });

            rq.add(jsReq);
            Data.NEW_SESSION = false;
        }
        else{

        }
    }


}
