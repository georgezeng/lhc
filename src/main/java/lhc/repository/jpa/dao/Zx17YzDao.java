package lhc.repository.jpa.dao;

import org.springframework.stereotype.Repository;

import lhc.domain.Zx17Yz;
import lhc.repository.jpa.BaseYzDao;

@Repository
public class Zx17YzDao extends BaseYzDao<Zx17Yz> {

	@Override
	protected String getTableName() {
		return "zx17_yz";
	}
}
