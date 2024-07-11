package com.ls.security.android.service;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;

public interface AndroidService {
    ResponseEntity<byte[]> requestPdfFromIntraServer(Integer digitalApprovalId);
}
