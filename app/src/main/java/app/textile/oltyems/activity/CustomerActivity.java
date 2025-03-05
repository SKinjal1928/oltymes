package app.textile.oltyems.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import app.textile.oltyems.common.NetworkUtils;
import app.textile.oltyems.common.SharedPref;
import app.textile.oltyems.databinding.ActivityCustomerBinding;
import app.textile.oltyems.databinding.ActivityNewProductBinding;
import app.textile.oltyems.model.CreateCustomer;
import app.textile.oltyems.pojo.CustomerReq;
import app.textile.oltyems.pojo.LoginReq;
import app.textile.oltyems.pojo.LoginResponse;
import app.textile.oltyems.retrofit.RetroInterface;
import app.textile.oltyems.retrofit.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomerActivity extends AppCompatActivity {

    ActivityCustomerBinding binding;
    RetroInterface apiInterface;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityCustomerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        SharedPref.init(this);
        binding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        binding.txtSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkAllFields()) {
                    if (NetworkUtils.isInternetAvailable(CustomerActivity.this)) {
                        saveCustomerApiCall();
                    } else {
                        Toast.makeText(CustomerActivity.this, "No internet connection", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }

    private void saveCustomerApiCall() {
        binding.progressBar.setVisibility(View.VISIBLE);
        apiInterface = RetrofitClient.getRetrofitInstance().create(RetroInterface.class);
        CustomerReq user = new CustomerReq(binding.etCustomerName.getText().toString() + "",
                binding.etEmail.getText().toString(),
                binding.etMobileNo.getText().toString(), binding.etCountry.getText().toString(), binding.etDestination.getText().toString(),
                binding.etAddress.getText().toString());
        Call<CreateCustomer> call1 = apiInterface.createCustomer(user, "Bearer " + SharedPref.getString("token", "") + "");
        call1.enqueue(new Callback<CreateCustomer>() {
            @Override
            public void onResponse(Call<CreateCustomer> call, Response<CreateCustomer> response) {
                binding.progressBar.setVisibility(View.GONE);
                Log.d("TAG", response.code() + "");

                if (response.code() == 201) {
//                    Log.d("Create Customer", response.body().getData().getToken() + "");
                    Toast.makeText(CustomerActivity.this, "Customer created successfully...", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(CustomerActivity.this, "Customer not created...", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<CreateCustomer> call, Throwable t) {
                binding.progressBar.setVisibility(View.GONE);
                Toast.makeText(CustomerActivity.this, "Something went wrong..", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean checkAllFields() {
        if (binding.etCustomerName.length() == 0) {
            binding.etCustomerName.setError("This field is required");
            return false;
        }

        if (binding.etEmail.length() == 0) {
            binding.etEmail.setError("This field is required");
            return false;
        }
        if (binding.etMobileNo.length() == 0) {
            binding.etMobileNo.setError("This field is required");
            return false;
        }
        if (binding.etMobileNo.length() < 10) {
            binding.etMobileNo.setError("Please enter valid Mobile no.");
            return false;
        }
        if (binding.etCountry.length() == 0) {
            binding.etCountry.setError("This field is required");
            return false;
        }
        if (binding.etDestination.length() == 0) {
            binding.etDestination.setError("This field is required");
            return false;
        }
        if (binding.etAddress.length() == 0) {
            binding.etAddress.setError("This field is required");
            return false;
        }

        // after all validation return true.
        return true;
    }
}
