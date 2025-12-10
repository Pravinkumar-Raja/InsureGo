package com.example.demo.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.bean.Claim;
import com.example.demo.bean.Document;
import com.example.demo.bean.Notification;
import com.example.demo.bean.Policy;
import com.example.demo.service.ClaimService;
import com.example.demo.service.DocumentService;
import com.example.demo.service.NotificationService;
import com.example.demo.service.PolicyService;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {
@Autowired
private DocumentService dserv;
private PolicyService pserv;
private ClaimService cserv;
private NotificationService nserv;
public ProfileController(DocumentService dserv,PolicyService pserv,ClaimService cserv,NotificationService nserv) {
	this.dserv=dserv;
	this.pserv=pserv;
	this.cserv=cserv;
	this.nserv=nserv;	
}
@PostMapping("/{userId}/policies/{PolicyId}/upload-card")
public ResponseEntity<?>uploadCard(@PathVariable Long userId,@PathVariable Long policyId1,@RequestParam("file")MultipartFile file) throws IOException{
	Document d=dserv.uploadInsuranceCard(file,userId,policyId1);
	return ResponseEntity.ok(d);
}
@GetMapping("/{userId}/policies")
public ResponseEntity<List<Policy>> getPolicies(@PathVariable Long userId){
	return ResponseEntity.ok(pserv.findByUser(userId));
}
@PostMapping("/policies/{policyId}/simulate-renewal")
public ResponseEntity<Policy>simulateRenewal(@PathVariable Long policyId, @RequestParam(defaultValue="12")int monthsToAdd){
	Policy renewed=pserv.simulateRenewal(policyId, monthsToAdd);
	return ResponseEntity.ok(renewed);
}
@PostMapping("/claims/submit")
public ResponseEntity<Claim>submitClaim(@RequestBody Claim c){
Claim saved=cserv.SubmitClaim(c);
nserv.create(c.getUserId(),"CLAIM_SUBMITTED", "Your claim has been submitted.");
return ResponseEntity.ok(saved);
}
@GetMapping("/{userId}/notifications")
public ResponseEntity<List<Notification>>getNotifications(@PathVariable Long userId){
	return ResponseEntity.ok(nserv.getUserNotifications(userId));
}
}
