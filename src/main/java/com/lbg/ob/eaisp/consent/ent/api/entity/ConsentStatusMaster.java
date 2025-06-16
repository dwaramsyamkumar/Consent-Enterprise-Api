package com.lbg.ob.eaisp.consent.ent.api.entity;

import com.lbg.ob.eaisp.consent.ent.api.enums.Status;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Table(name = "CONSENT_STATUS")
@Entity
public class ConsentStatusMaster {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "INTENT_ID")
    private Long intentId;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CONSENT_ID", referencedColumnName = "CONSENT_ID")
    private ConsentRequestMaster consentRequestMaster;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "CONSENT_STATUS")
    private Status status;
    
    @Column(name = "CREATED_AT")
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdDate;

    @Column(name = "UPDATED_AT")
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime updatedDate;
}
