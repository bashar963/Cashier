# cashier

# Project configuration
Check the `app` module root folder for the configuration files:
1. `config.properties` contains general configuration data
2. `release.properties` should contain your signing information in the following format
```
STORE_PASSWORD=your_store_password
KEY_ALIAS=your_key_alias
KEY_PASSWORD=your_key_password
```
3. `android.keystore` your keystore for signing

The last 2 files are required only for the release build type.
You can swap any of them to manage different build types (for instance, using your CI server like TeamCity).
