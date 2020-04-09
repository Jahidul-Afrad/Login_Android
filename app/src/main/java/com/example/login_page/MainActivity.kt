package com.example.login_page

import android.os.Bundle
import android.os.StrictMode
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.sql.DriverManager
import java.sql.ResultSetMetaData


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var Email = findViewById<View>(R.id.EID) as EditText
        var Password = findViewById<View>(R.id.PID) as EditText
        var Login = findViewById<View>(R.id.BID) as Button



        Login.setOnClickListener(View.OnClickListener {
            validate(Email.getText().toString(),Password.getText().toString())
        });
    }
    private fun validate(email: String?, password: String?) {
        val tv = findViewById<View>(R.id.TID) as TextView
        var result = "Welcome "
       // tv.text=result;

        val url = "jdbc:mysql://localhost:3306/java"
        val dbname = "root"
        val pass = ""

        try {

            val policy = StrictMode.ThreadPolicy.Builder()
                .permitAll()
                .build()
            StrictMode.setThreadPolicy(policy)

            Class.forName("com.mysql.jdbc.Driver")
            val query = "select * from student"
            result+=" Bkc "
            val con = DriverManager.getConnection(url, dbname, pass)

            val st = con.createStatement()
            val rs = st.executeQuery(query)
            val RSMD :ResultSetMetaData= rs.getMetaData();
            /* ResultSetMetaData RDMS=rs.getMetaData(); */

            rs.next()
                val EMAIL = rs.getString("EMAIL")
                val PASSWORD = rs.getString("password")
            result+=" EMAIL "
            result+=" PASSWORD "
                //out.println(email+" "+password);
                if (email != null && password != null) {
                    if (email == EMAIL && password == PASSWORD) {
                        result +=EMAIL + " \n"
                        result += "Your password is " + PASSWORD
                    } else {
                        result = "Invalid Email or Password";
                        tv.text = result
                    }
                } else {
                    result = "Empty Email or Password"
                    tv.text = result
                }

        } catch (e: Exception) {
            result+=e.toString()+ " Error"
            tv.text=result

        }
    }
}
