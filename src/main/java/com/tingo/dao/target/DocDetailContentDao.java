package com.tingo.dao.target;

import com.tingo.dto.target.DocDetailContent;
import java.math.BigDecimal;

public interface DocDetailContentDao {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table DOCDETAILCONTENT
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(BigDecimal docid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table DOCDETAILCONTENT
     *
     * @mbggenerated
     */
    int insert(DocDetailContent record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table DOCDETAILCONTENT
     *
     * @mbggenerated
     */
    int insertSelective(DocDetailContent record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table DOCDETAILCONTENT
     *
     * @mbggenerated
     */
    DocDetailContent selectByPrimaryKey(BigDecimal docid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table DOCDETAILCONTENT
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(DocDetailContent record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table DOCDETAILCONTENT
     *
     * @mbggenerated
     */
    int updateByPrimaryKeyWithBLOBs(DocDetailContent record);
}