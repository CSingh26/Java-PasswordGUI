package test;

import main.java.model.PasswordEntry;
import main.java.util.DatabaseUtil;
import main.dao.PasswordEntryDao;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

class PasswordEntryDaoTest {

    private PasswordEntryDao dao;

    @BeforeEach
    void setUp() {
        // Initialize your DAO here; consider using a test database or a mock database
        dao = new PasswordEntryDao();
    }

    @AfterEach
    void tearDown() {
        // Define your SQL statement for deleting the test entries
        String sql = "DELETE FROM password_entries WHERE serviceName = ?";

        try (Connection conn = DatabaseUtil.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Set the specific serviceName used for testing
            pstmt.setString(1, "TestService");
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            fail("Error cleaning up the database.");
        }
    }

    @Test
    void testSaveAndGetPasswordEntry() {
        // Create a new PasswordEntry object
        PasswordEntry entry = new PasswordEntry();
        entry.setServiceName("TestService");
        entry.setUserName("TestUser");
        entry.setPassword("TestPassword"); // This should be encrypted in the DAO
        entry.setEmailId("test@example.com");

        // Attempt to save the entry
        try {
            dao.savePasswordEntry(entry);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Saving password entry failed.");
        }

        // Attempt to retrieve the entry
        try {
            var entries = dao.getEntry("TestService");
            assertFalse(entries.isEmpty(), "No entries found for the given service name.");

            PasswordEntry retrievedEntry = entries.get(0);
            assertEquals("TestService", retrievedEntry.getServiceName());
            assertEquals("TestUser", retrievedEntry.getUserName());
            // Assuming decrypt happens in getEntry or PasswordEntry constructor/setter
            assertEquals("TestPassword", retrievedEntry.getPassword(), "The decrypted password does not match.");
            assertEquals("test@example.com", retrievedEntry.getEmailId());
        } catch (Exception e) {
            e.printStackTrace();
            fail("Retrieving password entry failed.");
        }
    }
}
