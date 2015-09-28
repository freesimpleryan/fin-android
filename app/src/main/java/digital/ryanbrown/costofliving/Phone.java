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
public class Phone extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static Phone newInstance(int sectionNumber) {
        Phone fragment = new Phone();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public Phone() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_phone, container, false);

        final TextView tv = (TextView) rootView.findViewById(R.id.phone);
        final TextView quote = (TextView) rootView.findViewById(R.id.phone_quote_label);
        quote.setText(getRandomQuote());

        tv.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
                Data.data.put("phone", tv.getText().toString());
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

        quotes.add(R.string.PHONE_1);
        quotes.add(R.string.PHONE_2);
        quotes.add(R.string.PHONE_3);
        quotes.add(R.string.PHONE_4);
        quotes.add(R.string.PHONE_5);

        int quote = rand.nextInt(quotes.size());
        return quotes.get(quote);
    }

}
