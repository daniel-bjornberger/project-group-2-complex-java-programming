package se.iths.complexjavaproject.mudders.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.iths.complexjavaproject.mudders.entity.ItemAmount;
import se.iths.complexjavaproject.mudders.entity.ItemAmountId;

@Repository
public interface ItemAmountRepository extends CrudRepository<ItemAmount, ItemAmountId> {



}
