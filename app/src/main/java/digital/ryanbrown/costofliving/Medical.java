package digital.ryanbrown.costofliving;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Ryan on 9/26/2015.
 */
public class Medical extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static Medical newInstance(int sectionNumber) {
        Medical fragment = new Medical();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public Medical() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_medical, container, false);

        final TextView tvMedical = (TextView) rootView.findViewById(R.id.medical);
        final TextView tvInsurance = (TextView) rootView.findViewById(R.id.medical_insurance);
        final TextView quote = (TextView) rootView.findViewById(R.id.medical_quote_label);
        quote.setText(getRandomQuote());

        tvMedical.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
                Data.data.put("medical", tvMedical.getText().toString());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
        });

        tvInsurance.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
                Data.data.put("healthInsurance", tvInsurance.getText().toString());
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

        quotes.add(R.string.MEDICAL_1);
        quotes.add(R.string.MEDICAL_2);
        quotes.add(R.string.MEDICAL_3);
        quotes.add(R.string.MEDICAL_4);
        quotes.add(R.string.MEDICAL_5);

        int quote = rand.nextInt(quotes.size());
        return quotes.get(quote);
    }

}
