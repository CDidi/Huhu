package dev.huhu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphRequestAsyncTask;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;



public class MainActivity extends Activity {
    CallbackManager callbackmanager;
    LoginButton loginbutton;
    GraphRequestAsyncTask request;
    String id;
    String nomcomplet;
    String nom;
    String prenom;
    String sexe;
    String mail;
    String pays;
    String timezone;
    String datenaissance;
    PagerAdapter mpa;
    ViewPager vp;
    String[] texttoshow;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        FacebookSdk.sdkInitialize(getApplicationContext());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        texttoshow = new String[]{"Découvrez des speakers natifs motivés pour échanger avec vous.","Matchez avec ceux qui vous plaisent.","Ayez des conversations passionnantes dans les langues que vous apprenez.","Le traducteur intégré est là pour vous aider."};

        vp = (ViewPager) findViewById(R.id.view);
        mpa = new ViewPagerAdapter(MainActivity.this,texttoshow);
        vp.setAdapter(mpa);


        AccessToken fb_token = AccessToken.getCurrentAccessToken();
        if(fb_token != null) {
            Intent intent3 = new Intent(MainActivity.this,SuccessLogin.class);
            startActivity(intent3);

        } else {

        }


        callbackmanager = CallbackManager.Factory.create();
        loginbutton = (LoginButton) findViewById(R.id.login_button);
        loginbutton.setReadPermissions(Arrays.asList("public_profile", "email"));
        loginbutton.registerCallback(callbackmanager, new FacebookCallback<LoginResult>() {

            @Override
            public void onSuccess(final LoginResult loginResult) {
                request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        object.optString("fields", "public_profile,email");
                        response.getJSONObject();
                        Log.v("Login", response.toString());

                        try {
                            id = object.getString("id");
                            Log.v("Id", id);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        try {
                            nomcomplet = object.getString("name");
                            Log.v("Nom Complet", nomcomplet);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            nom = object.getString("last_name");
                            Log.v("Nom", nom);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            prenom = object.getString("first_name");
                            Log.v("Prenom", prenom);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        try {
                            sexe = object.getString("gender");
                            Log.v("Sexe", sexe);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        try {
                            mail = object.getString("email");
                            Log.v("Mail", mail);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        try {
                            pays = object.getString("locale");
                            Log.v("Pays", pays);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            timezone = object.getString("timezone");
                            Log.v("Timezone", timezone);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            datenaissance = object.getString("birthday");
                            Log.v("Date de naissance", datenaissance);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                        User user = new User(id, nomcomplet, nom, prenom, sexe, mail, pays, timezone);
                        User.saveUser(user, getApplicationContext());
                        User user2 = User.getUser(getApplicationContext());
                        user2.getUser();


                    }

                }).executeAsync();

            }


            @Override
            public void onCancel() {
            }

            @Override
            public void onError(FacebookException except) {
                Log.v("Login", except.getCause().toString());

            }
        });
        Button but1 = (Button)findViewById(R.id.button);
        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, InscriptionActivity.class);
                startActivity(myIntent);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackmanager.onActivityResult(requestCode, resultCode, data);
        Intent intent2 = new Intent(MainActivity.this, SuccessLogin.class);
        startActivity(intent2);


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

}

