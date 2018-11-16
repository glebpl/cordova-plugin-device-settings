'use strict';

var exec = require('cordova/exec');
var _noop = function(){};
var SERVICE_NAME = 'DeviceSettings';

var _facade = {
    startActivity: function startActivity(key, extra) {
        exec(
            _noop,
            _noop,
            SERVICE_NAME,
            'startActivity',
            [key || this.APPLICATION, extra || null]
        );
    }
    , APPLICATION: 'ACTION_APPLICATION_SETTINGS' // >= 1
    , APP_NOTIFICATION: 'ACTION_APP_NOTIFICATION_SETTINGS'// >= 26
    , CHANNEL_NOTIFICATION: 'ACTION_CHANNEL_NOTIFICATION_SETTINGS'// >= 26, requires EXTRA_APP_PACKAGE, EXTRA_CHANNEL_ID
    , LOCATION_SOURCE: 'ACTION_LOCATION_SOURCE_SETTINGS'// >= 1
    , LOCALE: 'ACTION_LOCALE_SETTINGS'// >= 1
    , DEVICE_INFO: 'ACTION_DEVICE_INFO_SETTINGS'// >=8
    , ROOT: 'ACTION_SETTINGS'// >= 1
};



module.exports = _facade;