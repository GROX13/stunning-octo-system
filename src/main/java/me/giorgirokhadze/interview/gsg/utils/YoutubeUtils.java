package me.giorgirokhadze.interview.gsg.utils;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.YouTubeScopes;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.Collection;

public class YoutubeUtils {
	private static final String CLIENT_SECRETS = "client_secret.json";
	private static final String API_KEY = "youtube_api_key.txt";
	private static final String SERVICE_ACCOUNT_KEY = "service_account_key.txt";
	private static final String SERVICE_ACCOUNT_PRIVATE_KEY = "stunning-octo-system.p12";
	private static final Collection<String> SCOPES = Arrays.asList(YouTubeScopes.YOUTUBE_READONLY, YouTubeScopes.YOUTUBE_FORCE_SSL);

	private static final String APPLICATION_NAME = "Stunning octo system";
	private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

	/**
	 * Build and return an authorized API client service.
	 *
	 * @return an authorized API client service
	 * @throws GeneralSecurityException, IOException
	 */
	public static YouTube getService() throws GeneralSecurityException, IOException {
		final NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
		Credential credential = authorize(httpTransport);
		return new YouTube.Builder(httpTransport, JSON_FACTORY, credential)
				.setApplicationName(APPLICATION_NAME)
				.build();
	}

	/**
	 * Build and return an authorized API client service.
	 *
	 * @return an authorized API client service
	 * @throws GeneralSecurityException, IOException
	 */
	public static YouTube getServiceOAuth() throws GeneralSecurityException, IOException {
		final NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
		Credential credential = authorizeOAuth(httpTransport);
		return new YouTube.Builder(httpTransport, JSON_FACTORY, credential)
				.setApplicationName(APPLICATION_NAME)
				.build();
	}

	/**
	 * Create an authorized Credential object.
	 *
	 * @return an authorized Credential object.
	 * @throws IOException
	 */
	private static Credential authorizeOAuth(final NetHttpTransport httpTransport) throws IOException, GeneralSecurityException {
		return new GoogleCredential.Builder()
				.setTransport(httpTransport)
				.setJsonFactory(JSON_FACTORY)
				.setServiceAccountId("sos-service-account@stunning-octo-system.iam.gserviceaccount.com")
				.setServiceAccountPrivateKeyFromP12File(
						new File(YoutubeUtils.class.getClassLoader().getResource(SERVICE_ACCOUNT_PRIVATE_KEY).getFile())
				)
				.setServiceAccountScopes(SCOPES)
				.setServiceAccountUser("giorgirokhadze@gmail.com")
				.build();
	}

	/**
	 * Create an authorized Credential object.
	 *
	 * @return an authorized Credential object.
	 * @throws IOException
	 */
	private static Credential authorize(final NetHttpTransport httpTransport) throws IOException {
		// Load client secrets.
		InputStream in = YoutubeUtils.class.getClassLoader().getResourceAsStream(CLIENT_SECRETS);
		GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));
		// Build flow and trigger user authorization request.
		GoogleAuthorizationCodeFlow flow =
				new GoogleAuthorizationCodeFlow.Builder(httpTransport, JSON_FACTORY, clientSecrets, SCOPES)
						.build();
		return new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
	}

	public static ApiKeyProvider getApiKeyProvider() throws IOException {
		return new ApiKeyProvider(new String(YoutubeUtils.class.getClassLoader().getResourceAsStream(API_KEY).readAllBytes()));
	}

	public static ServiceAccountKeyProvider getServiceAccountKeyProvider() throws IOException {
		return new ServiceAccountKeyProvider(new String(YoutubeUtils.class.getClassLoader().getResourceAsStream(SERVICE_ACCOUNT_KEY).readAllBytes()));
	}

	public static class ApiKeyProvider extends KeyProvider {
		ApiKeyProvider(String key) {
			super(key);
		}
	}

	public static class ServiceAccountKeyProvider extends KeyProvider {
		public ServiceAccountKeyProvider(String key) {
			super(key);
		}
	}

	abstract static class KeyProvider {
		private final String key;

		KeyProvider(String key) {
			this.key = key;
		}

		public String getKey() {
			return key;
		}
	}
}
