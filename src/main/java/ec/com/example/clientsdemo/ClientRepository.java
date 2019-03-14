package ec.com.example.clientsdemo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface ClientRepository extends JpaRepository<Client, Long>, QuerydslPredicateExecutor<Client>{

}
