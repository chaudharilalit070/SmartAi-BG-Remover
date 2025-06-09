package in.rahul.bgremove.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="tbl_users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
private long id;
    @Column(unique = true,nullable = false)
private String clerkId;
    @Column(unique = true,nullable = false)
private String email;

private String firstName;
private String lastName;
private  Integer credits;
private String photoUrl;
@PrePersist
public void perPersist(){
if (credits==null || credits==0){
    credits=5;
}
}
}
