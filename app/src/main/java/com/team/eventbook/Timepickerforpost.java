package com.team.eventbook;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.TimePicker;

import androidx.fragment.app.DialogFragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public  class Timepickerforpost extends DialogFragment
        implements TimePickerDialog.OnTimeSetListener {
    final Calendar myCalendar = Calendar.getInstance();

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        myCalendar.set(Calendar.HOUR, hourOfDay);
        myCalendar.set(Calendar.MINUTE, minute);
        updateLabel();

    }
    private void updateLabel() {
        String myFormat = "HH:mm a"; //In which we need put here in text view
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        PostActivity.timepickerstart.setText(sdf.format(myCalendar.getTime()));
    }
}