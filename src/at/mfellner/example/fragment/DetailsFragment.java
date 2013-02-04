/*******************************************************************************
 * Copyright 2013 Maximilian Fellner
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package at.mfellner.example.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import at.mfellner.example.R;
import at.mfellner.example.delegate.FragmentDelegate;

public final class DetailsFragment extends Fragment {
    public static final String ARG_ID = "arg_id";
    private TextView mTxtState;
    private TextView mTxtCapital;

    public static DetailsFragment newInstance(int id) {
        DetailsFragment fragment = new DetailsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_ID, id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details, container, false);
        mTxtState = (TextView) view.findViewById(R.id.txt_fragment_details_state);
        mTxtCapital = (TextView) view.findViewById(R.id.txt_fragment_details_capital);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        FragmentDelegate.getInstance().onActivityCreatedFinished(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return FragmentDelegate.getInstance().onOptionsItemSelected(this, item);
    }

    public void setTextContent(int id) {
        String[] states = getResources().getStringArray(R.array.states);
        if (id < states.length) {
            mTxtState.setText(states[id]);
        }
        String[] capitals = getResources().getStringArray(R.array.capitals);
        if (id < capitals.length) {
            mTxtCapital.setText(capitals[id]);
        }
    }
}
