package com.mytona.Portalcustomer.Model.Repository;

import com.mytona.Portalcustomer.Model.Entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerDAO {

    public static  final String HASH_KEY = "Customer";

    @Autowired
    private RedisTemplate redisTemplate;


    public Customer save(Customer customer){
        redisTemplate.opsForHash().put(HASH_KEY, customer.getCustomerId(),customer);
        return customer;
    }

    public List<Customer> findAll(){
        return redisTemplate.opsForHash().values(HASH_KEY);
    }

    public Customer findCustomer(int customerId){
        return (Customer) redisTemplate.opsForHash().get(HASH_KEY,customerId);
    }

    public boolean deleteCustomer(int customerId){
        redisTemplate.opsForHash().delete(HASH_KEY, customerId);
        return true;
    }
}