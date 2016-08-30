package com.tingo.dao.origin;

import com.tingo.dto.origin.OriginHrmDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by user on 16/8/30.
 */
@Repository
public class OriginHrmDao extends OriginDao<OriginHrmDTO> {

    public List<Long> queryIds() {
        return null;
    }

    public List<OriginHrmDTO> queryByIds(List<Long> ids) {
        return null;
    }
}
