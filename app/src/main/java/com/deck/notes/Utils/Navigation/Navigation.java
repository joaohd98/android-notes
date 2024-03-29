package com.deck.notes.Utils.Navigation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.deck.notes.R;

public class Navigation {

    public static void push(FragmentActivity activity, Fragment fragment, Bundle bundle, int id) {

        FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();

        transaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left);

        fragment.setArguments(bundle);

        transaction.replace(id, fragment);
        transaction.addToBackStack(fragment.toString());

        transaction.commit();

    }

    public static void back(FragmentActivity activity) {

        FragmentManager manager = activity.getSupportFragmentManager();

        manager.popBackStack();

    }

    public static void setActivity(Activity activity, Class c, boolean animation) {

        if(activity != null) {

            Intent intent = new Intent(activity, c);

            activity.startActivity(intent);

            if(!animation)
                activity.overridePendingTransition(0, 0);

            activity.finish();

        }

    }

}
