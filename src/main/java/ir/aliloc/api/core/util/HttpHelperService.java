/**
 * 6/26/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.util;

import okhttp3.OkHttpClient;

public class HttpHelperService {

    private static HttpHelperService singletonInstance;

    // No need to be static; OkHttpSingleton is unique so is this.
    private OkHttpClient client;

    // Private so that this cannot be instantiated.
    private HttpHelperService() {
        client = new OkHttpClient.Builder()
                .retryOnConnectionFailure(true)
                .build();
    }

    public static HttpHelperService getInstance() {
        if (singletonInstance == null) {
            singletonInstance = new HttpHelperService();
        }
        return singletonInstance;
    }

    // In case you just need the unique OkHttpClient instance.
    public OkHttpClient getClient() {
        return client;
    }

    public void closeConnections() {
        client.dispatcher().cancelAll();
    }
}
