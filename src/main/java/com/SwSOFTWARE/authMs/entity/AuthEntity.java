package com.SwSOFTWARE.authMs.entity;

import com.SwSOFTWARE.authMs.repository.AuthRepository;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "auth", indexes = {
        @Index(name = "indexUsername", columnList = "username"),
        @Index(name = "indexEmail", columnList = "email")

})
public class AuthEntity {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   private String username;
   private String password;
   private String email;
   private boolean active;
   private LocalDateTime createdAt;
   private LocalDateTime disabledAt;
   @ManyToMany
   @JoinTable(
           name = "roles_auth",
           joinColumns = @JoinColumn(name = "auth_id"),
           inverseJoinColumns = @JoinColumn(name = "role_id")
   )
   private List<RoleEntity> roles;



}
