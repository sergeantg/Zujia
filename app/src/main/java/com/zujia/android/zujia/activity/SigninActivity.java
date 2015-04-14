package com.zujia.android.zujia.activity;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.zujia.android.zujia.R;

public class SigninActivity extends Activity {

    private EditText lastName;
    private EditText phone;
    private EditText vericode;
    private EditText email;
    private EditText psw1;
    private EditText psw2;
    private CheckBox ch;

    private boolean gender = true;
    private int type = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
    }

    private void initView(){
        lastName = (EditText)findViewById(R.id.lastName);
        phone = (EditText)findViewById(R.id.phone);
        vericode = (EditText)findViewById(R.id.verification);
        email = (EditText)findViewById(R.id.email);
        psw1 = (EditText)findViewById(R.id.password1);
        psw2 = (EditText)findViewById(R.id.password2);
        ch = (CheckBox)findViewById(R.id.agree);
    }

    public void submitClick(View v){
        String _name = lastName.getText().toString();
        String _phone = phone.getText().toString();
        String _psw1 = psw1.getText().toString();
        String _psw2 = psw2.getText().toString();
        String _email = email.getText().toString();

        if(!isLastName(_name)){
            lastName.setError("用户名无效");
            return;
        }else if(!isPhone(_phone)){
            phone.setError("号码无效");
            return;
        }else if(!isEmail(_email)){
            email.setError("邮箱地址无效");
            return;
        }else if(!isPsw(_psw1)){
            psw1.setError("密码无效");
            return;
        }else if(!_psw1.equals(_psw2)){
            psw2.setError("密码不一致");
            return;
        }
        new SigninTask(_name, _email, _phone, _psw1, gender, type).execute();
    }

    private boolean isLastName(String s){
        if("".equals(s) || s == null)
            return false;
        return true;
    }
    private boolean isPhone(String s){
        if("".equals(s) || s == null)
            return false;
        return true;
    }
    private boolean isEmail(String s){
        if("".equals(s) || s == null)
            return false;
        return true;
    }
    private boolean isPsw(String s){
        if("".equals(s) || s == null)
            return false;
        return true;
    }

    public void typeClick(View v){
        switch (v.getId()){
            case R.id.radio_type_custom:
                type = 1;
                break;
            case R.id.radio_type_lord:
                type = 0;
                break;
        }
    }

    public void genderClick(View v){
        switch (v.getId()){
            case R.id.radio_gender_female:
                gender = false;
                break;
            case R.id.radio_gender_male:
                gender = true;
                break;
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_signin, menu);
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

    /**
     * 注册类
     */
    private class SigninTask extends AsyncTask<Void, Void, Integer>{

        public SigninTask(String name,String email, String phone,  String psw, boolean gender, int type){

        }
        @Override
        protected Integer doInBackground(Void... params) {
            return null;
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);

        }
    }
}
