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

package at.mfellner.example.delegate;

import android.app.Fragment;
import android.app.FragmentManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import at.mfellner.example.R;
import at.mfellner.example.fragment.DetailsFragment;
import at.mfellner.example.fragment.ListFragment;

final class LandscapeDelegate extends FragmentDelegate {
    @Override
    public void onActivityCreatedFinished(Fragment fragment) {
        switch (fragment.getId()) {
            case R.id.fragment_list:
                configureListFragment((ListFragment) fragment);
                break;
            case R.id.fragment_details:
                configureDetailsFragment((DetailsFragment) fragment);
                break;
        }
        fragment.getActivity().getActionBar().setDisplayHomeAsUpEnabled(false);
    }

    @Override
    public boolean onOptionsItemSelected(Fragment fragment, MenuItem item) {
        // nothing to do
        return fragment.onOptionsItemSelected(item);
    }

    private void configureListFragment(final ListFragment fragment) {
        ListView listView = (ListView) fragment.getView();
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FragmentManager fm = fragment.getFragmentManager();
                DetailsFragment detailsFragment = (DetailsFragment) fm.findFragmentById(R.id.fragment_details);
                detailsFragment.setTextContent(position);
            }
        });
    }

    private void configureDetailsFragment(final DetailsFragment fragment) {
        int id = fragment.getArguments().getInt(DetailsFragment.ARG_ID, 0);
        fragment.setTextContent(id);
    }
}
