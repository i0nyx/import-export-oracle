package by.intexsoft.importexport.service;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.services.drive.Drive;

import java.io.IOException;
import java.security.GeneralSecurityException;

/**
 * google api class
 */
public interface IGoogleService {
    /**
     * authorize in google and create credentials
     *
     * @param httpTransport HTTP low-level transport
     * @return {@link Credential}
     * @throws IOException opportunity to get an exception
     */
    Credential getCredential(NetHttpTransport httpTransport) throws IOException;

    /**
     * Create credentials
     *
     * @return {@link Credential}
     * @throws GeneralSecurityException opportunity to get an exception
     * @throws IOException              opportunity to get an exception
     */
    Credential createAuthorized() throws GeneralSecurityException, IOException;

    /**
     * Create google drive service
     *
     * @return {@link Drive}
     * @throws GeneralSecurityException opportunity to get an exception
     * @throws IOException              opportunity to get an exception
     */
    Drive createGoogleDriveService() throws GeneralSecurityException, IOException;

    /**
     * Method create and save file in google drive
     *
     * @param googleFolderIdParent folder name for save in google drive
     * @param contentType          type document
     * @param customFileName       file name
     * @param uploadData           data
     * @throws GeneralSecurityException opportunity to get an exception
     * @throws IOException              opportunity to get an exception
     */
    void createAndSaveFileInGoogleDrive(String googleFolderIdParent, String contentType,
                                        String customFileName, byte[] uploadData) throws GeneralSecurityException, IOException;
}
