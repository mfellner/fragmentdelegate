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

package at.mfellner.example.activity;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import at.mfellner.example.R;
import at.mfellner.example.delegate.FragmentDelegate;
import at.mfellner.example.fragment.DetailsFragment;
import at.mfellner.example.fragment.ListFragment;

public final class MainActivity extends Activity implements FragmentDelegate.Delegator {
    private static final String ARG_POSITION = "arg_position";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentDelegate.initActivityDelegate(this);

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        if (isPortrait()) {
            ft.replace(R.id.fragment_list, new ListFragment());
        } else {
            int id = 0;
            if (savedInstanceState != null) {
                id = savedInstanceState.getInt(ARG_POSITION, 0);
            }
            ft.replace(R.id.fragment_list, new ListFragment());
            ft.replace(R.id.fragment_details, DetailsFragment.newInstance(id));
        }
        ft.commit();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        FragmentDelegate.resetInstance();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        ListFragment fragment = (ListFragment) getFragmentManager().findFragmentById(R.id.fragment_list);
        outState.putInt(ARG_POSITION, fragment.getSelectedPosition());
    }

    @Override
    public boolean isPortrait() {
        // If this view is not visible, we are using the portrait layout.
        return findViewById(R.id.fragment_details) == null;
    }
}
