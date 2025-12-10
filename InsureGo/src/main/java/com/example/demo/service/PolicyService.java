package com.example.demo.service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.bean.Notification;
import com.example.demo.bean.Policy;
import com.example.demo.repository.NotifiactionRepository;
import com.example.demo.repository.PolicyRepository;
import jakarta.transaction.Transactional;
@Service
public class PolicyService {
@Autowired
private PolicyRepository prepo;
 private NotifiactionRepository nrepo;
public Policy save(Policy p) {
	return prepo.save(p);
}
public List<Policy>findByUser(Long userId){
	return prepo.findByUserId(userId);
}
public Optional<Policy>findByUserId(Long id){
	return prepo.findById(id);
}
@Transactional
public Policy simulateRenewal(Long policyId, int addMonths) {
	Policy p=prepo.findById(policyId).orElseThrow();
	LocalDate newExpiryDate()=p.ExpiryDate().plusMonths(addMonths);
	p.setExpiryDate(newExpiry);
	p.setIssueDate(LocalDate.now());
	Policy saved=prepo.save(p);
	
	Notification n=new Notification();
	n.setUserId(p.getUserId());
	n.setType("RENEWAL_CONFIRM");
	n.setMessage("Your Policy "+p.getPolicyNumber()+"has been renewed untill"+newExpiry);
	return nrepo.save(n);
	//return saved;
}
public List<Policy>findByExpiringWithinDays(int days){
	LocalDate from=LocalDate.now();
	LocalDate to=from.plusDays(days);
	return prepo.findByExpiryDateBetween(from, to);

	}
}