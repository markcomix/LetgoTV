package com.letgo.letgotv.backend.util;

import com.letgo.letgotv.backend.ApiService;
import com.letgo.letgotv.config.AppConstants;
import com.letgo.letgotv.ui.util.BaseFunctions;

import java.security.cert.CertificateException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * REST Client Enum with all the REST Call Configuration
 */
public enum RestClient {

    INSTANCE;

    private ApiService apiService;

    private OkHttpClient okHttpClient = new OkHttpClient();

    public ApiService getApiService() {

        if (apiService == null) {

            synchronized (INSTANCE) {

                if (apiService == null)
                    init();
            }
        }

        return apiService;
    }

    /**
     * Create the Retrofit Adapter
     */
    private void init() {

        Retrofit restAdapter = new Retrofit.Builder()
                .baseUrl(AppConstants.Backend.BASE_URL)
                .client(configureHttpClient())
                .addConverterFactory(JacksonConverterFactory.create(BaseFunctions.getMapper()))
                .build();

        apiService = restAdapter.create(ApiService.class);
    }

    /**
     * Config the OK Http Client
     * @return
     */
    private OkHttpClient configureHttpClient() {

        configureUnsafeOkHttpClient();

        configureTimeouts();

        return okHttpClient;
    }

    /**
     * Config the Http Security
     */
    private void configureUnsafeOkHttpClient() {

        try {
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws
                                CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws
                                CertificateException {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };

            final SSLContext sslContext = SSLContext.getInstance("SSL");

            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());

            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            okHttpClient = okHttpClient.newBuilder()
                    .sslSocketFactory(sslSocketFactory)
                    .hostnameVerifier(new HostnameVerifier() {

                        @Override
                        public boolean verify(String hostname, SSLSession session) {
                            return true;
                        }

                    })
                    .build();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Config the Http Timeouts
     */
    private void configureTimeouts() {

        okHttpClient = okHttpClient.newBuilder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();
    }

}
