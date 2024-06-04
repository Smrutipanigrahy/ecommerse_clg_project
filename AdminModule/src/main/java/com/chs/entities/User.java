package com.chs.entities;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Data
@Document(collection = "user_login")
public class User implements UserDetails{
    @Id
    private String userId;
    @NotEmpty(message = "Enter Your Name")
    private String userName;
    //    @Email
    @Indexed(unique = true)
    @NotEmpty(message = "Enter Your Email_Id")
    private String emailId;
    private String password;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return this.emailId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
//    @NotNull(message = "Enter Country name")
//    private Long mobileNo;
//    @NotEmpty(message = "Enter Country name")
//    private String country;
//    @NotEmpty(message = "Enter State name")
//    private String state;

}
