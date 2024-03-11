package com.baeldung.auth.provider.user;

import java.sql.Date;
//import java.util.Date;
import java.util.List;
import java.util.Map;
//import java.util.stream.Stream;

//import javax.swing.text.DefaultEditorKit.CutAction;

import org.keycloak.common.util.MultivaluedHashMap;
import org.keycloak.component.ComponentModel;
import org.keycloak.credential.LegacyUserCredentialManager;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RealmModel;
import org.keycloak.models.SubjectCredentialManager;
import org.keycloak.models.UserModel;
import org.keycloak.storage.adapter.AbstractUserAdapter;

class CustomUser extends AbstractUserAdapter {
    private final String username;
    private final String email;
    private final String firstName;
    private final String lastName;
    private final String mobileNumber;
    private final String uid;
    private final int userMasterId;
    private final int roleId;
    private final String addressLine;
    private final Boolean isApproved;
    private final int userRegionId;
    private final String designation;
   // private final String textPassword;
    private final boolean passwordResetflag;
    private final Boolean isActive;
    private final String createdBy;
    private final Date createdOn;
    private final String updatedBy;
    private final String passwordModifiedby;
    private final String password;
    //private final String emailToken;
   // private final int email_verify_flag;

    
   
    private CustomUser(KeycloakSession session, RealmModel realm,
            ComponentModel storageProviderModel,
            String username,
            String email,
            String firstName,
            String lastName,
            String mobileNumber,
            String uid,
            int userMasterId,
            int roleId,
            String addressLine,
            Boolean isApproved,
            int userRegionId,
            String designation,
          //  String textPassword,
            boolean passwordResetflag,
            Boolean isActive,
            String createdBy,
            Date createdOn,
            String updatedBy,
            String passwordModifiedby,
            String password
           // String emailToken,
           // int email_verify_flag
            
            
            )
            
            {
        super(session, realm, storageProviderModel);
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobileNumber = mobileNumber;
        this.uid = uid;
        this.userMasterId = userMasterId;
        this.roleId = roleId;
        this.addressLine = addressLine;
        this.isApproved = isApproved;
        this.userRegionId = userRegionId;
        this.designation = designation;
       // this.textPassword = textPassword;
        this.passwordResetflag = passwordResetflag;
        this.isActive = isActive;
        this.createdBy = createdBy;
        this.createdOn = createdOn;
        this.updatedBy = updatedBy;
        this.passwordModifiedby = passwordModifiedby;
        this.password = password;
       // this.emailToken = emailToken;
      //  this.email_verify_flag = email_verify_flag;


    }
    @Override
    public String getUsername() {
        return username;
    }

    
    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    public String getMobile_number() {
        return mobileNumber;
    }
    
    public String getUid(){
        return uid;

    }

    public int getUserMasterId(){
        return userMasterId;
    }

    public int getRoleId(){
        return roleId;
    }

    public String getAddressline(){
        return addressLine;
    }

    public boolean getIsApproved(){
        return isApproved;
    }
    public int getUserRegionId(){
        return userRegionId;
    }

    public String getdesignation(){
        return designation;
    }

    // public String getTextpassword(){
    //     return textPassword;
    // }
    public boolean getPasswordResetflag(){
        return passwordResetflag;

    }
    public boolean getIsactive(){
        return isActive;
    }
    public String getCreatedBy(){
        return createdBy;
    }

    public Date getCreatedon(){
        return createdOn;
    }

    public String getUpdatedby(){
        return updatedBy;
    }
    public String getPasswordmodifiedby(){
        return passwordModifiedby;
    }
    // public String gerEmailtoken(){
    //     return emailToken;
    // }
    // public int getEmailverifyflag(){
    //     return email_verify_flag;
    // }
    public String getPassword(){
        return password;
    }

    

    // Attributes add
    @Override
    public Map<String, List<String>> getAttributes() {
        MultivaluedHashMap<String, String> attributes = new MultivaluedHashMap<>();
        attributes.add(UserModel.USERNAME, getUsername());
        attributes.add(UserModel.EMAIL, getEmail());
        attributes.add(UserModel.FIRST_NAME,getFirstName());
        attributes.add(UserModel.LAST_NAME,getLastName());
        attributes.add("mobileNumber", getMobile_number());
        attributes.add("userId",getUid());
        attributes.add("userMasterId",String.valueOf(getUserMasterId()));
        attributes.add("roleId",String.valueOf(getRoleId()));
        attributes.add("addressLine", getAddressline());
        attributes.add("isApproved",String.valueOf(getIsApproved()));
        attributes.add("userRegionId",String.valueOf(getUserRegionId()));
        attributes.add("designation", getdesignation());
       // attributes.add("textPassword",getTextpassword());
        attributes.add("passwordResetflag",String.valueOf(getPasswordResetflag()));
        attributes.add("isActive",String.valueOf(getIsactive()));
        attributes.add("createdBy",getCreatedBy());
        attributes.add("createdOn",String.valueOf(getCreatedon()));
        attributes.add("updatedBy",getUpdatedby());
        attributes.add("passwordModifiedby",getPasswordmodifiedby());
        attributes.add("password", getPassword());
       // attributes.add("emailToken",gerEmailtoken());
      // attributes.add("email_verify_flag",String.valueOf(getEmailverifyflag()));


        
        return attributes;
    }

