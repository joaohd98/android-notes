package com.deck.yugioh.Fragment;

import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.deck.yugioh.R;
import com.deck.yugioh.Utils.Helpers.Helpers;
import com.deck.yugioh.Utils.Validators.ValidatorModel;
import com.deck.yugioh.Utils.Validators.Validators;

import java.util.ArrayList;

public class InputFragment extends Fragment {

    public InputFragment() {}

    private TextView label;
    private TextView message;
    private EditText input;

    private boolean focus = false;
    private boolean isValid = false;

    private @Nullable
    InputFragmentCallBack inputFragmentCallBack;

    public interface InputFragmentCallBack {
        void onInput();
    }

    private ArrayList<ValidatorModel> rules = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_input, container, false);

        this.input = view.findViewById(R.id.customInput);
        this.label = view.findViewById(R.id.customLabel);
        this.message = view.findViewById(R.id.customMessage);

        this.input.addTextChangedListener(this.textChangedListener());
        this.input.setOnFocusChangeListener(this.focusChangeListener());

        this.setBorder(R.color.colorAccent);

        return view;

    }

    public void setContent(Bundle savedInstanceState) {

        if(savedInstanceState != null) {

            int type = savedInstanceState.getInt(getString(R.string.fragment_input_type));

            if(type == InputType.TYPE_TEXT_VARIATION_PASSWORD) {

                this.input.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                this.input.setTransformationMethod(PasswordTransformationMethod.getInstance());

            }

            else
                this.input.setInputType(InputType.TYPE_CLASS_TEXT | type);

            this.input.setHint(savedInstanceState.getString(getString(R.string.fragment_input_placeholder)));
            this.label.setText(savedInstanceState.getString(getString(R.string.fragment_input_label)));

            this.rules = savedInstanceState.getParcelableArrayList(getString(R.string.fragment_input_rules));

        }

    }

    private TextWatcher textChangedListener() {

      return new TextWatcher() {

          @Override
          public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

          @Override
          public void afterTextChanged(Editable editable) { }

          @Override
          public void onTextChanged(CharSequence s, int start, int before, int count) {

              if(start != before)
                InputFragment.this.checkValidation();

          }

      };

    }

    private View.OnFocusChangeListener focusChangeListener() {

        return new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View view, boolean hasFocus) {

                InputFragment.this.focus = hasFocus;
                InputFragment.this.checkValidation();

            }

        };

    }

    private void checkValidation() {

        boolean isValid = true;
        String message = "";

        for(ValidatorModel rule: this.rules) {

            if(rule.getParameterInt() != null)
                isValid = Validators.isValid(rule.getRule(), this.input.getText().toString(), rule.getParameterInt());

            else
                isValid = Validators.isValid(rule.getRule(), this.input.getText().toString());

            if(!isValid) {

                message = rule.getMessage();
                break;

            }

        }

        this.isValid = isValid;

        int color = this.isValid ? R.color.colorSuccess : this.focus ? R.color.colorWarning : R.color.colorDanger;

        this.message.setText(message);
        this.message.setTextColor(getResources().getColor(color));

        this.setBorder(color);

        if (this.inputFragmentCallBack != null)
            this.inputFragmentCallBack.onInput();
        
    }

    private void setBorder(int color) {

        this.input.setBackground(null);

        LayerDrawable bottomBorder = Helpers.getBorders(
                getResources().getColor(R.color.colorBackground), getResources().getColor(color),
                0, 0, 0, 4
        );

        this.input.setBackground(bottomBorder);

    }

    public boolean isValid() {
        return isValid;
    }

    public void setFormValidCallback(@Nullable InputFragmentCallBack inputFragmentCallBack) {
        this.inputFragmentCallBack = inputFragmentCallBack;
    }

}
