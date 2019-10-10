### Running the application

For starting application first you will need to configure local machine. I used nginx for setting up a proxy server. 
There are two separate applications in this project: web and spring. First you will need to configure nginx. I have included configuration `giorgirokhadze.conf` 
Which you'll need to add in `/etc/nginx/conf.d/` folder. Afterwards you'll need to restart nginx by running: `systemctl restart nginx.service`. 
You'll also might need to add `giorgirokhadze.me` into `/etc/hosts` file like `127.0.0.1       localhost giorgirokhadze.me`. If you would like to use another url you can modify 
it to your liking but will also need to modify configuration. If you are using any of the IDE you can run application directly from it by running `me.giorgirokhadze.interview.gsg.Application` 
else you will need to run `gradle build` and run `jar` from command line. On the web part of application for testing purposes you can run `ng serve --host giorgirokhadze.me --disable-host-check`. 
After you can view application by visiting: http://giorgirokhadze.me/

### Creating service account
https://developers.google.com/identity/protocols/OAuth2ServiceAccount

### Running with dev profile

For testing purposes I have added data initializer which saves default user in database. Because I wanted to avoid leaking this kind of user in production I made data initializer only available in `dev` profile.
If you need to use existing data initializer you need to run application with `-Dspring.profiles.active=dev` VM option.

### Accessing the H2 Console

H2 database has an embedded GUI console for browsing the contents of a database, after starting the application, we can navigate to http://localhost:8080/h2-console which will present us with a login page. 
On the login page, we'll supply the same credentials as we used in the `application.properties`.

#### Possible issues

* ##### In case you are using linux and you encounter `UnknownHostException` 
    While trying to use `h2-console` you might encounter exception similar to ```java.net.UnknownHostException: {hostname}: {hostname}: Name or service not known```.
     This can actually have a few different causes, but the most common cause on Linux is an inability of the computer running application to resolve its own hostname. 
     To solve this issue you'll need to add `{hostname}` in `/etc/hosts` file like: `127.0.0.1       localhost linux-krok` which should solve the issue.