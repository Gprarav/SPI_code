/**
 * 
 */
package com.baeldung.auth.provider.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.keycloak.component.ComponentModel;
import org.keycloak.credential.CredentialInput;
import org.keycloak.credential.CredentialInputValidator;
import org.keycloak.models.GroupModel;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RealmModel;
import org.keycloak.models.UserModel;
import org.keycloak.models.credential.PasswordCredentialModel;
import org.keycloak.storage.StorageId;
import org.keycloak.storage.UserStorageProvider;
import org.keycloak.storage.user.UserLookupProvider;
import org.keycloak.storage.user.UserQueryProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomUserStorageProvider implements UserStorageProvider,
        UserLookupProvider,
        CredentialInputValidator,
        UserQueryProvider {

    private static final Logger log = LoggerFactory.getLogger(CustomUserStorageProvider.class);
    private KeycloakSession ksession;
    private ComponentModel model;

    public CustomUserStorageProvider(KeycloakSession ksession, ComponentModel model) {
        this.ksession = ksession;
        this.model = model;
    }


    @Override
    public void close() {
        log.info("[I30] close()");
    }

    @Override
    public UserModel getUserById(RealmModel realm, String id) {
        log.info("[I35] getUserById({})", id);
        StorageId sid = new StorageId(id);
        return getUserByUsername(realm, sid.getExternalId());
    }

   
    //get user by name method
    @Override
    public UserModel getUserByUsername(RealmModel realm, String name) {
        log.info("[I41] getUserByUsername({})", name);
        try (Connection c = DbUtil.getConnection(this.model)) {
            PreparedStatement st = c.prepareStatement("select user_master_id, user_id, role_id, name, address_line1, phone_mobile, email_id, user_password, is_Approved, user_region_id, designation, text_password, password_reset_flag, is_Active, created_by, created_on, updated_by, updated_on, password_modified_by, password_modified_on, user_password_2 verified_time, genrated_time, email_token, email_verify_flag from user_master_new where name = ?");
            st.setString(1, name);  
            st.execute();
            ResultSet rs = st.getResultSet();
            if (rs.next()) {
                return mapUser(realm, rs);
            } else {
                return null;
            }
            
        } catch (SQLException ex) {
            throw new RuntimeException("Database error:" + ex.getMessage(), ex);
        }
    }

  

    
    @Override
    public UserModel getUserByEmail(RealmModel realm, String email) {
        log.info("[I48] getUserByEmail({})", email);
        try (Connection c = DbUtil.getConnection(this.model)) {
            PreparedStatement st = c.prepareStatement("select user_master_id, user_id, role_id, name, address_line1, phone_mobile, email_id, user_password, is_Approved, user_region_id, designation, text_password, password_reset_flag, is_Active, created_by, created_on, updated_by, updated_on, password_modified_by, password_modified_on, user_password_2 verified_time, genrated_time, email_token, email_verify_flag from user_master_new where email_id = ?");
            st.setString(1, email);
            st.execute();
            ResultSet rs = st.getResultSet();
            if (rs.next()) {
                return mapUser(realm, rs);
            } else {
                return null;
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Database error:" + ex.getMessage(), ex);
        }
    }

  

    @Override
    public boolean supportsCredentialType(String credentialType) {
        log.info("[I57] supportsCredentialType({})", credentialType);

        // Check if the credential type is a password type
        return PasswordCredentialModel.TYPE.endsWith(credentialType);
    }

    @Override
    public boolean isConfiguredFor(RealmModel realm, UserModel user, String credentialType) {
        log.info("[I57] isConfiguredFor(realm={}, user={}, credentialType={})", realm.getName(), user.getUsername(),
                credentialType);

        // In your case, password is the only type of credential, so always return
        // 'true' if
        // this is the credentialType
        return supportsCredentialType(credentialType);
    }

    @Override
    public boolean isValid(RealmModel realm, UserModel user, CredentialInput credentialInput) {
        log.info("[I57] isValid(realm={}, user={}, credentialInput.type={})", realm.getName(), user.getUsername(),
                credentialInput.getType());

        // Check if the credential type is supported
        if (!this.supportsCredentialType(credentialInput.getType())) {
            return false;
        }

        StorageId sid = new StorageId(user.getId());
        String name = sid.getExternalId();

        try (Connection c = DbUtil.getConnection(this.model)) {
            PreparedStatement st = c.prepareStatement("select user_password from user_master_new WHERE name = ?");
            st.setString(1, name);
            st.execute();
            ResultSet rs = st.getResultSet();
            if (rs.next()) {
                String storedPassword = rs.getString(1);
                String providedPassword = credentialInput.getChallengeResponse();
                return storedPassword.equals(providedPassword);
            } else {
                return false;
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Database error:" + ex.getMessage(), ex);
        }
    }
    // // UserQueryProvider implementation

    @Override
    public int getUsersCount(RealmModel realm) {
        log.info("[I93] getUsersCount: realm={}", realm.getName());

        try (Connection c = DbUtil.getConnection(this.model)) {
            Statement st = c.createStatement();
            st.execute("SELECT COUNT(*) FROM user_master_new");
            ResultSet rs = st.getResultSet();
            rs.next();
            return rs.getInt(1);
        } catch (SQLException ex) {
            throw new RuntimeException("Database error:" + ex.getMessage(), ex);
        }
    }

    

   
    @Override
    public Stream<UserModel> searchForUserStream(RealmModel realm, String search, Integer firstResult,
            Integer maxResults) {
        log.info("[I139] searchForUser: realm={}", realm.getName());

        try (Connection c = DbUtil.getConnection(this.model)) {
            PreparedStatement st = c.prepareStatement(
                    "select user_master_id, user_id, role_id, name, address_line1, phone_mobile, email_id, user_password, is_Approved, user_region_id, designation, text_password, password_reset_flag, is_Active, created_by, created_on, updated_by, updated_on, password_modified_by, password_modified_on, user_password_2 verified_time, genrated_time, email_token,  email_verify_flag from user_master_new WHERE name LIKE ? ORDER BY name LIMIT ? OFFSET ?");
            st.setString(1, search);
            st.setInt(2, maxResults);
            st.setInt(3, firstResult);
            st.execute();
            ResultSet rs = st.getResultSet();

            List<UserModel> users = new ArrayList<>();
            while (rs.next()) {
                users.add(mapUser(realm, rs));
            }

            return users.stream();
        } catch (SQLException ex) {
            throw new RuntimeException("Database error:" + ex.getMessage(), ex);
        }
    }
    @Override
    public Stream<UserModel> searchForUserStream(RealmModel realm, Map<String, String> params, Integer firstResult,
            Integer maxResults) {
        return getGroupMembersStream(realm, null, firstResult, maxResults);
    }

    @Override
    public Stream<UserModel> searchForUserByUserAttributeStream(RealmModel realm, String attrName, String attrValue) {
        // Placeholder implementation that returns an empty stream
        return Stream.empty();
    }
    @Override
    public Stream<UserModel> getGroupMembersStream(RealmModel realm, GroupModel group, Integer firstResult,
            Integer maxResults) {
        log.info("[I113] getUsers: realm={}", realm.getName());

        try (Connection c = DbUtil.getConnection(this.model)) {
            PreparedStatement st = c
                    .prepareStatement("select user_master_id, user_id, role_id, name, address_line1, phone_mobile, email_id, user_password, is_Approved, user_region_id, designation, text_password, password_reset_flag, is_Active, created_by, created_on, updated_by, updated_on, password_modified_by, password_modified_on, user_password_2 verified_time, genrated_time, email_token,  email_verify_flag from user_master_new ORDER BY name LIMIT ? OFFSET ?");
            st.setInt(1, maxResults);
            st.setInt(2, firstResult);
            st.execute();
            ResultSet rs = st.getResultSet();

            List<UserModel> users = new ArrayList<>();
            while (rs.next()) {
                users.add(mapUser(realm, rs));
            }

            return users.stream();
        } catch (SQLException ex) {
            log.info("[I113]-2 error: {}", ex.getStackTrace().toString());
            throw new RuntimeException("Database error:" + ex.getMessage(), ex);
        }
    }

    // //------------------- Implementation
    private UserModel mapUser(RealmModel realm, ResultSet rs) throws SQLException {
                //DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        CustomUser user = new CustomUser.Builder(ksession, realm, model, rs.getString("user_id"))
                .user_master_id(rs.getInt("user_master_id"))
                .role_id(rs.getInt("role_id"))
                .name(rs.getString("name"))
                .address_line1(rs.getString("address_line1"))
                .phone_mobile(rs.getString("phone_mobile"))
                .email_id(rs.getString("email_id"))
                .user_password(rs.getString("user_password"))
                .is_Approved(rs.getBoolean("is_approved"))
                .user_region_id(rs.getInt("user_region_id"))
                .designation(rs.getString("designation"))
                .text_password(rs.getString("text_password"))
                .password_reset_flag(rs.getBoolean("password_reset_flag"))
                .is_Active(rs.getBoolean("is_active"))
                .created_by(rs.getInt("created_by"))
                .created_on(rs.getTimestamp("created_on"))
                .updated_by(rs.getInt("updated_by"))
                .updated_on(rs.getTimestamp("updated_on"))
                .password_modified_by(rs.getInt("password_modified_by"))
                .password_modified_on(rs.getTimestamp("password_modified_on"))
                .user_password_2(rs.getString("user_password_2"))
                .verified_time(rs.getTimestamp("verified_time"))
                .genrated_time(rs.getTimestamp("genrated_time"))
                .email_token(rs.getString("email_token"))
                .email_verify_flag(rs.getBoolean("email_verify_flag"))


                .build();

        return user;
    }
}
