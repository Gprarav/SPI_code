package com.baeldung.auth.provider.user;

import java.util.Date;
import java.util.List;
import java.util.Map;
//import java.util.stream.Stream;

import org.keycloak.common.util.MultivaluedHashMap;
import org.keycloak.component.ComponentModel;
import org.keycloak.credential.LegacyUserCredentialManager;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RealmModel;
import org.keycloak.models.SubjectCredentialManager;
import org.keycloak.models.UserModel;
import org.keycloak.storage.adapter.AbstractUserAdapter;

class CustomUser extends AbstractUserAdapter {
    private final int user_master_id;
    private final int user_id;
    private final int role_id;
    private final String name;
    private final String address_line1;
    private final String phone_mobile;
    private final String email_id;
    private final String user_password;
    private final boolean is_Approved;
    private final int user_region_id;
    private final String designation;
    private final String text_password;
    private final boolean password_reset_flag;
    private final boolean is_Active;
    private final int created_by;
    private final Date created_on;
    private final int updated_by;
    private final Date updated_on;
    private final int password_modified_by;
    private final Date password_modified_on;
    private final String user_password_2;
    private final Date verified_time;
    private final Date genrated_time;
    private final String email_token;
    private final boolean email_verify_flag;

    private CustomUser(KeycloakSession session, RealmModel realm,
            ComponentModel storageProviderModel,
            int user_master_id,
            int user_id,
            int role_id,
            String name,
            String address_line1,
            String phone_mobile,
            String email_id,
            String user_password,
            boolean is_Approved,
            int user_region_id,
            String designation,
            String text_password,
            boolean password_reset_flag,
            boolean is_Active,
            int created_by,
            Date created_on,
            int updated_by,
            Date updated_on,
            int password_modified_by,
            Date password_modified_on,
            String user_password_2,
            Date verified_time,
            Date genrated_time,
            String email_token,
            boolean email_verify_flag)

    {
        super(session, realm, storageProviderModel);
        this.user_master_id = user_master_id;
        this.user_id = user_id;
        this.role_id = role_id;
        this.name = name;
        this.address_line1 = address_line1;
        this.phone_mobile = phone_mobile;
        this.email_id = email_id;
        this.user_password = user_password;
        this.is_Approved = is_Approved;
        this.user_region_id = user_region_id;
        this.designation = designation;
        this.text_password = text_password;
        this.password_reset_flag = password_reset_flag;
        this.is_Active = is_Active;
        this.created_by = created_by;
        this.created_on = created_on;
        this.updated_by = updated_by;
        this.updated_on = updated_on;
        this.password_modified_by = password_modified_by;
        this.password_modified_on = password_modified_on;
        this.user_password_2 = user_password_2;
        this.verified_time = verified_time;
        this.genrated_time = genrated_time;
        this.email_token = email_token;
        this.email_verify_flag = email_verify_flag;
    }

    public String getUsername() {
        return "sr";
    }

