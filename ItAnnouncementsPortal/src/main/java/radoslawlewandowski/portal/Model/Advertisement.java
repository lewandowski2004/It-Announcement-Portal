package radoslawlewandowski.portal.Model;

        import lombok.*;

        import javax.persistence.*;
        import java.util.Date;
        import java.util.List;
        import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "advertisement")
public class Advertisement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "advertisement_id")
    private long id;

    @Column(name = "title")
    private String title;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "salary")
    private String salary;

    @Column(name = "city")
    private String city;

    @Column(name = "description")
    private String description;

    @Column(name = "date_of_addition")
    @Temporal(TemporalType.DATE)
    private Date dateOfAddition;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToMany(cascade =  {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.PERSIST})
    @JoinTable(name = "advertisement_programming_language", joinColumns = @JoinColumn(name = "advertisement_id"), inverseJoinColumns = @JoinColumn(name = "programming_language_id"))
    private List<ProgrammingLanguage> programmingLanguages ;

    @ManyToMany(cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.PERSIST})
    @JoinTable(name = "advertisement_applied_user", joinColumns = @JoinColumn(name = "advertisement_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> users;

    @Enumerated(EnumType.STRING)
    private ExperienceLevel experienceLevel;

   /* @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "advertisement_skill", joinColumns = @JoinColumn(name = "advertisement_id"), inverseJoinColumns = @JoinColumn(name = "skill_id"))
    private Set<Skill> skills;
*/
}
