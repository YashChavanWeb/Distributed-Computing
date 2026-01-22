# JavaChat – Two-Computer Setup Guide

This guide explains how to properly configure and run **JavaChat** across two computers on the same network.

---

## 1. Pre-Run Checklist (Important)

Before running the application, verify the following:

### Configuration

* Open `config.properties`
* Ensure the **server computer’s IPv4 address** is correctly set
  **Example:**

  ```
  SERVER_IP=your-server-ip-value
  ```
* The IP must belong to the computer running the server.

### Network

* Both the **Server** and **Client** computers must be connected to the **same Wi-Fi network or router**.
* The application will not work across different networks.

### Firewall (Server Computer Only)

If the client fails to connect, allow the server port through Windows Firewall:

1. Open **Windows Defender Firewall**
2. Click **Advanced settings**
3. Select **Inbound Rules → New Rule**
4. Choose **Port**
5. Select **TCP**
6. Enter **Specific local ports:** `5000`
7. Choose **Allow the connection**
8. Name the rule **JavaChat**
9. Click **Finish**

---

## 2. How to Run the Application

### Server Computer

1. Double-click `RunServer.bat`
2. The script will:

   * Compile the Java code
   * Start the chat server
   * Begin listening for client connections

### Client Computer(s)

1. Double-click `RunClient.bat`
2. When prompted, enter your name
   **Example:**

   * Yash enters: `Yash`
   * Anjali enters: `Anjali`

---

## 3. How to Chat

### Group Chat

* Type your message and press **Enter**
* Everyone connected will see the message

### Private Chat

* Use `@username` followed by your message
  **Example:**

  ```
  @Anjali hey there
  ```
* This message will be visible **only** to Anjali

---

## 4. Notes

* Ensure the server is running **before** starting any clients
* Multiple clients can connect to the same server
* Port `5000` must remain open on the server machine

---

Happy chatting! 🚀
