package com.medicalRecord.hyperLedgerServer.Service;

import org.hyperledger.fabric.gateway.Contract;
import org.hyperledger.fabric.gateway.Gateway;
import org.hyperledger.fabric.gateway.Network;
import org.hyperledger.fabric.gateway.Wallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import com.medicalRecord.hyperLedgerServer.Config.hlfConfig.HlfGatewayProperties;
import com.medicalRecord.hyperLedgerServer.Config.hlfConfig.HlfProperties;

@Service
public class GatewayService {

	@Autowired
	HlfProperties hlfProperties;
	@Autowired
	Wallet wallet;
	@Autowired 
	HlfGatewayProperties hlfGatewayProperties;

	public Gateway gateway(String userId) throws Exception {

		if (wallet.get(userId)==null) throw new  RuntimeException("User not found");
		Gateway.Builder builder = Gateway.createBuilder();
		builder.discovery(true).networkConfig(hlfProperties.getNetworkConfig().getInputStream()).identity(wallet,
				userId);
		return builder.connect();
	}

	@ConditionalOnProperty(prefix = "hyperledger-fabric.gateway", name = "channel-name")
	public Network network(String userId ) throws Exception {
		Gateway gateway = gateway( userId);
		return gateway.getNetwork(hlfGatewayProperties.getChannelName());
	}

	@ConditionalOnProperty(prefix = "hyperledger-fabric.gateway", name = { "chaincode-name" })
	public Contract contract(String userId ) throws Exception {
		Network network = network(userId );
		return network.getContract(hlfGatewayProperties.getChaincodeName());
	}

}
