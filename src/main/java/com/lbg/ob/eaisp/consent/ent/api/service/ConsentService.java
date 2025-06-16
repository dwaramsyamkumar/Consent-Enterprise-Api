package com.lbg.ob.eaisp.consent.ent.api.service;

import com.lbg.ob.eaisp.consent.ent.api.enums.Status;
import com.lbg.ob.eaisp.consent.ent.api.model.ConsentResource;
import com.ob.eaisp.commons.constants.Brand;

import java.util.List;

public interface ConsentService {
    List<ConsentResource> getByInternalPartyId(
            String partyId, Brand brand, String journeyId, List<Status> consentStatus);
    ConsentResource createConsentResource(ConsentResource consentResource) throws Exception;
}
