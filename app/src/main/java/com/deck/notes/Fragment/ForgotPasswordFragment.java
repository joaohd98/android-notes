package com.deck.notes.Fragment;


import android.content.Context;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;

import com.deck.notes.Components.DialogView;
import com.deck.notes.Components.InputView;
import com.deck.notes.Components.LoadingView;
import com.deck.notes.Fragment.Utils.MasterFragment;
import com.deck.notes.HttpRequest.ForgotPasswordAPI;
import com.deck.notes.HttpRequest.Utils.RequestCallBack;
import com.deck.notes.Model.ForgotPassword.ForgotPasswordRequest;
import com.deck.notes.R;
import com.deck.notes.Utils.ActionBar.NavigationBar;
import com.deck.notes.Utils.Helpers.Helpers;
import com.deck.notes.Utils.Validators.ValidatorModel;
import com.google.firebase.FirebaseNetworkException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;

import java.util.ArrayList;

public class ForgotPasswordFragment extends MasterFragment {

    private LoadingView loadingView;

    private InputView inputEmail;
    private Button btnSubmit;

    public ForgotPasswordFragment() {}

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_forgot_password, container, false);

        this.inputEmail = view.findViewById(R.id.fragment_forgot_password_email);
        this.btnSubmit = view.findViewById(R.id.fragment_forgot_password_btn);

        this.loadingView = view.findViewById(R.id.fragment_forgot_password_loading);

        return view;

    }

    @Override
    public void onStart() {
        super.onStart();

        this.setEmailField();
        this.setSubmitBtn();

    }

     public void setNavBar() {

        NavigationBar.setActionBar(getActivity(), "Esquecer a senha", true);

    }

    private void setEmailField() {

        int type = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS;
        String label = getString(R.string.fragment_forgot_password_email_label);
        String placeholder = getString(R.string.fragment_forgot_password_email_placeholder);

        ArrayList<ValidatorModel> rules = new ArrayList<>();

        rules.add(new ValidatorModel(R.string.validators_required, getString(R.string.fragment_forgot_password_email_validation_required)));
        rules.add(new ValidatorModel(R.string.validators_email, getString(R.string.fragment_forgot_password_email_validation_invalid)));

        this.inputEmail.setFormValidCallback(new InputView.ViewCallBack() {

            @Override
            public void onInput() {
                isFormValid();
            }

        });

        this.inputEmail.setContent(type, label, placeholder, rules);
    }

    private void setSubmitBtn() {

        this.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitForm();
            }
        });

        this.isFormValid();


    }

    private void submitForm() {

        this.loadingView.show();

        ForgotPasswordRequest request = new ForgotPasswordRequest(this.inputEmail);

        ForgotPasswordAPI forgotPasswordAPI = new ForgotPasswordAPI();

        forgotPasswordAPI.callRequest(request, new RequestCallBack() {

            @Override
            public void success() {

                loadingView.hide();


                Context context = getContext();

                if(context != null) {

                    DialogView dialog = new DialogView(getContext());

                    dialog.setTitle(context.getString(R.string.fragment_forgot_password_alert_title));
                    dialog.setInfo(context.getString(R.string.fragment_forgot_password_alert_message_success));
                    dialog.setBtnSuccess(context.getString(R.string.fragment_forgot_password_alert_button));

                    dialog.show();

                }

            }

            @Override
            public void error(Exception exception) {

                loadingView.hide();

                Context context = getContext();

                if(context != null) {

                    DialogView dialog = new DialogView(getContext());

                    dialog.setTitle(context.getString(R.string.fragment_forgot_password_alert_title));

                    try {

                        throw exception;

                    } catch (FirebaseAuthInvalidUserException ignore) {

                        dialog.setInfo(context.getString(R.string.fragment_forgot_password_alert_message_email));

                    } catch (FirebaseNetworkException ignore) {

                        dialog.setInfo(context.getString(R.string.fragment_forgot_password_alert_message_no_internet));


                    } catch (Exception ignore) {

                        dialog.setInfo(context.getString(R.string.fragment_forgot_password_alert_message_generic));

                    }

                    dialog.setBtnSuccess(context.getString(R.string.fragment_forgot_password_alert_button));

                    dialog.show();

                }

            }

        });

    }

    private void isFormValid() {

        Helpers.checkIsValid(this.btnSubmit, this.inputEmail.isValid());

    }

}
