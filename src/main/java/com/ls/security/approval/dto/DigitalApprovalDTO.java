package com.ls.security.approval.dto;

import com.ls.security.global.emp.dto.EmpDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DigitalApprovalDTO {
    private Integer digitalApprovalId;

    private Integer drafterId;

    private String digitalApprovalName;

    private String digitalApprovalPath;

    private boolean digitalApprovalType;

    private boolean drafterStatus;

    private boolean managerStatus;

    private boolean ceoStatus;

    private LocalDateTime digitalApprovalCreateAt;

    private LocalDateTime digitalApprovalAt;

    private LocalDateTime managerRejectAt;

    private LocalDateTime ceoRejectAt;

    private EmpDTO empDTO;
}
