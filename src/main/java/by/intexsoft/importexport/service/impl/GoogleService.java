package by.intexsoft.importexport.service.impl;

import by.intexsoft.importexport.service.IGoogleService;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.AbstractInputStreamContent;
import com.google.api.client.http.ByteArrayContent;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.*;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;

import static by.intexsoft.importexport.constant.Constant.*;
import static by.intexsoft.importexport.util.FolderUtil.checkFile;

@Slf4j
@Service
public class GoogleService implements IGoogleService {
    private static final JacksonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final File CREDENTIALS_FOLDER = new File(System.getProperty(USER_FOLDER), CREDENTIAL_FOLDER);
    private static final List<String> SCOPES = Collections.singletonList(DriveScopes.DRIVE);
    private static NetHttpTransport netHttpTransport;

    @Override
    public Credential getCredential(final NetHttpTransport netHttpTransport) throws IOException {
        File clientSecretFilePath = checkFile(CREDENTIALS_FOLDER, CLIENT_SECRET_FILE_NAME);
        InputStream in = new FileInputStream(clientSecretFilePath);
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow
                .Builder(netHttpTransport, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(CREDENTIALS_FOLDER))
                .setAccessType(ACCESS_TYPE)
                .build();
        return new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver())
                .authorize(USER_ROLE);
    }

    @Override
    public Credential createAuthorized() throws GeneralSecurityException, IOException {
        netHttpTransport = GoogleNetHttpTransport.newTrustedTransport();
        return getCredential(netHttpTransport);
    }

    @Override
    public Drive createGoogleDriveService() throws GeneralSecurityException, IOException {
        return new Drive.Builder(netHttpTransport, JSON_FACTORY, createAuthorized())
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

    @Override
    public void createAndSaveFileInGoogleDrive(final String googleFolderIdParent, final String contentType,
                                               final String fileName, final byte[] uploadData) throws IOException, GeneralSecurityException {
        AbstractInputStreamContent uploadStreamContent = new ByteArrayContent(contentType, uploadData);
        createGoogleFile(googleFolderIdParent, fileName, uploadStreamContent);
    }

    private void createGoogleFile(final String googleFolderIdParent, final String fileName,
                                  final AbstractInputStreamContent uploadStreamContent) throws IOException, GeneralSecurityException {
        com.google.api.services.drive.model.File fileMetadata = new com.google.api.services.drive.model.File();
        fileMetadata.setName(fileName);
        List<String> parents = Collections.singletonList(googleFolderIdParent);
        fileMetadata.setParents(parents);
        Drive driveService = createGoogleDriveService();
        driveService.files()
                .create(fileMetadata, uploadStreamContent)
                .setFields("id, webContentLink, webViewLink, parents")
                .execute();
    }

}
