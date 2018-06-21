package com.example.notification.repositories;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import com.example.notification.pojo.NotificationTransaction;

@Repository
public interface NotificationTransactionRepository extends CassandraRepository<NotificationTransaction,String>{

}
