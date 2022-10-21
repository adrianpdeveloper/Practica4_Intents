package com.tokioschool.practica4_intents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;

import com.tokioschool.practica4_intents.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    String[] ageOptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.mainActivityToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setup();
        abrirCamara();
        verCondiciones();
        corregirTexto();

    }

    private void enableButton() {
        if (binding.mainActivityNameInputlayout.getError()==null && binding.mainActivitySurnameInputlayout.getError()==null && binding.mainActivityAgeAutocomplete.getText().toString().equalsIgnoreCase(getString(R.string.mayor_de_edad))){
            binding.mainActivityButton.setEnabled(true);
            binding.mainActivityButton.setBackgroundColor(getResources().getColor(R.color.main_activity_btn_yellow_enabled,null));
        }else{
            binding.mainActivityButton.setEnabled(false);
            binding.mainActivityButton.setBackgroundColor(getResources().getColor(R.color.main_activity_btn_yellow_disabled,null));
        }
    }

    private void corregirTexto() {

        binding.mainActivityNameEdittext.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (binding.mainActivityNameEdittext.getText().toString().contains("@") || binding.mainActivityNameEdittext.getText().toString().contains("!") ){
                    binding.mainActivityNameInputlayout.setError(getResources().getString(R.string.set_error_name_surname_input));
                }else{
                    binding.mainActivityNameInputlayout.setError(null);
                }
                enableButton();
            }
        });


        binding.mainActivitySurnameEdittext.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (binding.mainActivitySurnameEdittext.getText().toString().contains("@") || binding.mainActivitySurnameEdittext.getText().toString().contains("!") ){
                    binding.mainActivitySurnameInputlayout.setError(getResources().getString(R.string.set_error_name_surname_input));
                }else{
                    binding.mainActivitySurnameInputlayout.setError(null);
                }
                enableButton();
            }
        });

        binding.mainActivityAgeAutocomplete.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (binding.mainActivityAgeAutocomplete.getText().toString().equalsIgnoreCase(getString(R.string.mayor_de_edad))){
                    binding.mainActivityAgeInputlayout.setError(null);
                }else{
                    binding.mainActivityAgeInputlayout.setError(getResources().getString(R.string.set_error_age_input));
                }
                    enableButton();
            }
        });



    }

    private void verCondiciones() {
        binding.mainActivityConditionsTv.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://developers.google.com/ml-kit/terms"));
            startActivity(intent);
        });
    }

    private void abrirCamara() {
        binding.mainActivityCameraBtn.setOnClickListener(view -> {
             Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
             startActivity(intent);
        });
    }

    private void setup() {
        ageOptions = getResources().getStringArray(R.array.main_activity_age_inputtext_options);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, ageOptions);
        binding.mainActivityAgeAutocomplete.setAdapter(adapter);
    }




}