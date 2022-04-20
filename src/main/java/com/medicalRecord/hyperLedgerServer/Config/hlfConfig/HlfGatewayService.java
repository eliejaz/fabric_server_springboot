package com.medicalRecord.hyperLedgerServer.Config.hlfConfig;

import com.medicalRecord.hyperLedgerServer.Entity.FabricCAUser;
import com.medicalRecord.hyperLedgerServer.Service.FabricCAUserService;

final class HlfGatewayService {
    public static void registerFabricCAUserIfNotExists(FabricCAUserService fabricCAUserService,
                                                       FabricCAUser fabricCAUser) throws Exception {
        if (!fabricCAUserService.userExist(fabricCAUser.getName())) {
            fabricCAUserService.enrollAdmin(fabricCAUser.getMspId());
            fabricCAUserService.registerAndEnrollUser(fabricCAUser.getMspId(),
                    fabricCAUser.getName(), fabricCAUser.getAffiliation(),"doctor","pass1");
        }
    }
}
