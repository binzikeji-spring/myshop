package com.binzikeji.my.shop.commons.utils;

import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Arrays;

/**
 * HttpClient 工具类
 * @author: Chundekepa
 * @create: 2019-03-14 22:56
 **/
public class HttpClientUtils {

    private static final String GET = "get";
    private static final String POST = "post";
    // 设置长连接
    private static final String REQUEST_HEADER_CONNECTION = "keep-alive";
    // 设置代理模拟浏览器
    private static final String REQUEST_HEADER_USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3534.4 Safari/537.36";

    /**
     * GET 请求
     * @param url 请求地址
     * @return
     */
    public static String doGet(String url){
        return createRequest(url, GET, null);
    }

    /**
     * GET 请求
     * @param url 请求地址
     * @param cookie cookie
     * @return
     */
    public static String doGet(String url, String cookie){
        return createRequest(url, GET, cookie);
    }

    /**
     * POST 请求
     * @param url 请求地址
     * @param params 请求参数(可选)
     * @return
     */
    public static String doPost(String url, BasicNameValuePair... params){
        return createRequest(url, POST, null, params);
    }

    /**
     * POST 请求
     * @param url 请求地址
     * @param cookie cookie
     * @param params 请求参数(可选)
     * @return
     */
    public static String doPost(String url,String cookie, BasicNameValuePair... params){
        return createRequest(url, POST, cookie, params);
    }

    /**
     * 创建请求
     * @param url 请求地址
     * @param requestMethod 请求方式 GET/POST
     * @param cookie cookie
     * @param params 请求参数(可选)
     * @return
     */
    private static String createRequest(String url, String requestMethod, String cookie, BasicNameValuePair... params){
        // 创建 HttpClient 客户端
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse httpResponse = null;
        // 请求结果
        String result = null;

        try {
            // get 请求
            if (GET.equals(requestMethod)){
                // 创建 HttpClient 连接
                HttpGet httpGet = new HttpGet(url);
                // 设置长连接
                httpGet.setHeader("Connection", REQUEST_HEADER_CONNECTION);
                // 设置代理模拟浏览器
                httpGet.setHeader("User-Agent", REQUEST_HEADER_USER_AGENT);
                // 设置 Cookie
                httpGet.setHeader("Cookie", cookie);

                httpResponse = httpClient.execute(httpGet);

            }
            // post 请求
            else if (POST.equals(requestMethod)){
                // 创建 HttpClient 连接
                HttpPost httpPost = new HttpPost(url);
                // 设置长连接
                httpPost.setHeader("Connection", REQUEST_HEADER_CONNECTION);
                // 设置代理模拟浏览器
                httpPost.setHeader("User-Agent", REQUEST_HEADER_USER_AGENT);
                // 设置 Cookie
                httpPost.setHeader("Cookie", cookie);

                httpPost.setEntity(new UrlEncodedFormEntity(Arrays.asList(params), "UTF-8"));
                httpResponse = httpClient.execute(httpPost);
            }

            HttpEntity entity = httpResponse.getEntity();
            result = EntityUtils.toString(entity);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (httpResponse != null){
                try {
                    httpResponse.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (httpClient != null){
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
}
