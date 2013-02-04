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

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import at.mfellner.example.R;
import at.mfellner.example.activity.DetailsActivity;
import at.mfellner.example.activity.MainActivity;
import at.mfellner.example.fragment.DetailsFragment;
import at.mfellner.example.fragment.ListFragment;

final class PortraitDelegate extends FragmentDelegate {
    @Override
    public void onActivityCreatedFinished(Fragment fragment) {
        switch (fragment.getId()) {
            case R.id.fragment_list:
                configureListFragment((ListFragment) fragment);
                break;
            case R.id.fragment_details:
                configureDetailsFragment((DetailsFragment) fragment);
                break;
            default:
                throw new IllegalStateException("Wrong fragment id.");
        }
    }

    @Override
    public boolean onOptionsItemSelected(Fragment fragment, MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(fragment.getActivity(), MainActivity.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                fragment.startActivity(intent);
                return true;
            default:
                return fragment.onOptionsItemSelected(item);
        }
    }

    private void configureListFragment(final ListFragment fragment) {
        fragment.getActivity().getActionBar().setDisplayHomeAsUpEnabled(false);

        ListView listView = (ListView) fragment.getView();
        listView.setChoiceMode(ListView.CHOICE_MODE_NONE);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                fragment.setSelectedPosition(position);
                fragment.startActivity(DetailsActivity.newIntent(fragment.getActivity(), position));
            }
        });
    }

    private void configureDetailsFragment(final DetailsFragment fragment) {
        Activity activity = fragment.getActivity();
        activity.getActionBar().setDisplayHomeAsUpEnabled(true);

        int id = fragment.getArguments().getInt(DetailsFragment.ARG_ID, 0);
        fragment.setTextContent(id);
    }
}
