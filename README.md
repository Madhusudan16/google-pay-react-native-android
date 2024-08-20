
# react-native-google-pay

## Getting started

`$ npm install react-native-google-pay --save`

### Mostly automatic installation

`$ react-native link react-native-google-pay`

### Manual installation


#### Android

1. Open up `android/app/src/main/java/[...]/MainActivity.java`
  - Add `import com.reactlibrary.RNGooglePayPackage;` to the imports at the top of the file
  - Add `new RNGooglePayPackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':react-native-google-pay'
  	project(':react-native-google-pay').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-google-pay/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':react-native-google-pay')
  	```


## Usage
```javascript
import RNGooglePay from 'react-native-google-pay';

// TODO: What to do with the module?
RNGooglePay;
```
  