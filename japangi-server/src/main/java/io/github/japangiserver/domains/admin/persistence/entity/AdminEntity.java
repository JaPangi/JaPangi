package io.github.japangiserver.domains.admin.persistence.entity;

import static javax.persistence.EnumType.STRING;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/** NOTE
 * 관리자 entity
 */
@Entity
@Table(name = "admin")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AdminEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admin_id")
    private Long adminId;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Enumerated(STRING)
    @Column(name = "authority")
    private Authority authority;

    @Builder
    public AdminEntity(String username, String password, Authority authority) {
        this.username = username;
        this.password = password;
        this.authority = authority;
    }

    public void updatePassword(String password){
        this.password = password;
    }
}
