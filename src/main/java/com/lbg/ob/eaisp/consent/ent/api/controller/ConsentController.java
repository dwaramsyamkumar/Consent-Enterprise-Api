package com.lbg.ob.eaisp.consent.ent.api.controller;

import com.lbg.ob.eaisp.consent.ent.api.enums.Status;
import com.lbg.ob.eaisp.consent.ent.api.model.ConsentResource;
import com.lbg.ob.eaisp.consent.ent.api.service.ConsentService;
import com.ob.eaisp.commons.model.RequestContext;
import com.ob.eaisp.commons.validators.*;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.GroupSequence;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequestMapping("/v1/consents")
@Slf4j
@GroupSequence({
        NotBlankValidator.class,
        SizeValidator.class,
        PatternValidator.class,
        IsEnum.class,
        ConsentController.class
})
public class ConsentController {

    private final ConsentService consentService;
    public static final String CONSENT_GUID = "^[a-zA-Z0-9-]*$";

    @Autowired
    public ConsentController(ConsentService consentService){
        this.consentService = consentService;
    }

    @PostMapping("/createConsent")
    public ResponseEntity<ConsentResource> createConsent(
            @Parameter(hidden = true) RequestContext context,
            @Validated(PostGroup.class) @RequestBody ConsentResource consent)throws Exception{
        log.info("In Create Consent method of Consent Controller Class");
        consent.setJourneyId(context.getJourneyId());
        consent.setPartyId(context.getPartyId());
        consent.setBrand(context.getBrand());
        return new ResponseEntity<>(consentService.createConsentResource(consent), HttpStatus.CREATED);
    }


    @GetMapping()
    public ResponseEntity<List<ConsentResource>> getConsentsByPartyId(
            @Parameter(hidden = true) RequestContext context,
            @RequestParam(name = "status", required = false) final List<Status>  status)
    {
        return new ResponseEntity<>(consentService.getByInternalPartyId(
                context.getPartyId(), context.getBrand(), context.getJourneyId(), status
        ), HttpStatus.OK);
    }
/*

    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable("id") long id) {
        Account account = moneyHubService.getAccountById(id);
        if (account != null) {
            return new ResponseEntity<>(account, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/addAccount")
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        return new ResponseEntity<>(moneyHubService.createAccount(account), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Account> updateAccount(@PathVariable("id") long id, @RequestBody Account account) {
        Account updatedAccount = moneyHubService.updateAccount(id, account);
        if (updatedAccount != null) {
            return new ResponseEntity<>(updatedAccount, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteAccount(@PathVariable("id") long id) {
        moneyHubService.deleteAccount(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteAllAccounts() {
        moneyHubService.deleteAllAccounts();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
*/

/*    @GetMapping("/published")
    public ResponseEntity<List<Account>> findByPublished() {
        return new ResponseEntity<>(moneyHubService.findByPublished(true), HttpStatus.OK);
    }*/
}
