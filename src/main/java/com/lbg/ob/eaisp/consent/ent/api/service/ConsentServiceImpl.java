package com.lbg.ob.eaisp.consent.ent.api.service;

import com.lbg.ob.eaisp.consent.ent.api.entity.ConsentRequestMaster;
import com.lbg.ob.eaisp.consent.ent.api.entity.ConsentStatusMaster;
import com.lbg.ob.eaisp.consent.ent.api.enums.Status;
import com.lbg.ob.eaisp.consent.ent.api.model.ConsentResource;
import com.lbg.ob.eaisp.consent.ent.api.repository.ConsentRequestMasterRepository;
import com.ob.eaisp.commons.constants.Brand;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class ConsentServiceImpl implements ConsentService {

    private final ConsentRequestMasterRepository consentRepository;

    private final ModelMapper mapper;

    public ConsentServiceImpl(ConsentRequestMasterRepository consentRepository, ModelMapper mapper) {
        this.consentRepository = consentRepository;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public ConsentResource createConsentResource(ConsentResource consent) throws Exception {
        ConsentRequestMaster consentRequestMaster = consentRequestMaster(consent);
        consentRequestMaster.setConsentStatus(consentStatusMaster(consent, consentRequestMaster));
        log.info("ConsentId {}: ", consentRequestMaster.getInternalConsentId());
        return mapper.map(consentRepository.save(consentRequestMaster), ConsentResource.class);
    }

    @Override
    public List<ConsentResource> getByInternalPartyId(String partyId, Brand brand, String journeyId, List<Status> consentStatus) {
        List<ConsentRequestMaster> consents = consentRepository.findAllByPartyIdAndBrand(partyId, brand);

        if (consentStatus != null && !consentStatus.isEmpty()) {
            return consents.stream()
                    .filter(
                            consent ->
                                    consentStatus.contains(consent.getConsentStatus().getStatus()))
                    .map(consent -> mapper.map(consent, ConsentResource.class))
                    .toList();
        } else {
            return consents.stream()
                    .map(consent -> mapper.map(consent, ConsentResource.class))
                    .toList();
        }

    }

    private ConsentRequestMaster consentRequestMaster(ConsentResource consent) {
        ConsentRequestMaster consentRequestMaster = mapper.map(consent, ConsentRequestMaster.class);
        String uuid = UUID.randomUUID().toString();
        consentRequestMaster.setInternalConsentId(uuid);
        consentRequestMaster.setConsentScope(consent.getConsentScope());
        return consentRequestMaster;
    }

    private ConsentStatusMaster consentStatusMaster(ConsentResource consent, ConsentRequestMaster consentRequestMaster) {
        ConsentStatusMaster consentStatusMaster = new ConsentStatusMaster();
        consentStatusMaster.setStatus(consent.getConsentStatus());
        consentStatusMaster.setConsentRequestMaster(consentRequestMaster);
        return consentStatusMaster;
    }
}
