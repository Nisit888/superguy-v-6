package com.mycom.coe.retrofitexam;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mycom.coe.retrofitexam.model.GitHubUsers;
import com.mycom.coe.retrofitexam.services.GitHubService;
import com.mycom.coe.retrofitexam.util.HttpManager;
import com.squareup.picasso.Picasso;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private EditText edtName;
    private Button btnSubmit;
    private TextView tvDisplay;
    private HttpManager httpManager;

    private GitHubService gitHubService;
    private GitHubUsers gitHubUser;
    private ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtName = (EditText) findViewById(R.id.edtName);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        tvDisplay = (TextView) findViewById(R.id.tvDisplay);
        imageView = (ImageView) findViewById(R.id.imageView);


        httpManager = HttpManager.getInstance();
        gitHubService = httpManager.getservice();

        String name = edtName.getText().toString();


        //Picasso.with(this).load("http://www.metalbridges.com/wp-content/uploads/2016/08/ex-aid-16.jpg").into(imageView);

        Picasso.with(this)
                .load("http://www.metalbridges.com/wp-content/uploads/2016/08/ex-aid-16.jpg")
                .resize(1024, 1000)
                .centerCrop()
                .into(imageView);


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<GitHubUsers> call = gitHubService.loadUser(edtName.getText().toString());

                call.enqueue(new Callback<GitHubUsers>() {
                    @Override
                    public void onResponse(Call<GitHubUsers> call, Response<GitHubUsers> response) {
                        if (response.isSuccessful()) {
                            gitHubUser = response.body();
                            Toast.makeText(getApplicationContext(), gitHubUser.toString(), Toast.LENGTH_SHORT).show();
                            tvDisplay.setText(gitHubUser.toString());

                            Picasso.with(getApplicationContext())
                                    .load(gitHubUser.getImage())
                                    .resize(1024, 1000)
                                    .centerCrop()
                                    .into(imageView);

                        } else {
                            try {
                                Toast.makeText(getApplicationContext(), response.errorBody().string(), Toast.LENGTH_SHORT).show();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<GitHubUsers> call, Throwable t) {
                    }
                });
            }
        });


        Toast.makeText(this, "Hi..", Toast.LENGTH_SHORT).show();
    }
}
//
//            }
//        });
//        Call<GitHubUsers> call = gitHubService.loadUser(name);
//
//        call.enqueue(new Callback<GitHubUsers>() {
//            @Override
//            public void onResponse(Call<GitHubUsers> call, Response<GitHubUsers> response) {
//                if ( response.isSuccessful() ) {
//                    gitHubUser = response.body();
//                    Toast.makeText(getApplicationContext(), gitHubUser.toString() , Toast.LENGTH_SHORT).show();
//                    tvDisplay.setText(gitHubUser.toString());
//                }
//                else {
//                    try {
//                        Toast.makeText(getApplicationContext(), response.errorBody().string() , Toast.LENGTH_SHORT).show();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//
//            }
//
//
//        }
//
//            @Override
//            public void onFailure(Call<GitHubUsers> call, Throwable t) {
//
//            }
//        });
//
//
//    }
//}
