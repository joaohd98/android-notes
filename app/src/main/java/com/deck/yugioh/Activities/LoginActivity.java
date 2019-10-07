package com.deck.yugioh.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Toast;

import com.deck.yugioh.Fragment.InputFragment;
import com.deck.yugioh.R;
import com.deck.yugioh.Utils.Helpers.Helpers;
import com.deck.yugioh.Utils.Validators.ValidatorModel;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    FragmentManager fm = getSupportFragmentManager();
    InputFragment emailFrag;
    InputFragment passwordFrag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.emailFrag = (InputFragment) this.fm.findFragmentById(R.id.email_frag);
        this.passwordFrag = (InputFragment) this.fm.findFragmentById(R.id.password_frag);

        this.setEmailField();
        this.setPasswordField();

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {

        Helpers.removeFocusClickOutside(this, event);

        return super.dispatchTouchEvent(event);

    }

    private void setEmailField() {

        Bundle bundle = new Bundle();

        bundle.putString("label", "Email");
        bundle.putString("placeholder", "Digite o seu email");

        ArrayList<ValidatorModel> rules = new ArrayList<>();

        rules.add(new ValidatorModel(R.string.validators_required, "Campo E-mail é obrigatório."));
        rules.add(new ValidatorModel(R.string.validators_email, "E-mail inválido."));

        bundle.putParcelableArrayList("rules", rules);

        this.emailFrag.setContent(bundle);

    }

    private void setPasswordField() {

        Bundle bundle = new Bundle();

        bundle.putString("label", "Senha");
        bundle.putString("placeholder", "Digite a sua senha");

        ArrayList<ValidatorModel> rules = new ArrayList<>();

        rules.add(new ValidatorModel(R.string.validators_required, "Campo senha é obrigatório."));

        bundle.putParcelableArrayList("rules", rules);

        this.passwordFrag.setContent(bundle);

    }

    private void submitForm() {


    }

}
