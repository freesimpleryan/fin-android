package digital.ryanbrown.costofliving;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Ryan on 9/26/2015.
 */
public class Transportation extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static Transportation newInstance(int sectionNumber) {
        Transportation fragment = new Transportation();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public Transportation() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_transportation, container, false);

        final TextView transportation = (TextView) rootView.findViewById(R.id.transportation);
        final TextView gas = (TextView) rootView.findViewById(R.id.gas);
        final TextView carInsurance = (TextView) rootView.findViewById(R.id.carInsurance);

        final TextView ltransportation = (TextView) rootView.findViewById(R.id.transportation_label_nocar);
        final TextView lgas = (TextView) rootView.findViewById(R.id.transportation_label_gas);
        final TextView lcarInsurance = (TextView) rootView.findViewById(R.id.transportation_label_insurance);

        final RadioGroup rTransportation = (RadioGroup) rootView.findViewById(R.id.transportation_picker);
        final TextView quote = (TextView) rootView.findViewById(R.id.transportation_quote_label);
        quote.setText(getRandomQuote());

        rTransportation.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.hasCar:

                        ltransportation.setVisibility(View.GONE);
                        transportation.setVisibility(View.GONE);
                        transportation.setText("");
                        Data.data.put("transportation", "0");

                        gas.setVisibility(View.VISIBLE);
                        carInsurance.setVisibility(View.VISIBLE);
                        lgas.setVisibility(View.VISIBLE);
                        lcarInsurance.setVisibility(View.VISIBLE);

                        break;
                    case R.id.noCar:
                        gas.setVisibility(View.GONE);
                        carInsurance.setVisibility(View.GONE);
                        lgas.setVisibility(View.GONE);
                        lcarInsurance.setVisibility(View.GONE);

                        gas.setText("");
                        carInsurance.setText("");

                        Data.data.put("carInsurance", "0");
                        Data.data.put("gas", "0");

                        ltransportation.setVisibility(View.VISIBLE);
                        transportation.setVisibility(View.VISIBLE);
                        break;
                    default:
                        break;
                }
            }
        });

        rTransportation.check(R.id.noCar);

        gas.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
                Data.data.put("gas", gas.getText().toString());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
        });

        carInsurance.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
                Data.data.put("carInsurance", carInsurance.getText().toString());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
        });

        transportation.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
                Data.data.put("transportation", transportation.getText().toString());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
        });

        return rootView;
    }

    private int getRandomQuote(){
        Random rand = new Random();
        ArrayList<Integer> quotes = new ArrayList();

        quotes.add(R.string.TRANSPORTATION_1);
        quotes.add(R.string.TRANSPORTATION_2);
        quotes.add(R.string.TRANSPORTATION_3);
        quotes.add(R.string.TRANSPORTATION_4);
        quotes.add(R.string.TRANSPORTATION_5);

        int quote = rand.nextInt(quotes.size());
        return quotes.get(quote);
    }

}
