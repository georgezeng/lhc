package lhc.repository.jpa.dao;

import org.springframework.stereotype.Repository;

import lhc.domain.Zx14Yz;
import lhc.repository.jpa.BaseYzDao;

@Repository
public class Zx14YzDao extends BaseYzDao<Zx14Yz> {

	@Override
	protected String getTableName() {
		return "zx14_yz";
	}
}
