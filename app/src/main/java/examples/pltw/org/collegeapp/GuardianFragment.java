package examples.pltw.org.collegeapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by wdumas on 4/8/16.
 */
public class GuardianFragment extends Fragment {

    private TextView mFirstName;
    private EditText mEditFirstName;
    private TextView mLastName;
    private EditText mEditLastName;
    private Button mSubmitButton;

    private Guardian mGuardian;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_family_member, container, false);

        mGuardian = new Guardian();

        mFirstName = (TextView) rootView.findViewById(R.id.familyMemberFirstNameTextView);
        mFirstName.setText(mGuardian.getFirstName());
        mLastName = (TextView) rootView.findViewById(R.id.familyMemberLastNameTextView);
        mLastName.setText(mGuardian.getLastName());

        mEditFirstName = (EditText) rootView.findViewById(R.id.familyMemberFirstNameEditText);
        mEditLastName = (EditText) rootView.findViewById(R.id.familyMemberLastNameEditText);

        mSubmitButton = (Button) rootView.findViewById(R.id.familyMemberSubmitButton);

        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGuardian.setFirstName(mEditFirstName.getText().toString());
                mFirstName.setText(mGuardian.getFirstName());
                mGuardian.setLastName(mEditLastName.getText().toString());
                mLastName.setText(mGuardian.getLastName());
            }
        });

        return rootView;
    }

}
