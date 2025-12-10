package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.bean.Document;
import com.example.demo.repository.DocumentRepository;

import jakarta.transaction.Transactional;

@Service
public class DocumentService {
@Autowired
private DocumentRepository drepo;
private FileStorageService fileStorageService;
@Transactional
public Document uploadInsuranceCard(MultipleFilefile,Long userId,Long policyId) throws IOException{
 String fileUrl=fileStorageService.store(file,userId);
 Document d=new Document();
 d.setUserid(userId);
 d.setPolicyId(policyId);
 d.setDocumentType("INSURE_CARD");
 d.setFileName(file.getOriginalFilename());
 d.setFileName(file.getContentType());
 d.setFileFormat(file.getSize());
 d.setFileUrl(fileUrl);
 return drepo.save(s);
}

public Document uploadInsuranceCard(MultipartFile file, Long userId, Long policyId) {
	return fileStorageService.uploadInsuranceCard;
}}

