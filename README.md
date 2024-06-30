Ethereum Blockchain Balance Checking App

1. Objective
Develop a decentralized application (DApp) application will take input from an Ethereum Blockchain Public address and it will show the balance of the address.

2. Overview
Blockchain technology has been creating a significant impact in multiple sectors, such as banking, cybersecurity, and supply chain management. It is widely used as a means of secure payment between different parties.Ethereum is a decentralized Blockchain network where you can perform transactions using its native currency, Ether, and token. We can interact with the network through simple API calls. We are using Infura API keys to fetch data of a specific Ethereum Blockchain Public address.

3. Set up your environment

1. Compile SDK 34(Though 33 was recommended but I was having problem)
2. Build tool 33.0.2 
3. Android Gradle plugin 7.2.2
4. Gradle Version 7.4.

Sample Code
Here is a sample code 
https://github.com/Ahnafprio/assesment_crypto
Enable developer mode

We should enable developer mode by doing specific tasks.


4.Design of the Application
This is the UI of the app



Here we one editText field for the address two button to scan QR Code & Fetch Balance & Nonce.

5. Initialize the instance

We have to initialize all the fields of the app.


I also initialize the web3 instance







6.Import Library 
In build.gradle file I added dependencies.
I also import library to scan QR Code & to fetch etherium data.


After that I have to update libs.version.toml file

Be careful about adding the web3 & QR Code file not by group but by module.

7.Fetching Balance & Nonce
When we click the fetch button it checks if the textfield is empty or not. If not empty then it calls fetchBalanceAndNonce method

The fetchBalanceAndNonce  calls the EtheriumService class’s fetchBalanceAndNonce method

EtheriumService class’s fetchBalanceAndNonce method fetch the data through web3 api.

If the call is successful then the UI is updated

I created Infura account & create a key from there to call the API.

8.QR Code Scanning
To scan the QR Code I used a library.It scans the QR Code & and send it to the textField. Then if we click in Fetch Balance & Nonce button then the balance & nonce is fetched. 

9.Run The App
This is the first look of the app..
I copy a  Ethereum Blockchain Public address & click the button Fetch Balance and Nonce. Then the Balance & Nonce are shown.


Using the QR Code the app first asks for camera permission. If the permission is granted then the camera scans the QR Code & send the QR Code data in textField.


After scanning the address the address goes to the text field & if we click the Fetch Balance and Nonce button then Balance & Nonce are shown in the field if the address is correct.




