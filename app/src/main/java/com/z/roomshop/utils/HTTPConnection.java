package com.z.roomshop.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.z.roomshop.app.AppConstant;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by ZJer on 2017/4/30.
 */

public class HTTPConnection {

    private static final String TAG = "HTTPConnection";
    //接收返回结果
    private String result = "";
    //实例化URL
    private URL mUrl = null;
    //连接URL
    private HttpURLConnection mHttpURLConnection;

    public HTTPConnection() {}

    /**
     * 获取从网络上获取的值
     * @param http 能获取值得网络地址，如：“http://127.0.0.1:8080/test/serlvet”
     * @return 获取到的值；如果获取失败或网络异常，返回null
     */
    public String getConnectionResult(String http) throws IOException {
        mUrl = new URL(http);
        if (mUrl != null) {
            //使用HttpYRLConnection打开连接
            mHttpURLConnection = (HttpURLConnection) mUrl.openConnection();
            //Log.i(TAG, "-------------------->>>>网络请求：》》》" + mHttpURLConnection.getResponseCode());
            mHttpURLConnection.setConnectTimeout(1000);
            int responseCode = mHttpURLConnection.getResponseCode();
            Log.i(TAG, "-------------------->>>>网络请求：>>>" + responseCode);
            if (responseCode == 200) {
                //得到读取的内容（流）
                InputStreamReader inputStreamReader = new InputStreamReader(mHttpURLConnection.getInputStream());
                //为输出创建BufferedReader
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String inputLine = "";
                //使用循环来读取获得数据
                while ((inputLine = bufferedReader.readLine()) != null) {
                    result += inputLine + "\n";
                }
                inputStreamReader.close();
            } else {
                result = AppConstant.UNCONNECT;
                Log.i(TAG, "-------------------->>>>网络请求返回结果：>>>" + result);
            }
            mHttpURLConnection.disconnect();
            return result;
        }
        return null;
    }

    /**
     * 获取Bitmap文件
     * @param http
     * @return
     * @throws IOException
     */
    public Bitmap getConnectionImage(String http) throws IOException {
        Bitmap bitmap = null;

        mUrl = new URL(http);
        if (mUrl != null) {
            mHttpURLConnection = (HttpURLConnection) mUrl.openConnection();
            mHttpURLConnection.setConnectTimeout(1000);
            int connectCode = mHttpURLConnection.getResponseCode();
            if (connectCode == 200) {
                InputStream inputStream = mHttpURLConnection.getInputStream();
//                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
//                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                bitmap = BitmapFactory.decodeStream(inputStream);
            }
        }

        return bitmap;
    }

    public String sendText(String http, String txt) throws IOException {
        byte[] sendData = txt.getBytes();
        URL url = new URL(http);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setConnectTimeout(1000);
        connection.setDoOutput(true);
        connection.setRequestProperty("Content-Type", "text/xml");
        connection.setRequestProperty("Charset", "UTF-8");
        connection.setRequestProperty("Content-Length", String.valueOf(sendData.length));
        OutputStream outStream = connection.getOutputStream();
        outStream.write(sendData);
        outStream.flush();
        outStream.close();
        if (connection.getResponseCode() == 200) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
            String retData = null;
            String responseData = "";
            while ((retData = reader.readLine()) != null) {
                responseData += retData;
            }
            reader.close();
            return responseData;
        }
        return "sendText error!";
    }

    public String sendFile(String http, String filePath, String newFileName) throws IOException {
        String end = "\r\n";
        String twoHyphens = "--";
        String boundary = "*****";
        URL url = new URL(http);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setUseCaches(false);
        conn.setRequestMethod("POTH");
        conn.setRequestProperty("Connection", "Keep-Alive");
        conn.setRequestProperty("Charset", "UTF-8");
        conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
        DataOutputStream ds = new DataOutputStream(conn.getOutputStream());
        ds.writeBytes(twoHyphens + boundary + end);
        ds.writeBytes("Content-Disposition: form-data; name=\"file1\";filename=\"" + newFileName + "\"" + end);
        ds.writeBytes(end);
        /* 取得文件的FileInputStream */
        FileInputStream fStream = new FileInputStream(filePath);
        /* 设置每次写入1024bytes */
        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];
        int length = -1;
        /* 从文件读取数据至缓冲区 */
        while ((length = fStream.read(buffer)) != -1) {
            /* 将资料写入DataOutputStream中 */
            ds.write(buffer, 0, length);
        }
        ds.writeBytes(end);
        ds.writeBytes(twoHyphens + boundary + twoHyphens + end);
        fStream.close();
        ds.flush();
        InputStream is = conn.getInputStream();
        int ch;
        StringBuffer b = new StringBuffer();
        while ((ch = is.read()) != -1) {
            b.append((char) ch);
        }
        ds.close();
        return b.toString();
    }

}