    public int getUser_master_id() {
        return user_master_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public int getRole_id() {
        return role_id;
    }

    public String getName() {
        return name;
    }

    public String getAddress_line1() {
        return address_line1;
    }

    public String getPhone_mobile() {
        return phone_mobile;
    }

    public String getEmail_id() {
        return email_id;
    }

    public String getUser_password() {
        return user_password;
    }

    public boolean is_Approved() {
        return is_Approved;
    }

    public int getUserRegionId() {
        return user_region_id;
    }

    public String getDesignation() {
        return designation;
    }

    public String getText_password() {
        return text_password;
    }

    public boolean is_Password_reset_flag() {
        return password_reset_flag;
    }

    public boolean is_Active() {
        return is_Active;
    }

    public int getCreated_by() {
        return created_by;
    }

    public Date getCreated_on() {
        return created_on;
    }

    public int getUpdated_by() {
        return updated_by;
    }

    public Date getUpdated_on() {
        return updated_on;
    }

    public int getPassword_modified_by() {
        return password_modified_by;
    }

    public Date getPassword_modified_on() {
        return password_modified_on;
    }

    public String getUser_password_2() {
        return user_password_2;
    }

    public Date getVerified_time() {
        return verified_time;
    }

    public Date getGenrated_time() {
        return genrated_time;
    }

    public String getEmail_token() {
        return email_token;
    }

    public boolean isEmail_verify_flag() {
        return email_verify_flag;
    }

    // Attributes add
    @Override
    public Map<String, List<String>> getAttributes() {
        MultivaluedHashMap<String, String> attributes = new MultivaluedHashMap<>();
        attributes.add(UserModel.USERNAME, getUsername());
        attributes.add(UserModel.EMAIL, getEmail());
        attributes.add("user_master_id", String.valueOf(getUser_id()));
        attributes.add("user_id", String.valueOf(getUser_id()));
        attributes.add("role_id", String.valueOf(getRole_id()));
        attributes.add("name", getName());
        attributes.add("address_line1", getAddress_line1());
        attributes.add("phone_mobile", getPhone_mobile());
        attributes.add("is_Approved", String.valueOf(is_Approved()));
        attributes.add("user_region_id", String.valueOf(getUserRegionId()));
        attributes.add("designation", getDesignation());
        attributes.add("user_password", getText_password());
        attributes.add("password_reset_flag", String.valueOf(is_Password_reset_flag()));
        attributes.add("is_Active", String.valueOf(is_Active()));
        attributes.add("created_by", String.valueOf(getCreated_by()));
        attributes.add("created_on", String.valueOf(getCreated_by()));
        attributes.add("updated_by", String.valueOf(getCreated_by()));
        attributes.add("updated_on", String.valueOf(getCreated_on()));
        attributes.add("password_modified_by", String.valueOf(getPassword_modified_by()));
        attributes.add("password_modified_on", String.valueOf(getPassword_modified_by()));
        attributes.add("verified_time", String.valueOf(getVerified_time()));
        attributes.add("genrated_time", String.valueOf(getGenrated_time()));
        attributes.add("email_token", getEmail());
        attributes.add("email_verify_flag", String.valueOf(isEmail_verify_flag()));
        attributes.add("text_password", getText_password());
        attributes.add("user_password_2", getUser_password());

        return attributes;
    }

    static class Builder {

        private final KeycloakSession session;
        private final RealmModel realm;
        private final ComponentModel storageProviderModel;
        private int user_master_id;
        private int user_id;
        private int role_id;
        private String name;
        private String address_line1;
        private String phone_mobile;
        private String email_id;
        private boolean is_Approved;
        private int user_region_id;
        private String designation;
        private String user_password;
        private String text_password;
        private boolean password_reset_flag;
        private boolean is_Active;
        private int created_by;
        private Date created_on;
        private int updated_by;
        private Date updated_on;
        private int password_modified_by;
        private Date password_modified_on;
        private String user_password_2;
        private Date verified_time;
        private Date genrated_time;
        private String email_token;
        private boolean email_verify_flag;

        Builder(KeycloakSession session, RealmModel realm, ComponentModel storageProviderModel, String name) {
            this.session = session;
            this.realm = realm;
            this.storageProviderModel = storageProviderModel;
            this.name = name;

        }

        CustomUser.Builder user_master_id(int user_master_id) {
            this.user_master_id = user_master_id;
            return this;
        }

        CustomUser.Builder user_id(int user_id) {
            this.user_id = user_id;
            return this;
        }

        CustomUser.Builder role_id(int role_id) {
            this.role_id = role_id;
            return this;
        }

        CustomUser.Builder name(String name) {
            this.name = name;
            return this;
        }

        CustomUser.Builder address_line1(String addressLine1) {
            this.address_line1 = addressLine1;
            return this;
        }

        CustomUser.Builder phone_mobile(String phone_mobile) {
            this.phone_mobile = phone_mobile;
            return this;
        }

        CustomUser.Builder email_id(String email_id) {
            this.email_id = email_id;
            return this;
        }

        CustomUser.Builder user_password(String user_password) {
            this.user_password = user_password;
            return this;
        }

        CustomUser.Builder is_Approved(boolean is_Approved) {
            this.is_Approved = is_Approved;
            return this;
        }

        CustomUser.Builder user_region_id(int user_region_id) {
            this.user_region_id = user_region_id;
            return this;
        }

        CustomUser.Builder designation(String designation) {
            this.designation = designation;
            return this;
        }

        CustomUser.Builder text_password(String text_password) {
            this.text_password = text_password;
            return this;
        }

        CustomUser.Builder password_reset_flag(boolean password_reset_flag) {
            this.password_reset_flag = password_reset_flag;
            return this;
        }

        CustomUser.Builder is_Active(boolean is_Active) {
            this.is_Active = is_Active;
            return this;
        }

        CustomUser.Builder created_by(int created_by) {
            this.created_by = created_by;
            return this;
        }

        CustomUser.Builder created_on(Date created_on) {
            this.created_on = created_on;
            return this;
        }

        CustomUser.Builder updated_by(int updated_by) {
            this.updated_by = updated_by;
            return this;
        }

        CustomUser.Builder updated_on(Date updated_on) {
            this.updated_on = updated_on;
            return this;
        }

        CustomUser.Builder password_modified_by(int password_modified_by) {
            this.password_modified_by = password_modified_by;
            return this;
        }

        CustomUser.Builder password_modified_on(Date password_modified_on) {
            this.password_modified_on = password_modified_on;
            return this;
        }

        CustomUser.Builder user_password_2(String user_password_2) {
            this.user_password_2 = user_password_2;
            return this;
        }

        CustomUser.Builder verified_time(Date verified_time) {
            this.verified_time = verified_time;
            return this;
        }

        CustomUser.Builder genrated_time(Date genrated_time) {
            this.genrated_time = genrated_time;
            return this;
        }

        CustomUser.Builder email_token(String email_token) {
            this.email_token = email_token;
            return this;
        }

        CustomUser.Builder email_verify_flag(boolean email_verify_flag) {
            this.email_verify_flag = email_verify_flag;
            return this;
        }

        CustomUser build() {
            return new CustomUser(
                    session,
                    realm,
                    storageProviderModel,
                    user_master_id,
                    user_id,
                    role_id,
                    name,
                    address_line1,
                    phone_mobile,
                    email_id,
                    user_password,
                    is_Approved,
                    user_region_id,
                    designation,
                    text_password,
                    password_reset_flag,
                    is_Active,
                    created_by,
                    created_on,
                    updated_by,
                    updated_on,
                    password_modified_by,
                    password_modified_on,
                    user_password_2,
                    verified_time,
                    genrated_time,
                    email_token,
                    email_verify_flag);
        }
        
    }

    @Override
    public SubjectCredentialManager credentialManager() {
        return new LegacyUserCredentialManager(session, realm, this);
    }

}
