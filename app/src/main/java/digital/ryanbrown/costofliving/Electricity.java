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
public class Electricity extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static Electricity newInstance(int sectionNumber) {
        Electricity fragment = new Electricity();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public Electricity() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_electricity, container, false);

        final TextView tv = (TextView) rootView.findViewById(R.id.electricity);
        final TextView quote = (TextView) rootView.findViewById(R.id.electricity_quote_label);
        quote.setText(getRandomQuote());

        tv.addTextChangedListener(new TextWatcher(){

            @Override
            public void afterTextChanged(Editable s) {
                Data.data.put("electricity", tv.getText().toString());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count){

            }

        });

        return rootView;
    }

    private int getRandomQuote(){
        Random rand = new Random();
        ArrayList<Integer> quotes = new ArrayList();

        quotes.add(R.string.ELECTRICITY_1);
        quotes.add(R.string.ELECTRICITY_2);
        quotes.add(R.string.ELECTRICITY_3);

        int quote = rand.nextInt(quotes.size());
        return quotes.get(quote);
    }
}
