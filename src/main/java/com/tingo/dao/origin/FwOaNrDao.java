package com.tingo.dao.origin;

import com.tingo.dto.origin.FwOaNr;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FwOaNrDao {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FW_OA_NR
     *
     * @mbggenerated
     */
    int insert(FwOaNr record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FW_OA_NR
     *
     * @mbggenerated
     */
    int insertSelective(FwOaNr record);

    List<String> selectIds();

    List<FwOaNr> selectByIds(@Param("ids") List<String> ids);

    byte[] selectNr(String id);
}