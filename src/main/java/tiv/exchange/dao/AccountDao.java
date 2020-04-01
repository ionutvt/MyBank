package tiv.exchange.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tiv.exchange.models.Account;

@Repository
public interface AccountDao extends JpaRepository<Account, Long> {

}
