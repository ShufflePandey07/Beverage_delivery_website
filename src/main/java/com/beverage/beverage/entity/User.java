package com.beverage.beverage.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
@Table(name="users")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class User implements UserDetails {
    @Id
    @SequenceGenerator(name="user_seq_gen", sequenceName="user_id_seq", allocationSize=1)
    @GeneratedValue(generator="user_seq_gen", strategy=GenerationType.SEQUENCE)
    private int id;

    @Column(name="first_name", length=100, nullable=false)
    private String firstName;

    @Column(name="last_name", length=100, nullable=false)
    private String lastName;

    @Column(name="user_email", length=100, nullable=false)
    private String email;

    @Column(name="user_password", length=100, nullable=false)
    private String password;

    @Column(name="user_role", length=100, nullable=false)
    private String role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.firstName+" "+this.lastName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
