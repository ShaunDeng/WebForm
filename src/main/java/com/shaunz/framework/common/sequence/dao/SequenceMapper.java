package com.shaunz.framework.common.sequence.dao;

import org.springframework.stereotype.Repository;

@Repository
public interface SequenceMapper {
	int getNextSequenceNo(String sequenceNm);
}
