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
import android.view.MenuItem;

public abstract class FragmentDelegate {
    private static FragmentDelegate mCurrentInstance;

    public interface Delegator {
        public boolean isPortrait();
    }

    public static void initActivityDelegate(Delegator delegator) {
        if (delegator.isPortrait()) {
            mCurrentInstance = new PortraitDelegate();
        } else {
            mCurrentInstance = new LandscapeDelegate();
        }
    }

    public static FragmentDelegate getInstance() {
        if (mCurrentInstance == null) {
            throw new IllegalStateException("FragmentDelegate must be initialized first.");
        }
        return mCurrentInstance;
    }

    public static void resetInstance() {
        mCurrentInstance = null;
    }

    public abstract void onActivityCreatedFinished(Fragment fragment);

    public abstract boolean onOptionsItemSelected(Fragment fragment, MenuItem item);
}
