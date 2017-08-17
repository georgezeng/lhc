package lhc.repository.jpa.dao;

import org.springframework.stereotype.Repository;

import lhc.domain.TwelveLrYz;
import lhc.repository.jpa.BaseYzDao;

@Repository
public class TwelveLrYzDao extends BaseYzDao<TwelveLrYz> {

	@Override
	protected String getTableName() {
		return "twelve_lr_yz";
	}

}
