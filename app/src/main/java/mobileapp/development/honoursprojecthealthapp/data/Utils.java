package mobileapp.development.honoursprojecthealthapp.data;


import android.net.Uri;

import androidx.annotation.NonNull;


public class Utils {
    /**
     * Builds and returns a Uri based on the parameters
     * @param base The base of the URI
     * @param paramName The name of the single parameter that will be added to the URI
     * @param paramValue The value of that parameter
     * @return A URI of the form <base>?<paramName>:<paramValue>
     */
//    @NonNull
//    public static Uri buildUri(String base, String paramName, String paramValue) {
//        Uri healthApp= Uri.parse(base);
//        // create a URI Builder and add the parameter
//        Uri.Builder uriBuilder = healthApp.buildUpon();
//        uriBuilder.appendQueryParameter(paramName, paramValue);
//        return uriBuilder.build();
//    }

    @NonNull
    public static Uri buildUri(String base, String paramName, String paramValue, String paramName1, String paramValue1) {
        Uri healthApp= Uri.parse(base);
        // create a URI Builder and add the parameter
        Uri.Builder uriBuilder = healthApp.buildUpon();
        uriBuilder.appendQueryParameter(paramName, paramValue).appendQueryParameter(paramName1, paramValue1);
        return uriBuilder.build();
    }


}
