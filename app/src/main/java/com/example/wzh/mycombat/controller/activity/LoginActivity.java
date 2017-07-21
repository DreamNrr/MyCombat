package com.example.wzh.mycombat.controller.activity;

import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wzh.mycombat.R;
import com.example.wzh.mycombat.base.BaseActivity;
import com.example.wzh.mycombat.common.AppNetConfig;
import com.example.wzh.mycombat.modle.db.DBHelper;
import com.example.wzh.mycombat.utils.CacheUtils;
import com.example.wzh.mycombat.utils.HttpUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.InjectView;

public class LoginActivity extends BaseActivity {

    @InjectView(R.id.over_lay_vw)
    View overLayVw;
    @InjectView(R.id.login_back)
    ImageView loginBack;
    @InjectView(R.id.login_mobile_iv)
    ImageView loginMobileIv;
    @InjectView(R.id.mobile_et)
    EditText mobileEt;
    @InjectView(R.id.login_verify_iv)
    ImageView loginVerifyIv;
    @InjectView(R.id.password_et)
    EditText passwordEt;
    @InjectView(R.id.forgot_password_tv)
    TextView forgotPasswordTv;
    @InjectView(R.id.login_btn)
    Button loginBtn;
    @InjectView(R.id.tv_register)
    TextView tvRegister;
    @InjectView(R.id.loginApp_weibo)
    ImageButton loginAppWeibo;
    @InjectView(R.id.loginApp_tecentWeixin)
    ImageButton loginAppTecentWeixin;
    @InjectView(R.id.loginApp_tencentQQ)
    ImageButton loginAppTencentQQ;
    @InjectView(R.id.loginApp_Douban)
    ImageButton loginAppDouban;
    @InjectView(R.id.third_login)
    LinearLayout thirdLogin;
    @InjectView(R.id.ll_login)
    LinearLayout llLogin;
    @InjectView(R.id.close)
    ImageView close;
    @InjectView(R.id.back)
    ImageView back;
    @InjectView(R.id.icon_liangcangname)
    ImageView iconLiangcangname;
    @InjectView(R.id.divider2)
    View divider2;
    @InjectView(R.id.liangcangName)
    EditText liangcangName;
    @InjectView(R.id.liangcangName_layout)
    RelativeLayout liangcangNameLayout;
    @InjectView(R.id.icon_passwordOne)
    ImageView iconPasswordOne;
    @InjectView(R.id.divider3)
    View divider3;
    @InjectView(R.id.passwordOne)
    EditText passwordOne;
    @InjectView(R.id.passwordOne_layout)
    RelativeLayout passwordOneLayout;
    @InjectView(R.id.icon_lpasswordTwo)
    ImageView iconLpasswordTwo;
    @InjectView(R.id.divider4)
    View divider4;
    @InjectView(R.id.passwordTwo)
    EditText passwordTwo;
    @InjectView(R.id.passwordTwo_layout)
    RelativeLayout passwordTwoLayout;
    @InjectView(R.id.register)
    Button register;
    @InjectView(R.id.rl_register)
    RelativeLayout rlRegister;
    private DBHelper dbHelper;
    private SQLiteDatabase database;

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initData() {
         dbHelper = new DBHelper(this);
        //连接数据库
         database = dbHelper.getReadableDatabase();
    }


    @Override
    public void initListener() {
        //点击注册页面显示，登录页面隐藏
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rlRegister.setVisibility(View.VISIBLE);
                llLogin.setVisibility(View.GONE);
            }
        });
        loginBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginActivity.this.finish();
            }
        });

  loginBtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
          //获取用户名和密码
          final String name = mobileEt.getText().toString().trim();
          final String password = passwordEt.getText().toString().trim();
          //校验
          if (TextUtils.isEmpty(name) || TextUtils.isEmpty(password)) {
              showToast("用户名密码不能为空");
              return;
          }
          //登录
          Map<String, String> map = new HashMap<String, String>();
          map.put("phone", name);
          map.put("password", password);
          HttpUtils.getInstance().post(AppNetConfig.LOGIN, map, new HttpUtils.OnHttpClientListener() {
              @Override
              public void onSuccess(String json) {
                  Log.e("TAG", "onSuccess: " + json);
                  CacheUtils.putBoolean(LoginActivity.this,"Login",true);
                  Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                  finish();
              }

              @Override
              public void onFailure(String message) {
                  Log.e("TAG", "onFailure: " + message);
              }
          });
          //结束当前页面
          finish();
            }
        });

        //注册按钮
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //获取用户名和密码
                final String name = liangcangName.getText().toString().trim();
                final String password1 = passwordOne.getText().toString().trim();
                final String password2 = passwordTwo.getText().toString().trim();
                //校验

                if(TextUtils.isEmpty(name) || TextUtils.isEmpty(password1) || TextUtils.isEmpty(password2)){
                    showToast("用户名密码不能为空");
                    return;
                }
                if(password1.equals(password2)) {

                    //连网
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("phone",name);
                    map.put("password",password2);

                    HttpUtils.getInstance().post(AppNetConfig.REGISTER, map, new HttpUtils.OnHttpClientListener() {
                        @Override
                        public void onSuccess(String json) {
                            Log.e("TAG", "register--onSuccess: "+json);
                            try {
                                JSONObject obj = new JSONObject(json);
                                boolean isExist = obj.getBoolean("isExist");
                                if (isExist){
                                    showToast("用户已存在");
                                }else{
                                    showToast("注册成功");
                                  llLogin.setVisibility(View.VISIBLE);
                                    rlRegister.setVisibility(View.GONE);
                                    mobileEt.setText(name);
                                    passwordEt.setText(password2);
                                    //保存到数据库
                                    database.execSQL("insert into contact(username,password) values('" + name + "','" + password2 + "')");
                                    database.close();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onFailure(String message) {
                            Log.e("TAG", "register--onFailure: "+message);
                        }
                    });
                }else {
                    showToast("两次输入的密码不一致");
                }
            }
        });

    }

}
