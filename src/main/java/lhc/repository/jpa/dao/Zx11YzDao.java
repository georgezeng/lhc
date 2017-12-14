package lhc.repository.jpa.dao;

import org.springframework.stereotype.Repository;

import lhc.domain.Zx11Yz;
import lhc.repository.jpa.BaseYzDao;

@Repository
public class Zx11YzDao extends BaseYzDao<Zx11Yz> {

	@Override
	protected String getTableName() {
		return "zx11_yz";
	}
}
