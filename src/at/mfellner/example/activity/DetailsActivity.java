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
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import at.mfellner.example.R;
import at.mfellner.example.delegate.FragmentDelegate;
import at.mfellner.example.fragment.DetailsFragment;

public final class DetailsActivity extends Activity implements FragmentDelegate.Delegator {
    public static final String ARG_ID = "arg_id";

    public static Intent newIntent(Context context, int id) {
        Intent intent = new Intent(context, DetailsActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra(ARG_ID, id);
        return intent;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        FragmentDelegate.initActivityDelegate(this);

        int id = getIntent().getIntExtra(ARG_ID, 0);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_details, DetailsFragment.newInstance(id));
        ft.commit();
    }

    @Override
    public void onResume() {
        super.onResume();
        // If this activity was rotated from portrait to landscape, finish it and go to the main activity instead.
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            finish();
            return;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        FragmentDelegate.resetInstance();
    }

    @Override
    public boolean isPortrait() {
        // This activity should only exist in portrait mode.
        return true;
    }
}
