package com.tenda.mapper;

import com.tenda.hibernate.entity.ProvinciaEntity;
import com.tenda.vo.ProvinciaVO;

/**
 *
 * ProvinciasMapper
 */
public class ProvinciasMapper {
    
    /**
     * Mapper to VO
     * @param provinciaEntity
     * @return 
     */
    public static ProvinciaVO mapperEntityToVO (final ProvinciaEntity provinciaEntity) {
        
        return new ProvinciaVO(
                provinciaEntity.getId(),
                provinciaEntity.getNome());
        
    }
    
    /**
     * Mapper to Entity
     * @param provinciaVO
     * @return 
     */
    public static ProvinciaEntity mapperVOToEntity (final ProvinciaVO provinciaVO) {
        
        return new ProvinciaEntity(
                provinciaVO.getId(),
                provinciaVO.getNome());
        
    }
    
}
