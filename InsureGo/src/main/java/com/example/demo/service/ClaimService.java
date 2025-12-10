package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.bean.Claim;
import com.example.demo.repository.ClaimRepository;

public class ClaimService {
	@Autowired
	private ClaimRepository crepo;

	public Claim SubmitClaim(Claim c) {
		c.setClaimStatus("SUBMITTED");
		c.setSubmittedDate(LocalDateTime.now());
		c.setUpdatedDate(LocalDateTime.now());
		return crepo.save(c);
	}
	public List<Claim>getUserClaims(Long userId){
		return crepo.findByUserId(userId);
	}
	public Claim updateStatus(Long claimId,String status,String reason,Double approvedAmount) {
		Claim c=crepo.findById(claimId).orElseThrow();
		c.setClaimStatus(status);
		c.setRejectionReason(reason);
		c.setApprovedAmount(reason);
		c.setUpdatedDate(LocalDateTime.now());
		return crepo.save(c);
}}
