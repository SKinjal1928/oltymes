package app.textile.oltyems.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import app.textile.oltyems.common.NetworkUtils;
import app.textile.oltyems.common.SharedPref;
import app.textile.oltyems.databinding.ActivityLoginBinding;
import app.textile.oltyems.pojo.LoginReq;
import app.textile.oltyems.pojo.LoginResponse;
import app.textile.oltyems.retrofit.RetroInterface;
import app.textile.oltyems.retrofit.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding binding;
    String token = "";
    RetroInterface apiInterface;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SharedPref.init(this);
        binding.txtLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//               /* Intent i = new Intent(LoginActivity.this, DashboardActivity.class);
//                startActivity(i);*/
                if (checkAllFields()){
                    if (NetworkUtils.isInternetAvailable(LoginActivity.this)) {
                        loginApiCall();
                    } else {
                        Toast.makeText(LoginActivity.this, "No internet connection", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

    }

    private void loginApiCall() {
        binding.progressBar.setVisibility(View.VISIBLE);
        apiInterface = RetrofitClient.getRetrofitInstance().create(RetroInterface.class);
        LoginReq user = new LoginReq(binding.etUsername.getText().toString() + "", binding.etPassword.getText().toString() + "");
        Call<LoginResponse> call1 = apiInterface.loginApi(user);
        call1.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                binding.progressBar.setVisibility(View.GONE);
                Log.d("TAG", response.code() + "");

                if (response.code() == 200) {
                    Log.d("Login Token", response.body().getData().getToken() + "");
                    token = response.body().getData().getToken();
                    SharedPref.putString("token", token);
                    SharedPref.putString("user", response.body().getData().getUser().getName());
                    startMainScreen(token);

                } else {
                    Toast.makeText(LoginActivity.this, "User not found..", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                binding.progressBar.setVisibility(View.GONE);
                Toast.makeText(LoginActivity.this, "Something went wrong..", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void startMainScreen(String token) {
        Intent i = new Intent(LoginActivity.this, DashboardActivity.class);
        startActivity(i);
        finish();
    }

    private boolean checkAllFields() {
        if (binding.etUsername.length() == 0) {
            binding.etUsername.setError("This field is required");
            return false;
        }

        if (binding.etPassword.length() == 0) {
            binding.etPassword.setError("This field is required");
            return false;
        }

        // after all validation return true.
        return true;
    }


}