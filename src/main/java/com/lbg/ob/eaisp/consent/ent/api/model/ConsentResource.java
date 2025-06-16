package com.lbg.ob.eaisp.consent.ent.api.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.lbg.ob.eaisp.consent.ent.api.enums.Scope;
import com.lbg.ob.eaisp.consent.ent.api.enums.Status;
import com.ob.eaisp.commons.constants.Brand;
import com.ob.eaisp.commons.constants.PatternConstants;
import com.ob.eaisp.commons.validators.PostGroup;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.ZonedDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ConsentResource {

    @JsonProperty("InternalConsentId")
    private String consentId;

    @JsonProperty("ConsentStartDate")
    @JsonFormat(pattern = PatternConstants.DATE_FORMAT_UTC,timezone = PatternConstants.UTC)
    @Schema(type="string", example="2024-09-06T14:28:152")
    @NotNull(groups = PostGroup.class)
    private ZonedDateTime startDate;

    @JsonProperty("ConsentEndDate")
    @JsonFormat(pattern = PatternConstants.DATE_FORMAT_UTC,timezone = PatternConstants.UTC)
    @Schema(type="string", example="2024-09-06T14:28:152")
    private ZonedDateTime endDate;

    @JsonProperty("JourneyId")
    private String journeyId;

    @JsonIgnore private Brand brand;

    @JsonIgnore private String partyId;

    @JsonProperty("ConsentStatus")
    @NotNull(groups = PostGroup.class)
    private Status consentStatus;

    @JsonProperty("ConsentScope")
    @NotNull(groups = PostGroup.class)
    private Scope consentScope;

    @JsonProperty("ConsentUpdatedDate")
    @JsonFormat(pattern = PatternConstants.DATE_FORMAT_UTC,timezone = PatternConstants.UTC)
    @Schema(type="string", example="2024-09-06T14:28:152")
    private ZonedDateTime updatedDate;
}
