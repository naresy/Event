package com.team.eventbook;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class User_PostFragment extends Fragment {


    public User_PostFragment() {


    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {



        return inflater.inflate(R.layout.fragment_user__post, container, false);

    }
    public   void click(View view)
    {

    }

}
