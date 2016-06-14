package com.mysampleapp.demo;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.amazonaws.mobile.AWSMobileClient;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.widget.AdapterView.OnItemClickListener;
import android.view.View.OnClickListener;
import android.support.v7.widget.Toolbar;

import com.amazonaws.mobile.user.IdentityManager;
import com.mysampleapp.MainActivity;
import com.mysampleapp.R;
import com.mysampleapp.SplashActivity;

import java.util.ArrayList;

/**
 * Created by HP on 6/13/2016.
 */
public class category_show extends AppCompatActivity {

    private TextView textViewResult;
    private IdentityManager identityManager;

    private ProgressDialog loading;

    private Toolbar toolbar;
    private String value;
    ArrayList<String> cat_name=new ArrayList<String>();
    ArrayList<String> cat_com=new ArrayList<String>();
    ArrayList<Integer> Imageid=new ArrayList<Integer>();
    private void setupToolbar(final Bundle savedInstanceState) {
        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        // Set up the activity to use this toolbar. As a side effect this sets the Toolbar's title
        // to the activity's title.
        setSupportActionBar(toolbar);

        if (savedInstanceState != null) {
            // Some IDEs such as Android Studio complain about possible NPE without this check.
            assert getSupportActionBar() != null;

            // Restore the Toolbar's title.
            getSupportActionBar().setTitle(
                    savedInstanceState.getCharSequence(value));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        AWSMobileClient.initializeMobileClientIfNecessary(this);

        // Obtain a reference to the mobile client. It is created in the Application class.
        final AWSMobileClient awsMobileClient = AWSMobileClient.defaultMobileClient();

        // Obtain a reference to the identity manager.
        identityManager = awsMobileClient.getIdentityManager();
        setContentView(R.layout.categories_show_layout);
        getData();
        setupToolbar(savedInstanceState);
        GridView gridview=(GridView) findViewById(R.id.grid_catshow);
        final GridAdapterCatShow gridadapter= new GridAdapterCatShow(this,cat_name,cat_com,Imageid);
        gridview.setAdapter(gridadapter);
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
           /* Intent i=new Intent(Intent.ACTION_VIEW);
             i.setData(Uri.parse(gridadapter.getItem(position).getImageUrlString()));
        startActivity(i);}});*/
                //Toast.makeText(this, "You Clicked at " + [+position], Toast.LENGTH_SHORT).show();
                /*Intent i = new Intent(this, category_show.class);
                i.putExtra("cat", cat[+position]);
                startActivity(i);*/
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (!AWSMobileClient.defaultMobileClient().getIdentityManager().isUserSignedIn()) {
            // In the case that the activity is restarted by the OS after the application
            // is killed we must redirect to the splash activity to handle the sign-in flow.
            Intent intent = new Intent(this, SplashActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            return;
        }

        final AWSMobileClient awsMobileClient = AWSMobileClient.defaultMobileClient();
    }

    private void getData() {
        Bundle extras = getIntent().getExtras();
        value = extras.getString("cat");
        loading = ProgressDialog.show(this,"Please wait...","Fetching...",false,false);
        int x=1;
        String url = Config.DATA_URL+'"'+value+'"';
        Toast.makeText(this,url, Toast.LENGTH_LONG).show();
        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                loading.dismiss();
                showJSON(response);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(category_show.this,error.getMessage().toString(),Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showJSON(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray(Config.JSON_ARRAY);
            Toast.makeText(category_show.this,result.toString(),Toast.LENGTH_LONG).show();
            int i=result.length();
            for(int j=0;j<i;j++)
            {
                JSONObject Data = result.getJSONObject(j);
                cat_name.add(Data.getString(Config.KEY_NAME));
                cat_com.add(Data.getString(Config.KEY_CAT));
                Imageid.add(R.drawable.sg1);
            }
            System.out.println(cat_name);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void onPause() {
        super.onPause();
    }


}
