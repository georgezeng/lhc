package lhc.repository.jpa.dao;

import org.springframework.stereotype.Repository;

import lhc.domain.Zx12Yz;
import lhc.repository.jpa.BaseYzDao;

@Repository
public class Zx12YzDao extends BaseYzDao<Zx12Yz> {

	@Override
	protected String getTableName() {
		return "zx12_yz";
	}
}
