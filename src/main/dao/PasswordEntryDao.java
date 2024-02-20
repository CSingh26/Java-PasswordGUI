package main.dao;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.java.model.PasswordEntry;
import main.java.util.DatabaseUtil;
import main.java.util.EncryptionUtil;

public class PasswordEntryDao {

    String keyPath = "encryptionKey.txt";
    String IvPath = "encryptionIv.txt";
    
    public void savePasswordEntry(PasswordEntry entry) throws 
        NoSuchPaddingException, NoSuchAlgorithmException,
        InvalidAlgorithmParameterException, InvalidKeyException,
        BadPaddingException, IllegalBlockSizeException, SQLException {
        String sql = "INSERT INTO password_entries  (serviceName, username, password, email) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseUtil.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, entry.getServiceName());
                pstmt.setString(2, entry.getUserName());
                pstmt.setString(3, EncryptionUtil.encrypt(
                    entry.getPassword(), 
                    EncryptionUtil.loadKeyFromFile(keyPath), 
                    EncryptionUtil.loadIvFromFile(IvPath)));
                pstmt.setString(4, entry.getEmailId());
                pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<PasswordEntry> getEntry(String serviceName) throws 
    NoSuchPaddingException, NoSuchAlgorithmException,
    InvalidAlgorithmParameterException, InvalidKeyException,
    BadPaddingException, IllegalBlockSizeException, SQLException {
        List<PasswordEntry> entries = new ArrayList<>();

        String sql = "SELECT * FROM password_entries  WHERE serviceName = ?";
        try (Connection conn = DatabaseUtil.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, serviceName);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                PasswordEntry entry = new PasswordEntry();
                String decrpytedPwd = EncryptionUtil.decrypt(
                    rs.getString("password"), 
                    EncryptionUtil.loadKeyFromFile(keyPath), 
                    EncryptionUtil.loadIvFromFile(IvPath));
                    entry.setServiceName(rs.getString("serviceName"));
                    entry.setUserName(rs.getString("username"));
                    entry.setEmailId(rs.getString("email"));
                    entry.setPassword(decrpytedPwd);
                    entries.add(entry);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return entries;
    }
}
