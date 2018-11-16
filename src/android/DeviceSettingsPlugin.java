package ru.ritm.cordova.device;

import android.content.Intent;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;

import java.lang.Exception;
import java.lang.reflect.Field;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.PluginResult;

import org.json.JSONArray;
import org.json.JSONException;

public class DeviceSettingsPlugin extends CordovaPlugin  {
    public static final String LOG_TAG = "=DevSetPlugin=";
	
    private static final String START_ACTIVITY = "startActivity";
	
	@Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        boolean result = false;
		
        if (START_ACTIVITY.equals(action)) {
            start(callbackContext, args);
            result = true;
        }

        return result;  // Returning false results in a "MethodNotFound" error.
    }
	
	/**
     * Opens required activity
     */
    private void start(CallbackContext callbackContext, JSONArray args) {
        String actionKey = args.getString(0);
        try {
            Field field = Settings.getClass().getDeclaredField(actionKey);
            String actionId = (String) field.get(Settings);
            Intent i = new Intent(actionId);
            String packageName = cordova.getActivity().getPackageName();

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O && actionKey.equals("ACTION_CHANNEL_NOTIFICATION_SETTINGS")) {
                i.putExtra(Settings.EXTRA_APP_PACKAGE, packageName);
                i.putExtra(Settings.EXTRA_CHANNEL_ID, args.optString(1));
            }

            // i.putExtra(Settings.EXTRA_APP_PACKAGE, cordova.getActivity().getPackageName());
            cordova.getActivity().startActivity(i);
        } catch (Exception ex) {
            Log.e(LOG_TAG, ex.getMessage());
        }
    }
}