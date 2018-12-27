package by.intexsoft.importexport.service;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.services.drive.Drive;

import java.io.IOException;
import java.security.GeneralSecurityException;

public interface IGoogleService {
    Credential getCredential(NetHttpTransport httpTransport) throws IOException;
    Credential createAuthorized() throws GeneralSecurityException, IOException;
    Drive createGoogleDriveService() throws GeneralSecurityException, IOException;
    void createAndSaveFileInGoogleDrive(String googleFolderIdParent, String contentType,
                                        String customFileName, byte[] uploadData) throws GeneralSecurityException, IOException;
}
