# AirBite Client
AirBite is a socket programming application designed to facilitate food ordering in an airport scenario. This client-side implementation of AirBite is written in Java and uses `java.net.Socket`, `java.io.InputStream`, and `java.io.OutputStream` to communicate with the server over TCP port `3721`.

## Dependencies
This client application has been developed and tested on Java 8. It does not require any external dependencies.

## Usage
To use the AirBite client, simply compile and run the Client.java file:

```bash
javac Client.java
java Client localhost:3721
```

The client will connect to the server `localhost` running on the default port `3721`. You can modify the server IP address and port number in the command line argument to connect to a different server.

Once connected, the client will prompt you to enter your name and select a restaurant. After selecting a restaurant, you can browse the menu, select items to order, and choose whether you want the food delivered to your gate or if you will pick it up.

## Contributing
Contributions to AirBite are welcome! If you find a bug or have a feature request, please open an issue in the issue tracker. If you'd like to contribute code, please fork the repository and submit a pull request.

## License
This software is released under the MIT License. See the LICENSE file for more information.