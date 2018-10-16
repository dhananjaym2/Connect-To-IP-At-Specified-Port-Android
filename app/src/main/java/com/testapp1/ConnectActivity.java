package com.testapp1;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by dhananjay on 9/5/15.
 */
public class ConnectActivity extends Activity {
    private TextView txtData;
    private String response;
    private int PORT_NO = 25;
    private String DESTINATION_ADDRESS = "indition.cc";
    private String str_Hello_Request = "EHLO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect);

        txtData = (TextView) findViewById(R.id.txtData);
        txtData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("test", "txtData clicked");
                new MyAsync().execute();
            }
        });
    }

    private class MyAsync extends AsyncTask<String, Void, String> {

        ProgressDialog mProgressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog = new ProgressDialog(ConnectActivity.this);
            mProgressDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            startConnection();
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (s != null)
                txtData.setText(response);
            else
                Log.d("test", "onPostExecute() s null");
            mProgressDialog.dismiss();
        }
    }

    private void startConnection() {
        Socket socket = null;
        DataOutputStream dataOutputStream = null;
        DataInputStream dataInputStream = null;

        try {
            socket = new Socket(DESTINATION_ADDRESS, PORT_NO);
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
            Log.d("test", "1");
            dataInputStream = new DataInputStream(socket.getInputStream());
            Log.d("test", "2");
            dataOutputStream.writeUTF(str_Hello_Request);
            Log.d("test", "3");
            response = dataInputStream.readUTF();
            txtData.setText(response);
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            Log.d("test", "4");
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            Log.d("test", "5");
            e.printStackTrace();
        } finally {
            Log.d("test", "6");
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    Log.d("test", "7");
                    e.printStackTrace();
                }
            }
            Log.d("test", "8");
            if (dataOutputStream != null) {
                try {
                    dataOutputStream.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            Log.d("test", "9");
            if (dataInputStream != null) {
                try {
                    dataInputStream.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

}