package com.ls.security.android.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

public interface AndroidController {
    public ResponseEntity<byte[]> requestPdfFromIntraServer(@PathVariable Integer digitalApprovalId);
}
