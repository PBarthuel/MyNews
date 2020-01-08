package com.example.mynews.Search;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import org.threeten.bp.LocalDate;

public class DatePickerDialogFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    private static final String IS_START = "IS-START";
    private static final String INITIAL_DATE = "INITIAL_DATE";
    private OnDateSelectedListener mListener;


    public static DatePickerDialogFragment newInstance(boolean isStart, LocalDate dateToDisplay) {
        DatePickerDialogFragment frag = new DatePickerDialogFragment();
        Bundle args = new Bundle();
        args.putBoolean(IS_START, isStart);
        args.putSerializable(INITIAL_DATE, dateToDisplay);
        frag.setArguments(args);
        return frag;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        LocalDate localDate = (LocalDate) getArguments().getSerializable(INITIAL_DATE);
        return new DatePickerDialog(requireContext(), this, localDate.getYear(), localDate.getMonthValue() - 1, localDate.getDayOfYear());
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (OnDateSelectedListener)context;
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        mListener.onDateSelected(LocalDate.of(year, month + 1, dayOfMonth), getArguments().getBoolean(IS_START));
    }

    public interface OnDateSelectedListener {
        void onDateSelected(LocalDate localDate, boolean isStartDate);

    }
}
