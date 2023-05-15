package com.krish.communication.factory;

import com.krish.communication.datamodel.record.CommunicationOptionsRecord;
import com.krish.communication.service.Communication;
import com.krish.communication.service.impl.InstagramServiceImpl;
import com.krish.communication.service.impl.WhatsAppServiceImpl;

public class CommunicationFactory {
    public static void sendCommunicationMessage(Communication.ServiceName serviceName, CommunicationOptionsRecord communicationOptionsRecord){
        switch (serviceName){
            case WHATSAPP -> new WhatsAppServiceImpl().sendCommunicationMessage(communicationOptionsRecord);
            case INSTAGRAM -> new InstagramServiceImpl().sendCommunicationMessage(communicationOptionsRecord);
        };
    }
}
