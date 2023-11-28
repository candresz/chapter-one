package com.chapterone.chapteroneapp.dto;

public class ChargeResponseDTO {
    private String id;
    private Long amount;
    private String status;

    public ChargeResponseDTO() {
    }

    public ChargeResponseDTO(String id, Long amount, String status) {
        this.id = id;
        this.amount = amount;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
