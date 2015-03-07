package com.yaps.petstore.barkbank.client.impl;

import com.barkbank.v1.CreditCardPort;
import com.yaps.petstore.barkbank.client.CreditCardFacade;
import com.yaps.petstore.common.exception.CheckException;
import com.yaps.petstore.model.CreditCardDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreditCardFacadeImpl implements CreditCardFacade {

    Logger logger = LoggerFactory.getLogger(CreditCardFacadeImpl.class.getName());


    private com.barkbank.v1.CreditCardPort creditCardService;

    public CreditCardPort getCreditCardService() {
        return creditCardService;
    }

    public void setCreditCardService(CreditCardPort creditCardService) {
        this.creditCardService = creditCardService;
    }


    public String verifyCreditCard(CreditCardDTO creditCardDTO) throws CheckException {

        logger.debug("start verifyCreditCard");


        logger.debug("end verifyCreditCard");

        return "";
    }

}
