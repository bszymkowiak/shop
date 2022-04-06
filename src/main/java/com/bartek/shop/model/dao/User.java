package com.bartek.shop.model.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(indexes = @Index(name = "idx_email", columnList = "email", unique = true))
@Builder
@EntityListeners(AuditingEntityListener.class) // EntityListener włącza jakikolwiek auditing, który podamy w parametrze.
@Audited //adnotacja, która stworzy mi tabelke w bazie danych User_Aud, w którym jest zapisywana historaia zmian obiektów
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String login;
    @NotAudited
    private String password;
    private String email;
    @ManyToMany
    @JoinTable(name = "user_role", inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;
    @CreatedDate // Ustawia czas stworzenia obiektu (dla AuditingEntityListener)
    private LocalDateTime createdDate;
    @LastModifiedDate
    private LocalDateTime lastModifiedDate;
    @CreatedBy
    private String createdBy;
    @LastModifiedBy
    private String lastModifiedBy;
}
