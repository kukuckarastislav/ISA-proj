package com.isa.isa.DTO;

import com.isa.isa.model.enums.IsaEntityType;

public class AdminDeleteEntityDTO {
    private IsaEntityType isaEntityType;
    private int idEntity;

    public AdminDeleteEntityDTO(){}

    public AdminDeleteEntityDTO(IsaEntityType isaEntityType, int idEntity) {
        this.isaEntityType = isaEntityType;
        this.idEntity = idEntity;
    }

    public IsaEntityType getIsaEntityType() {
        return isaEntityType;
    }

    public void setIsaEntityType(IsaEntityType isaEntityType) {
        this.isaEntityType = isaEntityType;
    }

    public int getIdEntity() {
        return idEntity;
    }

    public void setIdEntity(int idEntity) {
        this.idEntity = idEntity;
    }
}
