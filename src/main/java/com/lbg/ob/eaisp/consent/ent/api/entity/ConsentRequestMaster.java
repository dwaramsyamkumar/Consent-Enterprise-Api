package com.lbg.ob.eaisp.consent.ent.api.entity;

import com.lbg.ob.eaisp.consent.ent.api.enums.Scope;
import com.ob.eaisp.commons.constants.Brand;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.ZonedDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@NamedEntityGraph(
        name = "ConsentRequestMaster.withStatus",
        attributeNodes = @NamedAttributeNode("consentStatus"))
@Entity
@Table(name = "CONSENT_REQUEST_MASTER")
public class ConsentRequestMaster {

    @Id
    @Column(name = "CONSENT_ID", updatable = false, nullable = false)
    private String internalConsentId;

    @Column(name = "PARTY_ID", updatable = false, nullable = false)
    private String partyId;

    @Column(name = "JOURNEY_ID", updatable = false, nullable = false)
    private String journeyId;

    @Enumerated(EnumType.STRING)
    @Column(name = "BRAND", updatable = false, nullable = false)
    private Brand brand;

    @Enumerated(EnumType.STRING)
    @Column(name = "CONSENT_SCOPE", updatable = false, nullable = false)
    private Scope consentScope;

    @Column(name = "CONSENT_START_DATE")
    private ZonedDateTime startDate;

    @Column(name = "CONSENT_END_DATE")
    private ZonedDateTime endDate;

    @Column(name = "CREATED_AT", updatable = false, nullable = false)
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private ZonedDateTime createdDate;

    @Column(name = "UPDATED_AT", updatable = false, nullable = false)
    //@UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private ZonedDateTime updatedDate;

    @OneToOne(mappedBy = "consentRequestMaster", cascade = CascadeType.ALL)
    private ConsentStatusMaster consentStatus;

}
