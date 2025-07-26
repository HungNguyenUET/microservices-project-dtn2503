package vti.dtn.department_service.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "department")
public class DepartmentEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", length = 100, nullable = false, unique = true)
    private String name;

    @Column(name = "total_member")
    private int totalMember;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "type", columnDefinition = "ENUM('DEV, 'TEST','SCRUM_MASTER', 'PM')")
    @Enumerated(EnumType.STRING)
    private DepartmentType type;

    public enum DepartmentType {
        DEV, TEST, SCRUM_MASTER, PM;

        public static DepartmentType toEnum(String type) {
            for (DepartmentType item : values()) {
                if (item.toString().equals(type)) return item;
            }
            return null;
        }
    }
}
