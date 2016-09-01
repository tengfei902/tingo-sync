package com.tingo.dao.target;

import com.tingo.dto.target.HrmResource;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

public interface HrmResourceDao {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table HRMRESOURCE
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(BigDecimal id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table HRMRESOURCE
     *
     * @mbggenerated
     */
    int insert(HrmResource record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table HRMRESOURCE
     *
     * @mbggenerated
     */
    int insertSelective(HrmResource record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table HRMRESOURCE
     *
     * @mbggenerated
     */
    HrmResource selectByPrimaryKey(BigDecimal id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table HRMRESOURCE
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(HrmResource record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table HRMRESOURCE
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(HrmResource record);

    HrmResource selectByFid(@Param("fid") String fid);
}