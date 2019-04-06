package radoslawlewandowski.portal.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "company")
public class Company {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "company_id", columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @Embedded
    private Address address;

    @Column(name = "password")
    private String password;

    @Transient
    private String confirmPassword;

    @Column(name = "active")
    private int active;

    @ManyToMany(cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.PERSIST})
    @JoinTable(name = "company_role", joinColumns = @JoinColumn(name = "company_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @Transient
    private int nrRoli;

    @OneToMany(mappedBy = "company", fetch=FetchType.EAGER, cascade = CascadeType.REMOVE)
    private Set<Advertisement> advertisements;

    @Transient
    private String newPassword;

    @Transient
    private String oldPassword;

    protected Company(){
    }

}
