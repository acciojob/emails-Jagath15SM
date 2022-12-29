package com.driver;

public class Email {

    private String emailId;
    private String password;

    public Email(String emailId){
        this.emailId = emailId;
        this.password = "Accio@123";
    }

    public String getEmailId() {
        return emailId;
    }

    public String getPassword() {
        return password;
    }

    public void changePassword(String oldPassword, String newPassword){
        //Change password only if the oldPassword is equal to current password and the new password meets all of the following:
        // 1. It contains at least 8 characters
        // 2. It contains at least one uppercase letter
        // 3. It contains at least one lowercase letter
        // 4. It contains at least one digit
        // 5. It contains at least one special character. Any character apart from alphabets and digits is a special character

        if(password.equals(oldPassword)){
            if(isValidPassword(newPassword)){
                password = newPassword;
            }
        }
    }

    public boolean isValidPassword(String newPassword){
        int length = newPassword.length();
        if(length < 8) return false;
        boolean upperCase = false, lowerCase = false, digit = false;
        boolean specialChar = false;

        for(int i=0;i<length;i++){
            char ch = newPassword.charAt(i);
            if(Character.isUpperCase(ch)){
                upperCase = true;
            }
            else if(Character.isLowerCase(ch)){
                lowerCase = true;
            }
            else if(Character.isDigit(ch)){
                digit = true;
            }
            else{
                specialChar = true;
            }
        }

        return (length >= 8 && upperCase && lowerCase && digit && specialChar);
    }
}
