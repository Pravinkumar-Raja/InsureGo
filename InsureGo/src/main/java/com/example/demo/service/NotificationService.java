package com.example.demo.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.bean.Notification;
import com.example.demo.repository.NotifiactionRepository;
@Service
public class NotificationService {
 @Autowired
 private NotifiactionRepository nrepo;
	
 public Notification create(Long userId,String type,String message) {
	 Notification n=new Notification();
	 n.setUserId(userId);
	 n.setType(type);
	 n.setMessage(message);
	 return nrepo.save(n);
 }
 public List<Notification>getUserNotifications(Long userId){
	 return nrepo.findByUserIdOrderByCreatedAtDesc(userId);
 }
}
