### Accessing the H2 Console

H2 database has an embedded GUI console for browsing the contents of a database, after starting the application, we can navigate to http://localhost:8080/h2-console which will present us with a login page. 
On the login page, we'll supply the same credentials as we used in the application.properties.

#### Possible issues

* ##### In case you are using linux and you encounter `UnknownHostException` 
    While trying to use `h2-console` you might encounter exception similar to ```java.net.UnknownHostException: {hostname}: {hostname}: Name or service not known```.
     This can actually have a few different causes, but the most common cause on Linux is an inability of the computer running bliss to resolve its own hostname. 
     To solve this issue you'll need to add `{hostname}` in `/etc/hosts` file like: `127.0.0.1       localhost linux-krok` which should solve the issue.