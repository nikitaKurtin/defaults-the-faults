# Defaults - the faults
Code examples for my talk at DefCon30

## Android `MaliciousApp` 
Toast customization examples 
Attack examples - you can choose which attack to execute in the `MaliciousActivity.scheduleAttack` method
The app will close, and the attack would execute after delay configured in the `howLongToWait` param

### Notes :
- For location bypass - the server MUST to be called over HTTPS
- For uninstall attack - add `REQUEST_DELETE_PACKAGES` permission in the AndroidManifest

## Android `VictimUninstall`
Target victim example - used for clickjacking and uninstall examples

## NodeJS Server `MaliciousServer`
Simple server example for the two-way internet bypass and location bypass examples