    static class Builder {

        private final KeycloakSession session;
        private final RealmModel realm;
        private final ComponentModel storageProviderModel;
        private  String username;
        private  String email;
        private  String firstName;
        private  String lastName;
        private  String mobileNumber;
        private String uid;
        private int userMasterId;
        private int roleId;
        private String addressLine;
        private boolean isApproved;
        private int userRegionId;
        private String designation;
       // private String textPassword;
        private boolean passwordResetflag;
        private boolean isActive;
        private String createdBy;
        private Date createdOn;
        private String updatedBy;
        private String passwordModifiedby;
        private String password;
        // private String emailToken;
        // private int email_verify_flag;
        

        Builder(KeycloakSession session, RealmModel realm, ComponentModel storageProviderModel, String username) {
            this.session = session;
            this.realm = realm;
            this.storageProviderModel = storageProviderModel;
            this.username = username;
            

        }
        CustomUser.Builder email(String email) {
            this.email = email;
            return this;
        }
        
        CustomUser.Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }
        
        CustomUser.Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        CustomUser.Builder mobileNumber(String mobileNumber){
            this.mobileNumber = mobileNumber;
            return this;
        }

        CustomUser.Builder uid(String uid){
            this.uid = uid;
            return this;
        }
       
        CustomUser.Builder userMasterId(int userMasterId){
            this.userMasterId = userMasterId;
            return this;
        }

        CustomUser.Builder roleId(int roleId){
            this.roleId = roleId;
            return this;
        }

        CustomUser.Builder addressLine(String addressline1){
            this.addressLine = addressline1;
            return this;
        }

        CustomUser.Builder isApproved(boolean isApproved){
            this.isApproved = isApproved;
            return this;
        }
        CustomUser.Builder userRegionId(int userRegionId){
            this.userRegionId = userRegionId;
            return this;
        }

        CustomUser.Builder designation(String designation){
            this.designation = designation;
            return this;
        }

        // CustomUser.Builder textPassword(String textPassword){
        //     this.textPassword = textPassword;
        //     return this;
        // }

        CustomUser.Builder passwordResetflag(boolean passwordResetflag){
            this.passwordResetflag = passwordResetflag;
            return this;
        }

        CustomUser.Builder isActive(boolean isActive){
            this.isActive = isActive;
            return this;
        }

        CustomUser.Builder createdBy(String createdBy){
            this.createdBy = createdBy;
            return this;
        }

        CustomUser.Builder createdOn(Date createdOn){
            this.createdOn = createdOn;
            return this;
        }

        CustomUser.Builder updatedBy(String updatedBy){
            this.updatedBy = updatedBy;
            return this;
        }
        CustomUser.Builder passwordModifiedby(String passwordModifiedby){
            this.passwordModifiedby = passwordModifiedby;
            return this;
        }
        // CustomUser.Builder emailToken(String emailToken){
        //     this.emailToken = emailToken;
        //     return this;
        // }
        // CustomUser.Builder email_verify_flag (int email_verify_flag){
        //     this.email_verify_flag = email_verify_flag;
        //     return this;
        // }
        CustomUser.Builder password(String password){
            this.password = password ;
            return this;
        }








        CustomUser build() {
            return new CustomUser(
                    session,
                    realm,
                    storageProviderModel,
                    username,
                    email,
                    firstName,
                    lastName,
                    mobileNumber,
                    uid,
                    userMasterId,
                    roleId,
                    addressLine,
                    isApproved,
                    userRegionId,
                    designation,
                   // textPassword,
                    passwordResetflag,
                    isActive,
                    createdBy,
                    createdOn,
                    updatedBy,
                    passwordModifiedby,
                    password
                    // emailToken,
                    // email_verify_flag
                   );
        }
        
    }

    @Override
    public SubjectCredentialManager credentialManager() {
        return new LegacyUserCredentialManager(session, realm, this);
    }
   

}
