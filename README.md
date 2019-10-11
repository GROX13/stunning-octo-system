## Running the application

There are two separate applications in this project: **web** and **java** and to properly run them you need to configure nginx. I have included configuration `giorgirokhadze.conf` 
Which you should add in `/etc/nginx/conf.d/` folder and restart nginx by running: `systemctl restart nginx.service`. 
Add `giorgirokhadze.me` into `/etc/hosts` file like `127.0.0.1       localhost giorgirokhadze.me`. If you would like to use another url you can modify 
it to your liking but not forget to modify configuration. If you are using any of the IDE you can run application directly from it by running `me.giorgirokhadze.interview.gsg.Application` 
else you need to run `gradle build` and run `jar` from command line. On the web part of application for testing purposes you can run `ng serve --host giorgirokhadze.me --disable-host-check`. 
After you can view application by visiting: http://giorgirokhadze.me/

### Google API keys

> 1. You need a Google Account to access the Google API Console, request an API key, and register your application.
> 2. Create a project in the Google Developers Console and obtain authorization credentials so your application can submit API requests.
> 3. After creating your project, make sure the YouTube Data API is one of the services that your application is registered to use:
>   * Go to the API Console and select the project that you just registered.
>   * Visit the Enabled APIs page. In the list of APIs, make sure the status is ON for the YouTube Data API v3.

Additional resources: https://developers.google.com/youtube/v3/getting-started

You need to download and add `client_secret.json` and add API key in resources `youtube_api_key.txt` file. 

#### Creating service account

> The Google OAuth 2.0 system supports server-to-server interactions such as those between a web application and a Google service. 
> For this scenario you need a service account, which is an account that belongs to your application instead of to an individual end user.

Additional resources: https://developers.google.com/identity/protocols/OAuth2ServiceAccount#creatinganaccount

You need to download service account private key rename it to `stunning-octo-system.p12` and add to `resources` in this case application will automatically read it in case running with `prod` profile.

### Running with dev profile

For testing purposes I have added data initializer which saves default user in database. Because I wanted to avoid leaking this kind of user in production I made data initializer only available in `dev` profile.
If you need to use existing data initializer you need to run application with `-Dspring.profiles.active=dev` VM option.

### Accessing the H2 Console

H2 database has an embedded GUI console for browsing the contents of a database, after starting the application, we can navigate to http://giorgirokhadze.me/h2-console which will present us with a login page. 
On the login page, we'll supply the same credentials as we used in the `application.properties`.

#### Possible issues

* ##### In case you are using linux and you encounter `UnknownHostException` 
    While trying to use `h2-console` you might encounter exception similar to ```java.net.UnknownHostException: {hostname}: {hostname}: Name or service not known```.
     This can actually have a few different causes, but the most common cause on Linux is an inability of the computer running application to resolve its own hostname. 
     To solve this issue you'll need to add `{hostname}` in `/etc/hosts` file like: `127.0.0.1       localhost linux-krok` which should solve the issue.