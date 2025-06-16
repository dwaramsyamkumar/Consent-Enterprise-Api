package com.lbg.ob.eaisp.consent.ent.api.repository;

import com.lbg.ob.eaisp.consent.ent.api.entity.ConsentRequestMaster;
import com.ob.eaisp.commons.constants.Brand;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsentRequestMasterRepository extends JpaRepository<ConsentRequestMaster, String> {

    @EntityGraph(value = "ConsentRequestMaster.withStatus", type = EntityGraph.EntityGraphType.LOAD)
    List<ConsentRequestMaster> findAllByPartyIdAndBrand(String partyId, Brand brand);
}